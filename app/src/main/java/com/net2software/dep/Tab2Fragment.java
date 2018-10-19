package com.net2software.dep;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.net2software.dep.adapter.OrderAdapter;
import com.net2software.dep.adapter.OrderSuccessAdapter;
import com.net2software.dep.app.AppController;
import com.net2software.dep.model.Order;
import com.net2software.dep.model.OrderSuccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment {


    int position;
    private TextView textView;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private OrderSuccessAdapter orderAdapter;
    protected Context context;
    private ArrayList<OrderSuccess> OrderArraylist = new ArrayList<>();

    ProgressDialog pd;
    public static final String url = Server.URL_LISTORDER;
    private static final String TAG = Tab1Fragment.class.getSimpleName();
    String tag_json_obj = "json_obj_req";


    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        String MY_USER = "id_user";
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_USER, MODE_PRIVATE);
        String id_user = prefs.getString("user_id", null);
        if (id_user != null){
            id = prefs.getString("user_id", "No name defined");

        }

        loadJSON(id);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_order_tab2);
        linearLayoutManager = new LinearLayoutManager(context);
        orderAdapter = new OrderSuccessAdapter(getActivity(),OrderArraylist);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderAdapter);

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        OrderSuccess orderSuccess = OrderArraylist.get(position);
                        Intent intent = new Intent(getActivity().getBaseContext(), DetailOrderSelesai.class);
                        intent.putExtra("nama", ""+orderSuccess.getNama().toString());
                        intent.putExtra("nohp", ""+orderSuccess.getNo_hp().toString());
                        intent.putExtra("tempat", ""+orderSuccess.getTempat().toString());
                        intent.putExtra("lapangan", ""+orderSuccess.getLapangan().toString());
                        intent.putExtra("tanggal", ""+orderSuccess.getTglmain().toString());
                        intent.putExtra("jam", ""+orderSuccess.getJam().toString());
                        intent.putExtra("status", ""+orderSuccess.getStatus().toString());
                        getActivity().startActivity(intent);


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return rootView;


    }
    public void loadJSON(final String iduser){
        pd = new ProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.setMessage("Memuat...");
//        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean status = jObj.getBoolean("status");
                    String code = jObj.getString("code");
                    String message = jObj.getString("message");

                    if (status) {
                        JSONArray datas = jObj.getJSONArray("data");

                        if (datas !=null) {


                            for (int index = 0; index < datas.length(); index++) {
                                JSONObject jsonObject = datas.getJSONObject(index);

                                Boolean statusOrder = jsonObject.getBoolean("statusorder");
                                if (statusOrder) {
                                    String nama = jsonObject.getString("nama");
                                    String nohp = jsonObject.getString("no_hp");
                                    String tgl = jsonObject.getString("tanggal");
                                    String lpg = jsonObject.getString("lapangan");
                                    String tmp = jsonObject.getString("tempat");
                                    String tglmain = jsonObject.getString("tanggalmain");
                                    String jammain = jsonObject.getString("jammain");

                                    OrderSuccess order = new OrderSuccess();
                                    order.setNama(nama);
                                    order.setNo_hp(nohp);
                                    order.setLapangan(lpg);
                                    order.setTanggal(tgl);
                                    order.setTempat(tmp);
                                    order.setJam(jammain);
                                    order.setTglmain(tglmain);
                                    OrderArraylist.add(order);

                                }
                            }
                        }else{
                            Toast.makeText(getActivity(), "Data tidak tersedia", Toast.LENGTH_LONG).show();

                        }


                    } else {
//                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                }
                orderAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, " Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", iduser);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    private void showDialog() {
        if (!pd.isShowing())
            pd.show();
    }

    private void hideDialog() {
        if (pd.isShowing())
            pd.dismiss();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

}

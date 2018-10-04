package com.net2software.dep;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {



    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASSWORD = "password";

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        Button btn_logout = view.findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_EMAIL, null);
                editor.putString(TAG_PASSWORD, null);
                editor.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().finish();
                getActivity().startActivity(intent);
            }

        });


        return view;
    }

}

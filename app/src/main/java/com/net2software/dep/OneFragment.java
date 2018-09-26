package com.net2software.dep;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.net2software.dep._sliders.FragmentSlider;
import com.net2software.dep._sliders.SliderIndicator;
import com.net2software.dep._sliders.SliderPagerAdapter;
import com.net2software.dep._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    private View view;

    private ImageView image_kereta, imagepesawat;
    private ImageView imagetravel, imagefutsal;
    private ImageView imagehotel, imagerestoran;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);

        sliderView = (SliderView) view.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.pagesContainer);
        setupSlider();

        image_kereta =view.findViewById(R.id.iv_kereta);
        imagepesawat = view.findViewById(R.id.iv_pesawat);
        imagetravel = view.findViewById(R.id.iv_travel);
        imagefutsal= view.findViewById(R.id.iv_futsal);
        imagehotel = view.findViewById(R.id.iv_hotel);
        imagerestoran = view.findViewById(R.id.iv_restoran);

        image_kereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),KeretaActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imagepesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PesawatActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imagetravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TravelActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imagefutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),FutsalActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imagehotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HotelActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imagerestoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RestoranActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;


    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://techad.co.id/baidu/wp-content/uploads/2016/10/Inilah-Manfaat-Pasang-Iklan-di-Baidu-Untuk-Restoran-Indonesia2.jpg"));
        fragments.add(FragmentSlider.newInstance("https://rnsholidays.com.my/wp/wp-content/uploads/2018/08/68184730.jpg"));
        fragments.add(FragmentSlider.newInstance("https://lantaifutsaltermurah.com/wp-content/uploads/2017/11/harga-lapangan-futsal.jpg"));
        fragments.add(FragmentSlider.newInstance("https://s2.bukalapak.com/img/7474847951/w-1000/keretea_api_640x320_scaled.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

package com.net2software.dep;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import android.widget.Button;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.net2software.dep.adapter.TabAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentActivity myContext;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("My Order");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter( getFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Order");
        adapter.addFragment(new Tab2Fragment(), "Selesai");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        return view;
    }

}

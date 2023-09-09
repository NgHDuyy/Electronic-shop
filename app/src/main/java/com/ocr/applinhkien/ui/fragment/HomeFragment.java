package com.ocr.applinhkien.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocr.applinhkien.R;
import com.ocr.applinhkien.adapter.ProductAdapter;
import com.ocr.applinhkien.model.Item;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ProductAdapter adapter;
    private RecyclerView rcView;
    private View view;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view =inflater.inflate( R.layout.fragment_home, container, false );
        adapter = new ProductAdapter( getContext() );
        GridLayoutManager gridLayoutManager = new GridLayoutManager( getActivity(),2 );
        adapter.setData( getListItem());
        rcView.setAdapter( adapter );
        intUI();
        return view;
    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        return list;
    }

    private void intUI() {
        rcView = view.findViewById( R.id.rcvItem );
    }


}
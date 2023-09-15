package com.ocr.applinhkien.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocr.applinhkien.R;
import com.ocr.applinhkien.SearchActivity;
import com.ocr.applinhkien.adapter.ProductAdapter;
import com.ocr.applinhkien.model.Item;
import com.ocr.applinhkien.model.UserManager;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private ProductAdapter adapter;
    private RecyclerView rcView;
    private View view;
    private TextView tvName;
    private LinearLayout linearLayoutSerch;


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
        intUI();
        rcView.setLayoutManager( new GridLayoutManager( getActivity(),2 ) );
        rcView.setAdapter(adapter);
        adapter.setData( getListItem());
        tvName.setText( UserManager.getInstance().getCurrentUser().getUsername());
        onClickLister();
        return view;
    }



    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        list.add( new Item( 1,"aaa","aaa","aaa",100,200,10 ) );
        list.add( new Item( 1,"aaa","aaa","aaa",100,200 ,20) );
        list.add( new Item( 1,"aaa","aaa","aaa",100,200 ,0) );
        list.add( new Item( 1,"aaa","aaa","aaa",100,200 ,20) );

        return list;
    }

    private void intUI() {
        rcView = view.findViewById( R.id.rcvItem );
        tvName=view.findViewById( R.id.tv_name );
        linearLayoutSerch=view.findViewById( R.id.llSearch );
    }
    private void onClickLister() {
        linearLayoutSerch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SearchActivity.class );
                startActivity( intent );
            }
        } );
    }


}
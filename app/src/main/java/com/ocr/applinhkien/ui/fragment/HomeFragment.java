package com.ocr.applinhkien.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.adapter.ProductAdapter;
import com.ocr.applinhkien.databinding.FragmentHomeBinding;
import com.ocr.applinhkien.interfaceAPI.IntegerCallback;
import com.ocr.applinhkien.model.Item;
import com.ocr.applinhkien.model.User;
import com.ocr.applinhkien.ui.activity.CardActivity;
import com.ocr.applinhkien.ui.activity.DetailItemActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductAdapter adapter;

    private PreferenceHelper preferenceHelper;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        preferenceHelper = new PreferenceHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        adapter = new ProductAdapter(getContext(), itemClickCallback);
        intUI();
        onClick();
        User user = new User("11", "duy", "Nam", "03649118", "thai binh", "thai binh", "duy@gmail.com", "123456");
        preferenceHelper.setUser(new Gson().toJson(user));
        return binding.getRoot();
    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, "aaa1", "aaa", "aaa", 100, 200, 10, 0));
        list.add(new Item(2, "aaa2", "aaa", "aaa", 100, 200, 20, 0));
        list.add(new Item(3, "aaa3", "aaa", "aaa", 100, 200, 0, 0));
        list.add(new Item(4, "aaa4", "aaa", "aaa", 100, 200, 20, 0));

        return list;
    }

    private void intUI() {
        binding.rcvItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rcvItem.setAdapter(adapter);
        adapter.setData(getListItem());
        binding.tvName.setText(preferenceHelper.getUserInfo(1));
        setViewCard();
    }

    private void onClick(){
        binding.btnCard.setOnClickListener(v->{
            Intent intent = new Intent(requireActivity(), CardActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        setViewCard();
    }

    private void setViewCard() {
        binding.tvCountCard.setText(preferenceHelper.quanListItemCard());
    }

    private final IntegerCallback itemClickCallback = pos -> {
        Bundle bundle = new Bundle();
        Item item = getListItem().get(pos);
        String strItem = new Gson().toJson(item);
        bundle.putString("item", strItem);
        Intent intent = new Intent(requireActivity(), DetailItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    };


}
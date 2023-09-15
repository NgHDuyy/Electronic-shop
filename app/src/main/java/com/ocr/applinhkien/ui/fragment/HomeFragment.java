package com.ocr.applinhkien.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.adapter.ProductAdapter;
import com.ocr.applinhkien.databinding.FragmentHomeBinding;
import com.ocr.applinhkien.interfaceAPI.IntegerCallback;
import com.ocr.applinhkien.model.Item;
import com.ocr.applinhkien.model.User;
import com.ocr.applinhkien.ui.activity.CardActivity;
import com.ocr.applinhkien.ui.activity.DetailItemActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProductAdapter adapter;

    private PreferenceHelper preferenceHelper;

    private ArrayList<Item> _listItem = new ArrayList<>();

    private ArrayList<Item> _listItemShow = new ArrayList<>();

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
        getListItem();
        intUI();
        onClick();
        User user = new User("11", "duy", "Nam", "03649118", "thai binh", "thai binh", "duy@gmail.com", "123456");
        preferenceHelper.setUser(new Gson().toJson(user));
        return binding.getRoot();
    }

    private void getListItem() {
        String strListItem = preferenceHelper.getListItem();
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        try {
            _listItem = new Gson().fromJson(strListItem, type);
        } catch (JsonSyntaxException e){
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void intUI() {
        binding.rcvItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rcvItem.setAdapter(adapter);
        binding.tvName.setText(preferenceHelper.getUserInfo(1));
        setViewCard();
        setTypeChoose(0);
    }

    private void onClick() {
        binding.btnCard.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CardActivity.class);
            startActivity(intent);
        });
        binding.btn1.setOnClickListener(v->{
            setTypeChoose(0);
        });
        binding.btn2.setOnClickListener(v->{
            setTypeChoose(1);
        });
        binding.btn3.setOnClickListener(v->{
            setTypeChoose(2);
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
        Item item = _listItemShow.get(pos);
        String strItem = new Gson().toJson(item);
        bundle.putString("item", strItem);
        Intent intent = new Intent(requireActivity(), DetailItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    };

    private void setTypeChoose(int _type) {
        if (_type == 0) {
            binding.btn1.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_fill_8dp));
            binding.btn2.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));
            binding.btn3.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));

            binding.btn1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.btn2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.btn3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
        } else if (_type == 1) {
            binding.btn1.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));
            binding.btn2.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_fill_8dp));
            binding.btn3.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));

            binding.btn1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.btn2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            binding.btn3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
        } else {
            binding.btn1.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));
            binding.btn2.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_white_outline_8dp));
            binding.btn3.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_fill_8dp));

            binding.btn1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.btn2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
            binding.btn3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        }
        setRvAdapter(_type);
    }

    private void setRvAdapter(int _type) {
        for (int i= 0; i < _listItem.size(); i++){
            if (_listItem.get(i).getType() == _type){
                _listItemShow.add(_listItem.get(i));
            }
        }
        adapter.setData(_listItemShow);
    }


}
package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.adapter.CardAdapter;
import com.ocr.applinhkien.databinding.ActivityCardBinding;
import com.ocr.applinhkien.interfaceAPI.IntegerCallback;
import com.ocr.applinhkien.model.Item;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    private ActivityCardBinding binding;
    private ArrayList<Item> _mListItem = new ArrayList<>();
    private ArrayList<Item> _mListOrder = new ArrayList<>();
    private PreferenceHelper preferenceHelper;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceHelper = new PreferenceHelper(this);

        String strListItem = preferenceHelper.getListItemCard();
        try {
            Type type = new TypeToken<ArrayList<Item>>() {
            }.getType();
            _mListItem = new Gson().fromJson(strListItem, type);
        } catch (JsonSyntaxException e) {
            _mListItem = new ArrayList<>();
        }

        binding.rcvListItemOrder.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter(this, itemChooseListener, itemDeleteListener);
        binding.rcvListItemOrder.setAdapter(cardAdapter);

        cardAdapter.setData(_mListItem);

        onClick();

    }

    private void onClick() {
        binding.icBack.setOnClickListener(v -> finish());
        binding.btnOrder.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isCard", true);
            bundle.putString("list_item_order", new Gson().toJson(_mListOrder));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private final IntegerCallback itemChooseListener = pos -> {
        Item item = _mListItem.get(pos);
        boolean checkExit = false;
        for (int i = 0; i < _mListOrder.size(); i++) {
            if (item.getId() == _mListOrder.get(i).getId()) {
                checkExit = true;
                _mListOrder.remove(i);
                break;
            }
        }
        if (!checkExit) {
            _mListOrder.add(item);
            setTotalPrice();
        } else {
            setTotalPrice();
        }
    };

    private void setTotalPrice() {
        if (_mListOrder.isEmpty()) {
            binding.layoutTotalCount.setVisibility(View.GONE);
        } else {
            binding.layoutTotalCount.setVisibility(View.VISIBLE);
            binding.tvTotalChoose.setText(String.valueOf(_mListOrder.size()));
            int totalPrice = 0;
            for (int i = 0; i < _mListOrder.size(); i++) {
                totalPrice += _mListOrder.get(i).getPrice() * _mListOrder.get(i).getSoluong();
            }
            binding.finalTotalPrice.setText(String.valueOf(totalPrice));
        }
    }

    private final IntegerCallback itemDeleteListener = pos -> {
        Item item = _mListItem.get(pos);
        _mListItem.remove(pos);
        preferenceHelper.setListItemCard(new Gson().toJson(_mListItem));
        int index = -1;
        for (int i = 0; i < _mListOrder.size(); i++) {
            if (item.getId() == _mListOrder.get(i).getId()){
                index = i;
                break;
            }
        }
        if (index != -1){
            _mListOrder.remove(index);
        }
        cardAdapter.setData(_mListItem);
    };
}
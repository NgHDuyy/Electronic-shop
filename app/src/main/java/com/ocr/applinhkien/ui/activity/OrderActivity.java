package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ocr.applinhkien.NetworkHelper;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.adapter.OrderAdapter;
import com.ocr.applinhkien.databinding.ActivityOrderBinding;
import com.ocr.applinhkien.model.Item;
import com.ocr.applinhkien.model.Order;
import com.ocr.applinhkien.model.ResponeObject;
import com.ocr.applinhkien.model.User;
import com.ocr.applinhkien.retrofit.APIHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    private PreferenceHelper preferenceHelper;
    private ArrayList<Item> _listItem = new ArrayList<>();
    private User _user;
    private OrderAdapter orderAdapter;
    private int _totalPrice = 0;
    private int _shipPrice = 0;
    private int _finalTotalPrice = 0;
    private boolean _isCard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceHelper = new PreferenceHelper(this);

        showPlaceHolder(true, false, false);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                _isCard = bundle.getBoolean("isCard", false);
                String jsonItem = bundle.getString("list_item_order", "");
                try {
                    Type type = new TypeToken<ArrayList<Item>>() {
                    }.getType();
                    _listItem = new Gson().fromJson(jsonItem, type);
                    if (!_listItem.isEmpty()) {
                        initUI();
                    } else {
                        showPlaceHolder(false, false, true);
                    }
                } catch (JsonSyntaxException e) {
                    showPlaceHolder(false, false, true);
                }
            }
        }
    }

    private void initUI() {
        showPlaceHolder(false, true, false);
        if (_user != null) {
            binding.userName.setText(preferenceHelper.getUserInfo(1));
            binding.phoneNumber.setText(String.valueOf(preferenceHelper.getUserInfo(3)));
            binding.address.setText(preferenceHelper.getUserInfo(4));
            binding.city.setText(preferenceHelper.getUserInfo(5));
        }
        binding.rcvListItemOrder.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(this);
        binding.rcvListItemOrder.setAdapter(orderAdapter);
        orderAdapter.setData(_listItem);
        setPrice();
        onClick();

        binding.city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String city = binding.city.getText().toString().trim();
                if (city.isEmpty()) {
                    binding.tvShipPrice.setVisibility(View.GONE);
                } else {
                    binding.tvShipPrice.setVisibility(View.VISIBLE);
                    if (city.equals("Hà Nội")) {
                        _shipPrice = 10000;
                    } else {
                        _shipPrice = 30000;
                    }
                    binding.tvShipPrice.setText(_shipPrice + "đ");
                }
            }
        });
    }

    private void setPrice() {
        for (int i = 0; i < _listItem.size(); i++) {
            _totalPrice += (_listItem.get(i).getPrice() * _listItem.get(i).getSoluong());
        }
        binding.tvTotalPrice.setText(_totalPrice + "đ");
        String city = binding.city.getText().toString().trim();
        if (city.isEmpty()) {
            binding.tvShipPrice.setVisibility(View.GONE);
        } else {
            binding.tvShipPrice.setVisibility(View.VISIBLE);
            if (city.equals("Hà Nội")) {
                _shipPrice = 10000;
            } else {
                _shipPrice = 30000;
            }
            binding.tvShipPrice.setText(_shipPrice + "đ");
        }
        _finalTotalPrice = _totalPrice + _shipPrice;
        binding.tvFinalTotalPrice.setText(_finalTotalPrice + "đ");
        binding.finalTotalPrice.setText(_finalTotalPrice + "đ");
    }

    private void onClick() {
        binding.icBack.setOnClickListener(v -> finish());
        binding.btnOrder.setOnClickListener(v -> clickOrder());
    }

    private void clickOrder() {
        String phoneNum = binding.phoneNumber.getText().toString().trim();
        String address = binding.address.getText().toString().trim() + binding.city.getText().toString().trim();
        String note = binding.note.getText().toString().trim();
        String createAt = new Date().toString();
        Order order = new Order(_user.getUser_id(), _listItem, _finalTotalPrice, _user.getEmail(), phoneNum, address, createAt, note);
        if (_isCard) {
            preferenceHelper.setListItemCard("");
        }
        if (new NetworkHelper().isNetwork(this)) {
            showPlaceHolder(true, true, false);
            new APIHelper().order(order, str -> {
                if (str.isEmpty()) {
                    showPlaceHolder(false, true, false);
                    Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
                } else {
                    ResponeObject res = new Gson().fromJson(str, ResponeObject.class);
                    if (res.isSuccess()) {
                        showPlaceHolder(false, true, false);
                        Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                }
            });
        } else showPlaceHolder(false, false, true);
    }

    private void showPlaceHolder(boolean isLoading, boolean isContent, boolean isError) {
        if (isLoading) {
            binding.layoutError.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.GONE);
            binding.layoutContent.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.VISIBLE);
        }
        if (isContent) {
            binding.layoutError.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.VISIBLE);
            binding.layoutContent.setVisibility(View.VISIBLE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
        if (isError) {
            if (new NetworkHelper().isNetwork(this)) {
                binding.tvError.setText("Không có kết nối mạng");
            }
            binding.layoutError.setVisibility(View.VISIBLE);
            binding.layoutContent.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
    }
}
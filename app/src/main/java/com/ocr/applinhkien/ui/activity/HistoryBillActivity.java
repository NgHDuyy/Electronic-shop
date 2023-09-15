package com.ocr.applinhkien.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.ocr.applinhkien.NetworkHelper;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.adapter.HisBillAdapter;
import com.ocr.applinhkien.databinding.ActivityHistoryBillBinding;
import com.ocr.applinhkien.model.BillRespone;
import com.ocr.applinhkien.model.Bill;
import com.ocr.applinhkien.retrofit.APIHelper;

import java.util.ArrayList;

public class HistoryBillActivity extends AppCompatActivity {

    private ActivityHistoryBillBinding binding;
    private PreferenceHelper preferenceHelper;

    private ArrayList<Bill> _listBill = new ArrayList<>();
    private HisBillAdapter _hisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceHelper = new PreferenceHelper(this);

        getHistoryBill();
    }

    private void getHistoryBill() {
        showPlaceHolder(true, false, false);
        new APIHelper().getHistoryBill(preferenceHelper.getUserInfo(0), str -> {
            if (str.isEmpty()){
                showPlaceHolder(false, false, true);
            } else {
                BillRespone billRespone = new Gson().fromJson(str, BillRespone.class);
                _listBill = billRespone.getListBill();
                initUi();
            }
        });
    }

    private void initUi() {
        showPlaceHolder(false, true, false);
        binding.rcvListItem.setLayoutManager(new LinearLayoutManager(this));
        _hisAdapter = new HisBillAdapter(this);
        _hisAdapter.setData(_listBill);
        binding.rcvListItem.setAdapter(_hisAdapter);
    }


    private void showPlaceHolder(boolean isLoading, boolean isContent, boolean isError) {
        if (isLoading) {
            binding.layoutError.setVisibility(View.GONE);
            binding.rcvListItem.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.VISIBLE);
        }
        if (isContent) {
            binding.layoutError.setVisibility(View.GONE);
            binding.rcvListItem.setVisibility(View.VISIBLE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
        if (isError) {
            if (new NetworkHelper().isNetwork(this)) {
                binding.tvError.setText("Không có kết nối mạng");
            }
            binding.layoutError.setVisibility(View.VISIBLE);
            binding.rcvListItem.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
    }
}
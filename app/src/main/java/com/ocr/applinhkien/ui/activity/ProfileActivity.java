package com.ocr.applinhkien.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PreferenceHelper preferenceHelper = new PreferenceHelper(this);
        binding.ivBackAddress.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.edtChangeName.setText(preferenceHelper.getUserInfo(1));
        if (preferenceHelper.getUserInfo(2).equals("Nam")) {
            binding.radioNam.setChecked(true);
        } else {
            binding.radioNu.setChecked(true);
        }
        binding.edtChangePhone.setText(preferenceHelper.getUserInfo(3));
        binding.edtChangeAddress.setText(preferenceHelper.getUserInfo(4));
        binding.edtChangeCity.setText(preferenceHelper.getUserInfo(5));
        binding.tvEmail.setText(preferenceHelper.getUserInfo(6));
    }
}
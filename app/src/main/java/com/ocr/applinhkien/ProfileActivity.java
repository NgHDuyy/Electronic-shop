package com.ocr.applinhkien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ocr.applinhkien.databinding.ActivityProfileBinding;
import com.ocr.applinhkien.databinding.ActivitySearchBinding;
import com.ocr.applinhkien.model.UserManager;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.ivBackAddress.setOnClickListener( v->{
            onBackPressed();
        } );

        binding.tvEmail.setText( UserManager.getInstance().getCurrentUser().getEmail());
        binding.edtChangeName.setText( UserManager.getInstance().getCurrentUser().getUsername());
        binding.edtChangePhone.setText( UserManager.getInstance().getCurrentUser().getPhoneNumber());
        binding.edtChangeCity.setText( UserManager.getInstance().getCurrentUser().getCity());
        binding.edtChangeAddress.setText( UserManager.getInstance().getCurrentUser().getAddress());
        binding.tvEmail.setText( UserManager.getInstance().getCurrentUser().getEmail());
        if (UserManager.getInstance().getCurrentUser().getGender().equals("Nam")) {
            binding.radioNam.setChecked(true);
        } else {
           binding.radioNu.setChecked(true);
        }

    }
}
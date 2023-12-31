package com.ocr.applinhkien.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.adapter.ViewPagerAdapter;
import com.ocr.applinhkien.databinding.ActivityMainBinding;
import com.ocr.applinhkien.retrofit.APIHelper;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        preferenceHelper = new PreferenceHelper(this);
        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home) {
                    binding.viewpager.setCurrentItem(0);

                } else if (id == R.id.bottom_person) {
                    binding.viewpager.setCurrentItem(1);
                }
                return true;
            }
        });
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottom.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottom.getMenu().findItem(R.id.bottom_person).setChecked(true);
                        break;
                }
            }
        });

        getListProduct();
    }

    private void getListProduct() {
        new APIHelper().getListProduct(str -> {
            if (str.isEmpty()) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            } else {
                preferenceHelper.setListItem(str);
            }
        });
    }
}
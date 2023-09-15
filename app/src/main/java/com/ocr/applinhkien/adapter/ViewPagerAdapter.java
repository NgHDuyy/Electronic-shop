package com.ocr.applinhkien.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ocr.applinhkien.ui.fragment.HomeFragment;
import com.ocr.applinhkien.ui.fragment.PersonFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super( fragmentActivity );
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1: return new PersonFragment();
            default: return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

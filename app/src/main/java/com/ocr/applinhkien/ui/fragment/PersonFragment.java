package com.ocr.applinhkien.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ocr.applinhkien.ProfileActivity;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.SignInActivity;
import com.ocr.applinhkien.databinding.FragmentPersonBinding;
import com.ocr.applinhkien.model.UserManager;

public class PersonFragment extends Fragment {
    private FragmentPersonBinding binding;

    private Button btnProfile;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        binding.btnChinhSuaProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        } );
        binding.btnDangXuat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SignInActivity.class );
                startActivity(intent);
                UserManager.getInstance().dangXuat();
                getActivity().finish();

            }
        } );
        binding.tvShowUserName.setText( UserManager.getInstance().getCurrentUser().getUsername());
        binding.tvShowEmail.setText( UserManager.getInstance().getCurrentUser().getEmail() );

        return view;
    }
}
package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.model.User;
import com.ocr.applinhkien.retrofit.APIHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private EditText edtEmailSignIn, edtPasswordSignIn;
    private Button btnSignIn;
    private List<User> mListUser;
    private User mUser;
    private TextView txtSignUp;
    private PreferenceHelper preferenceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        preferenceHelper = new PreferenceHelper(this);
        iniUi();
        initControll();

        btnSignIn.setOnClickListener(view -> {
            getListUser();
            clickLogin();
        });

    }

    private void initControll() {
        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void iniUi() {
        edtEmailSignIn = findViewById(R.id.edt_email_in);
        edtPasswordSignIn = findViewById(R.id.edt_password_in);
        btnSignIn = findViewById(R.id.btn_sign_in);
        txtSignUp = findViewById(R.id.txt_sign_up);
    }

    private void clickLogin() {
        String strEmail = edtEmailSignIn.getText().toString().trim();
        String strPassword = edtPasswordSignIn.getText().toString().trim();

        if (mListUser == null || mListUser.isEmpty()) {
            return;
        }

        boolean isHasUser = false;
        for (User user : mListUser) {
            if (strEmail.equals(user.getEmail()) && strPassword.equals(user.getPassword())) {
                isHasUser = true;
                mUser = user;
                break;
            }
        }
        if (isHasUser) {
            //MainActivity
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Object_user", mUser);
            preferenceHelper.setIsLogin(true);
            preferenceHelper.setUser(new Gson().toJson(mUser));
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SignInActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private void getListUser() {
        new APIHelper().getListUser(str -> {
            if (str.isEmpty()) {
                mListUser = new ArrayList<>();
            } else {
                Type type = new TypeToken<List<User>>() {
                }.getType();
                mListUser = new Gson().fromJson(str, type);
            }
        });
    }
}
package com.ocr.applinhkien;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ocr.applinhkien.model.User;

public class PreferenceHelper {
    private final SharedPreferences sharedPreferences;

    public PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("preferencesHelper", Context.MODE_PRIVATE);
    }

    public String getListItemCard() {
        return sharedPreferences.getString(ItemCard, "");
    }

    public void setListItemCard(String jsonItemCard) {
        sharedPreferences.edit().putString(ItemCard, jsonItemCard).apply();
    }

    public int quanListItemCard() {
        return sharedPreferences.getInt(QuanItemCard, 0);
    }

    public void setQuanItemCard(int quanItemCard) {
        sharedPreferences.edit().putInt(QuanItemCard, quanItemCard).apply();
    }

    public Boolean isLogin() {
        return sharedPreferences.getBoolean(IsLogin, false);
    }

    public void setIsLogin(Boolean isLogin) {
        sharedPreferences.edit().putBoolean(IsLogin, isLogin).apply();
    }

    public String getUserInfo(int type) {
        User user;
        String strUser = sharedPreferences.getString(User, "");
        try {
            user = new Gson().fromJson(strUser, User.class);
        } catch (JsonSyntaxException e) {
            user = null;
        }
        if (user != null){
            switch (type){
                case 0: return user.getUser_id();
                case 1: return user.getUsername();
                case 2: return user.getGender();
                case 3: return user.getPhoneNumber();
                case 4: return user.getAddress();
                case 5: return user.getCity();
                case 6: return user.getEmail();
                default: return "";
            }
        } else return "";
    }

    public void setUser(String strUser) {
        sharedPreferences.edit().putString(User, strUser).apply();
    }


    private final String ItemCard = "itemCard";
    private final String IsLogin = "isLogin";
    private final String User = "user";
    private final String QuanItemCard = "quanItemCard";
}

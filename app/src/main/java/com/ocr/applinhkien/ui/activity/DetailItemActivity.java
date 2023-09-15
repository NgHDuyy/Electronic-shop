package com.ocr.applinhkien.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ocr.applinhkien.NetworkHelper;
import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.databinding.ActivityDetailItemBinding;
import com.ocr.applinhkien.model.Item;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DetailItemActivity extends AppCompatActivity {

    private ActivityDetailItemBinding binding;
    private Item _item;
    private int _count = 0;
    private int _price = 0;
    private int _totalPrice = 0;

    private ArrayList<Item> _listItemCard;

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceHelper = new PreferenceHelper(this);

        showPlaceHolder(true, false, false);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String jsonItem = bundle.getString("item", "");
                try {
                    _item = new Gson().fromJson(jsonItem, Item.class);
                    if (_item != null) {
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
        Glide.with(this).load(_item.getUrlImage()).into(binding.imgItem).onLoadFailed(ContextCompat.getDrawable(this, R.drawable.img_linhkien));
        binding.itemName.setText(_item.getName());
        binding.itemId.setText(String.valueOf(_item.getId()));
        binding.itemDes.setText(_item.getMota());
        binding.itemTotalQuan.setText(String.valueOf(_item.getSoluong()));
        if (_item.getSale() == 0) {
            binding.layoutDiscount.setVisibility(View.GONE);
            _price = _item.getPrice();
            binding.itemPrice.setText(_price + "đ");
        } else {
            binding.layoutDiscount.setVisibility(View.VISIBLE);
            _price = _item.getPrice() - _item.getPrice() * _item.getSale() / 100;
            binding.itemPrice.setText(_price + "đ");
            binding.itemPriceNoDis.setText(_item.getPrice() + "đ");
            binding.itemPriceNoDis.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvDiscount.setText(" (giảm giá " + _item.getSale() + "%)");
        }
        binding.tvCount.setText(String.valueOf(_count));
        getListItemCard();
        onClick();
    }

    private void getListItemCard() {
        String strItemCard = preferenceHelper.getListItemCard();
        try {
            Type type = new TypeToken<ArrayList<Item>>() {
            }.getType();
            if (!strItemCard.isEmpty()) {
                _listItemCard = new Gson().fromJson(strItemCard, type);
            } else {
                _listItemCard = new ArrayList<>();
            }
        } catch (JsonSyntaxException e) {
            _listItemCard = new ArrayList<>();
        }
        binding.tvCountCard.setText(preferenceHelper.quanListItemCard());
    }

    private void onClick() {
        binding.icBack.setOnClickListener(v -> {
            finish();
        });
        binding.icAdd.setOnClickListener(v -> {
            if (_count >= 0 && _count < _item.getSoluong()) {
                _count++;
                _totalPrice = _price * _count;
                binding.tvCount.setText(String.valueOf(_count));
                binding.tvTotalPrice.setText(_totalPrice + "đ");
                if (_count != 0) {
                    binding.layoutTotalPrice.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.icSub.setOnClickListener(v -> {
            if (_count > 0 && _count <= _item.getSoluong()) {
                _count--;
                _totalPrice = _price * _count;
                binding.tvCount.setText(String.valueOf(_count));
                if (_count == 0) {
                    binding.layoutTotalPrice.setVisibility(View.GONE);
                } else {
                    binding.tvTotalPrice.setText(_totalPrice + "đ");
                }
            }
        });
        binding.btnAddToCard.setOnClickListener(v -> {
            if (_count > 0) {
                Item itemCard = _item;
                itemCard.setSoluong(_count);
                for (int i = 0; i < _listItemCard.size(); i++) {
                    if (itemCard.getId() == _listItemCard.get(i).getId()) {
                        itemCard.setSoluong(itemCard.getSoluong() + _listItemCard.get(i).getSoluong());
                        _listItemCard.remove(i);
                        break;
                    }
                }
                _listItemCard.add(0, itemCard);
                preferenceHelper.setListItemCard(new Gson().toJson(_listItemCard));
                binding.tvCountCard.setText(String.valueOf(_listItemCard.size()));
                preferenceHelper.setQuanItemCard(_listItemCard.size());
            }
        });

        binding.btnBuyNow.setOnClickListener(v -> {
            if (_count > 0) {
                Item itemCard = new Item();
                itemCard.setId(_item.getId());
                itemCard.setUrlImage(_item.getUrlImage());
                itemCard.setMota(_item.getMota());
                itemCard.setName(_item.getName());
                itemCard.setPrice(_item.getPrice());
                itemCard.setSale(_item.getSale());
                itemCard.setSoluong(_count);
                ArrayList<Item> listItem = new ArrayList<>();
                listItem.add(itemCard);
                String strItemCard = new Gson().toJson(listItem);
                Intent intent = new Intent(this, OrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("list_item_order", strItemCard);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.btnCard.setOnClickListener(v -> {
            Intent intent = new Intent(this, CardActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void showPlaceHolder(boolean isLoading, boolean isContent, boolean isError) {
        if (isLoading) {
            binding.btnCard.setVisibility(View.GONE);
            binding.layoutError.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.GONE);
            binding.layoutContent.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.VISIBLE);
        }
        if (isContent) {
            binding.btnCard.setVisibility(View.VISIBLE);
            binding.layoutError.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.VISIBLE);
            binding.layoutContent.setVisibility(View.VISIBLE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
        if (isError) {
            if (new NetworkHelper().isNetwork(this)) {
                binding.tvError.setText("Không có kết nối mạng");
            }
            binding.btnCard.setVisibility(View.GONE);
            binding.layoutError.setVisibility(View.VISIBLE);
            binding.layoutContent.setVisibility(View.GONE);
            binding.layoutOrder.setVisibility(View.GONE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
    }
}
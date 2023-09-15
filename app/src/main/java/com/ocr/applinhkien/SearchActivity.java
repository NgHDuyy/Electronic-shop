package com.ocr.applinhkien;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ocr.applinhkien.adapter.SearchAdapter;
import com.ocr.applinhkien.databinding.ActivitySearchBinding;
import com.ocr.applinhkien.model.Item;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding binding;
    private SearchAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recySearch.setLayoutManager(linearLayoutManager);

        itemList = getListItem();
        adapter = new SearchAdapter(itemList);
        binding.recySearch.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.recySearch.addItemDecoration(itemDecoration);
        int itemCount = adapter.getItemCount();
        binding.tvSoLuong.setText( Integer.toString(itemCount) );

        setupListeners();
    }

    private void setupListeners() {
        binding.imgBack.setOnClickListener(v -> onBackPressed());

        binding.autoSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int itemCount = adapter.getItemCount();
                binding.tvSoLuong.setText( Integer.toString(itemCount) );
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
                adapter.filter(searchText);
                int itemCount = adapter.getItemCount();
               binding.tvSoLuong.setText( Integer.toString(itemCount) );
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Item item) {
//                // Xử lý khi một mục được chọn trong danh sách tìm kiếm
//                // Ví dụ: chuyển đến màn hình chi tiết sản phẩm
//                // onClickgotoChitiet(item);
//            }
//        });
    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, "a", "aaa", "aaa", 100, 200, 10));
        list.add(new Item(2, "b", "bbb", "bbb", 200, 300, 20));
        list.add(new Item(3, "c", "ccc", "ccc", 300, 400, 30));
        list.add(new Item(4, "aaa", "aaa", "aaa", 400, 500, 40));
        return list;
    }

    private void onClickgotoChitiet(Item product) {
        // Xử lý khi một mục trong danh sách được chọn, ví dụ chuyển đến màn hình chi tiết sản phẩm.
        // Intent intent = new Intent(SearchActivity.this, ChiTietProductActivity.class);
        // Bundle bundle = new Bundle();
        // bundle.putSerializable("object_product", product);
        // intent.putExtras(bundle);
        // startActivity(intent);
    }
}

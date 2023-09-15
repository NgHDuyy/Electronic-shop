package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.model.Item;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private ArrayList<Item> mListItem = new ArrayList<>();

    public OrderAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(ArrayList<Item> listItem) {
        this.mListItem = listItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        if (!mListItem.isEmpty()) {
            Item item = mListItem.get(position);
            holder.tvName.setText(item.getName());
            Glide.with(mContext).load(item.getUrlImage()).into(holder.imgItem).onLoadFailed(ContextCompat.getDrawable(mContext, R.drawable.img_linhkien));
            holder.tvPrice.setText(item.getPrice() + "đ");
            holder.tvCount.setText("x" + item.getSoluong());
        }
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPrice, tvCount;
        private ImageView imgItem;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_name);
            tvPrice = itemView.findViewById(R.id.item_price);
            tvCount = itemView.findViewById(R.id.item_count);
            imgItem = itemView.findViewById(R.id.img_item);
        }
    }
}

package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.interfaceAPI.IntegerCallback;
import com.ocr.applinhkien.model.Item;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context mContext;
    private ArrayList<Item> mListItem = new ArrayList<>();

    private IntegerCallback mItemChoose, mItemDelete;

    public CardAdapter(Context context, IntegerCallback itemChoose, IntegerCallback itemDelete) {
        this.mContext = context;
        this.mItemChoose = itemChoose;
        this.mItemDelete = itemDelete;
    }

    public void setData(ArrayList<Item> listItem) {
        this.mListItem = listItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (!mListItem.isEmpty()) {
            Item item = mListItem.get(position);
            holder.tvName.setText(item.getName());
            Glide.with(mContext).load(item.getUrlImage()).into(holder.imgItem).onLoadFailed(ContextCompat.getDrawable(mContext, R.drawable.img_linhkien));
            holder.tvPrice.setText(item.getPrice() + "đ");
            holder.tvCount.setText("x" + item.getSoluong());
            holder.cbChoose.setOnClickListener(v -> mItemChoose.execute(position));
            holder.icDelete.setOnClickListener(v -> mItemDelete.execute(position));
        }
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbChoose;
        private TextView tvName, tvPrice, tvCount;
        private ImageView imgItem, icDelete;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_name);
            tvPrice = itemView.findViewById(R.id.item_price);
            tvCount = itemView.findViewById(R.id.item_count);
            imgItem = itemView.findViewById(R.id.img_item);
            icDelete = itemView.findViewById(R.id.ic_delete);
            cbChoose = itemView.findViewById(R.id.cb_choose);
        }
    }
}

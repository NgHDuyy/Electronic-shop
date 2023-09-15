package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.interfaceAPI.ClickItemProduc;
import com.ocr.applinhkien.model.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    private Context mContext;
    private List<Item> mItemList;
    private List<Item> mItemListOld;
    private ClickItemProduc clickItemProduc;

    public SearchAdapter(List<Item> itemList) {
        this.mItemList = itemList;
        this.mItemListOld = new ArrayList<>(itemList);
    }

    public void setData(List<Item> itemList, ClickItemProduc listener) {
        this.clickItemProduc = listener;
        this.mItemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Item item = mItemList.get(position);
        if (item == null) {
            return;
        }
        Glide.with(mContext).load(item.getUrlImage()).into(holder.imgItem);
        holder.tvName.setText(item.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText(decimalFormat.format(item.getPrice()) + " VND");
        int giaSale = item.getPrice() - (item.getPrice() * item.getSale() / 100);
        if (item.getSale() == 0) {
            holder.tvSale.setVisibility(View.GONE);
            holder.tvphamtramSale.setVisibility(View.GONE);
            // Xóa gạch ngang khi không có giá sale
            holder.tvGia.setPaintFlags(holder.tvGia.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGia.setTextColor(Color.BLACK);
        } else {
            holder.tvSale.setVisibility(View.VISIBLE);
            holder.tvSale.setText(decimalFormat.format(giaSale) + " VND");
            holder.tvphamtramSale.setVisibility(View.VISIBLE);
            holder.tvphamtramSale.setText("-" + decimalFormat.format(item.getSale()) + "%");
            // Gạch ngang tvgiaProduct
            holder.tvGia.setPaintFlags(holder.tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGia.setTextColor(Color.GRAY);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItemProduc != null) {
                    clickItemProduc.onItemProductClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList != null ? mItemList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString().toLowerCase().trim();
                if (strSearch.isEmpty()) {
                    mItemList = new ArrayList<>(mItemListOld);
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item item : mItemListOld) {
                        if (item.getName().toLowerCase().contains(strSearch)) {
                            filteredList.add(item);
                        }
                    }
                    mItemList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mItemList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItemList = (List<Item>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvName, tvGia, tvSale, tvphamtramSale;
        private CardView cardView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.tv_name);
            tvGia = itemView.findViewById(R.id.tv_list_cost_product);
            tvSale = itemView.findViewById(R.id.tv_cost_sale);
            tvphamtramSale = itemView.findViewById(R.id.tv_sale);
            cardView = itemView.findViewById(R.id.carView);
        }
    }

    public void filter(String text) {
        if (text.isEmpty()) {
            mItemList = new ArrayList<>(mItemListOld);
        } else {
            List<Item> filteredList = new ArrayList<>();
            for (Item item : mItemListOld) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            mItemList = filteredList;
        }
        notifyDataSetChanged();
    }
}

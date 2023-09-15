package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.applinhkien.PreferenceHelper;
import com.ocr.applinhkien.R;
import com.ocr.applinhkien.model.Bill;

import java.util.ArrayList;

public class HisBillAdapter extends RecyclerView.Adapter<HisBillAdapter.HisBillViewHolder> {

    private PreferenceHelper preferenceHelper;

    private Context _mContext;
    private ArrayList<Bill> _mListBill = new ArrayList<>();
    public HisBillAdapter(Context context){
        this._mContext = context;
        this.preferenceHelper = new PreferenceHelper(_mContext);
    }
    public void setData(ArrayList<Bill> listBill){
        this._mListBill = listBill;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HisBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HisBillViewHolder(LayoutInflater.from(_mContext).inflate(R.layout.item_his_bill, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HisBillViewHolder holder, int position) {
        if (!_mListBill.isEmpty()){
            Bill bill = _mListBill.get(position);
            holder.orderId.setText(bill.getOrderId());
            holder.userName.setText(preferenceHelper.getUserInfo(1));
            holder.phoneNumber.setText(bill.getPhoneNumber());
            holder.address.setText(bill.getAddress());
            holder.phoneNumber.setText(bill.getPhoneNumber());
            holder.note.setText(bill.getNote());
            holder.createAt.setText(bill.getCreateAt());

            holder.rvListItem.setLayoutManager(new LinearLayoutManager(_mContext));
            OrderAdapter orderAdapter = new OrderAdapter(_mContext);
            orderAdapter.setData(bill.getListItem());
            holder.rvListItem.setAdapter(orderAdapter);

            holder.tvInfo.setOnClickListener(v->{
                if (holder.layoutInfo.getVisibility() == View.VISIBLE){
                    holder.layoutInfo.setVisibility(View.GONE);
                } else if (holder.layoutInfo.getVisibility() == View.GONE){
                    holder.layoutInfo.setVisibility(View.VISIBLE);
                }
            });
            holder.tvListItem.setOnClickListener(v->{
                if (holder.rvListItem.getVisibility() == View.VISIBLE){
                    holder.rvListItem.setVisibility(View.GONE);
                } else if (holder.rvListItem.getVisibility() == View.GONE){
                    holder.rvListItem.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _mListBill.size();
    }

    public class HisBillViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId, tvInfo, userName, phoneNumber, address, note, createAt, tvListItem;
        private RecyclerView rvListItem;
        private LinearLayout layoutInfo;
        public HisBillViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            tvInfo = itemView.findViewById(R.id.tv_info);
            userName = itemView.findViewById(R.id.user_name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            address = itemView.findViewById(R.id.address);
            note = itemView.findViewById(R.id.note);
            createAt = itemView.findViewById(R.id.create_at);
            tvListItem = itemView.findViewById(R.id.tv_list_item);
            rvListItem = itemView.findViewById(R.id.rcv_list_item);
            layoutInfo = itemView.findViewById(R.id.layout_info);
        }
    }
}

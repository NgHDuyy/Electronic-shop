package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.applinhkien.R;
import com.ocr.applinhkien.interfaceAPI.IntegerCallback;
import com.ocr.applinhkien.model.Item;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context mCOContext;
    private IntegerCallback itemClickCallback;
    private List<Item> mItemList;

    public ProductAdapter(Context mCOContext, IntegerCallback listener) {
        this.mCOContext = mCOContext;
        this.itemClickCallback = listener;
    }
    public void setData(List<Item> itemList){
        this.mItemList=itemList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.iitem_linh_kien,parent,false );
        return new ProductViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Item item =mItemList.get( position);
        if (item==null){
            return;
        }
        holder.tvName.setText(item.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGia.setText(decimalFormat.format(item.getPrice()) + " VND");
        int giaSale = item.getPrice() - (item.getPrice() * item.getSale() / 100);
        if (item.getSale() == 0) {
            holder.tvSale.setVisibility(View.GONE);
            holder.tvSale.setVisibility( View.GONE );
            // Xóa gạch ngang khi không có giá sale
            holder.tvGia.setPaintFlags(holder.tvGia.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGia.setTextColor( Color.BLACK);

        } else {
            holder.tvSale.setVisibility(View.VISIBLE);
            holder.tvSale.setText(decimalFormat.format(giaSale) + " VND");
            holder.tvphamtramSale.setVisibility( View.VISIBLE );
            holder.tvphamtramSale.setText("-"+ decimalFormat.format( item.getSale() )+"%" );
            // Gạch ngang tvgiaProduct
            holder.tvGia.setPaintFlags(holder.tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvGia.setTextColor(Color.GRAY);

        }

        holder.itemView.setOnClickListener(v -> {
            itemClickCallback.execute(position);
        });

    }

    @Override
    public int getItemCount() {
    if (mItemList!=null){
        return mItemList.size();
    }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvName,tvGia,tvSale,tvphamtramSale;
        public ProductViewHolder(@NonNull View itemView) {
            super( itemView );
            imgItem=itemView.findViewById( R.id.img_product );
            tvName=itemView.findViewById( R.id.tv_name );
            tvGia=itemView.findViewById( R.id.tv_list_cost_product );
            tvSale=itemView.findViewById( R.id.tv_cost_sale );
            tvphamtramSale=itemView.findViewById( R.id.tv_sale );
        }
    }
}

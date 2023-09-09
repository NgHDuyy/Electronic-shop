package com.ocr.applinhkien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.applinhkien.R;
import com.ocr.applinhkien.model.Item;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context mCOContext;
    private List<Item> mItemList;

    public ProductAdapter(Context mCOContext) {
        this.mCOContext = mCOContext;
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
        holder.tvName.setText( item.getName() );


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
        private TextView tvName;
        public ProductViewHolder(@NonNull View itemView) {
            super( itemView );
            imgItem=itemView.findViewById( R.id.img_product );
            tvName=itemView.findViewById( R.id.tv_name );
        }
    }
}

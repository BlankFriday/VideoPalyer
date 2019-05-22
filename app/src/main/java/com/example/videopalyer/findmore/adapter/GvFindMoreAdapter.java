package com.example.videopalyer.findmore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.videopalyer.R;
import com.example.videopalyer.bean.FindBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GvFindMoreAdapter extends RecyclerView.Adapter {


    private ArrayList<FindBean> newList;
    private Context context;

    public void setNewList(ArrayList<FindBean> newList) {
        this.newList = newList;
    }

    public GvFindMoreAdapter(ArrayList<FindBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_find, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        final FindBean bean = newList.get(position);
        Glide.with(context).load(bean.getBgPicture()).into(vh.imgFind);
        vh.tvFind.setText(bean.getName());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClick!=null){
                    itemOnClick.onClick(position,bean);
                }
            }
        });
    }

    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_find)
        ImageView imgFind;
        @BindView(R.id.tv_find)
        TextView tvFind;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ItemOnClick{
        void onClick(int position,FindBean bean);
    }

}

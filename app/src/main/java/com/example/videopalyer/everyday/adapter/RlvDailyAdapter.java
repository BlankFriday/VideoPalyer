package com.example.videopalyer.everyday.adapter;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.videopalyer.R;
import com.example.videopalyer.bean.DailyBean;
import com.example.videopalyer.widget.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyAdapter extends RecyclerView.Adapter {


    private ArrayList<DailyBean.IssueListBean.ItemListBean> newList;
    private ArrayList<String> titles;
    private Context context;

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public RlvDailyAdapter(ArrayList<DailyBean.IssueListBean.ItemListBean> newList, ArrayList<String> titles, Context context) {
        this.newList = newList;
        this.titles = titles;
        this.context = context;
    }

    public void setNewList(ArrayList<DailyBean.IssueListBean.ItemListBean> newList) {
        this.newList = newList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false);
            holder = new TextHolder(view);
        } else {
                View view = LayoutInflater.from(context).inflate(R.layout.item_daily, parent, false);
                holder = new ItemHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == 0){
            TextHolder th = (TextHolder) holder;
            th.tv1.setText(titles.get(0));
            th.tv2.setText(titles.get(1));
        }else {

            String imagePath = "";
            final ItemHolder ih = (ItemHolder) holder;
            final DailyBean.IssueListBean.ItemListBean.DataBean itemListBean = newList.get(position).getData();

            int duration = itemListBean.getDuration();
            int last = duration % 60;
            String stringLast;
            if (last <= 9) {
                stringLast = "0" + last;
            } else {
                stringLast = last + "";
            }

            //获取视频时间
            String durationString;
            int minit = duration / 60;
            if (minit < 10) {
                durationString = "0" + minit;

            } else {
                durationString = "" + minit;

            }
            final String stringTime = durationString + "' " + stringLast + '"';
            String category = itemListBean.getCategory();
            category = "#" + category + "  /  ";
            if (itemListBean.getTitle()!=null&&!itemListBean.getTitle().equals("")){
                ih.tvCategory.setText(category+stringTime);
                ih.tvTitle.setText(itemListBean.getTitle());
                imagePath = itemListBean.getCover().getFeed();
                setFilter(ih.imgDetail);
                Glide.with(context).load(imagePath).into(ih.imgDetail);
            }
            ih.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            final String finalCategory = category;
            ih.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemOnClick!=null&&itemListBean!=null){
                        itemOnClick.onClick(itemListBean, finalCategory +stringTime);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position%6==0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    class TextHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        CustomTextView tv1;
        @BindView(R.id.tv2)
        CustomTextView tv2;

        public TextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_detail)
        ImageView imgDetail;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public interface ItemOnClick{
        void onClick(DailyBean.IssueListBean.ItemListBean.DataBean bean,String str);
    }
    private void setFilter(ImageView imageView) {
        //先获取设置的src图片
        Drawable drawable=imageView.getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable==null) {
            drawable=imageView.getBackground();
        }
        if(drawable!=null){
            //设置滤镜
            drawable.setColorFilter(context.getApplicationContext().getResources().getColor(R.color.grey_daily),PorterDuff.Mode.DARKEN);;
        }
    }
}

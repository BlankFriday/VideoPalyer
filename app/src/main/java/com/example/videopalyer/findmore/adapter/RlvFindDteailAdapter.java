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
import com.example.videopalyer.bean.FindDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvFindDteailAdapter extends RecyclerView.Adapter {


    private ArrayList<FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean> newList;
    private Context context;

    public void setNewList(ArrayList<FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean> newList) {
        this.newList = newList;
    }

    public RlvFindDteailAdapter(ArrayList<FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_daily, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder vh = (ViewHolder) holder;
        final FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean bean = newList.get(position);

            FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean.DataBean data = bean.getData();

            int duration = data.getDuration();
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
            String category = data.getCategory();
            category = "#" + category + "  /  ";



            vh.tvCategory.setText(category+stringTime);
            vh.tvTitle.setText(data.getTitle());
            Glide.with(context).load(data.getCover().getFeed()).into(vh.imgDetail);
        final String finalCategory1 = category;
        vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemOnClick!=null){
                        itemOnClick.onClick(position,bean, finalCategory1 +stringTime);
                    }
                }
            });
        }


    @Override
    public int getItemCount() {
        return newList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_detail)
        ImageView imgDetail;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public interface ItemOnClick{
        void onClick(int position,FindDetailBean.ItemListBeanX.DataBeanX.ItemListBean bean,String str);
    }

}

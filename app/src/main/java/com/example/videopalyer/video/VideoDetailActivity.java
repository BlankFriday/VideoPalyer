package com.example.videopalyer.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.videopalyer.R;
import com.example.videopalyer.utils.NetConnectedUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VideoDetailActivity extends AppCompatActivity {

    @BindView(R.id.ibt_right)
    ImageButton ibtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cate)
    TextView tvCate;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.img_blurred)
    ImageView imgBlurred;
    @BindView(R.id.img_video)
    ImageView imgVideo;
    private Unbinder unbinder;
    private String video;
    private String title;
    private String datemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        datemail = getIntent().getStringExtra("datemail");
        String blurred = getIntent().getStringExtra("blurred");
        String category = getIntent().getStringExtra("category");
        video = getIntent().getStringExtra("video");
        //收藏量
        int collect = getIntent().getIntExtra("collect", 0);
        //分享量
        int share = getIntent().getIntExtra("share", 0);
        //回复量
        int reply = getIntent().getIntExtra("reply", 0);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText(title);
        tvCate.setText(category);
        tvDesc.setText(desc);

        tvLike.setText(collect+"");
        tvComment.setText(reply+"");
        tvShare.setText(share+"");

        Glide.with(this).load(datemail).into(img);
        Glide.with(this).load(blurred).into(imgBlurred);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }

    @OnClick(R.id.img_video)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_video:
                if (NetConnectedUtils.isNetConnected(this)){
                    Intent intent = new Intent(this, SimpleDetailActivity.class);
                    intent.putExtra("video",video);
                    intent.putExtra("title",title);
                    intent.putExtra("imgUrl",datemail);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "网络异常,请连接网络后重试", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

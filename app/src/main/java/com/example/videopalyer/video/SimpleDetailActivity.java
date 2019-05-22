package com.example.videopalyer.video;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.videopalyer.R;
import com.example.videopalyer.widget.PreViewGSYVideoPlayer;
import com.example.videopalyer.widget.ScrollWebView;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleDetailActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {


    @BindView(R.id.scroll_webView)
    ScrollWebView scrollWebView;
    @BindView(R.id.web_top_layout)
    NestedScrollView webTopLayout;
    @BindView(R.id.web_player)
    PreViewGSYVideoPlayer webPlayer;
    @BindView(R.id.web_top_layout_video)
    RelativeLayout webTopLayoutVideo;
    private String url;
    private String title;
    private String imgUrl;
    private boolean isSmall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_detail);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("video");
        title = getIntent().getStringExtra("title");
        imgUrl = getIntent().getStringExtra("imgUrl");

        //设置为Surface播放模式，注意此设置是全局的
        GSYVideoType.setRenderType(GSYVideoType.SUFRACE);

        resolveNormalVideoUI();
        initVideoBuilderMode();
        webPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                    webPlayer.getCurrentPlayer().setRotateViewAuto(!lock);
                }
            }
        });


        orientationUtils.setRotateWithSystem(false);

        webTopLayout.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (!webPlayer.isIfCurrentIsFullscreen() && scrollY >= 0 && isPlay) {
                    if (scrollY > webPlayer.getHeight()) {
                        //如果是小窗口就不需要处理
                        if (!isSmall) {
                            isSmall = true;
                            int size = CommonUtil.dip2px(SimpleDetailActivity.this, 150);
                            webPlayer.showSmallVideo(new Point(size, size), true, true);
                            orientationUtils.setEnable(false);
                        }
                    } else {
                        if (isSmall) {
                            isSmall = false;
                            orientationUtils.setEnable(true);
                            //必须
                            webTopLayoutVideo.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    webPlayer.hideSmallVideo();
                                }
                            }, 50);
                        }
                    }
                    webTopLayoutVideo.setTranslationY((scrollY <= webTopLayoutVideo.getHeight()) ? -scrollY : -webTopLayoutVideo.getHeight());
                }
            }
        });

    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return webPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        loadCover(imageView, imgUrl);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setVideoTitle(title)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setRotateWithSystem(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.nice_check);
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000000)
                                .centerCrop()
                                .error(R.mipmap.bad)
                                .placeholder(R.mipmap.bad))
                .load(url)
                .into(imageView);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    private void resolveNormalVideoUI() {
        //增加title
        webPlayer.getTitleTextView().setVisibility(View.GONE);
        webPlayer.getBackButton().setVisibility(View.GONE);
    }
}

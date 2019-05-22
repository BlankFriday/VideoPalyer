package com.example.videopalyer.spash;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videopalyer.R;
import com.example.videopalyer.main.activity.MainActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpashActivity extends AppCompatActivity {

    @BindView(R.id.iv_spash)
    ImageView ivSpash;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        ButterKnife.bind(this);
        initBackground();
        startAnimation();
    }

    private void startAnimation() {
        final View splashIv = findViewById(R.id.iv_spash);
        ValueAnimator animator = ValueAnimator.ofObject(new FloatEvaluator(), 1.0f, 1.2f);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                if (value != 1.2f) {
                    splashIv.setScaleX(value);
                    splashIv.setScaleY(value);
                } else {
                    goToActivity();
                }
            }

            private void goToActivity() {
                Intent intent = new Intent(SpashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, android.R.anim.fade_out);
                finish();
            }

        });
        animator.start();
    }

    private void initBackground() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK) - 1;
        Log.e("!!!!!!!!", day + "");
        switch (day) {
            case 0:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_6);
                break;
            case 1:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_12);
                break;
            case 2:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_7);
                break;
            case 3:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_10);
                break;
            case 4:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_8);
                break;
            case 5:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_11);
                break;
            case 6:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_9);
                break;
            default:
                ivSpash.setBackgroundResource(R.mipmap.wallpaper_9);
        }
    }
}

package com.example.videopalyer.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.videopalyer.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(createLayoutId(), null);
        setContentView(view);
        unbinder = ButterKnife.bind(this);
        createView(view);
        initView();
        initData();
    }

    public void createView(View view){};

    protected void initData(){};

    protected void initView(){};

    protected abstract int createLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder==null){
            unbinder.unbind();
        }
    }
}

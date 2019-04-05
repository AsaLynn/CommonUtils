package com.zcommon.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zcommon.lib.TVUtil;
import com.zcommon.lib.ZToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    TextView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TVUtil.drawableTop(ivImage, R.mipmap.ic_launcher);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ZToastUtils.showToast(this, "toast!!!");
    }
}

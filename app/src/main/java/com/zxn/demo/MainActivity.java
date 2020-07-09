package com.zxn.demo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zxn.utils.DrawableFactory;
import com.zxn.utils.NetUtils;
import com.zxn.utils.SoftKeyBoardManager;
import com.zxn.utils.TVUtil;
import com.zxn.utils.UIUtils;
import com.zxn.utils.ZToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    TextView ivImage;
    @BindView(R.id.tv_shape1)
    TextView tvShape1;
    @BindView(R.id.tv_shape2)
    TextView tvShape2;
    @BindView(R.id.tv_net)
    TextView tvNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TVUtil.drawableTop(ivImage, R.mipmap.ic_launcher);

        SoftKeyBoardManager.setKeyBoardListener(this, new SoftKeyBoardManager.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Toast.makeText(MainActivity.this, "keyBoardShow" + height, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void keyBoardHide(int height) {
                Toast.makeText(MainActivity.this, "keyBoardHide" + height, Toast.LENGTH_SHORT).show();
            }
        });
        UIUtils.init(this);
        tvShape1.setBackground(DrawableFactory.create(UIUtils.getColor(R.color.c_ff460d), 10f));
        tvShape2.setBackground(DrawableFactory.create(UIUtils.getColor(R.color.c_ffffff),
                UIUtils.getColor(R.color.c_ff460d),
                10f,
                2));

        int networkState = NetUtils.getNetworkState(this);
        if (networkState == NetUtils.NETWORK_WIFI) {
            tvNet.setText("Wifi");
        } else if (networkState == NetUtils.NETWORK_2G) {
            tvNet.setText("2G");
        } else if (networkState == NetUtils.NETWORK_3G) {
            tvNet.setText("3G");
        } else if (networkState == NetUtils.NETWORK_4G) {
            tvNet.setText("4G");
        } else if (networkState == NetUtils.NETWORK_5G) {
            tvNet.setText("5G");
        }
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ZToastUtils.showToast(this, "toast!!!");
    }
}

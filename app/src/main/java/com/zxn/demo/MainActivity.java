package com.zxn.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.zxn.utils.DrawableFactory;
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

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ZToastUtils.showToast(this, "toast!!!");
    }
}

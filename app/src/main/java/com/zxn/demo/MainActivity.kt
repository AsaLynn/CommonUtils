package com.zxn.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.zxn.utils.*
import com.zxn.utils.DrawableFactory.create
import com.zxn.utils.NetUtils.getNetworkState
import com.zxn.utils.SoftKeyBoardManager.OnSoftKeyBoardChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TVUtil.drawableTop(ivImage, R.mipmap.ic_launcher)
        SoftKeyBoardManager.setKeyBoardListener(this, object : OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                Toast.makeText(this@MainActivity, "keyBoardShow$height", Toast.LENGTH_SHORT).show()
            }

            override fun keyBoardHide(height: Int) {
                Toast.makeText(this@MainActivity, "keyBoardHide$height", Toast.LENGTH_SHORT).show()
            }
        })
        UIUtils.init(this)
        tvShape1!!.background = create(UIUtils.getColor(R.color.c_ff460d), 10f)
        tvShape2!!.background = create(UIUtils.getColor(R.color.c_ffffff),
                UIUtils.getColor(R.color.c_ff460d),
                10f,
                2)
        val networkState = getNetworkState(this)
        if (networkState == NetUtils.NETWORK_WIFI) {
            tvNet!!.text = "Wifi"
        } else if (networkState == NetUtils.NETWORK_2G) {
            tvNet!!.text = "2G"
        } else if (networkState == NetUtils.NETWORK_3G) {
            tvNet!!.text = "3G"
        } else if (networkState == NetUtils.NETWORK_4G) {
            tvNet!!.text = "4G"
        } else if (networkState == NetUtils.NETWORK_5G) {
            tvNet!!.text = "5G"
        }
    }

}
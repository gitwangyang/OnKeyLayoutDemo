package cn.dota.onkeylayoutdemo.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.dota.onkeylayoutdemo.view.activity.KeyBoardActivity;
import cn.dota.onkeylayoutdemo.R;
import cn.dota.onkeylayoutdemo.view.activity.SoftKeyboardActivity;

public class MainActivity extends AppCompatActivity implements View.OnLayoutChangeListener, View.OnClickListener{
    Button btn_name,btn_01,btn_02,btn_03;
    // 开始写输入法弹出的
    private LinearLayout lin_01;
    private EditText edit_01;
    private TextView tv_01;
    // 显示隐藏键盘用的
    InputMethodManager m;
    // 屏幕高度
    private int screenHeight = 0;
    // 软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_name = (Button) findViewById(R.id.btn_name);
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
        btn_03 = (Button) findViewById(R.id.btn_03);
        lin_01 = (LinearLayout) findViewById(R.id.lin_01);
        edit_01 = (EditText) findViewById(R.id.edit_01);
        tv_01 = (TextView) findViewById(R.id.tv_01);
        // 实例化显示隐藏键盘用的，当前显示则隐藏，当前隐藏则显示
        m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        tv_01.setOnClickListener(this);
        btn_name.setOnClickListener(this);
        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        // 获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        // 阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }

    // 监听软键盘弹出收起的
    @Override
    public void onLayoutChange(View v, int left, int top, int right,
                               int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        // old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
        // 现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            Log.e("onLayoutChange===","监听到软键盘弹起");
            Toast.makeText(MainActivity.this, "监听到软键盘弹起", Toast.LENGTH_SHORT).show();
            // 监听到软键盘弹起
        } else if (oldBottom != 0 && bottom != 0
                && (bottom - oldBottom > keyHeight)) {
            lin_01.setVisibility(View.GONE);
            // 失去焦点
            edit_01.setFocusable(false);
            edit_01.setFocusableInTouchMode(false);
            edit_01.setText("");
            Log.e("onLayoutChange===","监听到软件盘关闭");
            Toast.makeText(MainActivity.this, "监听到软件盘关闭", Toast.LENGTH_SHORT).show();
            // 监听到软件盘关闭
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_name:
                // 点击添加按钮的代码
                // 显示隐藏键盘用的，当前显示则隐藏，当前隐藏则显示
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                lin_01.setVisibility(View.VISIBLE);
                // 获取焦点
                edit_01.setFocusable(true);
                edit_01.setFocusableInTouchMode(true);
                // 显示光标
                edit_01.requestFocus();// 获取焦点 光标出现
                break;
            case R.id.tv_01:
                String edit_tian = edit_01.getText().toString().trim();
                if (TextUtils.isEmpty(edit_tian)) {
                    Toast.makeText(MainActivity.this, "请输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 显示隐藏键盘用的，当前显示则隐藏，当前隐藏则显示
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Toast.makeText(MainActivity.this, edit_tian, Toast.LENGTH_SHORT).show();
                break;
            case R.id. btn_01:
                startActivity(new Intent(MainActivity.this,KeyBoardActivity.class));
                break;
            case R.id. btn_02:
                startActivity(new Intent(MainActivity.this,SoftKeyboardActivity.class));
                break;
            case R.id. btn_03:

                break;


        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 添加layout大小发生改变监听器
        lin_01.addOnLayoutChangeListener(this);
    }
}

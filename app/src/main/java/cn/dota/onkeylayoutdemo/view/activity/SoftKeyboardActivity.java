package cn.dota.onkeylayoutdemo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.dota.onkeylayoutdemo.R;
import cn.dota.onkeylayoutdemo.widget.SoftKeyboardStateHelper;

public class SoftKeyboardActivity extends AppCompatActivity implements SoftKeyboardStateHelper.SoftKeyboardStateListener{

    private SoftKeyboardStateHelper softKeyboardStateHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_keyboard);
        initView();
    }

    private void initView() {
        softKeyboardStateHelper = new SoftKeyboardStateHelper(this);
        softKeyboardStateHelper.addSoftKeyboardStateListener(this);
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        Toast.makeText(SoftKeyboardActivity.this, "键盘打开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSoftKeyboardClosed() {
        Toast.makeText(SoftKeyboardActivity.this, "键盘关闭", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        softKeyboardStateHelper.removeSoftKeyboardStateListener(this);
    }
}

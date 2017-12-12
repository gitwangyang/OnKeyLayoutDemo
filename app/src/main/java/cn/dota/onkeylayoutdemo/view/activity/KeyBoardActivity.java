package cn.dota.onkeylayoutdemo.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.dota.onkeylayoutdemo.widget.KeyboardLayout;
import cn.dota.onkeylayoutdemo.R;

public class KeyBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
        initView();
    }

    private void initView() {
        KeyboardLayout mainView = (KeyboardLayout) findViewById(R.id.keyboardLay);
        final TextView tv = (TextView) findViewById(R.id.tv_content);
        mainView.setOnkbdStateListener(new KeyboardLayout.onKybdsChangeListener() {

            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardLayout.KEYBOARD_STATE_HIDE:
                        tv.setVisibility(View.VISIBLE);
                        Toast.makeText(KeyBoardActivity.this, "软键盘隐藏", Toast.LENGTH_SHORT).show();
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_SHOW:
                        tv.setVisibility(View.INVISIBLE);
                        Toast.makeText(KeyBoardActivity.this, "软键盘弹起", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}

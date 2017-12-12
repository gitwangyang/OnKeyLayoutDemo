package cn.dota.onkeylayoutdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by ${Dota.Wang} on 2017/12/6.
 */
public class KeyboardLayout extends RelativeLayout {
    private static final String TAG = KeyboardLayout.class.getSimpleName();
    public static final byte KEYBOARD_STATE_SHOW = -3;//软键盘弹起
    public static final byte KEYBOARD_STATE_HIDE = -2;//软键盘隐藏
    public static final byte KEYBOARD_STATE_INIT = -1;//初始
    private boolean mHasInit;
    private boolean mHasKeybord;
    private int mHeight;
    private onKybdsChangeListener mListener;

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardLayout(Context context) {
        super(context);
    }
    /**
     * 设置键盘状态监听器
     */
    public void setOnkbdStateListener(onKybdsChangeListener listener){
        mListener = listener;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!mHasInit) {
            mHasInit = true;
            mHeight = b;//获取底部高度
            if (mListener != null) {//初始状态
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
            }
        } else {
            mHeight = mHeight < b ? b : mHeight;
        }
        if (mHasInit && mHeight > b) {//大于则表示布局本遮挡或顶起
            mHasKeybord = true;
            if (mListener != null) {//弹出
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
            }
            Log.w(TAG, "show keyboard.......");
        }
        if (mHasInit && mHasKeybord && mHeight == b) {//布局曾被遮挡或顶起，且回到了初始高度
            mHasKeybord = false;
            if (mListener != null) {//收起
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
            }
            Log.w(TAG, "hide keyboard.......");
        }
    }

    public interface onKybdsChangeListener{
        public void onKeyBoardStateChange(int state);
    }
}
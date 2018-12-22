package com.sh.loadingdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class LoadingDialog extends Dialog {
    private String content;
    private TextView tvContent;
    private boolean isShowNavBar;

    public LoadingDialog(Context context, String content, boolean isShowNavBar) {
        super(context, R.style.CustomDialog);
        this.content = content;
        this.isShowNavBar = isShowNavBar;
        initView();
    }

    public LoadingDialog(Context context, int resId, boolean isShowNavBar) {
        super(context, R.style.CustomDialog);
        this.content = (String) context.getText(resId);
        this.isShowNavBar = isShowNavBar;
        initView();
    }

    private void initView() {
        setContentView(R.layout.dialog_loading);
        tvContent = findViewById(R.id.tvContent);
        tvContent.setText(content);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.9f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
    }

    public void show(String content) {
        if (tvContent != null) {
            tvContent.setText(content);
            tvContent.requestLayout();
        }
        show();
    }

    @Override
    public void show() {
        if (isShowNavBar) {
            super.show();
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            super.show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            }
        }
    }
}
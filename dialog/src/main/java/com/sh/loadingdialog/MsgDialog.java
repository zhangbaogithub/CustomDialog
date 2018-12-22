package com.sh.loadingdialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MsgDialog extends DialogFragment {
    private String msg;
    private TextView tvTitle;
    private TextView tvConfirm;
    private TextView tvCancel;
    private OnConfirmListener onConfirmListener;
    private OnCancelListener onCancelListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_msg, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(msg);
        tvConfirm = view.findViewById(R.id.tvConfirm);
        tvCancel = view.findViewById(R.id.tvCancel);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = getDialog();
                onConfirmListener.onConfirm(dialog);
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = getDialog();
                onCancelListener.onCancel();
                dialog.dismiss();
            }
        });
        return view;
    }

    public static class Builder {
        private OnConfirmListener onConfirmListener;
        private OnCancelListener onCancelListener;
        private String msg;

        public Builder setOnConfirmListener(OnConfirmListener onConfirmListener) {
            this.onConfirmListener = onConfirmListener;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public MsgDialog build() {
            MsgDialog msgDialog = new MsgDialog();
            msgDialog.msg = msg;
            msgDialog.onConfirmListener = onConfirmListener;
            msgDialog.onCancelListener = onCancelListener;
            return msgDialog;
        }
    }
}

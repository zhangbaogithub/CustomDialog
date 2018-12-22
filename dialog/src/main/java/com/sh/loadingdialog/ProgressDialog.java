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
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressDialog extends DialogFragment {

    private String title;
    private TextView tvTitle;
    private TextView tvCancel;
    private ProgressBar progressBar;
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
        View view = inflater.inflate(R.layout.dialog_progress, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(getString(R.string.progress_dialog_title, title == null ? "" : title, "0%"));
        tvCancel = view.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = getDialog();
                onCancelListener.onCancel();
                dialog.dismiss();
            }
        });
        progressBar = view.findViewById(R.id.progressBar);
        return view;
    }

    public void setProgress(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
            tvTitle.setText(getString(R.string.progress_dialog_title, title, progress + "%"));
        }
    }

    public static class Builder {
        private OnCancelListener onCancelListener;
        private String title;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public ProgressDialog build() {
            ProgressDialog progressDialog = new ProgressDialog();
            progressDialog.title = title;
            progressDialog.onCancelListener = onCancelListener;
            return progressDialog;
        }

    }
}

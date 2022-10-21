package com.pesonal.adsdk.vpn;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pesonal.adsdk.R;

public class DialogVpn {
    public static Dialog dialog;
    public static void progressDialog(Activity context) {
        try {
            if (!(context).isFinishing()) {
                if (dialog != null)
                    if (dialog.isShowing())
                        dialog.dismiss();
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.progress_dialog);
                dialog.setCancelable(false);
                ImageView ivProgress = dialog.findViewById(R.id.ivProgress);
                Glide.with(context).asGif().load(R.drawable.progress)
                        .into(ivProgress);
                dialog.show();
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        }
    }

    public static void isProgressDismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }



}

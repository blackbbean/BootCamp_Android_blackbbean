package com.example.android.boostcamp;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by Minjeong Kim on 2018-12-16.
 */

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_progress_dialog);
    }
}

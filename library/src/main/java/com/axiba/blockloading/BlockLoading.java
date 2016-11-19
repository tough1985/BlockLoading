package com.axiba.blockloading;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by axiba on 2016/11/17.
 */

public class BlockLoading extends View {

    public static final String TAG = BlockLoading.class.getSimpleName();


    public BlockLoading(Context context) {
        super(context);
    }

    public BlockLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlockLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

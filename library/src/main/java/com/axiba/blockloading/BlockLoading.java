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

    //画笔
    private Paint paint;

    //默认模块颜色
    private int defaultColor = Color.rgb(200, 200, 200);

    //绘制区域的宽高
    private int width;
    private int height;

    //方块的间距
    private float blockSpace;

    //方块的宽和高
    private float blockWidth;
    private float blockHeight;

    //方块的顶坐标
    private float blockTop;

    //方块的左坐标
    private float block1Left;
    private float block2Left;
    private float block3Left;



    public BlockLoading(Context context) {
        super(context);
    }

    public BlockLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlockLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化参数
    private void init(){

        //创建画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置画笔颜色
        paint.setColor(defaultColor);
        //设置透明度
        paint.setAlpha(100);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        blockSpace = 25;
        blockWidth = 100;
        blockHeight = 110;

        blockTop = (height - blockHeight) / 2;

        block2Left = (width - blockWidth) / 2;
        block1Left = block2Left - blockSpace - blockWidth;
        block3Left = block2Left + blockSpace + blockWidth;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {


        //绘制方块
        canvas.drawRoundRect(block1Left, blockTop, block1Left + blockWidth, blockTop + blockHeight, 5, 5, paint);
        canvas.drawRoundRect(block2Left, blockTop, block2Left + blockWidth, blockTop + blockHeight, 5, 5, paint);
        canvas.drawRoundRect(block3Left, blockTop, block3Left + blockWidth, blockTop + blockHeight, 5, 5, paint);
    }
}

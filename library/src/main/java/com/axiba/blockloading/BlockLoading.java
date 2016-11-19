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

    private static final int DEFAULT_MIN_WIDTH = 200;   //默认宽度
    private static final int DEFAULT_MIN_HEIGHT = 100;  //默认高度

    private static final int BLOCK_STATE_IN = 1;       //增加
    private static final int BLOCK_STATE_DE = 2;       //减小

    private static final int STEP_NUM = 15;    //总帧数，从0开始为第一帧
    private float blockStepHeight;  //每次变化的高度
    private int blockStep;  //当前帧

    //方块的间距
    private float blockSpace;

    //方块的宽和高
    private float blockWidth;
    private float blockHeight;

    private float blockMinHeight;   //最小的高度
    private float blockMaxHeight;   //最大的高度

    //方块的顶坐标
    private float blockTop;

    //方块的左坐标
    private float block1Left;
    private float block2Left;
    private float block3Left;

    //状态 增加或减小
    private int blockState;





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

        width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

//        blockSpace = 25;
//        blockWidth = 100;
//        blockHeight = 110;
        blockSpace = width * 0.05f;
        blockWidth = width * 0.2f;
        blockMinHeight = blockWidth * 1.1f;
        blockMaxHeight = blockWidth * 1.5f;

        blockHeight = blockMinHeight;

        blockTop = (height - blockHeight) / 2;

        block2Left = (width - blockWidth) / 2;
        block1Left = block2Left - blockSpace - blockWidth;
        block3Left = block2Left + blockSpace + blockWidth;

        //初始化方块起始状态为变大
        blockState = BLOCK_STATE_IN;

        //初始化每一帧height变化量
        blockStepHeight = (blockMaxHeight - blockMinHeight) / STEP_NUM;

        blockStep = 0;
        
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getMode(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_MIN_WIDTH, DEFAULT_MIN_HEIGHT);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_MIN_WIDTH, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DEFAULT_MIN_HEIGHT);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {

        if (blockState == BLOCK_STATE_IN) {
            //如果是变大状态，帧数+1
            blockStep++;
        } else {
            //如果是变小状态，帧数-1
            blockStep--;
        }

        //根据当前帧数获得高度
        blockHeight = blockMinHeight + blockStep * blockStepHeight;

        //获取顶点坐标
        blockTop = (height - blockHeight) / 2;

        //绘制方块
        canvas.drawRoundRect(block1Left, blockTop, block1Left + blockWidth, blockTop + blockHeight, 5, 5, paint);
        canvas.drawRoundRect(block2Left, blockTop, block2Left + blockWidth, blockTop + blockHeight, 5, 5, paint);
        canvas.drawRoundRect(block3Left, blockTop, block3Left + blockWidth, blockTop + blockHeight, 5, 5, paint);


        if (blockStep >= STEP_NUM) {
            //如果帧数已经是最后一帧，状态改为变小状态
            blockState = BLOCK_STATE_DE;
        } else if(blockStep <= 0){
            //如果帧数已经是第一帧，状态改为变大状态
            blockState = BLOCK_STATE_IN;
        }

        //触动刷新
        postInvalidate();

    }
}

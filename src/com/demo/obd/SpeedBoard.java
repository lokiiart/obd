package com.demo.obd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chensam on 4/17/15.
 */
public class SpeedBoard extends View {

    private int max=100;
    private int progress;

    public SpeedBoard(Context context) {
        super(context);
    }

    public SpeedBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeedBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.GREEN);                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        paint.setStrokeWidth((float) 5.0);              //线宽
        paint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(20,20, 680,680);
        canvas.drawArc(oval, 180, 180, false, paint);


        Paint tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(5);
        tmpPaint.setTextSize(25f);

        float  y=100;
        int count = 180; //总刻度数

        canvas.save();

        canvas.translate(350f, 350f);
        canvas.rotate(180f);
        for(int i=0 ; i <count/2+1 ; i++){
            if(i%5 == 0){
                canvas.drawLine(315f, 0f, 330f, 0f, tmpPaint);

            }else{
                canvas.drawLine(300f,0f,330f,0f,paint);
            }
            canvas.rotate(360/count); //旋转画纸
        }

        canvas.restore();
        canvas.translate(350f, 350f);
        for (float j=-180; j<=0; j=j+10){
            if(j%10==0) {
                canvas.drawText(String.valueOf((int) j + 180), (float) Math.cos( Math.toRadians((double) j)) * 250, (float) Math.sin(Math.toRadians((double) j)) * 250, tmpPaint);
            }
        }
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        progress-=180;
        canvas.drawLine(0, 0,   (float) Math.cos( Math.toRadians((double) progress)) * 250, (float) Math.sin(Math.toRadians((double) progress)) * 250,  paint);



    }


    public synchronized int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        if(max < 0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }

    }
}

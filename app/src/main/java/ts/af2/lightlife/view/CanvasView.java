package ts.af2.lightlife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import ts.af2.lightlife.R;

/**
 * Created by pengtian on 2017/5/28.
 */

public class CanvasView extends View {

    private Context mContext;

    public CanvasView(Context context) {
        super(context);
        mContext = context;
    }

    //1、创建一个画笔
    private Paint mPaint = new Paint();

    //2、初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    //3、在构造函数中初始化
    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private float[] points = new float[] {
            500, 500,
            500, 600,
            500, 700};

    private float[] lines = new float[] {
            100, 200, 200, 200,
            100, 300, 200, 300
    };
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        //draw the whole canvas
        canvas.drawColor(mContext.getResources().getColor(R.color.red_500));

        //draw one point on the specified point
        canvas.drawPoint(200, 200, mPaint);

        //draw points
        canvas.drawPoints(points, mPaint);

        canvas.drawLine(300, 300, 500, 600, mPaint);

        canvas.drawLines(lines, mPaint);

//        canvas.drawRect(100, 100, 800, 400, mPaint);
//
//        Rect rect = new Rect(100, 100, 800, 400);
//        canvas.drawRect(rect, mPaint);
//
//        RectF rectF = new RectF(100, 100, 800, 400);
//        canvas.drawRect(rectF, mPaint);

        RectF rectF1 = new RectF(100, 100, 800, 400);
        //canvas.drawRoundRect(rectF1, 30, 30, mPaint);

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(rectF1, 700, 400, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawOval(rectF1, mPaint);

        canvas.drawCircle(800, 800, 100, mPaint);

        RectF rectF = new RectF(200, 600, 800, 900);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 0, 90, false, mPaint);

        RectF rectF2 = new RectF(200, 1000, 800, 1300);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rectF2, mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF2, 0, 90, true, mPaint);

        RectF rectF3 = new RectF(100, 100, 600, 600);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawRect(rectF3, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF3, 0 , 90, false, mPaint);

        RectF rectF4 = new RectF(100, 700, 600, 1200);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF4, mPaint);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(rectF4, 0, 90, true, mPaint);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(40);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 200, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 500, 100, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, paint);
        

    }
}

package ts.af2.lightlife.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wisdong on 17-5-14.
 */

public class DiamondView extends View {
    private List<Integer> mUserProfiles;

    private Paint mPaint;
    private Context mContext;

    private static final int DIAMOND_DIMENSION = 4;
    private static final int MAX_LEVELS = 4;
    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final float DEFAULT_BORDER_WIDTH = (float) 2.0;
    private static final Paint.Style DEFAULT_PAINT_STYLE = Paint.Style.STROKE;
    private static final int DEFAULT_ANGLE_ROATAE = -90;

    private float mDiamondSize = (float) 0.0;

    public DiamondView(Context context) {
        super(context);
        mContext = context;
        initPaint();
    }

    public DiamondView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(DEFAULT_PAINT_STYLE);
        mPaint.setStrokeWidth(DEFAULT_BORDER_WIDTH);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else {
            width = widthSize * 2/3;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else {
            height = heightSize * 2/3;
        }
        int  diamondSize = width < height ? width : height;

        setMeasuredDimension(diamondSize, diamondSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDiamondSize = getWidth();
        drawBorderView(canvas);
        drawUserProfiles(canvas);
    }

    private void drawUserProfiles(Canvas canvas) {
        Path userPath = new Path();
        if (mUserProfiles == null || mUserProfiles.size() != DIAMOND_DIMENSION) {
            Log.d(TAG, "Error: no user profile or wrong profile");
            return;
        }
        float oldRadius = mDiamondSize/2;
        float radius = mUserProfiles.get(0) * oldRadius/DIAMOND_DIMENSION;
        userPath.moveTo(0, -radius);
        for (int i = 1; i <  DIAMOND_DIMENSION; i++){
            radius = mUserProfiles.get(i) * oldRadius/DIAMOND_DIMENSION;
            userPath.lineTo(radius * cos(360/DIAMOND_DIMENSION  * i + DEFAULT_ANGLE_ROATAE), radius * sin(360/DIAMOND_DIMENSION * i + DEFAULT_ANGLE_ROATAE));
        }
        mPaint.setStrokeWidth(DEFAULT_BORDER_WIDTH);
        userPath.close();
        canvas.save();
        canvas.translate(getWidth()/2, getHeight()/2);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(128);
        canvas.drawPath(userPath,mPaint);
        canvas.restore();
    }

    private void drawBorderView(Canvas canvas) {
        Path borderPath = new Path();
        float[] xArray = new float[DIAMOND_DIMENSION-1];
        float[] yArray = new float[DIAMOND_DIMENSION-1];

        float radius = mDiamondSize/2;

        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(200);


        canvas.save();
        // translate center as zero
        canvas.translate(getWidth()/2, getHeight()/2);

        // Draw radial line
        for (int i = 1; i< DIAMOND_DIMENSION; i++){
            xArray[i-1] = cos(360/ DIAMOND_DIMENSION * i + DEFAULT_ANGLE_ROATAE);
            yArray[i-1] = sin(360/ DIAMOND_DIMENSION *i + DEFAULT_ANGLE_ROATAE);
            canvas.drawLine(0, 0, radius*xArray[i-1], radius*yArray[i-1], mPaint);
        }
        canvas.drawLine(0, 0, 0, -radius, mPaint);

        // Draw circle level line
        for (int i=1; i<=MAX_LEVELS; i++) {
            Path gridPath = new Path();
            float newRadius = radius * i/MAX_LEVELS;
            gridPath.moveTo(0, -newRadius);
            for (int j = 1; j< DIAMOND_DIMENSION; j++) {
                gridPath.lineTo(newRadius*xArray[j-1], newRadius*yArray[j-1]);
            }
            gridPath.close();
            canvas.drawPath(gridPath, mPaint);
        }

        // Draw background
        borderPath.moveTo(0, -radius);
        for (int i = 1; i< DIAMOND_DIMENSION; i++){
            float x = radius * xArray[i-1];
            float y = radius * yArray[i-1];
            borderPath.lineTo(x, y);
        }
        borderPath.close();

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(64);
        canvas.drawPath(borderPath, mPaint);

        // Restore canvas
        canvas.restore();
    }

    float sin(int num){
        return (float) Math.sin(num * Math.PI/180);
    }
    float cos(int num){
        return (float) Math.cos(num * Math.PI/180);
    }

    public void setUserProfile(List<Integer> list) {
        if (list.size() != DIAMOND_DIMENSION) {
            throw new IllegalArgumentException("bad profile size !!!");
        }
        mUserProfiles = list;
        invalidate();
    }

}

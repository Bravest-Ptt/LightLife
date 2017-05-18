package ts.af2.lightlife.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import ts.af2.lightlife.R;

public class CircleImage extends View {

    public void setBitmap(Bitmap src) {
        this.src = src;
        drawCircle();
    }

    public void setBitmap(int resId) {
        this.src = BitmapFactory.decodeResource(this.context.getResources(),
                resId);
        drawCircle();
    }

    public void setBitmap(byte[] data) {
        this.src = BitmapFactory.decodeByteArray(data, 0, data.length);
        drawCircle();
    }

    Context context = null;

    Bitmap src = null;
    Bitmap target = null;
    Bitmap result = null;

    int d = 0;

    int square = 0;

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.circleimage) ;
        for (int attrId = 0; attrId < typedArray.getIndexCount(); attrId++) {
            switch (attrId) {
                case R.styleable.circleimage_d:
                    d = typedArray.getInteger(attrId, 0);
                    break;
                case R.styleable.circleimage_src:
                    BitmapDrawable drawable = (BitmapDrawable) typedArray
                            .getDrawable(attrId);
                    src = drawable.getBitmap();
                    break;
            }
        }

        drawCircle();
    }

    private void drawCircle() {
        int width = src.getWidth();
        int height = src.getHeight();
        if (width > height)
            square = height;
        else
            square = width;

        target = Bitmap.createBitmap(square, square, Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(target);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, square, square);
        targetCanvas.drawRoundRect(rect, square / 2, square / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        targetCanvas.drawBitmap(src, null, rect, paint);

        result = Bitmap.createBitmap(d, d, target.getConfig());
        Canvas resultCanvas = new Canvas(result);
        resultCanvas.drawBitmap(target, null, new Rect(0, 0, result.getWidth(),
                result.getHeight()), null);

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(d, d);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(result, 0, 0, null);
    }

}

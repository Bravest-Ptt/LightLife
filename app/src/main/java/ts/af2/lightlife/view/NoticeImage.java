package ts.af2.lightlife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NoticeImage extends ImageView {

	int notice;

	public NoticeImage(Context context) {
		super(context);
	}

	public NoticeImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setNotice(int notice) {
		this.notice = notice;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(notice!=0) {
			Paint paint = new Paint();
			paint.setColor(Color.RED);
			paint.setTextSkewX(-0.3f);
			int width = this.getWidth();
			int height = this.getHeight();
			int x;
			int y;
			String str = String.valueOf(notice);
			if (str.length() == 1) {
				x = (int) (width / 1.6);
				y = (int) (height / 2.2);
				paint.setTextSize((int) (width / 2 / 1.0));
			} else if (str.length() == 2) {
				x = (int) (width / 1.8);
				y = (int) (height / 2.8);
				paint.setTextSize((int) (width / 2 / 1.4));
			} else {
				x = (int) (width / 2);
				y = (int) (height / 3.2);
				paint.setTextSize((int) (width / 2 / 1.8));
			}
			canvas.drawText(str, x, y, paint);
		}
	}
}

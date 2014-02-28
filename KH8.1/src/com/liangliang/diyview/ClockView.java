package com.liangliang.diyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {

	private Time time;
	private int hour, minute, second;
	private int degree_h, degree_m, degree_s;

	public ClockView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// 获得系统时间
		time = new Time();
		time.setToNow();
		hour = time.hour;
		minute = time.minute;
		second = time.second;
		degree_h = hour * 30;
		degree_m = minute * 6;
		degree_s = second * 6;

		// 得到宽度和高度
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();

		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.clock);
		canvas.drawBitmap(bitmap, (width - bitmap.getWidth()) / 2,
				(height - bitmap.getHeight()) / 2, paint);

		// 绘制指针
		paint.setStrokeWidth(6);
		canvas.save();
		canvas.rotate(degree_m, width / 2 - 2, height / 2);
		canvas.drawLine(width / 2 - 2, height / 2, width / 2 - 2,
				height / 2 - 55, paint);
		canvas.restore();

		paint.setStrokeWidth(6);
		canvas.save();
		canvas.rotate(degree_h, width / 2 - 2, height / 2);
		canvas.drawLine(width / 2 - 2, height / 2, width / 2 - 2,
				height / 2 - 30, paint);
		canvas.restore();

		paint.setStrokeWidth(3);
		canvas.save();
		canvas.rotate(degree_s, width / 2 - 2, height / 2);
		canvas.drawLine(width / 2 - 2, height / 2, width / 2 - 2,
				height / 2 - 52, paint);
		canvas.restore();
	}

}

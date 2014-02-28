package com.liangliang.diyview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyView extends View {

	// ����
	private Paint mPaint;

	private int textColor;

	private String text = "�������ֻ�����";

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPaint = new Paint();

		// ��������
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.MyView);
		textColor = typedArray.getColor(R.styleable.MyView_textColor,
				0XFFFFFFFF);
		float textSize = typedArray.getDimension(R.styleable.MyView_textSize,
				36);
		int bgColor = typedArray.getColor(R.styleable.MyView_bgColor,
				0XFF000000);

		// ���ñ�����ɫ
		mPaint.setColor(bgColor);
		mPaint.setTextSize(textSize);

		// ����
		typedArray.recycle();

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// �������
		mPaint.setStyle(Style.FILL);

		// mPaint.setColor(Color.BLACK);

		// canvas.drawRect(, , , , mPaint);
		// ���ƺ�ɫ����
		canvas.drawColor(Color.BLACK);

		mPaint.setColor(textColor);

		canvas.drawText(this.text, 20, 70, mPaint);
	}

	public String getText() {
		return text;
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param text
	 *            Ҫ��ʾ������
	 */
	public void setText(String text) {

		System.out.println("----�ֻ�����--->>>" + text);

		// ������ʽ��֤�ֻ�����ĺϷ���
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(15[0-1,7-9])|(18[0,5-9]))[0-9]{8}$");

		Matcher matcher = pattern.matcher(text);

		if (!matcher.matches()) {
			Toast.makeText(getContext(), "��������ֻ���������", 1).show();
			return;
		}

		// �������ֵ���ʾ
		this.text = text;
		invalidate();

	}

}

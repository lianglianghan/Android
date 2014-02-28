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

	// 画笔
	private Paint mPaint;

	private int textColor;

	private String text = "请输入手机号码";

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPaint = new Paint();

		// 属性数组
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.MyView);
		textColor = typedArray.getColor(R.styleable.MyView_textColor,
				0XFFFFFFFF);
		float textSize = typedArray.getDimension(R.styleable.MyView_textSize,
				36);
		int bgColor = typedArray.getColor(R.styleable.MyView_bgColor,
				0XFF000000);

		// 设置背景颜色
		mPaint.setColor(bgColor);
		mPaint.setTextSize(textSize);

		// 回收
		typedArray.recycle();

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// 设置填充
		mPaint.setStyle(Style.FILL);

		// mPaint.setColor(Color.BLACK);

		// canvas.drawRect(, , , , mPaint);
		// 绘制黑色背景
		canvas.drawColor(Color.BLACK);

		mPaint.setColor(textColor);

		canvas.drawText(this.text, 20, 70, mPaint);
	}

	public String getText() {
		return text;
	}

	/**
	 * 更新文字信息
	 * 
	 * @param text
	 *            要显示的文字
	 */
	public void setText(String text) {

		System.out.println("----手机号码--->>>" + text);

		// 正则表达式验证手机号码的合法性
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(15[0-1,7-9])|(18[0,5-9]))[0-9]{8}$");

		Matcher matcher = pattern.matcher(text);

		if (!matcher.matches()) {
			Toast.makeText(getContext(), "您输入的手机号码有误", 1).show();
			return;
		}

		// 更新文字的显示
		this.text = text;
		invalidate();

	}

}

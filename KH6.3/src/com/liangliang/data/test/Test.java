package com.liangliang.data.test;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public void dataTest() {
		new Thread(new Runnable() {

			int data;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					data = (int) (Math.random() * 10000);

					System.out.println("--data-->>" + data);
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

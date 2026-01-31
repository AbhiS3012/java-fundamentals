package com.multithreading;

public class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		thread.start();
//		thread.start();
	}
	
}

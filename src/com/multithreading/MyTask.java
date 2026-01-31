package com.multithreading;

public class MyTask implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		Runnable task = new MyTask();
		Thread thread = new Thread(task);
		thread.run();
	}

}

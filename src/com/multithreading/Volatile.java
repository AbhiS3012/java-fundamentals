package com.multithreading;

/**
 * Threads may cache variables locally so changes made by one thread may not be
 * visible all other threads.
 * 
 * volatile ensures changes made by one thread are visible to all other threads
 * immediately.
 * 
 * Note: volatile guarantees visibility but not atomicity like count++.
 */
public class Volatile {

	static volatile boolean flag = true;

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			System.out.println("Thread-1 start");
			while (flag) {
				// infinite loop
			}
			System.out.println("Thread-1 stopped");
		});

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			flag = false;
			System.out.println("Flag changed");
		});

		t1.start();
		t2.start();
	}

}

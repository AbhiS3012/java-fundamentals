package com.multithreading;

/**
 * While one thread holds the monitor lock on the same object, no other thread
 * can enter a synchronized block synchronized on that object.
 * 
 * sleep pauses execution but keeps the lock.
 */
public class ThreadSleep {

	public static void main(String[] args) {
		ThreadSleep obj = new ThreadSleep();

		Thread t1 = new Thread(() -> {
			synchronized (obj) {
				try {
					System.out.println("Thread-1 sleep");
					Thread.sleep(3000);
					System.out.println("Thread-1 woke up");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		Thread t2 = new Thread(() -> {
			synchronized (obj) {
				System.out.println("Thread-2");
			}
		});

		t1.start();
		t2.start();
	}

}

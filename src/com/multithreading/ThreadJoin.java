package com.multithreading;

/**
 * join() causes the calling thread to wait until the specified thread finishes
 * execution. Effectively making the threads execute sequentially rather than
 * concurrently.
 */
public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			try {
				System.out.println("Thread-1 started");
				Thread.sleep(2000);
				System.out.println("Thread-1 finished");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});

		Thread t2 = new Thread(() -> {
			System.out.println("Thread-2 started");
		});

		t1.start();
		t1.join(); // main thread waits for Thread-1 to finish before starting Thread-2
		t2.start();
	}

}

package com.multithreading;

/**
 * Two threads increment a shared counter concurrently. Without synchronization,
 * count++ is not atomic, so the final result is unpredictable and often less
 * than expected.
 */
class Counter {
	private int count;

	public void increment() {
		count++;
	}

	public int getCount() {
		return count;
	}

}

public class RaceConditon {

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; ++i) {
				counter.increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; ++i) {
				counter.increment();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(counter.getCount());
	}

}

package com.multithreading;

/**
 * - Object lock (synchronized on 'this') ensures thread-safe updates to
 * instance variables when multiple threads share the same object.
 * 
 * - Class lock (synchronized on Class object) ensures thread-safe updates to
 * static variables shared across all instances.
 */
class Counter1 {
	private int instanceCount;
	private static int staticCount;

//	public synchronized void incrementInstance() { // Object lock (synchronized method)
//		instanceCount++;
//	}

	public void incrementInstance() {
		synchronized (this) { // Object lock (synchronized block)
			instanceCount++;
		}
	}

//	public static synchronized void incrementStatic() { // Class lock (synchronized method)
//		staticCount++;
//	}

	public static void incrementStatic() {
		synchronized (Counter1.class) { // Class lock (synchronized block)
			staticCount++;
		}
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public static int getStaticCount() {
		return staticCount;
	}

}

public class MonitorLock {

	public static void main(String[] args) throws InterruptedException {
		Counter1 counter = new Counter1();

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; ++i) {
				counter.incrementInstance();
				Counter1.incrementStatic();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; ++i) {
				counter.incrementInstance();
				Counter1.incrementStatic();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println(counter.getInstanceCount());
		System.out.println(Counter1.getStaticCount());
	}

}

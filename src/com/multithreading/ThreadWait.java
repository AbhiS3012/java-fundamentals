package com.multithreading;

/**
 * wait() releases the monitor lock and causes the current thread to wait until
 * another thread calls notify() or notifyAll() on the same object.
 *
 * If notify() is called before the waiting thread actually enters wait(), the
 * notification is lost and the waiting thread may block forever.
 *
 * This can lead to a deadlock-like situation where the program never
 * progresses.
 */
public class ThreadWait {

	public static void main(String[] args) {
		ThreadWait obj = new ThreadWait();

		Thread t1 = new Thread(() -> {
			synchronized (obj) {
				try {
					System.out.println("Thread-1 waits");
					obj.wait(); //could be deadlock if notify() runs before this wait()
//					obj.wait(5000); // wait for fixed time
					System.out.println("Thread-1 starts");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		Thread t2 = new Thread(() -> {
			synchronized (obj) {
				System.out.println("Thread-2 notifies");
				obj.notify();
			}
		});

		t1.start();
		t2.start();
	}

}

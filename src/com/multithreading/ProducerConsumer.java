package com.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
	public static void main(String[] args) {
		Buffer buffer = new Buffer();

		new Thread(() -> {
			try {
				for (int i = 0; i < 50; ++i) {
					buffer.produce(i);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}).start();

		new Thread(() -> {
			try {
				for (int i = 0; i < 50; ++i) {
					buffer.consume();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}).start();

	}
}

class Buffer {

	private final Queue<Integer> buffer = new LinkedList<>();
	private final int capacity = 5;

	public void produce(int i) throws InterruptedException {

		synchronized (this) {

			// Wait if buffer is full
			while (buffer.size() == capacity) {
				System.out.println("Producer is waiting...");
				wait();
			}

			System.out.println("Producing: " + i);
			buffer.add(i);

			// Notify consumer that an item is available
			notify();
		}
	}

	public void consume() throws InterruptedException {

		synchronized (this) {

			// Wait if buffer is empty
			while (buffer.isEmpty()) {
				System.out.println("Consumer is waiting...");
				wait();
			}

			int value = buffer.poll();
			System.out.println("Consuming: " + value);

			// Notify producer that space is available
			notify();
		}
	}
}
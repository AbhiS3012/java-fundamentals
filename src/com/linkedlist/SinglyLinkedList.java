package com.linkedlist;

class Node {

	Node next;
	int data;

	Node(int data) {
		this.data = data;
	}
}

public class SinglyLinkedList {

	Node head;

	public void insertEnd(int data) {
		Node node = new Node(data);

		if (head == null) {
			head = node;
			return;
		}

		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}

		temp.next = node;
	}

	public void insertStart(int data) {
		Node node = new Node(data);

		if (head == null) {
			head = node;
			return;
		}

		node.next = head;
		head = node;
	}

	public void insertSpecific(int index, int data) {
		// invalid index
		if (index < 0) {
			System.out.println("Invalid index");
			return;
		}

		Node node = new Node(data);

		// empty list
		if (head == null) {
			if (index == 0) {
				head = node;
			}
			return;
		}

		// insert at head
		if (index == 0) {
			node.next = head;
			head = node;
			return;
		}

		int count = 0;
		Node temp = head;

		/**
		 * temp should stop at the node just before the position where you want to
		 * insert (index - 1)
		 */
		while (temp.next != null && count < index - 1) {
			temp = temp.next;
			count++;
		}

		// index greater than size
		if (count < index - 1) {
			System.out.println("Invalid index");
			return;
		}

		node.next = temp.next;
		temp.next = node;
	}

	public void deleteStart() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		head = head.next;

		/**
		 * Optional: Explicitly free reference (clarity)
		 */
//		Node temp = head;
//		head = head.next;
//		temp.next = null;
	}

	public void deleteEnd() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		if (head.next == null) {
			head = null;
			return;
		}

		Node temp1 = head;
		Node temp2 = head;
		while (temp1.next != null) {
			temp2 = temp1;
			temp1 = temp1.next;
		}

		temp2.next = null;
	}

	public void deleteSpecific(int index) {
		// invalid index
		if (index < 0) {
			System.out.println("Invalid index");
			return;
		}

		// empty list
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		// delete first
		if (index == 0) {
			head = head.next;
			return;
		}

		int count = 0;
		Node temp = head;

		/**
		 * temp should stop at the node just before the position where you want to
		 * delete (index - 1)
		 */
		while (temp.next != null && count < index - 1) {
			temp = temp.next;
			count++;
		}

		// index out of range
		if (temp.next == null) {
			System.out.println("Invalid index");
			return;
		}

		temp.next = temp.next.next;
	}

	public void reverse() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}

		Node prev = null;
		Node current = head;
		Node next = null;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}

		head = prev;
	}

	public void display() {
		if (head == null) {
			System.out.println("List is empty");
		}

		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " -> ");
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList sll = new SinglyLinkedList();
		int i = 1;
		int j = 10;
		while (i <= 5) {
//			sll.insertEnd(j);
			sll.insertStart(j);
			i++;
			j += 10;
		}

		sll.display();
		System.out.println();

		sll.insertSpecific(4, 60);
		sll.display();

		sll.deleteStart();
		System.out.println();
		sll.display();

		sll.deleteEnd();
		System.out.println();
		sll.display();

		System.out.println();
		sll.deleteSpecific(3);
		sll.display();

		System.out.println();
		sll.reverse();
		sll.display();
	}
}

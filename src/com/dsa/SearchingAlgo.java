package com.dsa;

public class SearchingAlgo {

	public static void main(String[] args) {
		System.out.println(linear());
		System.out.println(binary());
	}

	public static int linear() {
		int[] arr = { 10, 25, 30, 45, 60 };
		int target = 25;
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == target) {
				return i;
			}
		}

		return -1;
	}

	public static int binary() {
		int[] arr = { 10, 20, 30, 40, 50, 60, 70 };
		int target = 30;

		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}
}

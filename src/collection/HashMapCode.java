package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashMapCode {

	static class HashMap<k, v> {

		private class Node {
			k key;
			v value;

			public Node(k key, v value) {
				this.key = key;
				this.value = value;
			}
		}

		private int n; // no of nodes;
		private int N; // no of buckets (capacity);
		private LinkedList<Node> buckets[];

		@SuppressWarnings("unchecked")
		public HashMap() {
			N = 4;
			this.buckets = new LinkedList[N];

			// Create empty list at each bucket index to avoid NPE
			// buckets -> [ [], [], [], [] ]
			for (int i = 0; i < N; ++i) {
				buckets[i] = new LinkedList<>();
			}
		}

		@SuppressWarnings("unchecked")
		private void reHash() {
			LinkedList<Node> oldBucket[] = buckets;
			n = 0;
			N = N * 2;
			buckets = new LinkedList[N];

			// Create empty list at each bucket index
			for (int i = 0; i < N; ++i) {
				buckets[i] = new LinkedList<>();
			}

			for (int i = 0; i < oldBucket.length; ++i) {
				LinkedList<Node> ll = oldBucket[i];

				for (Node node : ll) {
					put(node.key, node.value);
				}
			}
		}

		private int hash(k key) {
			int index = key == null ? 0 : key.hashCode();
			return Math.abs(index) % N; // lies always between 0 to N-1
		}

		private int searchInLL(k key, int bi) {
			LinkedList<Node> ll = buckets[bi];

			for (int i = 0; i < ll.size(); ++i) {
				if (ll.get(i).key.equals(key)) {
					return i;
				}
			}

			return -1;
		}

		public void put(k key, v value) {
			int bi = hash(key);
			int di = searchInLL(key, bi);

			if (di == -1) { // key doesn't exist
				Node node = new Node(key, value);
				buckets[bi].add(node);
				n++;
			} else { // key exist
				Node node = buckets[bi].get(di);
				node.value = value;
			}

			double lambda = (double) n / N;
			double k = 2.0; // threshold value (capacity * load factor(0.75))
			if (lambda > k) {
				reHash();
			}
		}

		public v get(k key) {
			int bi = hash(key);
			int di = searchInLL(key, bi);

			if (di == -1) { // key doesn't exist
				return null;
			} else { // key exist
				return buckets[bi].get(di).value;
			}
		}

		public boolean containsKey(k key) {
			int bi = hash(key);
			int di = searchInLL(key, bi);

			return di != -1;
		}

		public v remove(k key) {
			int bi = hash(key);
			int di = searchInLL(key, bi);

			if (di == -1) { // key doesn't exist
				return null;
			} else { // key exist
				Node node = buckets[bi].remove(di);
				n--;
				return node.value;
			}
		}

		public List<k> keySet() {
			List<k> keys = new ArrayList<>();

			for (int i = 0; i < buckets.length; ++i) {
				LinkedList<Node> ll = buckets[i];

				for (Node node : ll) {
					keys.add(node.key);
				}
			}

			return keys;
		}

		public boolean isEmpty() {
			return n == 0;
		}

	}

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("India", 190);
		map.put("China", 200);
		map.put("US", 50);

		List<String> keys = map.keySet();
		for (int i = 0; i < keys.size(); i++) {
			System.out.println(keys.get(i) + " " + map.get(keys.get(i)));
		}

		System.out.println(map.containsKey("India"));
		System.out.println(map.containsKey("Japan"));

		map.remove("India");
		System.out.println(map.get("India"));
	}

}

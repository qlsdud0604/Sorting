public abstract class AbstractSort {

	public static void sort(Comparable[] a) {

	};

	/** compare the size of two elements */
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/** change two elements */
	protected static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	/** show all elements in an array */
	protected static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	/** check if the array is sorted */
	protected static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}
}
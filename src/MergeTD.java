public class MergeTD extends AbstractSort {
	private static void merge(Comparable[] a, Comparable[] aux, int low, int middle, int high) {

		/** copy the contents of the a[] to the aux[] */
		for (int k = low; k <= high; k++)
			aux[k] = a[k];

		int i = low;
		int j = middle + 1;

		/** compare the aux[] and save the merged result back to the a[] */
		for (int k = low; k <= high; k++) {
			if (i > middle)
				a[k] = aux[j++];
			else if (j > high)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
		if (high <= low)
			return;

		int middle = low + (high - low) / 2;

		sort(a, aux, low, middle);
		sort(a, aux, middle + 1, high);
		merge(a, aux, low, middle, high);
	}
}
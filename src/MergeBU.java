public class MergeBU extends AbstractSort {
	private static void merge(Comparable[] in, Comparable[] out, int low, int middle, int high) {
		int i = low;
		int j = middle + 1;

		/** merge in[] and store in out[] */
		for (int k = low; k <= high; k++) {
			if (i > middle)
				out[k] = in[j++];
			else if (j > high)
				out[k] = in[i++];
			else if (less(in[j], in[i]))
				out[k] = in[j++];
			else
				out[k] = in[i++];
		}
	}

	public static void sort(Comparable[] a) {
		Comparable[] src = a;
		Comparable[] dst = new Comparable[a.length];
		Comparable[] tmp;

		int N = a.length;

		/** array size of the parts to be merged */
		for (int n = 1; n < N; n *= 2) {

			/** array position of the part to be merged */
			for (int i = 0; i < N; i += 2 * n)
				merge(src, dst, i, i + n - 1, Math.min(i + 2 * n - 1, N - 1));

			tmp = src;
			src = dst;
			dst = tmp;
		}
		if (src != a)
			System.arraycopy(src, 0, a, 0, N);
	}
}
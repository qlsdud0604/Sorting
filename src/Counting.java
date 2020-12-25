public class Counting {
	public static int[] sort(int[] A, int K) {
		int i, N = A.length;
		int[] C = new int[K];
		int[] B = new int[N];

		for (i = 0; i < N; i++)
			C[A[i]]++;
		for (i = 1; i < K; i++)
			C[i] += C[i - 1];
		for (i = N - 1; i >= 0; i--)
			B[--C[A[i]]] = A[i];

		return B;
	}
}
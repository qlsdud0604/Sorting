# 다양한 정렬 알고리즘
## 1. 구현한 정렬 알고리즘
* 선택 정렬
* 삽입 정렬
* 쉘 정렬
* 계수 정렬
* 기수 정렬
* 병합 정렬 (Top-Down)
* 병합 정렬 (Bottom-Up)

-----
## 2. 구현 방법
**1) 추상 클래스**
```
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
```
ㆍ 각각의 정렬 알고리즘들이 상속받을 추상클래스를 구현하였다.   

**2) 선택 정렬**
```
public class Selection extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;

		for (int i = 0; i < N - 1; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
		}
	}
}
```
ㆍ 입력된 배열 리스트에서 최솟값을 찾고, 이 값을 현재 위치의 값과 교환한다.  
ㆍ 그리고 현재 위치를 다음으로 이동하면서 앞에 과정을 반복해나가는 방식이다.

**3) 삽입 정렬**
```
public class Insertion extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;

		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
		}
	}
}
```
ㆍ 현재 위치를 i라고 한다. (0 < i < n)   
ㆍ i번째 원소를 0부터 i-1까지 정렬된 배열 리스트에 추가한다.  
ㆍ i를 n-1까지 증가하면서 위 과정을 반복한다.  

**4) 쉘 정렬**
```
public class Shell extends AbstractSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exch(a, j, j - h);
			}
			h = h / 3;
		}
	}
}
```
ㆍ hr, hr-1, ..., h1까지의 수열을 가정한다. (h1 = 1, hi-1 < hi)  
ㆍ 처음에는 hr개 떨어진 원소들간에 삽입 정렬을 한다.  
ㆍ 이후 hr-1부터 h1까지 h를 줄이면서 삽입 정렬을 하는 방식이다.  

**5) 계수 정렬**
```
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
```
ㆍ 키 값이 0 ~ K-1 사이의 정수일 경우 적용 가능한 정렬이다.  
ㆍ 입력 배열: A[n], 임시 배열: C[K], 결과 배열: B[n]을 사용하여 정렬을 한다.  
ㆍ A에 나오는 모든 값들에 대해 그 빈도수를 C에 계산한다.   
ㆍ A[i] = 72이며, C[0] 부터 C[71]까지의 합이 53일 때, B[53]에 72를 저장하는 방식이다.   
<img src="https://user-images.githubusercontent.com/61148914/87389119-1d69ae00-c5e1-11ea-9fed-ef019a0a2473.JPG" width="45%">   
ㆍ 계수 정렬이 동작하는 과정은 위 사진과 같다.   
   
**6) 기수 정렬**
```
public class Radix {
	public static void sort(int[] A) {
		int i, m = A[0];
		int exp = 1;
		int N = A.length;

		int[] B = new int[N];

		for (i = 1; i < N; i++)
			if (A[i] > m)
				m = A[i];

		while (m / exp > 0) {
			int[] C = new int[10];

			for (i = 0; i < N; i++)
				C[(A[i] / exp) % 10]++;
			for (i = 1; i < 10; i++)
				C[i] += C[i - 1];
			for (i = N - 1; i >= 0; i--)
				B[--C[(A[i] / exp) % 10]] = A[i];

			for (i = 0; i < N; i++)
				A[i] = B[i];

			exp *= 10;
		}
	}
}
```
ㆍ 낮은 자리수부터 비교하여 정렬하는 형태의 알고리즘이다.   
ㆍ 낮은 자리수의 정렬이 끝나면 높은 자리수 순서대로 위 과정을 반복한다.   

**7) 병합 정렬 (Top-Down)**
```
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
```
ㆍ 배열을 두 부분으로 분할한다.   
ㆍ 각 부분을 재귀적으로 정렬한다.   
ㆍ 두 부분을 병합하는 순으로 정렬이 이루어진다.   
<img src="https://user-images.githubusercontent.com/61148914/87500871-9d068400-c698-11ea-8aa7-608e099ec86f.JPG" width="50%">   
ㆍ Top-Down 병합 정렬의 동작 과정은 위 사진과 같다.   

**8) 병합 정렬 (Bottom-Up)**
```
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
```
ㆍ 배열에서 크기 1인 부분 즉, 배열내 원소 단위부터 병합을 한다.   
ㆍ 부분의 크기를 2, 4, 8, ... 로 증가하면서 위 과정을 반복한다.    
<img src="https://user-images.githubusercontent.com/61148914/87501113-40f02f80-c699-11ea-819b-598f1e1e9a7d.JPG" width="50%">   
ㆍ Bottom-UP 병합 정렬의 동작 과정은 위 사진과 같다.

-----
## 3. 참고 문헌 및 자료
* R.Sedgewick and K. Wayne, Algorithms (4th Ed.), Addison-Wesley.
* E. Horowitz, S. Sahni, S. Anderson-Freed, Fundamentals of Data Structures in C, Silicon Press, 2nd Edition.

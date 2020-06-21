package streamAPI;

import java.util.Arrays;
import java.util.Random;
import java.lang.*;

public class SortBenchmark {
	private static final int NOF_ELEMENTS = 1_000;


	public void quickSort(long [] array, int left, int right){
		int i = left;
		int j = right;
		long pivot = array[(j-i)/2];
		while (i<=j){
			while (array[i] < pivot) i++;
			while (array[j] > pivot) j--;
			if (i<=j) {
				long element = array[i];
				array[i] = array[j];
				array[j] = element;
				i++; j--;
			}
		}
		if (left < j) quickSort(array, left, j);
		if (i < right) quickSort(array, i, right);

	}

	public void swap(long [] array, int i, int j){
		long element = array[i];
		array[i] = array[j];
		array[j] = element;
	}

	public void measureQuicksort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		// TODO: Call your quicksort implementation
		quickSort(array, 0, array.length-1);
		long end = System.currentTimeMillis();
		System.out.println("Quicksort: " + (end - start) + " ms");
		checkSorted(array);
	}

	public void measureJavaSort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		Arrays.sort(array);
		long end = System.currentTimeMillis();
		System.out.println("Java Arrays.sort: " + (end - start) + " ms");
		checkSorted(array);
	}
	
	public void measureTreeSetSort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		// TODO: sort using TreeSet
		long end = System.currentTimeMillis();
		System.out.println("Quicksort: " + (end - start) + " ms");
		checkSorted(array);
	}

	public void bubbleSort(long [] array) {
		int size = array.length - 1;
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 0; i < size; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
					swapped = true;
				}
			}
		}while (swapped);
	}

	public void measureBubbleSort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		// TODO: call your bubble sort implementation
		bubbleSort(array);
		long end = System.currentTimeMillis();
		System.out.println("Bubble sort: " + (end - start) + " ms");
		checkSorted(array);
	}

	private long[] getTestArray() {
		Random random = new Random(4711); // arbitrary but reproducible seed
		long[] array = new long[NOF_ELEMENTS];
		for (int i = 0; i < NOF_ELEMENTS; i++) {
			array[i] = random.nextLong();
		}
		return array;
	}

	private void checkSorted(long[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				throw new AssertionError("not sorted");
			}
		}
	}

	public static void main(String[] args) {
		SortBenchmark benchmark = new SortBenchmark();
		benchmark.measureQuicksort();
		//benchmark.measureJavaSort();
		//benchmark.measureTreeSetSort();
		benchmark.measureBubbleSort();

	}
}

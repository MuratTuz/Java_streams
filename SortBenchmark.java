package streamAPI;

import java.util.Arrays;
import java.util.Random;

public class SortBenchmark {
	private static final int NOF_ELEMENTS = 1_000_000;
	
	public void measureQuicksort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		// TODO: Call your quicksort implementation
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
	
	public void measureBubbleSort() {
		long[] array = getTestArray();
		long start = System.currentTimeMillis();
		// TODO: call your bubble sort implementation
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
		benchmark.measureJavaSort();
		benchmark.measureTreeSetSort();
		benchmark.measureBubbleSort();
	}
}

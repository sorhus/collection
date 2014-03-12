package sorhus.collection;

/**
 * @author: anton.sorhus@gmail.com
 */
public class Arrays {

    public static void mergeSort(int[] array) {
        mergeSort(array, new int[array.length], 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] copy, int begin, int end) {
        if(end - begin > 0) {
            int length = end - begin;
            int mid = begin + length / 2;
            mergeSort(array, copy, begin, mid);
            mergeSort(array, copy, mid + 1, end);
            merge(array, copy, begin, mid + 1, end);
        }
    }

    private static void merge(int[] array, int[] copy, final int begin, final int mid, final int end) {
        int i = begin;
        int j = mid;
        int k = begin;
        while (k <= end) {
            if(i < mid && (j > end || array[i] <= array[j])) {
                copy[k++] = array[i++];
            } else {
                copy[k++] = array[j++];
            }
        }
        for (k = begin; k <= end; k++) {
            array[k] = copy[k];
        }
    }

    public static int search(int[] array, int element) {
        return search(array, element, 0, array.length - 1);
    }

    private static int search(int[] array, int element, int begin, int end) {
        int mid = begin + (end - begin) / 2;
        if(array[mid] == element) {
            return mid;
        } else if(end - begin == 0) {
            return -1;
        } else if(element > array[mid]) {
            return search(array,element, mid+1, end);
        } else {
            return search(array,element, begin, mid-1);
        }
    }

}

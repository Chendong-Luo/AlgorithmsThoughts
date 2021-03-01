package main;

public class QuickSort extends HelperFunctions {


    public QuickSort() {

    }


    public void sort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int pi = partition(arr, lo, hi);
            sort(arr, lo, pi - 1);
            sort(arr, pi + 1, hi);
        }
    }


    public int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int j = lo;
        for (int i = lo; i < hi + 1; i++) {
            if (arr[i] < pivot) {
                j++;
                swap(i, j, arr);
            }
        }
        swap(lo, j, arr);
        return j;
    }
}


class runQS {
    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] arr = new int[] {3, 2, 1, 5, 4, 6, 0};
        qs.sort(arr, 0, 6);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}

package main;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends HelperFunctions {


    public MergeSort() {}


    public void Sort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int pi = (hi + lo) / 2;
            Sort(arr, lo, pi);
            Sort(arr, pi + 1, hi);
            Merge(arr, lo, hi, pi);
        }
    }


    public void Merge(int[] arr, int lo, int hi, int pi) {
        int i = lo;
        int j = pi + 1;
        List<Integer> temp = new ArrayList<>();
        while (i <= pi && j <= hi) {
            if (arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }
        if (j <= hi) {
            while (j <= hi) {
                temp.add(arr[j]);
                j++;
            }
        } else if (i <= pi){
            while (i <= pi) {
                temp.add(arr[i]);
                i++;
            }
        }


        for (int m = lo; m < hi + 1; m++) {
            arr[m] = temp.get(m - lo);
        }

    }
}

class run {
    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = new int[] {1, 4, 3, 5, 6, 0};
        ms.Sort(arr, 0, 5);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

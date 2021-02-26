package main;

import java.util.ArrayList;
import java.util.List;

public class DeterministicSelect extends HelperFunctions{

    public DeterministicSelect() {

    }

    public int DS(int[] arr, int k) {
        if (arr.length >= 5) {
            int[][] arrays = new int[arr.length / 5][5];
            for (int i = 0; i < arr.length / 5; i++) {
                for (int j = 0; j < 5; j++) {
                    arrays[i][j] = arr[i * 5 + j];
                }
                insertionSort(arrays[i]);
            }
            int[] medians = new int[arr.length / 5];
            for (int i = 0; i < medians.length; i++) {
                medians[i] = arrays[i][2];
            }
            int pivot = quickSelect(medians, medians.length / 2, 0, medians.length - 1);
            List<Integer> Lesser = new ArrayList<>();
            List<Integer> Greater = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > pivot) {
                    Greater.add(arr[i]);
                } else if (arr[i] < pivot) {
                    Lesser.add(arr[i]);
                }
            }
            if (Lesser.size() == k) {
                return pivot;
            } else if (Lesser.size() > k) {
                int[] LesserAry = new int[Lesser.size()];
                for (int i = 0; i < Lesser.size(); i++) {
                    LesserAry[i] = Lesser.get(i);
                }
                return DS(LesserAry, k);
            } else {
                int[] GreaterAry = new int[Greater.size()];
                for (int i = 0; i < Greater.size(); i++) {
                    GreaterAry[i] = Greater.get(i);
                }
                return DS(GreaterAry, k - Lesser.size() - 1);
            }
        }
        int[] temp = insertionSort(arr);
        return temp[k];
    }

    public int quickSelect(int[] arr, int k, int lo, int hi) {
        if (hi > lo) {
            int pi = partition(arr, lo, hi);
            if (k > pi) {
                return quickSelect(arr, k - 1 - pi, pi + 1, hi);
            }
            if (k < pi) {
                return quickSelect(arr, k, lo, pi - 1);
            }
            return arr[pi];
        }
        return arr[lo];
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

    public int[] insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(j, j - 1, arr);
                } else {
                    break;
                }
            }
        }
        return arr;
    }

}


class runDS {
    public static void main(String[] args) {
        DeterministicSelect ds = new DeterministicSelect();
        int output = ds.DS(new int[]{3, 1, 2, 4, 5, 6, 16}, 1);
        System.out.println(output);
    }
}

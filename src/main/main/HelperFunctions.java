package main;

public class HelperFunctions {


    public void swap(int first, int second, int[] arr) {
        int temp = arr[second];
            arr[second] = arr[first];
            arr[first] = temp;

    }
}

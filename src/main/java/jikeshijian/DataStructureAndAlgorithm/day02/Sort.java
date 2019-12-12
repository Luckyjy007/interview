package jikeshijian.DataStructureAndAlgorithm.day02;


import java.io.IOException;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-10 17:09
 **/
public class Sort {


    public static int[] bubbleSort(int[] array) {

        int n = array.length;

        for (int i = 0; i < n; i++) {


            for (int j = 0; j < n - i - 1; j++) {

                if (array[j + 1] > array[j]) {

                    int tmp = array[j];

                    array[j] = array[j + 1];

                    array[j + 1] = tmp;


                }

            }


        }

        return array;

    }


    public static int[] insertSort(int[] arr) {


//        int n = arr.length;
//
//        if (n <= 1) return arr;
//
//
//        for (int i = 1; i < n; i++) {
//
//            int key = arr[i];
//
//
//
//
//            for (int j=0; j<=i ;j++){
//
//                if (arr[j]<key){
//
//                    int tmp = arr[j];
//                    arr[j]=key;
//                   key=tmp;
//
//
//                }else break;
//
//
//            }
//
//
//        }

        int temp;

        for (int i = 1; i < arr.length; i++) {

            //待排元素小于有序序列的最后一个元素时，向前插入
            if (arr[i] < arr[i - 1]) {
                temp = arr[i];
                for (int j = i; j >= 0; j--) {
                    if (j > 0 && arr[j - 1] > temp) {
                        arr[j] = arr[j - 1];
                    } else {
                        arr[j] = temp;
                        break;

                    }
                }
            }
        }


        return arr;
    }


    public static void main(String[] args) {

        int[] a = {32, 31, 54, 12, 53, 76, 1, -12};






    }
}
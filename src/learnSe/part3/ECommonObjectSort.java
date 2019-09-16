package learnSe.part3;
//3.1常见对象
//
//1.冒泡排序
//    相邻比较，每进行一次完整的冒泡，找出一个最大的，剩下的值继续进行冒泡，所以
//    对于n个数，要进行n-1次冒泡才能找出所有顺序（剩的一个最小，不用再考虑）
//    外循环控制冒泡次数，内循环控制进行每次的冒泡
//
//2.选择排序
//    从0索引开始，依次和其他数顺序比较，小的放在左边，所以每次选择，会找出一个最小的（放在左边）
//    外循环控制选择的第一个数，内循环进行每次的选择的第二个数
//3.快速排序
//        参考： https://blog.csdn.net/qq_24946843/article/details/88973742
//    概述：在数组中选择一个数字，接下来把数组中的数字分为两部分，保证所选数左边都比它小，右边都比它大
//    思路：
//        1.任意取一个基准数（默认取index=0）
//        2.左右分区
//        3.再对左右分区进行1，2
//            分区的操作可以理解为挖坑，填坑
//            从右向左找比基准数小的，从左往右找比基准数大的，交换
//            基准数归位（和小的数做交换）

import java.util.Arrays;

//4.二分查找
//    前提：n个数是有序的
//    按照索引取到值，每次拿(min+max)/2和目标值比较
//        相同，返回mid
//        不同
//            value在arr[mid]左边，则max=mid继续二分
//            value在arr[mid]右边，则min=mid继续二分
public class ECommonObjectSort {

    //冒泡排序
    public int[] bubbleSort(int[] arr) {
        //判空
        if (arr.length > 0) {
            //外循环控制冒泡次数（arr.length个数，进行arr.length-1次）
            for (int j = 0; j < arr.length - 1; j++)
                //内循环进行每次的冒泡比较（arr.length个数，比较arr.length-1次）
                for (int i = 0; i < arr.length - 1 - j; i++) {
                    if (arr[i] > arr[i + 1]) {
                        changeExchange(arr,i);
                    }
                }
            return arr;
        } else {
            return arr;
        }
    }

    //选择排序
    public int[] selectSort(int[] arr) {
        if (arr.length>0) {
            //外循环选择第一个数
            for (int i = 0; i<arr.length-1;i++) {
                //内循环选择第二个数
                for (int j = i+1; j<arr.length;j++) {
                    if (arr[i]>arr[j]) {
                        changeExchange(arr,i);
                    }
                }
            }
            return arr;
        }else {
            return arr;
        }
    }

    //快速排序
    public void quickSort(int[] array, int left, int right) {
        //递归判断是否已经全部的数都参与了比较
        if(left > right) {
            return;
        }
        // base中存放基准数，默认取left（数组最左为基准数）
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了符合的数的位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }


    //二分查找
    public int binarySearch(int[] arr, int value) {
        if (arr.length > 0) {
            int min = 0;
            int max = arr.length - 1;
            int mid = (min + max) / 2;
            //判断中值是否是目标值
            while (arr[mid] != value) {
                //根据目标值所在位置确定下一个min,max,mid的位置
                if (arr[mid] > value) {
                    max = mid;
                } else {
                    min = mid;
                }

                if (min > max) {
                    return -1;
                } else {
                    mid = (min + max) / 2;
                }
            }
            return mid;
        } else {
            return -1;
        }
    }

    private void changeExchange(int[] arr, int i) {
        int temp = arr[i];
        arr[i] = arr[i+1];
        arr[i+1] = temp;
    }
}

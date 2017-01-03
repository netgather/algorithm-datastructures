package com.neu.array;

/**问题:有一个升序排列的数组，数组中可能有正数、负数或0，求数组中元素的绝对值最小的数
 * Created by lihongyan on 2015/10/29.
 */
public class GetMinAbsoluteValue {
    //求绝对值最小的数可以分为3种情况:
    // ①如果数组的第一个元素为非负数，那么绝对值最小的数肯定为数组的第一个元素
    // ②如果数组最后一个元素为负数，那么绝对值最小的数肯定为数组的最后一个元素
    // ③数组中即有正数又有负数时，首先找到正数与负数的分界点，如果分界点恰好为0
    //   那么0就是绝对值最小的数，否则通过比较分界点左右的正数与负数的绝对值来确定最小的数
    //采用二分法查找正数与负数分界点的方法:取数组中间位置的值array[mid]:
    // ①array[mid]=0,那么这个数就是绝对值最小的数
    // ②array[mid]>0,如果array[mid-1]<0,那么就找到了分界点，通过比较array[mid-1]与array[mid]的
    //   绝对值就可以找到数组中绝对值最小的数,如果array[mid-1]=0,那么array[mid-1]就是要找的数,
    //   否则接着在数组的左半部分查找;
    // ③如果array[mid]<0,如果array[mid+1]>0,那么通过比较array[mid]与array[mid+1]的绝对值即可，
    //   如果array[mid+1]=0,那么array[mid+1]就是要查找的数，否则接着在数组的右半部分继续查找
    public static int getMinAbsoluteValue(int[] array){
        if(array==null){
            return Integer.MIN_VALUE;
        }
        int length = array.length;
        if(length==0){
            return Integer.MIN_VALUE;
        }
        //第一种情况:数组中没有负数
        if(array[0]>0){
            return array[0];
        }
        //第二种情况:数组中没有正数
        if(array[length-1]<0){
            return array[length-1];
        }
        //第三种情况:数组中既有正数又有负数,使用二分法查找正数与负数的分界点
        int mid = 0;
        int begin = 0;
        int end = length-1;
        while(true){
            mid = begin + (end - begin)/2;
            if(array[mid]==0){
                return 0;
            }else if(array[mid]>0){
                if(array[mid-1]>0){
                    end = mid -1;
                }else if(array[mid-1]==0){
                    return 0;
                }else{
                    break;
                }
            }else{
                if(array[mid+1]<0){
                    begin = mid + 1;
                }else if(array[mid+1]==0){
                    return 0;
                }else{
                    break;
                }
            }
        }
        //获取正负数分界点处绝对值最小的数
        int absMin = 0;
        if(array[mid]>0){
            if(array[mid]>Math.abs(array[mid-1])){
                absMin = array[mid-1];
            }else if(array[mid]==Math.abs(array[mid-1])){
                absMin = array[mid-1];
            }else{
                absMin = array[mid];
            }
        }else if(array[mid]<0){
            if(array[mid+1]>Math.abs(array[mid])){
                absMin = array[mid];
            }else if(array[mid+1]==Math.abs(array[mid])){
                absMin = array[mid];
            }else{
                absMin = array[mid+1];
            }
        }
        return absMin;
    }
    public static void main(String[] args){
        int[] a1 = {-10,-5,-2,7,15,50};
        int[] a2 = {2,4,6,8,27};
        int[] a3 = {-13,-9,-7,-3};
        System.out.println(getMinAbsoluteValue(a1));
        System.out.println(getMinAbsoluteValue(a2));
        System.out.println(getMinAbsoluteValue(a3));
    }
}

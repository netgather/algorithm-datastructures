package com.neu.array;

/**
 * ���⣺Ѱ�������е����ֵ����Сֵ
 * ����һ������ֽⷨ
 * ��������ȡ��Ԫ�ط�
 * ��������ȥ˫Ԫ�ط�
 * Created by lihongyan on 2015/10/24.
 */
public class GetMaxAndMin {

    private static int max;
    private static int min;
    public static void getMaxAndMin(int[] array){
        min = array[0];
        max = array[0];
        int length = array.length;
        for(int i=1;i<length-1;i++){
            if(array[i]>array[i+1]){
                if(array[i]>max){
                    max = array[i];
                }
                if(array[i+1]<min){
                    min = array[i+1];
                }
            }
            if(array[i]<array[i+1]){
                if(array[i+1]>max){
                    max = array[i+1];
                }
                if(array[i]<min){
                    min = array[i];
                }
            }
        }
    }
    public static void main(String[] args){
        int[] array = {7,3,19,40,4,7,1};
        getMaxAndMin(array);
        System.out.println(max);
        System.out.print(min);
    }
}

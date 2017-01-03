package com.neu.array;

/**
 * ���⣺���Ѱ�������еڶ������
 * �������ȶ�������������һ�����������洢�����е����������ʼֵΪ������Ԫ�أ�
 *      ��һ�����������洢����Ԫ�صĵڶ���������ʼֵΪ��С��������Ȼ���������
 *      �������Ԫ�ص�ֵ�������������ֵ���򽫵ڶ�����������ֵ����Ϊ�����������ֵ
 *      �����������ֵ����Ϊ������Ԫ�ص�ֵ��������Ԫ�ص�ֵ�����ֵ����С�����жϸ�����Ԫ�ص�ֵ�Ƿ�
 *      �ȵڶ�������ֵ��������Ԫ�ص�ֵ�ȵڶ�������ֵ������µڶ�������ֵΪ������Ԫ�ص�ֵ
 * Created by lihongyan on 2015/10/24.
 */
public class FindSecMax {
    public static int findSecMax(int[] array){
        int length = array.length;
        int max = array[0];
        int secMax = Integer.MIN_VALUE;
        for(int i=1;i<length;i++){
            if(array[i]>max){
                secMax = max;
                max = array[i];
            }else{
                if(array[i]>secMax){
                    secMax = array[i];
                }
            }
        }
        return secMax;
    }
    public static void main(String[] args){
        int[] a = {7,3,19,40,4,7,1};
        System.out.print(findSecMax(a));
    }
}

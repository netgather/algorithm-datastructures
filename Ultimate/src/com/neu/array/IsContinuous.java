package com.neu.array;

/**
 * 问题:一个数组序列，元素取值可能是0~65535中的任意一个数，相同数值不会重复出现。0是例外，可以反复出现。
 *     设计一种算法，当从该数组序列中随意选取5个数字，判断这5个数值是否连续相邻。需要注意以下四点
 *     ①5个数值允许是乱序的，例如{8,7,5,0,6}。
 *     ②0可以通配任意数值，例如{8,7,5,0,6}中的0可以通配成9或者4。
 *     ③0可以出现多次。
 *     ④全0算连续，只有一个非0算连续
 * Created by lihongyan on 2015/10/30.
 */
public class IsContinuous {
    //方法：如果没有0存在，要组成连续的数列，最大值和最小值的差距必须是4，
    //     存在0的情况下，只要最大值和最小值的差距小于4就可以了，
    //     所以找出数列中非0的最大值和非0的最小值，时间复杂度为O(n)，
    //     如果非0最大值-非0最小值<=4,那么这5个数值连续相邻，否则，不连续相邻。
    //     因此，该算法的时间复杂度为O(n)。
    public static Boolean isContinuous(int[] array){
        if(array==null){
            return false;
        }
        int length = array.length;
        int max = -1;
        int min = -1;
        for(int i=0;i<length;i++){
            if(array[i]!=0){
                if(array[i]<min||min==-1){
                    min = array[i];
                }
                if(array[i]>max||max==-1){
                    max = array[i];
                }
            }
        }
        if(max-min>length-1){
            return false;
        }else {
            return true;
        }
    }
    public static void main(String[] args){
        int[] array = {8,7,5,0,6};
        System.out.print(isContinuous(array));
    }
}

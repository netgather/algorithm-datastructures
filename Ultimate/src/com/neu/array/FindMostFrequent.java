package com.neu.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 问题：找出数组中重复次数最多的元素
 *      result 出现次数最多的元素
 *      most   该元素出现的次数
 * Created by lihongyan on 2015/10/26.
 */
public class FindMostFrequent {
    //方法一：空间换时间
    public static int findMostFrequent1(int[] array){
        int most = 0;
        int result = 0;
        int[] count = new int[100];
        for(int i=0;i<array.length;i++){
            count[array[i]]++;
            if(most<count[array[i]]){
                most = count[array[i]];
                result = array[i];
            }
        }
        return result;
    }
    //方法二：使用map映射表。通过引入map映射表来记录每一个元素出现的次数，然后判断次数大小，进而找出重复次数最多的元素
    public static int findMostFrequent2(int[] array){
        int result = 0;
        int length = array.length;
        if(length==0){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<length;i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }
        //找出出现次数最多的元素
        int most = 0;
        Iterator<Integer> it = map.keySet().iterator();
        while(it.hasNext()){
            int key = it.next();
            int value = map.get(key);
            if(value>most){
                most = value;
                result = key;
            }
        }
        return most;
    }
    public static void main(String[] args){
        int[] array = {1,5,4,3,4,4,5,4,5,5,6,6,6,6,6};
        System.out.print("出现次数最多的元素:"+findMostFrequent1(array)+" ");
        System.out.println("该元素出现的次数:"+findMostFrequent2(array));
    }
}

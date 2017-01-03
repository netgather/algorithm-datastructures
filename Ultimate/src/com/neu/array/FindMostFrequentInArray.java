package com.neu.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 问题：找出数组中重复元素最多的数
 * Created by lihongyan on 2015/10/28.
 */
public class FindMostFrequentInArray {
    public static HashMap<Integer,Integer> findMostFrequentInArray(int[] array){
        int length = array.length;
        int result = 0;
        if(length==0){
            HashMap<Integer,Integer> temp = new HashMap<Integer, Integer>();
            temp.put(Integer.MIN_VALUE,Integer.MIN_VALUE);
            return temp;
        }
        //记录每个元素出现的次数
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<length;i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }
        int most = 0;
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            int key = (Integer)entry.getKey();
            int value = (Integer)entry.getValue();
            if(value>most){
                result = key;
                most = value;
            }
        }
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        hashMap.put(result,most);
        return hashMap;
    }
    public static void main(String[] args){
        int[] array = {1,5,4,3,4,4,5,4,5,5,6,6,6,6,6,5,5};
        HashMap<Integer,Integer> result = findMostFrequentInArray(array);
        if(result.keySet().iterator().hasNext()){
            System.out.println("出现次数最多的元素为:"+result.keySet().iterator().next()+
                               " 共出现:"+result.get(result.keySet().iterator().next())+"次");
        }
    }
}

package com.neu.string;

/**
 * 问题：如何输出字符串的所有组合
 *      假设字符串中的所有字符都不重复，如何输出字符串的所有组合。例如：输入字符串“abc”，
 *      则输出 a,b,c,ab,ac,bc,abc，共7种组合
 * 如果字符串中有n个字符，根据排列组合的行至，此时一共需要输出2^n-1种组合
 * Created by lihongyan on 2015/11/1.
 */
public class CombineRecursive {
    //方法一、递归法：遍历字符串，每个字符只能取或不取。若取该字符，就把它放到结果字符串中，
    //             遍历完毕后，输出结果字符串
    //算法分析：当n值不是很大时不存在效率低下的问题，当n值较大值当n值很大时，会进行2^n次栈调用
    public static void combineRecursive(char[] array,int begin,int length,StringBuffer sb){
        if(length==0){
            System.out.print(sb+" ");
            return;
        }
        if(begin==array.length){
            return;
        }
        if(length<0){
            return;
        }
        sb.append(array[begin]);
        combineRecursive(array, begin + 1, length - 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        combineRecursive(array,begin + 1,length,sb);
    }
    //方法二、构造一个长度为n的01字符串表示输出结果中是否包含某个字符，结果就是2^n-1个字符串的集合
    public static void combine(char[] array){
        if(array==null){
            return ;
        }
        int length = array.length;
        boolean[] used = new boolean[length];
        char[] cache = new char[length];
        int result = length;
        while(true){
            int index = 0;
            while(used[index]){//寻找没有被使用的字符
                used[index] = false;
                result++;
                index++;
                if(index==length){
                    return;
                }
            }
            used[index] = true;
            result--;
            cache[result] = array[index];
            System.out.print(new String(cache).substring(result)+" ");
        }
    }
    public static void main(String[] args){
        String str = "abc";
        char[] array = str.toCharArray();
        StringBuffer sb = new StringBuffer("");
        int length = array.length;
        for(int i=1;i<=length;i++){
            combineRecursive(array,0,i,sb);
        }
        System.out.println();
        combine(array);
    }
}

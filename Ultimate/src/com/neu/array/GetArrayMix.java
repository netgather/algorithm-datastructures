package com.neu.array;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 问题:如何计算两个有序整型数组的交集
 *     假设两个含有n个元素的有序(非降序)整型数组a和b，其中a={0,1,2,3,4},b={1,3,5,7,9},那么它们的交集为{1,3}
 *     计算数组交集可以采用很多种方法，但数组的相对大小一般会影响算法的效率，所以需要根据两个数组的相对大小来确定采用的方法
 * Created by lihongyan on 2015/10/30.
 */
public class GetArrayMix {
    //对于两个数组长度相当的情况，一般可以采用如下3中方法
    //方法一：二路归并法
    //方法二：顺序遍历法
    //方法三：散列法
    //对于两个数组长度相差悬殊的情况，一般采用如下3中方法
    //方法四：一次遍历长度短的数组，将便利得到的数组元素在长数组中进行二分查找。
    //      具体而言：设两个指向两个数组末尾元素的指针，取较小的那个数在另一个数组中二分查找，找到，则存在交集，
    //               并且将该数组的指针指向该位置的前一个位置。如果没有找到，同样可以找到一个位置，
    //               使得目标数组中在该位置后的数肯定不在另一个数组中存在，直接移动该目标数组的指针指向该位置的前一个位置，
    //               再循环查找，直到一个数组为空为止。
    //               由于两个数组中都可能出现重复的数，因此二分查找时，当找到一个相同的数x时，其下标为i，
    //               那么下一个二分查找的霞姐变为i+1,避免x重复使用
    //方法五：采用与方法四类似的方法，但是每次查找在前一次查找的基础↑进行，这样可以缩小查找表的长度
    //方法六：采用与方法五类似的方法，但是遍历长度小的数组的方法有所不同，即从数组的头部和尾部同时开始遍历

    //方法一：设两个数组分别为array1[n1],array2[n2],分别以i,j从头开始遍历两个数组。
    //       在遍历的过程中，若当前遍历位置的array1[i]与array[j]相等，则此数为两个数组的交集，记录下来，
    //       并继续向后遍历array1和array2。若array1[i]大于array2[j],则继续向后遍历array2。
    //       若array1[i]小于array2[j],则继续向后遍历array1，直到有一个数组结束遍历时停止
    public static ArrayList<Integer> getArrayMix(int[] array1,int[] array2){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i=0,j=0;
        int l1 = array1.length;
        int l2 = array2.length;
        while(i<l1&&j<l2){
            if(array1[i]==array2[j]){
                list.add(array1[i]);
                i++;
                j++;
            }else if(array1[i]>array2[j]){
                j++;
            }else{
                i++;
            }
        }
        return list;
    }
    //方法二：顺序遍历两个数组，将数组元素放到哈希表中，同时对统计的数组元素进行计数，若为2，则为二者的交集元素
    //方法三：遍历两个数组中任意一个数组，将遍历得到的元素存放进散列表中，然后办理另外一个数组，
    //       同时对建立的散列表进行查询，若存在则为交集元素
    public static void main(String[] args){
        int[] a1 = {0,1,2,3,4};
        int[] a2 = {1,3,5,7,9};
        ArrayList<Integer> result = getArrayMix(a1,a2);
        for(Iterator<Integer> it = result.iterator();it.hasNext();){
            System.out.print(it.next()+" ");
        }
    }
}

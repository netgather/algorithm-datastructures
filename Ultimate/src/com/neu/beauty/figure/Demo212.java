package com.neu.beauty.figure;

import java.util.Arrays;
public class Demo212{
		public static void getTwoNum(int sum,int[] array1,int[] array2){
			int length = array1.length + array2.length;
			int[] array = new int[length];
			for(int i=0;i<array1.length;i++){
				array[i] = array1[i];
			}
			for(int i=array1.length,j=0;i<length;i++,j++){
				array[i] = array2[j];
			}
			Arrays.sort(array);
			//for(int i=0;i<length;i++){
			//	System.out.print(array[i]+",");
			//}
			for(int i=0;i<length/2;i++){
					if(sum==(array[i]+array[length-1-i])){
						System.out.println(array[i]+":"+array[length-1-i]);
					}
			}
		}
		public static void main(String[] args){
			int[] array11 = {1,3,5,9,7};
			int[] array22 = {2,4,6,8,10};
			getTwoNum(11,array11,array22);
		}
}

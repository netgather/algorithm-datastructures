package com.neu.beauty.figure;

import java.math.*;
public class Demo27_2{
		public long gcd(long x,long y){
				long max = x>y?x:y;
				long min = x>y?y:x;
				long result = 0;
				if(min==0){
					return max;
				}else if(max%min==0){
					return min;
				}else{
					result = gcd(min,max-min);
				}
				return result;
		}
		public static void main(String[] args){
			Demo27_2 demo = new Demo27_2();
			System.out.println(demo.gcd(42,30));
		}
}

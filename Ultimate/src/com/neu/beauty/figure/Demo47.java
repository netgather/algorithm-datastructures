package com.neu.beauty.figure;

import java.util.*;
public class Demo47{
		private int length;
		{
			this.length = 27;
		}
		public int getMax(int x,int y,int m,int n){
			x = (x-0)>(length-x)?(x-0):(length-x);
			y = (y-0)>(length-y)?(y-0):(length-y);
			m = (m-0)>(length-m)?(m-0):(length-m);
			n = (n-0)>(length-n)?(n-0):(length-n);
			return x+y+m+n;
		}
		public int getMin(int x,int y,int m,int n){
			x = (x-0)<(length-x)?(x-0):(length-x);
			y = (y-0)<(length-y)?(y-0):(length-y);
			m = (m-0)<(length-m)?(m-0):(length-m);
			n = (n-0)<(length-n)?(n-0):(length-n);
			return x+y+m+n;
		}
		public static void main(String[] args){
			Demo47 demo = new Demo47();
			int x;int y;int m;int n;
			Scanner scan = new Scanner(System.in);
			x = scan.nextInt();
			y = scan.nextInt();
			m = scan.nextInt();
			n = scan.nextInt();
			System.out.println(demo.getMax(x,y,m,n));
			System.out.println(demo.getMin(x,y,m,n));
		}
}

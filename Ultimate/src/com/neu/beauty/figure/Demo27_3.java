package com.neu.beauty.figure;

public class Demo27_3{
		public boolean isEven(int index){
			return (index%2==0)?true:false;
		}
		public int gcd(int x,int y){
			int max = 0;int min = 0;
			if(x>y){
				max = x;
				min = y;
			}else if(x==y){
				return x;
			}else{
				max = y;
				min = x;
			}
			if(min==0){
				return max;
			}
			if(isEven(max)){
					if(isEven(min)){
						return gcd(max>>1,min>>1)<<1;
					}else{
						return gcd(max>>1,min);
					}
			}else{
					if(isEven(min)){
						return gcd(max,min>>1);
					}else{
						return gcd(max-min,min);
					}
			}
		}
		public static void main(String[] args){

				Demo27_3 demo = new Demo27_3();
				System.out.println(demo.gcd(84,60));
		}
}

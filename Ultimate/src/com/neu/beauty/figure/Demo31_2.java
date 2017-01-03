package com.neu.beauty.figure;

public class Demo31_2{
			  
				public boolean isInclude(String str1,String other){
								StringBuffer sb = new StringBuffer(str1);
								String target = sb.append(str1).toString();
								char first = other.charAt(0);
								int tooffset = target.indexOf(first);
								if(target.regionMatches(tooffset,other,0,other.length())){
											return true;
								}else{
											return false;
								}
				}
				public boolean isEqual(char[] src,char[] des){

								return isInclude(new String(src),new String(des));
				}
				public static void main(String[] args){
								Demo31_2 demo = new Demo31_2();
								char[] src = {'A','B','C','D'};
								char[] des = {'C','D','A','B'};
								System.out.println(demo.isEqual(src,des));
				}
} 

package com.neu.beauty.figure;

public class Demo31_1{

			  public char[] getRotate(char[] array){
								int length = array.length;
								char[] temp = new char[length];
								char temp_char = array[0];
								for(int i=0;i<length-1;i++){
											temp[i] = array[i+1];
								}
								temp[length-1] = temp_char;
								return temp;
				}
				public boolean isEqual(char[] src,char[] des){
									
								for(int i=0;i<src.length;i++){
										src = getRotate(src);
										if(new String(src).substring(0,des.length).equals(new String(des))){
												return true;
										}
								}
								return false;
				}
				public static void main(String[] args){
								Demo31_1 demo = new Demo31_1();
								//char[] src = {'A','A','B','B','C','D'};
								//char[] src = {'B','C','D','A','A','B'};
								//char[] des = {'C','D','A','A'};
								//***************************************
								char[] src = {'A','B','C','D'};
								char[] des = {'A','C','B','D'};
								System.out.println(demo.isEqual(src,des));
				}
}

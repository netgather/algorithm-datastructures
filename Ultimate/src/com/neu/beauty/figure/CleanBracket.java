package com.neu.beauty.figure;

public class CleanBracket{

	public String cleanBracket(String str){
		String result = "(";
		char[] array = str.toCharArray();
		int bracket_num = 0;
		int i = 0;
		while(i<array.length){
			if(array[i]=='('){
				bracket_num++;
			}else if(array[i]==')'){
				if(bracket_num>0){
					bracket_num--;
				}else{
					System.out.println("error");
					return null;
				}
			}else if(array[i]==','){
				result += array[i];
			}else if(array[i]>'0'&&array[i]<'9'){
				result += array[i];
			}else{
				System.out.println("invalied character");
			}
			i++;
		}
		if(bracket_num>0){
			System.out.println("error");
			return null;
		}
		result += ')';
		return result;
	}

	public static void main(String[] args){
		CleanBracket cb = new CleanBracket();
		String s1 = "(1,(2,3),(4,(5,6),7))";
		System.out.println(cb.cleanBracket(s1));
		String s2 = "((1,(2,3),(4,(5,6),7))";
		System.out.println(cb.cleanBracket(s2));
	}
}

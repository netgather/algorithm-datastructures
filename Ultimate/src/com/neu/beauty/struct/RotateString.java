package com.neu.beauty.struct;

/**
 * 问题：字符串意味包含的问题
 *      给定两个字符串str1和str2，要求判断str2是否能够被str1做循环移位(rotate)得到的字符串包含。
 *      例如，给定str1=AABCD和str2=CDAA,返回true；给定str1=ABCD和str2=ACBD,返回false
 * Created by lihongyan on 2015/11/2.
 */
public class RotateString {
    //方法一：我们可以直接使用最直接的方法对str1进行循环移位，再进行字符串包含的判断，
    //       从而遍历其所有的可能性。即穷举str1(ABCD)做循环意味所能得到的所有字符串，
    //       看其结果是否与str2相等。
    //若字符串的长度N较大，显然效率很低
    public static boolean rotateString1(String str1,String str2){
        if(str1==null||str2==null){
            return false;
        }
        char[] buffer = str1.toCharArray();
        for(int i=0;i<buffer.length;i++){
            char temp = buffer[0];
            for(int j=0;j<buffer.length-1;j++){
                buffer[j] = buffer[j+1];
            }
            buffer[buffer.length-1] = temp;
            String newString = new String(buffer);
            if(newString.contains(str2)){
                return true;
            }
            newString = null;
        }
        return false;
    }
    //方法二、对循环移位之后的结果进行分析：
    //以str1=ABCD为例，先分析对str1进行循环移位之后的结果，如下所示：
    //ABCD-BCDA-CDAB-DABC-ABCD...
    //假设我们把前面一走的数据进行保留，会发现如下规律：
    //ABCD-ABCDA-ABCDAB-ABCDABC-ABCDABCD
    //因此，可以看出对str1做循环移位所得到的字符串，都是str1str1的子字符串。
    //如果str2可以有str1循环移位得到，那么str2必定是str1str1的子字符串
    public static boolean rotateString2(String str1,String str2){
        str1 += str1;
        if(str1.contains(str2)){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        System.out.println(rotateString1("AABCD", "CDAA"));
        System.out.println(rotateString1("ABCD", "ACBD"));
        System.out.println();
        System.out.println(rotateString2("AABCD", "CDAA"));
        System.out.println(rotateString2("ABCD", "ACBD"));
    }
}

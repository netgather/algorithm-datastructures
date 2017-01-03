package com.neu.string;

/**
 * 问题：如何统计一行字符中有多少个单词
 * Created by lihongyan on 2015/11/1.
 */
public class WordCount {
    //方法：单词的数目可以由空格出现的次数决定（连续的若干空格作为出现一次的空格；一行开头的空格不统计在内）。
    //     若测出当前字符为非空格，而它前面的字符是空格，则表示“新的单词开始了”，此时使单词计数器count值加1；
    //     若当前字符为非空格而其前面的字符也是非空格，则意味着仍然是原来那个单词的继续，count值不应该加1。
    //     前面一个字符是否为空格可以从word的值看出来，若word等于0，则表示前一个字符是空格；
    //     若word等于1，意味着前一个字符为非空格。
    public static int wordCount(String str){
        if(str==null){
            return 0;
        }
        boolean isSpace = true;
        int count = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                isSpace = true;
            }else if(isSpace){
                count++;
                isSpace = false;
            }else{
                continue;
            }
        }
        return count;
    }
    public static void main(String[] args){
        String str = " Hello  World IntelliJ IDEA Java ";
        System.out.print(wordCount(str));
    }
}

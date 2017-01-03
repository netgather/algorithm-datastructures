package com.neu.beauty.struct;

/**
 * 问题：计算字符串的相似度
 *      许多程序会大量使用字符串。对于不同的字符串，我们希望能够有办法判断其相似程度。
 *      我们定义了一套操作方法来把两个不同的字符串变得相同。具体操作方法为：
 *        ①修改一个字符（如把“a”替换为“b”）;
 *        ②增加一个字符（如把“abdd”变为“aebdd”）;
 *        ③删除一个字符（如把“travelling”变为“traveling”）;
 *      比如，对于“abcdefg”和“abcdef”两个字符来说，我们认为可以通过增加/减少一个“g”的方式
 *      来达到目的。上面的两种方案，都仅需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
 *      而相似度等于“距离+1”的倒数。
 *      上面的两种方案，都仅需要一次操作，也就是说，“abcdefg”和“abcdef”的距离为1，相似度为1/2=0.5
 * Created by lihongyan on 2015/11/3.
 */
public class CalculateSimilarity {
    //分析与解法：
    //可以看出，两个字符串的距离肯定不超过它们的长度和（通过删除操作，把两个字符串都变为空串，都为空串时它们是相等的）
    //由此可以得出任意两个字符串的距离都是有限的
    //考虑如何才能把这个问题转化成规模较小的同样的问题：
    //①如果两个字符串的第一个字符是相等的，那么只需要计算其他字符的距离即可
    //②两个字符串的第一个字符不相同，那么可以进行如下的操作：
    //  1:删除A串的第一个字符，然后与B计算其他字符的距离
    //  2:删除B串的第一个字符，然后与A计算其他字符的距离
    //  3:修改A串的第一个字符为B串的第一个字符，然后计算A串和B串的距离
    //  4:修改B串的第一个字符为A串的第一个字符，然后计算A串和B穿的距离
    //  5:增加B串的第一个字符到A串的第一个字符之前，然后计算A串和B串的距离
    //  6:增加A串的第一个字符到B串的第一个字符之前，然后计算A串和B串的距离
    //在本题中，我们并不在乎两个字符串变得相等之后的字符串是怎样的，我们只关心需要经过多少步的操作之后，两个字符串是相等的。
    //所以，可以将这6个操作合并为：
    //  1:一步操作之后，再将A[2,...,lenA]和B[1,...,lenB]变成相同的字符串
    //  2:一步操作之后，再将A[1,...,lenA]和B[2,...,lenB]变成相同的字符串
    //  3:一步操作之后，再将A[2,...,lenA]和B[2,...,lenB]变成相同的字符串
    private static int distance = 0;
    public static int min(int t1,int t2,int t3){
        t2 = t2>t1?t2:t1;
        t3 = t3>t2?t3:t2;
        return t3;
    }
    public static void calculateSimilarity(String[] A,int aBegin,int aEnd,String[] B,int bBegin,int bEnd){
        if(aEnd==bEnd){
            if(aBegin<=aEnd){
                if (A[aBegin] == B[bBegin]){
                    calculateSimilarity(A, aBegin + 1, aEnd, B, bBegin + 1, bEnd);
                } else {
                    distance = distance + 1;
                    calculateSimilarity(A, aBegin + 1, aEnd, B, bBegin + 1, bEnd);
                }
            }else{
                return;
            }
        }else if(aEnd>bEnd){
            distance = aEnd - bEnd;
            calculateSimilarity(A,aBegin,bEnd,B,bBegin,bEnd);
        }else{
            distance = bEnd - aEnd;
            calculateSimilarity(A, aBegin, aEnd, B, bBegin, aEnd);
        }
    }
    public static int book(String[] A,int aBegin,int aEnd,String[] B,int bBegin,int bEnd){
        if(aBegin>aEnd){
            if(bBegin>aEnd){
                return 0;
            }else{
                return bEnd - bBegin + 1;
            }
        }
        if(bBegin>bEnd){
            if(aBegin>aEnd){
                return 0;
            }else{
                return aEnd - aBegin + 1;
            }
        }
        if(A[aBegin]==B[bBegin]){
            return book(A,aBegin+1,aEnd,B,bBegin+1,bEnd);
        }else{
            int d1 = book(A,aBegin+1,aEnd,B,bBegin,bEnd);
            int d2 = book(A,aBegin,aEnd,B,bBegin+1,bEnd);
            int d3 = book(A,aBegin+1,aEnd,B,bBegin+1,bEnd);
            return min(d1,d2,d3) + 1;
        }
    }
    public static void main(String[] args){
        String[] A = {"a","b","c","d","g","e","f"};
        String[] B = {"a","b","c","d","e","f"};
        calculateSimilarity(A, 0, A.length - 1, B, 0, B.length - 1);
        System.out.println();
        System.out.println("距离为:" + distance);
        distance += 1;
        System.out.println("相似度为:1/" + distance);

        System.out.println("书上提供的方法");
        int result = book(A,0,A.length-1,B,0,B.length-1);
        System.out.println("距离为:"+result);
        result += 1;
        System.out.println("相似度为:1/" + result);
    }
}
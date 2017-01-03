package com.neu.beauty.struct;

/**
 * 问题：如何生成最短的摘要（在搜索结果中，标题和URL之间的内容就是摘要）
 * 简化：假设给定的已经是经过网页分词之后的结果，词语序列数组为W。其中W[0],W[1],...W[N]为已经分好的词语
 *      假设用户输入的搜索关键词为数组Q。其中Q[0],Q[1],...,Q[m]为搜有输入的搜索关键词
 *      这样，生成的最短摘要实际上就是一串相互联系的分词序列。
 *      比如从W[i]到W[j]，其中0<i<j<=N。
 * Created by lihongyan on 2015/11/4.
 */
public class GenerateShortSummary {
    public int min(int m,int n){
        return m<n?m:n;
    }
    //分析与解法：
    //①从W数组的第一个位置开始查找出一段包含所有关键词数组Q的序列，
    //  计算当前的最短长度，并更新Seq数组。
    //②对目标数组W进行遍历，从第二个位置开始，重新查找包含所有关键词数组Q的序列，
    //  同样计算出最短长度，以及更新包含所有关键词的序列Seq，然后求出最短路径
    //③依次操作下去，一直到遍历至目标数组W的最后一个位置为止。
    //算法复杂度：O(N^2*M)
    //要遍历所有其他的关键词（M），对于每个关键词，要遍历整个网页的词（N），
    //而每个关键词在整个网页中的每一次出现，要遍历所有Seq,
    //以更新这个关键词与所有其他关键词的最小距离。
    private String[] W = {"微软","亚洲","研究院","成立","于","1998","年",",","我们","的",
            "使命","是","使","未来","的","计算机","能够","看","、","听","、",
            "学",",","能","用","自然语言","与","人类","进行","交流","。","在",
            "此","基础","上",",","微软","亚洲","研究院","还","将","促进","计算机",
            "在","亚太","地区","的","普及",",","改善","亚太","用户","的","计算","体验","。"};
    String[] Q = {"微软","亚洲","研究院","计算机"};
    public String[] generateShortSummary(){
        int distance = Integer.MAX_VALUE;
        int begin = 0;int end = 0;
        for(int i=0;i<W.length;i++){
            int pos = 0;
            if(pos<Q.length) {
                if(W[i] == Q[pos]){
                    pos++;
                    begin = i;
                    for(int j=i+1;j<W.length;j++){
                        if(pos<Q.length&&j<W.length){
                            if(W[j] == Q[pos]){
                                if(pos==Q.length-1){
                                    end = j;
                                    distance = min(distance,end-begin);
                                }
                                pos++;
                            }
                        }
                    }
                }else{
                    continue;
                }
            }
        }
        System.out.println("begin:"+begin+":"+"end:"+end);
        System.out.println(distance);
        return null;
    }
    public static void main(String[] args){
        GenerateShortSummary gss = new GenerateShortSummary();
        gss.generateShortSummary();
    }
}

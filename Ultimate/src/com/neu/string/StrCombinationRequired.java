package com.neu.string;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 问题：如何按要求打印数组的排列情况
 *      针对1,2,2,3,4,5这六个数字，写一个函数，打印出所有不同的排列，
 *      要求‘4’不能再第三位，‘3’与‘5’不能相连
 * 打印数组的排列组合方式最简单的方法就是递归，但是本题有条件限制：①数组中存在重复数字②明确规定了某些位的特性
 * 因此，求解这六个数的排列组合问题可以转化为图的遍历问题。
 * Created by lihongyan on 2015/11/1.
 */
public class StrCombinationRequired {
    //方法：把1,2,2,3,4,5这六个点看做图的六个节点，对六个节点两两相连可以组成一个无向连通图，
    //     这六个数字对应的全排列等价于从这个图中各个节点出发，深度遍历这个图所有可能路径所组成的数字集合。
    //     例如，从节点1出发的所有遍历路径组成了以1开头的所有数字的组合。由于‘3’与‘5’不能相连，
    //     因此在构造图时使图中‘3’和‘5’对应的节点不连通就可以满足这个条件。
    //     对于‘4’不能再第三位，可以在遍历结束后判断是否满足这个条件
    //步骤：①用1,2,2,3,4,5这六个数字作为6个节点，构造一个无向连通图。除了‘3’与‘5’不能连通外，其他所有节点都两两相连
    //     ②分别从这6个节点出发对图做深度优先遍历。每次遍历完所有节点，把遍历的路径对应数字的组合记录下来，
    //       若这个数字的第三位不是‘4’，则把这个数字存放到set集合中(由于这6个数中有重复的数，因此最终的组合肯定也会有重复的。
    //       由于set集合的特点为集合中的元素是唯一的，不能有重复的元素，因此通过吧组合的结果放到set集合中可以过滤掉重复的组合)
    //     ③遍历set集合，打印出集合中的所有结果，这些结果就是本问题的答案
    private String combination = "";
    private int[] nodes;
    private boolean[] vistited;
    private int[][] graph;
    private int length;
    public StrCombinationRequired(int[] nodes){
        this.nodes = nodes;
        this.length = nodes.length;
        this.vistited = new boolean[length];
        this.graph = new int[length][length];

    }
    private void buildGraph(){
        for(int i=0;i<this.length;i++){
            for(int j=0;j<this.length;j++){
                if(i==j){
                    this.graph[i][j] = 0;
                }else{
                    this.graph[i][j] = 1;
                }
            }
        }
        this.graph[3][5] = 0;
        this.graph[5][3] = 0;
    }
    private void depthFirstSearch(int start,Set<String> set){
        vistited[start] = true;
        this.setCombination(this.getCombination()+nodes[start]);
        if(this.getCombination().length()==this.length){
            if(this.getCombination().indexOf("4")!=2){
                set.add(combination);
            }
        }
        for(int i=0;i<this.length;i++){
            if(this.graph[start][i]==1&&this.vistited[i]==false){
                depthFirstSearch(i,set);
            }
        }
        combination = combination.substring(0,this.getCombination().length()-1);
        vistited[start] = false;
    }
    public Set<String> getAllCombinations(){
        buildGraph();
        Set<String> set = new HashSet<String>();
        for(int i=0;i<this.length;i++){
            depthFirstSearch(i,set);
        }
        return set;
    }
    public static void main(String[] args){
        int[] nodes = {1,2,2,3,4,5};
        StrCombinationRequired scr = new StrCombinationRequired(nodes);
        Set<String> set = scr.getAllCombinations();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public String getCombination() {
        return combination;
    }
    public void setCombination(String combination) {
        this.combination = combination;
    }
}

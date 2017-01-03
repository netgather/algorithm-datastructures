package com.neu.struct.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 问题：最小生成树问题
 *
 * Kruskal算法和Prim算法都使用贪心策略来解决最小生成树问题，但是它们使用贪心策略的方式有所不同
 * Kruskal算法和Prim算法都使用一条具体的规则来确定GENERIC-MST(G,w)所描述的安全边
 * 贪心策略的通用表示：
 *   GENERIC-MST(G,w)
 *     A≠∅
 *     while A does not from a spanning tree
 *       find an edge(u,v) that is safe for A
 *       A = A ∪ {(u,v)}
 *     return A
 *该通用方法在每个时刻生成最小生成树的一条边，并在整个策略实施的过程中，
 *管理一个遵循循环不变式(在每遍循环之前，A是某棵最小生成树的子集)的边集合A
 *
 *
 * 定义：
 *   切割：无向图G=(V,E)的一个切割(S,V-S)是集合V的一个划分
 *   横跨：如果一条边的一个端点位于集合S，另一个端点位于集合V-S，则称该条边横跨切割。
 *   尊重：如果集合A中不存在横跨该切割的边，则称该切割尊重集合A
 *   轻量级边：在横跨一个切割的所有边中，权重最小的边称为轻量级边。轻量级边可能不是唯一的。
 *   满足性质的轻量级边：如果一条边是轻量级边，并且满足某个性质的边时，则称该边是满足给定性质的轻量级边。
 *
 * 定理：辨认安全边
 *   设G=(V,E)是一个在边集合E上定义了实数值权重函数w的连通无向图。设集合A为E的一个子集，
 *   且A包括在图G的某棵最小生成树中，设(S,V-S)是图G中尊重集合A的任意一个子集，
 *   又设(u,v)是横跨切割(S,V-S)的一条轻量级边，那么边(u,v)对于集合A是安全的。
 * 推论：设G=(V,E)是一个连通无向图，并有定义在边集合E上的实数值权重函数w。
 *      设集合A为E的一个子集，且该子集包括在G的某棵最小生成树里，
 *      并设C=(V(c),E(c))为森林G(A)=(V,A)中的一个连通分量(树)。
 *      如果边(u,v)是连接C和G(A)中某个其他连通分量的一条轻量级边，
 *      则边(u,v)对于集合A是安全的。
 *
 * Kruskal算法：
 *   概述：在Kruskal算法中，集合A是一个森林，其节点就是给定图的节点。
 *        每次加入到集合A中的安全边永远是权重最小的连接两个不同分量的边。
 *   具体：每个集合代表当前森林中的一棵树。操作find_set(u)用来返回包含元素u的集合的代表元素，
 *        可以通过测试find_set(u)是否等于find_set(v)来判断节点u和节点v是否属于同一棵树。
 *        使用union()操作来对两棵树进行合并。
 * Prim算法：
 *   描述：在Prim算法中，集合A是一棵树。每次加入到A中的安全边永远是连接A和A之外某个节点的边中权重最小的边
 * Created by lihongyan on 2015/12/11.
 */
public class MSTree<T extends Comparable<T>> implements ENV {

    private GraphAdj<T> graphAdj = new GraphAdj<T>();
    private Map<GraphAdj.Vertex<T>, ArrayList<GraphAdj.Edge<T>>> graph;
    private ArrayList<GraphAdj.Edge<T>> edgeColl;//边集
    private ArrayList<GraphAdj.Vertex<T>> vertexColl;//点集

    public void init(T[][] matrix){
        this.graph = graphAdj.buildUnDigraphWeight(matrix);
        this.vertexColl = graphAdj.getVertexColl();
        this.edgeColl = graphAdj.getEdgeColl();
    }

    protected static class KForest<T extends Comparable<T>> implements ENV {

        private ArrayList<ArrayList<GraphAdj.Vertex<T>>> forest = null;
        private ArrayList<GraphAdj.Vertex<T>> result = null;
        {
            forest = new ArrayList<ArrayList<GraphAdj.Vertex<T>>>();
            result = new ArrayList<GraphAdj.Vertex<T>>();
            forest.add(result);
        }
        public void make_set(GraphAdj.Vertex<T> vertex){
            ArrayList<GraphAdj.Vertex<T>> tree = new ArrayList<GraphAdj.Vertex<T>>();
            tree.add(vertex);
            this.forest.add(tree);
        }

        public int find_set(GraphAdj.Vertex<T> vertex){
            ArrayList<GraphAdj.Vertex<T>> tree = new ArrayList<GraphAdj.Vertex<T>>();
            tree.add(vertex);
            if(forest.contains(tree)){
                int index = forest.indexOf(tree);
                return index;
            }else{
                return -1;
            }
        }

        public void union(GraphAdj.Vertex<T> u,GraphAdj.Vertex<T> v){
            ArrayList<GraphAdj.Vertex<T>> u_tree = new ArrayList<GraphAdj.Vertex<T>>();
            ArrayList<GraphAdj.Vertex<T>> v_tree = new ArrayList<GraphAdj.Vertex<T>>();
            u_tree.add(u);v_tree.add(v);
            if(this.forest.contains(u_tree)){
                this.forest.remove(u_tree);
                result.add(u);
            }
            if(this.forest.contains(v_tree)){
                this.forest.remove(v_tree);
                result.add(v);
            }
        }

        public ArrayList<ArrayList<GraphAdj.Vertex<T>>> getForest() {
            return forest;
        }
        public ArrayList<GraphAdj.Vertex<T>> getResult() {
            return result;
        }
    }

    public void kruskal(){
        ArrayList<GraphAdj.Edge<T>> A = new ArrayList<GraphAdj.Edge<T>>();
        KForest<T> kf = new KForest<T>();
        Iterator<GraphAdj.Vertex<T>> vertexs = this.vertexColl.iterator();
        while(vertexs.hasNext()){
            GraphAdj.Vertex<T> vertex = vertexs.next();
            kf.make_set(vertex);
        }

        Iterator<GraphAdj.Edge<T>> edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            GraphAdj.Edge<T> edge = edges.next();
            GraphAdj.Vertex<T> u = edge.getU();
            GraphAdj.Vertex<T> v = edge.getV();
            //判断u,v是否属于同一棵树
            int u_index = kf.find_set(u);
            int v_index = kf.find_set(v);
            System.out.println(u_index + " " + v_index);
            if(u_index==v_index){
                continue;
            }else{
                if(u_index==-1){

                }else if(v_index==-1){

                }else{
                }
                kf.union(u,v);
                A.add(edge);
            }
        }
        System.out.println(kf.getForest().size());
        print(A);
    }

    private void print(ArrayList<GraphAdj.Edge<T>> A){
        Iterator<GraphAdj.Edge<T>> edges = A.iterator();
        while(edges.hasNext()){
            GraphAdj.Edge<T> edge = edges.next();
            GraphAdj.Vertex<T> u = edge.getU();
            GraphAdj.Vertex<T> v = edge.getV();
            System.out.println("(起始顶点:"+u.getData()+","+"终止顶点:"+v.getData()+")->");
        }
    }

    public void prim(){}

    public static void main(String[] args){
        Integer[][] matrix = {{0,4,0,0,0,0,0,8,0},
                              {4,0,8,0,0,0,0,11,0},
                              {0,8,0,7,0,4,0,0,2},
                              {0,0,7,0,9,14,0,0,0},
                              {0,0,0,9,0,10,0,0,0},
                              {0,0,4,14,10,0,2,0,0},
                              {0,0,0,0,0,2,0,1,6},
                              {8,11,0,0,0,0,1,0,7},
                              {0,0,2,0,0,0,6,7,0}};
        MSTree<Integer> mst = new MSTree<Integer>();
        mst.init(matrix);
        mst.kruskal();
    }
}

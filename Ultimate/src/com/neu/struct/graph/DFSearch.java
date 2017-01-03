package com.neu.struct.graph;

import java.util.*;

/**
 * 问题：深度优先搜索
 *
 *  只要可能，就在图中尽量“深入”。深度优先搜索总是对最近才发现的节点u的出发边进行搜索，
 *  直到该节点的所有出发边都被发现为止。一旦节点u的所有出发边都被发现，搜索则“回溯”到u的前驱节点，
 *  来搜索该前驱节点的出发边。该过程一直持续到从源节点可以达到的所有节点都被发现为止。如果还存在尚未发现的节点，
 *  则深度优先搜索将从这些未被发现的节点中任选一个作为新的源节点，并重复同样的过程。深度优先搜索重复整个过程，
 *  直到图中的所有节点都被发现为止。深度优先搜索的前驱子图形成一个由多棵深度优先树构成的深度优先森林。
 *
 *  除了创建一个深度优先搜索森林外，深度优先搜索算法还在每个节点盖上一个时间戳。
 *  每个节点u有两个时间戳：第一个时间戳u.d记录节点u第一次被发现的时间(涂上灰色的时候)；
 *  第二个时间戳u.f记录的是搜索完成对u的邻接链表扫描的时间(涂上黑色的时候)
 *  这些时间戳提供了图结构的重要信息，通常能够帮助推断深度优先搜索算法的行为
 *
 * 深度优先搜索的性质:
 *  1.深度优先搜索的结果可能以来于算法中对节点进行检查的次序和算法对每个节点的邻接节点的访问次序。
 *  2.u=v.precursor当且仅当DFS_VISIT()在算法对u的邻接链表进行探索时发现节点v。
 *  3.节点v是深度优先搜索森林里节点u的后代当且仅当节点v在节点u为灰色的时间段里被发现。
 *  4.节点的发现时间和完成时间具有所谓的括号化结构。
 *  5.如果两个时间区间存在重叠，则其中一个区间必定完全襄括在另一个区间的内部，
 *    而对应较小区间的节点是对应较大区间的节点的后代。
 *  6.在对有向图或无向图进行的任意深度的优先搜索中，对于任意两个节点u和v来说，下面三种情况只有一种成立
 *    a.区间[u.b,u.e]和[v.b,v.e]完全分离，在深度优先搜索森林中，节点u不是节点v的后代，节点v不是节点u的后代
 *    b.区间[u.b,u.e]完全包括[v.b,v.e]，在深度优先搜索树中，节点v是节点u的后代
 *    c.区间[v.b,v.e]完全包括[u.b,u.e]，在深度优先搜索树中，节点u是节点v的后代
 *  7.在深度优先搜索森林中，节点v是节点u的真后代当且仅当u.b<v.b<v.e<u.e成立
 *  8.在深度优先搜索森林中，节点v是节点u的后代当且仅当在发现节点u的时间u.b，
 *    存在一条从节点u到节点v的全部由白色节点所构成的路径。
 *  9.边的分类--可以通过搜索来对输入图的边进行分类，每条边的类型可以提供关于图的重要信息。
 *    树边：如果节点v是因为算法对边(u,v)的探索而首先被发现的，则(u,v)是一条树边
 *    后向边：后向边(u,v)是将节点u连接到其在深度优先搜索树中一个祖先节点v的边，
 *          只要其中一个节点不是另外一个节点的祖先，也可以连接不同深度优先搜索树中的两个节点。
 *    前向边：将节点u连接到其在深度优先搜索树中一个后代节点v的边(u,v)。
 *    横向边：指其他所有的边。这些边可以连接同一棵深度优先搜索树中的节点，只要其中一个节点不是另一个节点的祖先；
 *          也可以连接不同深度优先搜索树中的节点。
 *  10.通过节点v的颜色判断边(u,v)的信息
 *     a.节点v为白色时，表明(u,v)是一条树边
 *     b.节点v为灰色时，表明(u,v)是一条后向边
 *     c.节点v为黑色是，表明(u,v)是一条前向边或横向边
 *  11.在对无向图进行深度优先搜索时，每条边要么是树边，要么是后向边。
 * Created by lihongyan on 2015/12/10.
 */
public class DFSearch<T extends Comparable<T>> implements ENV {

    private GraphAdj<T> graphAdj = new GraphAdj<T>();
    private Map<GraphAdj.Vertex<T>, ArrayList<GraphAdj.Edge<T>>> graph;
    private ArrayList<GraphAdj.Edge<T>> edgeColl;//边集
    private ArrayList<GraphAdj.Vertex<T>> vertexColl;//点集
    private ArrayList<ArrayList<GraphAdj.Vertex<T>>> DFSForest = new ArrayList<ArrayList<GraphAdj.Vertex<T>>>();//深度优先搜索森林
    private int timestamp = 0;

    public void initDigraphUnweight(T[][] matrix){
        this.graph = graphAdj.buildDigraph(matrix);
        this.vertexColl = graphAdj.getVertexColl();
        this.edgeColl = graphAdj.getEdgeColl();
    }

    public void DFSDigraphUnweight(Integer source){
        Iterator<GraphAdj.Vertex<T>> vertexs = this.vertexColl.iterator();
        ArrayList<GraphAdj.Vertex<T>> Vtemp = new ArrayList<GraphAdj.Vertex<T>>();
        while(vertexs.hasNext()){
            GraphAdj.Vertex<T> u = vertexs.next();
            u.setColor(WHITE);
            u.setPrecursor(null);
            Vtemp.add(u);
        }
        this.vertexColl = Vtemp;

        vertexs = this.vertexColl.iterator();
        while(vertexs.hasNext()){
            ArrayList<GraphAdj.Vertex<T>> DFSTree = new ArrayList<GraphAdj.Vertex<T>>();//深度优先搜索树
            GraphAdj.Vertex<T> vertex = vertexs.next();
            print(vertex);
            if(vertex.getColor().equals(WHITE)){
                DFS_VISIT(vertex,DFSTree);
            }
        }
    }
    private void DFS_VISIT(GraphAdj.Vertex<T> u,ArrayList<GraphAdj.Vertex<T>> DFSTree){
        timestamp = timestamp + 1;
        u.setBegin(timestamp);
        u.setColor(GRAY);
        DFSTree.add(u);
        Iterator<GraphAdj.Edge<T>> vs = this.graph.get(u).iterator();
        while(vs.hasNext()){
            GraphAdj.Vertex<T> v = vs.next().getV();
            if(v.getColor().equals(WHITE)){
                v.setPrecursor(u);
                v.setColor(GRAY);
                DFS_VISIT(v,DFSTree);
            }
        }
        u.setColor(BLACK);
        timestamp = timestamp + 1;
        u.setEnd(timestamp);
        this.DFSForest.add(DFSTree);
    }

    private void print(GraphAdj.Vertex<T> vertex){
        System.out.print("顶点:" + vertex.getData() + "开始时间:" + vertex.getBegin() + "结束时间:" + vertex.getEnd() + "->");
    }

    public ArrayList<ArrayList<GraphAdj.Vertex<T>>> getDFSForest() {
        return DFSForest;
    }

    public static void main(String[] args){
        Integer[][] matrix = {{0,1,0,1,0,0},
                              {0,0,0,0,1,0},
                              {0,0,0,0,1,1},
                              {0,1,0,0,0,0},
                              {0,0,0,1,0,0},
                              {0,0,0,0,0,1}};
        DFSearch<Integer> dfs = new DFSearch<Integer>();
        dfs.initDigraphUnweight(matrix);
        dfs.DFSDigraphUnweight(3);
        dfs.getDFSForest();
    }
}

package com.neu.struct.graph;

import java.util.*;

/**
 * 问题：广度优先搜索（bsf）：
 *        假定输入图G=(V,E)是以邻接链表所表示的，bsf()为图中的每个节点赋予了一些额外的属性：
 *        将每个节点u的颜色存放在属性u.color里，将每个节点u的前驱节点存放在属性u.p里。
 *        如果节点u没有前驱节点(例如，如果u=s或者节点u尚未被发现)，则u.p=null。
 *        属性u.d记录的是广度优先搜索算法所计算出的从源节点s到节点u之间的距离。
 *        bsf()使用一个先进先出的队列Q来管理灰色节点。
 *
 *给定图G=(V,E)和一个可以识别的源节点s，广度优先搜索对图G中的边进行系统性地探索来发现可以从源节点s到达的所有节点。
 *该算法能够计算从源节点s到每个可到达的节点的距离(最少的边数)，同时生成一棵“广度优先搜索树”。该树以源节点s为根节点，
 *包含所有可以从s到达的节点。
 *对于每个从源节点s可以到达的节点v，在广度优先搜索树里从节点s到节点v的简单路径所对应的就是图G中从节点s到节点v的“最短路径”，
 *即包含最少边数的路径。广度优先搜索算法既可以用于无向图，也可以用于有向图。
 *
 *广度优先搜索算法始终是将已发现节点和未发现节点之间的边界，沿其广度方向向外扩展，即算法需要在发现所有距离源节点s为k的所有节点
 *之后，才会发现距离源节点s为k+1的其他节点。
 *
 *为了跟踪算法的进展，广度优先搜索在概念上将每个节点涂上白色、灰色或黑色。所有节点在一开始的时候均涂上白色。
 *在算法推进过程中，这些节点可能会变成灰色或者黑色。在搜索过程中，第一次遇到一个节点就称该节点被“发现”，
 *此时该节点的颜色将发生改变。因此，凡是灰色和黑色的节点都是已被发现的节点。但广度优先搜索对灰色和黑色加以区别，
 *以确保搜索按照广度优先模式进行推进：1.如果边(u,v)∈E且节点u是黑色的，则节点v即可能是灰色的也可能是黑色的。
 *也就是说，所有与黑色节点邻接的节点都已被发现；2.对于灰色节点来说，其邻接节点中可能存在未被发现的白色节点。
 *灰色节点所代表的就是已知和未知两个集合之间的边界。
 *
 *在执行广度优先搜索的过程中将构造出一棵广度优先搜索树。一开始，该树仅含有根节点，也就是源节点s。
 *在扫描已发现节点u的邻接链表时，每当发现一个白色节点v，就将节点v和边(u,v)同时加入该树中，
 *在广度优先搜索树中，称节点u是节点v的前驱或者父节点。由于每个节点最多被发现一次，它最多只有一个父节点。
 *
 *在广度优先搜索树中，祖先和后代的关系皆以相对于根节点s的相对位置来定义：
 *如果节点u是从根节点s到节点v的简单路径上的一个节点，则节点u是节点v的祖先，节点v是节点u的后代。
 *
 *
 *
 * 定义：从节点s到节点v的最短路径距离为：从节点s到节点v之间所有路劲里面最少的边数。如果从节点s到节点v之间没有路径，
 *      则最短路径距离为无穷大。
 *      最短路径：从节点s到节点v的长度为最短路径距离的路径
 * 引理1：给定G=(V,E)，G为一个有向图或无向图，设
 * 证明：广度优先搜索算法能够正确计算出最短路径距离
 *
 * Created by lihongyan on 2015/11/29.
 */
public class BFSearch<T extends Comparable<T>> implements ENV{


    private GraphAdj<T> graphAdj = new GraphAdj<T>();
    private Map<GraphAdj.Vertex<T>, ArrayList<GraphAdj.Edge<T>>> graph;
    private ArrayList<GraphAdj.Edge<T>> edgeColl;//边集
    private ArrayList<GraphAdj.Vertex<T>> vertexColl;//点集
    private Queue<GraphAdj.Vertex<T>> box = new LinkedList<GraphAdj.Vertex<T>>();
    private LinkedList<GraphAdj.Vertex<T>> BFSTree = new LinkedList<GraphAdj.Vertex<T>>();//广度优先搜索树

    //初始化：有向无权图、边集、点集
    public void initDigraphUnweight(T[][] matrix){
        this.graph = graphAdj.buildDigraph(matrix);
        this.vertexColl = graphAdj.getVertexColl();
        this.edgeColl = graphAdj.getEdgeColl();
    }
    //广度优先搜索--有向无权图
    public void BFSDigraphUnweight(Integer source){
        Iterator<GraphAdj.Vertex<T>> vertexs = this.vertexColl.iterator();
        ArrayList<GraphAdj.Vertex<T>> Vtemp = new ArrayList<GraphAdj.Vertex<T>>();
        while(vertexs.hasNext()){
            GraphAdj.Vertex<T> u = vertexs.next();
            if(u.getData().compareTo((T)source)!=0){
                u.setColor(WHITE);
                u.setDistance(INFINITY);
                u.setPrecursor(null);
                Vtemp.add(u);
            }else{
                u.setDistance(0);
                u.setColor(GRAY);
                u.setPrecursor(null);
                this.box.add(u);
            }
        }
        this.vertexColl = Vtemp;

        //循环不变式：队列中保存的始终是灰色的节点，即该节点被发现，但是其邻接节点尚未被完全发现。
        //证明循环不变式在循环过程中的不变性：
        //①当一个节点被涂上灰色时被加入到队列中
        //②当一个节点被弹出队列时被涂上黑色
        while(!this.box.isEmpty()){
            GraphAdj.Vertex<T> u = this.box.poll();
            print(u);
            BFSTree.add(u);
            Iterator<GraphAdj.Edge<T>> vs = this.graph.get(u).iterator();
            while(vs.hasNext()){
                GraphAdj.Vertex<T> v = vs.next().getV();
                if(v.getColor().equals(WHITE)){
                    v.setColor(GRAY);
                    v.setDistance(u.getDistance()+1);
                    v.setPrecursor(u);
                    this.box.add(v);
                }
            }
            u.setColor(BLACK);
        }
    }

    private void print(GraphAdj.Vertex<T> vertex){
        System.out.print("顶点:" + vertex.getData() +" 距离:"+vertex.getDistance()+ "->");
    }

    public LinkedList<GraphAdj.Vertex<T>> getBFSTree() {
        return BFSTree;
    }

    public static void main(String[] args){
        Integer[][] matrix = {{0,1,0,1,0,0,0},
                              {0,0,0,1,1,0,0},
                              {1,0,0,0,0,1,0},
                              {0,0,1,0,1,1,1},
                              {0,0,0,0,0,0,1},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,1,0}};
        BFSearch<Integer> bfs = new BFSearch<Integer>();
        bfs.initDigraphUnweight(matrix);
        bfs.BFSDigraphUnweight(3);//对有向无权图进行广度优先搜索
        bfs.getBFSTree();
    }
}

package com.neu.struct.graph;

import java.util.*;

/**
 *     拓扑排序
 *
 * 定义：拓扑排序是对有向无圈图的顶点的一种排序，使得如果存在一条从v(i)到v(j)的路径，
 *      那么在排序中v(j)就出现在v(i)的后面。如果图含有圈，那么拓扑排序是不可能的。
 *      此外，拓扑排序不必是唯一的，任何合理的排序都是可以的。
 * 问题：拓扑排序的算法
 *      ①先找出任意一个没有入边的顶点
 *      ②显示出该顶点，并将它及其边从图中一起删除
 *      ③对图中其余的部分同样应用这样的方法处理
 * Created by lihongyan on 2015/12/1.
 */
public class TopologySort<T extends Comparable<T>> {

    private GraphAdj<T> graphAdj = new GraphAdj<T>();
    private Map<GraphAdj.Vertex<T>, ArrayList<GraphAdj.Edge<T>>> graph;
    private ArrayList<GraphAdj.Edge<T>> edgeColl;//边集
    private ArrayList<GraphAdj.Vertex<T>> vertexColl;//点集
    private Queue<GraphAdj.Vertex<T>> box = new LinkedList<GraphAdj.Vertex<T>>();//盒子

    public void topology(T[][] martix){
        //初始成员变量
        this.graph = graphAdj.buildDigraph(martix);
        this.vertexColl = graphAdj.getVertexColl();
        this.edgeColl = graphAdj.getEdgeColl();
        //初始盒子
        initBox();
        //拓扑排序
        sort();
    }
    private void sort(){
        if(!this.box.isEmpty()){
            GraphAdj.Vertex<T> vertex = this.box.poll();
            print(vertex);
            shuffle(vertex);
            sort();
        }else{
            return;
        }
    }
    private void shuffle(GraphAdj.Vertex<T> vertex){
        ArrayList<GraphAdj.Vertex<T>> changes = new ArrayList<GraphAdj.Vertex<T>>();//存储删除一个顶点之后，受到影响的那些顶点
        Iterator<GraphAdj.Edge<T>> edges = this.edgeColl.iterator();
        ArrayList<GraphAdj.Edge<T>> edgeCollTemp = new ArrayList<GraphAdj.Edge<T>>();
        while(edges.hasNext()){
            edgeCollTemp.add(edges.next());
        }
        Iterator<GraphAdj.Edge<T>> edgesTemp = edgeCollTemp.iterator();
        while(edgesTemp.hasNext()){
            GraphAdj.Edge<T> edge = edgesTemp.next();
            GraphAdj.Vertex<T> u = edge.getU();
            if(vertex.equals(u)){
                changes.add(edge.getV());
                this.edgeColl.remove(edge);
            }
        }
        Iterator<GraphAdj.Vertex<T>> its = changes.iterator();
        while(its.hasNext()){
            GraphAdj.Vertex<T> v = its.next();
            v.setIndegree(v.getIndegree() - 1);
            if(v.getIndegree()==0){
                this.box.add(v);
            }else{
                if(this.vertexColl.contains(v)){
                    this.vertexColl.remove(v);
                    this.vertexColl.add(v);
                }else{
                    this.vertexColl.add(v);
                }
            }
        }
        edgeCollTemp.clear();
        edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            edgeCollTemp.add(edges.next());
        }
        edgesTemp = edgeCollTemp.iterator();
        while(edgesTemp.hasNext()){
            GraphAdj.Edge<T> edge = edgesTemp.next();
            GraphAdj.Vertex<T> u = edge.getU();
            GraphAdj.Vertex<T> v = edge.getV();
            if(this.vertexColl.contains(u)){
                this.edgeColl.remove(edge);
                int index = this.vertexColl.indexOf(u);
                GraphAdj.Vertex<T> newU = this.vertexColl.get(index);
                GraphAdj.Edge<T> newEdge = new GraphAdj.Edge<T>(newU,v);
                this.edgeColl.add(newEdge);
            }else{

            }
        }
    }
    private void initBox(){
        Iterator<GraphAdj.Vertex<T>> vertexs = this.vertexColl.iterator();
        ArrayList<GraphAdj.Vertex<T>> temps = new ArrayList<GraphAdj.Vertex<T>>();
        while(vertexs.hasNext()){
            GraphAdj.Vertex<T> vertex = vertexs.next();
            if(vertex.getIndegree()==0){
                box.add(vertex);
            }else{
                temps.add(vertex);
            }
        }
        this.vertexColl = temps;
    }
    private void print(GraphAdj.Vertex<T> vertex){
        System.out.print(vertex.getData()+"->");
    }
    public static void main(String[] args){
        Integer[][] matrix = {{0,1,1,1,0,0,0},
                              {0,0,0,1,1,0,0},
                              {0,0,0,0,0,1,0},
                              {0,0,1,0,0,1,1},
                              {0,0,0,1,0,0,1},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,1,0}};
        TopologySort<Integer> topologySort = new TopologySort<Integer>();
        topologySort.topology(matrix);
    }
}

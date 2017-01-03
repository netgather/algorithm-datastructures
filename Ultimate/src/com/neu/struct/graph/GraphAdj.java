package com.neu.struct.graph;

import java.util.*;

/**
 * 问题：如何使用邻接表保存图
 *      在图论算法中，通常需要找出与某个给定顶点u邻接的所有的顶点。
 *      首先，邻接表本身可以保存在任何种类的List中，即ArrayList或LinkedList。然而，对于非常稀疏的图，
 *           当使用ArrayList时，可能需要从一个比默认容量更小的容量开始ArrayList。
 *      其次，设计的关键是：能够迅速得到与任意顶点邻接的那些顶点。所以有两种方法设计Adj[]，
 *           ①使用一个映射，在这个映射下，关键字就是那些顶点，而他们的值就是那些邻接表。
 *           ②把每一个邻接表作为Vertex类的数据成员保存起来。例如，假设顶点是一个String，
 *             那么可以使用映射，在映射下，关键字是顶点名而关键字的值则是一个Vertex，
 *             并且每一个Vertex对象都拥有一个邻接顶点表List。
 *             这种方式会更快一些，因为它避免了在映射下的重复查找
 *      最后，如果图是一个权重图时，我们可以直接将边(u,v)∈E的权重值w(u,v)存放在节点u的邻接链表里。
 *           并且，该权重值通常由一个型为w:E→R的权重函数给出，即输入一条边，由权重函数计算出该边的权重值。
 *
 * 1.对于图G=(V,E)来说，其邻接链表表示法是一个由包含|V|条链表的数组A所组成，每个节点有一条链表。
 *   对于每个节点u∈V，临界链表A[u]包含所以与节点u之间有边相连的节点v，即A[u]包含图G中所有
 *   与u邻接的节点。 邻接链表代表的是图的边。
 *
 * 2.如果G是一个有向图，则对于边(u,v)来说，节点v将出现在链表A[u]里，因此，所有邻接链表的长度之和等图|E|。
 *   如果G是一个无向图，则对于边(u,v)来说，节点v将出现在链表A[u]里，节点u将出现在链表A[v]里，因此，所有
 *   邻接链表的长度之和等于2|E|
 *
 * 3.不管是有向图还是无向图，邻接表表示法的存储空间需求均为O(V+E)
 *
 * 4.邻接链表的一个缺点是无法快速判断一条边(u,v)是否图中的一条边，唯一的办法是在邻接链表A[u]里面搜索节点v。
 *   邻接举证表示法克服了这个缺点，但付出的代价是更大的存储空间消耗。
 *
 * Created by lihongyan on 2015/12/1.
 */
public class GraphAdj<T extends Comparable<T>> extends ArrayList{

    private ArrayList<Edge<T>> edgeColl = new ArrayList<Edge<T>>();//边集
    private ArrayList<Vertex<T>> vertexColl = new ArrayList<Vertex<T>>();//点集
    private Map<Vertex<T>,ArrayList<Edge<T>>> graph = new HashMap<Vertex<T>, ArrayList<Edge<T>>>();//图
    public ArrayList<Edge<T>> getEdgeColl() {
        return edgeColl;
    }
    public ArrayList<Vertex<T>> getVertexColl() {
        return vertexColl;
    }
    public Map<Vertex<T>, ArrayList<Edge<T>>> getGraph() {
        return graph;
    }

    private ArrayList<Edge<T>> borrow_edge = new ArrayList<Edge<T>>();//边借用集合
    private ArrayList<Vertex<T>> borrow_vertex = new ArrayList<Vertex<T>>();//点借用集合

    protected static class Vertex<T extends Comparable<T>>{
        private T data;
        private String color;
        private Vertex precursor;
        private int distance;
        private int indegree;
        private int outdegree;
        private int begin;
        private int end;
        private ArrayList<Vertex<T>> vertexs = new ArrayList<Vertex<T>>();
        private ArrayList<Edge<T>>  edges = new ArrayList<Edge<T>>();
        Vertex(){}
        Vertex(T data){
            this.data = data;
            this.color = "white";
            this.precursor = null;
            this.distance = 0;
            this.begin = 0;
            this.end = 0;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public Vertex getPrecursor() {
            return precursor;
        }
        public void setPrecursor(Vertex precursor) {
            this.precursor = precursor;
        }
        public int getDistance() {
            return distance;
        }
        public void setDistance(int distance) {
            this.distance = distance;
        }
        public int getIndegree() {
            return indegree;
        }
        public void setIndegree(int indegree) {
            this.indegree = indegree;
        }
        public int getOutdegree() {
            return outdegree;
        }
        public void setOutdegree(int outdegree) {
            this.outdegree = outdegree;
        }
        public ArrayList<Vertex<T>> getVertexs() {
            return vertexs;
        }
        public void setVertexs(ArrayList<Vertex<T>> vertexs) {
            this.vertexs = vertexs;
        }
        public ArrayList<Edge<T>> getEdges() {
            return edges;
        }
        public void setEdges(ArrayList<Edge<T>> edges) {
            this.edges = edges;
        }
        public int getBegin() {
            return begin;
        }
        public void setBegin(int begin) {
            this.begin = begin;
        }
        public int getEnd() {
            return end;
        }
        public void setEnd(int end) {
            this.end = end;
        }


        public boolean equals(Vertex<T> obj) {
            Vertex<T> temp = obj;
            if(this.getData().compareTo(temp.getData())==0){
                return true;
            }else{
                return false;
            }
        }
    }
    protected static class Edge<T extends Comparable<T>>{
        private Vertex u;
        private Vertex v;
        private int weight;
        Edge(){
            this.u = null;
            this.v = null;
            this.weight = 1;
        }
        Edge(int weight){
            this.u = null;
            this.v = null;
            this.weight = weight;
        }
        Edge(Vertex<T> u,Vertex<T> v){
            this.u = u;
            this.v = v;
            this.weight = 1;
        }
        Edge(Vertex<T> u,Vertex<T> v,int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public Vertex getU() {
            return u;
        }
        public void setU(Vertex u) {
            this.u = u;
        }
        public Vertex getV() {
            return v;
        }
        public void setV(Vertex v) {
            this.v = v;
        }
        public int getWeight() {
            return weight;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<T> other = (Edge<T>)obj;
            if(this.getU().equals(other.getV())&&this.getV().equals(other.getU())){
                return true;
            }else{
                return false;
            }
        }
    }

    //有向无权图
    public Map<Vertex<T>,ArrayList<Edge<T>>> buildDigraph(T[][] martix){
        for(Integer row=0;row<martix.length;row++){
            Vertex<T> u = new Vertex<T>((T)(Integer)(row+1));
            ArrayList<Vertex<T>> vs = new ArrayList<Vertex<T>>();
            for(Integer col=0;col<martix[row].length;col++){
                Vertex<T> v = new Vertex<T>((T)(Integer)(col+1));
                if(martix[row][col].compareTo((T)(Integer)(1))==0){
                    u.setOutdegree(u.getOutdegree() + 1);
                    v.setIndegree(v.getOutdegree() + 1);
                    vs.add(v);
                }
                if(martix[col][row].compareTo((T)(Integer)(1))==0){
                    u.setIndegree(u.getIndegree()+1);
                    v.setOutdegree(v.getIndegree()+1);
                }
            }
            u.setVertexs(vs);
            this.vertexColl.add(u);
        }
        initVertexColl();
        initEdgeColl();
        return this.graph;
    }

    private void initVertexColl(){
        ArrayList<Vertex<T>> vertexCollTemp  = new ArrayList<Vertex<T>>();
        Iterator<Vertex<T>> us = this.vertexColl.iterator();
        while(us.hasNext()){
            Vertex<T> vertex = us.next();
            vertexCollTemp.add(vertex);
        }
        us = this.vertexColl.iterator();
        ArrayList<Vertex<T>> result = new ArrayList<Vertex<T>>();
        while(us.hasNext()){
            Vertex<T> u = us.next();
            Iterator<Vertex<T>> vs = u.getVertexs().iterator();
            ArrayList<Vertex<T>> temps = new ArrayList<Vertex<T>>();
            while(vs.hasNext()){
               Vertex<T> v = vs.next();
                if(vertexCollTemp.contains(v)){
                    int index = vertexCollTemp.indexOf(v);
                    Vertex<T> temp = vertexCollTemp.get(index);
                    temps.add(temp);
                }
            }
            u.setVertexs(temps);
            result.add(u);
        }
        this.vertexColl = result;
    }

    private void initEdgeColl(){
        Iterator<Vertex<T>> us = this.vertexColl.iterator();
        while(us.hasNext()){
            Vertex<T> u = us.next();
            Iterator<Vertex<T>> vs = u.getVertexs().iterator();
            ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();
            while(vs.hasNext()){
                Vertex<T> v = vs.next();
                Edge<T> edge = new Edge<T>(u,v);
                this.edgeColl.add(edge);
                edges.add(edge);
            }
            this.graph.put(u, edges);
        }
    }

    //有向赋权图
    public Map<Vertex<T>,ArrayList<Edge<T>>> buildUnDigraphWeight(T[][] martix){
        for(Integer row=0;row<martix.length;row++){
            Vertex<T> u = new Vertex<T>((T)(Integer)(row+1));
            ArrayList<Edge<T>> vs = new ArrayList<Edge<T>>();
            for(Integer col=0;col<martix[row].length;col++){
                Vertex<T> v = new Vertex<T>((T)(Integer)(col+1));
                if(martix[row][col].compareTo((T)(Integer)0)!=0){
                    Edge<T> edge = new Edge<T>(u,v,weight(martix[row][col]));
                    vs.add(edge);
                    this.edgeColl.add(edge);
                }
            }
            u.setEdges(vs);
            this.vertexColl.add(u);
            this.graph.put(u,vs);
        }
        initUndigraphEdgeColl();
        removeEnd();
        return this.graph;
    }

    private void initUndigraphEdgeColl(){
        TreeSet<Edge<T>> set = new TreeSet<Edge<T>>(new Comparator<Edge<T>>(){
            @Override
            public int compare(Edge<T> o1, Edge<T> o2) {
                if(o1.getWeight()>o2.getWeight()){
                    return 1;
                }else if(o1.getWeight()<o2.getWeight()){
                    return -1;
                }else{
                    if(o1.equals(o2)){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            }
        });

        //去除边集合中的重复边
        Iterator<Edge<T>> edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            Edge<T> edge = edges.next();
            if(isContains(edge)){
                edges.remove();
            }
        }

        //将边集合中的边按权值由大到小的顺序排列
        edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            Edge<T> edge = edges.next();
            set.add(edge);
        }

        //重新初始化边集合
        this.edgeColl.clear();
        Iterator<Edge<T>> sets = set.iterator();
        while(sets.hasNext()){
            Edge<T> edge = sets.next();
            this.edgeColl.add(edge);
        }
    }

    private void removeEnd(){
        this.borrow_edge.clear();
        Iterator<Edge<T>> edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            Iterator<Vertex<T>> vertexs = this.vertexColl.iterator();
            Edge<T> edge = edges.next();
            int w = edge.getWeight();
            Vertex<T> u = edge.getU();
            Vertex<T> false_v = edge.getV();
            while(vertexs.hasNext()){
                Vertex<T> true_v = vertexs.next();
                if(false_v.getData().compareTo(true_v.getData())==0){
                    Edge<T> true_edge = new Edge<T>(u,true_v,w);
                    this.borrow_edge.add(true_edge);
                }
            }
        }
        this.edgeColl = this.borrow_edge;
    }

    private boolean isContains(Edge<T> target){
        Iterator<Edge<T>> edges = this.edgeColl.iterator();
        while(edges.hasNext()){
            Edge<T> edge = edges.next();
            if(edge.equals(target)){
                return true;
            }
        }
        return false;
    }

    protected int weight(T weight){
        return (Integer)weight;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<Vertex<T>> us = this.iterator();
        while(us.hasNext()){
            Vertex<T> u = us.next();
            if(u.equals(o)){
                return true;
            }
        }
        return false;
    }

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
        GraphAdj<Integer> graphAdj = new GraphAdj<Integer>();
        Map<Vertex<Integer>,ArrayList<Edge<Integer>>> graph = graphAdj.buildUnDigraphWeight(matrix);
        Iterator<Edge<Integer>> edges = graphAdj.getEdgeColl().iterator();
        while(edges.hasNext()){
            Edge<Integer> edge = edges.next();
            System.out.println("起始顶点:"+edge.getU().getData()+" 终止顶点:"+edge.getV().getData()+" 权值:"+edge.getWeight());
        }
        System.out.print(graphAdj.getEdgeColl().size());
    }
}

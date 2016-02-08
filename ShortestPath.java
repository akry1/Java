import java.util.*;
import org.jgrapht.util.*;

/**
 * Created by aravind on 2/4/2016.
 */
public class ShortestPath {
    public static void main(String []args){
        Graph_AdjacencyList g = new Graph_AdjacencyList(0);
        //Graph g = new Graph(0);
        //Graph_AdjacencyMatrix g= new Graph_AdjacencyMatrix(0);
        g.addNode(1);
        g.addNode(2);
        g.addEdge(0,2,3.0);
        g.addEdge(1,2,5.0);
        g.displayGraph();

        Double[] distances = DijkstraWithFibonacciHeap(g,0);
        for( double i: distances)
            System.out.println(i);
    }

    //With custom implementation
    public static Double[] Dijkstra(Graph_AdjacencyList g, int source){
        int lenghtOfGraphh = g.nodes.size();
        Double[] dist = new Double[lenghtOfGraphh];
        int[] prev = new int[lenghtOfGraphh];
        dist[source] = 0.0;

        PriorityQueue Q = new PriorityQueue();

        for (int i:g.nodes ) {
            if(i!=source)
                dist[i] = Double.POSITIVE_INFINITY;

            Q.insertWithPriority(i, dist[i]);
        }

        while(!Q.isEmpty()){
            int bestNode = Q.extractMin();
            for (int i:g.edges.get(bestNode) ) {
                Double currentDist = g.weights.get(bestNode).get(i);
                    Double newDist = dist[bestNode]+ currentDist;
                    if(newDist<dist[i]){
                        dist[i] = newDist;
                        prev[i] = bestNode;
                        Q.decreasePriority(i, newDist);
                }
            }
        }

        return dist;
    }


    public static Double[] DijkstraWithFibonacciHeap(Graph_AdjacencyList g, int source){
        int lenghtOfGraphh = g.nodes.size();
        Double[] dist = new Double[lenghtOfGraphh];
        int[] prev = new int[lenghtOfGraphh];
        dist[source] = 0.0;

        FibonacciHeap<Integer> Q = new FibonacciHeap<Integer>();
        FibonacciHeapNode[] heapNodes = new FibonacciHeapNode[lenghtOfGraphh];

        for (int i:g.nodes ) {
            if(i!=source)
                dist[i] = Double.POSITIVE_INFINITY;
            heapNodes[i] = new FibonacciHeapNode(i);
            Q.insert(heapNodes[i], dist[i]);
        }

        while(!Q.isEmpty()){
            FibonacciHeapNode<Integer> bestNode = Q.removeMin();
            int current = bestNode.getData();
            int index=0;
            for (int i:g.edges.get(current) ) {
                Double currentDist = g.weights.get(current).get(index);
                Double newDist = dist[current]+ currentDist;
                if(newDist<dist[i]){
                    dist[i] = newDist;
                    prev[i] = current;
                    Q.decreaseKey(heapNodes[i], newDist);
                }
                index++;
            }
        }

        return dist;
    }
}

class Graph1{
    ArrayList<Integer> nodes = new ArrayList<Integer>();
    HashMap<String,Float> edges = new HashMap<String, Float>();

    Graph1(int root){
        nodes.add(root);
    }

    public void addNode(int val){
        nodes.add(val);
    }

    public String mapToString(int start,int dest){
        return Integer.toString(start) + ":" + Integer.toString(dest);
    }

    public int[] mapToInt(String key){
        int[] result = new int[2];
        int j=0;
        for (String i:key.split(":"))
            result[j++] = Integer.valueOf(i);
        return result;
    }

    public void addEdge(int start, int dest, float distance){
        if(nodes.contains(start) && nodes.contains(dest)){
            edges.put(mapToString(start,dest),distance);
        }
        else{
            System.out.println("Cannot add path. One of the node not present!!");
        }
    }

    public void displayGraph(){
        for(Map.Entry<String,Float> i : edges.entrySet())
            System.out.println(i.getKey()+":"+ i.getValue());
    }

    public Float getDistance(int start, int dest){
        String edge = mapToString(start,dest);
        Float dist = edges.get(edge);
        if(dist==null) {
            System.out.println("Path not found");
            return null;
        }
        else
            return dist;
    }

}


class Graph_AdjacencyList{
    ArrayList<Integer> nodes = new ArrayList<Integer>();
    HashMap<Integer,List<Integer>> edges = new HashMap<Integer, List<Integer>>();
    HashMap<Integer,List<Double>> weights = new HashMap<Integer, List<Double>>();

    Graph_AdjacencyList(int root){
        nodes.add(root);
        edges.put(root,new ArrayList<Integer>());
        weights.put(root,new ArrayList<Double>());
    }

    public void addNode(int val){
        nodes.add(val);
        edges.put(val,new ArrayList<Integer>());
        weights.put(val,new ArrayList<Double>());
    }

    public void addEdge(int start, int dest, Double distance){
        if(nodes.contains(start) && nodes.contains(dest)){
            edges.get(start).add(dest);
            weights.get(start).add(distance);
        }
        else{
            System.out.println("Cannot add path. One of the node not present!!");
        }
    }

    public void displayGraph(){
        for(Map.Entry<Integer,List<Integer>> i : edges.entrySet())
            System.out.println(i.getKey()+":"+ i.getValue());
    }

    public Double getDistance(int start, int dest){
        //String edge = mapToString(start,dest);
        int index = edges.get(start).indexOf(dest);
        if(index==-1){
            System.out.println("Path not found");
            return 0.0;
        }
        return weights.get(start).get(index);
    }

}


class Graph_AdjacencyMatrix{
    ArrayList<Integer> nodes = new ArrayList<Integer>();
    ArrayList<ArrayList<Float>> edges = new ArrayList<ArrayList<Float>>();

    Graph_AdjacencyMatrix(int root){
        nodes.add(root);
        edges.add(new ArrayList<Float>());
    }

    public void addNode(int val){
        nodes.add(val);
        edges.add(new ArrayList<Float>());
    }

    public void addEdge(int start, int dest, float distance){
        if(nodes.contains(start) && nodes.contains(dest)){
            int size = edges.get(start).size();
            for(int i=size-1;i< dest;i++) {
                edges.get(start).add(Float.valueOf(0));
            }
            edges.get(start).set(dest,distance);
        }
        else{
            System.out.println("Cannot add path. One of the node not present!!");
        }
    }

    public void displayGraph(){
        for(ArrayList<Float> i : edges)
            System.out.println(i);
    }

    public Float getDistance(int start, int dest){
        Float dist = edges.get(start).get(dest);
        if(dist==null) {
            System.out.println("Path not found");
            return null;
        }
        else
            return dist;
    }

}


class PriorityQueue{

    public void insertWithPriority(int vertex, Double distance){

    }

    public int extractMin(){
        return 1;
    }

    public void decreasePriority(int vertex, Double distance){

    }

    public Boolean isEmpty(){
        return true;
    }
}
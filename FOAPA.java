import java.util.*;
import org.jgrapht.util.*;

public class FOAPA {

    public static Double[] DijkstraWithFibonacciHeap(Graph g, int source, boolean isReverse){
        int lenghtOfGraphh = g.nodes.size();
        Double[] dist = new Double[lenghtOfGraphh];
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
            ArrayList<Integer> edges;
            if (isReverse)
                edges = g.reverseEdges.get(current);
            else
                edges = g.edges.get(current);
            for (int i: edges ) {
                Double currentDist;
                if(isReverse)
                    currentDist = g.reverseWeights.get(current).get(index);
                else
                    currentDist = g.weights.get(current).get(index);
                Double newDist = dist[current]+ currentDist;
                if(newDist<dist[i]){
                    dist[i] = newDist;
                    Q.decreaseKey(heapNodes[i], newDist);
                }
                index++;
            }
        }

        return dist;
    }

    public static void main(String []args){
        Graph g = new Graph(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addEdge(0,2,20.0);
        g.addEdge(0,1,10.0);
        g.addEdge(1,3,20.0);
        g.addEdge(2,3,10.0);
        //g.addEdge(1,2,5.0);
        g.displayGraph();

        g.displayReverseGraph();

        int start = 0,end =3;

        Double[] distances = DijkstraWithFibonacciHeap(g,start, false);
        Double[] distancesFromEnd = DijkstraWithFibonacciHeap(g,end, true);

        for( double i: distances)
            System.out.println(i);

        int k=2;
        for(int i=0;i<k;i++){
            int newRoadStart = 1;
            int newRoadEnd = 2;
            Double newDist = 5.0;

            Double newShortestDist = distances[newRoadStart] + newDist + distancesFromEnd[newRoadEnd];

            if(newShortestDist<distances[end]){
                System.out.println("Old Distance: "+ distances[end]);
                System.out.println("New Distance: "+ newShortestDist);
            }

        }
    }
}

class Graph{
    ArrayList<Integer> nodes = new ArrayList<Integer>();
    HashMap<Integer,ArrayList<Integer>> edges = new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer,ArrayList<Double>> weights = new HashMap<Integer, ArrayList<Double>>();

    HashMap<Integer,ArrayList<Integer>> reverseEdges = new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer,ArrayList<Double>> reverseWeights = new HashMap<Integer, ArrayList<Double>>();

    Graph(int root){
       add(root);
    }

    public void addNode(int val){
        add(val);
    }

    void add(int val){
        nodes.add(val);
        edges.put(val,new ArrayList<Integer>());
        weights.put(val,new ArrayList<Double>());

        reverseEdges.put(val,new ArrayList<Integer>());
        reverseWeights.put(val,new ArrayList<Double>());
    }

    public void addEdge(int start, int dest, Double distance){
        if(nodes.contains(start) && nodes.contains(dest)){
            edges.get(start).add(dest);
            weights.get(start).add(distance);

            reverseEdges.get(dest).add(start);
            reverseWeights.get(dest).add(distance);
        }
        else{
            System.out.println("Cannot add path. One of the node not present!!");
        }
    }

    void display(HashMap<Integer,ArrayList<Integer>> e){
        for(Map.Entry<Integer,ArrayList<Integer>> i : e.entrySet())
            System.out.println(i.getKey()+":"+ i.getValue());
    }
    public void displayGraph(){
        display(edges);
    }

    public void displayReverseGraph(){
        display(reverseEdges);
    }

    public Double getDistance(int start, int dest){
        int index = edges.get(start).indexOf(dest);
        if(index==-1){
            System.out.println("Path not found");
            return 0.0;
        }
        return weights.get(start).get(index);
    }

}

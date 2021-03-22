package utils;
import java.util.*;

public class Graph {

    public Map<Stop, Node> nodes;
    private NodeMatrix nodeMatrix;

    public Graph() {
        nodes = new HashMap<>();
        nodeMatrix = new NodeMatrix();
    }

    public Graph(List<Stop> Stops) {
        nodeMatrix = new NodeMatrix(Stops);
        this.nodes = new HashMap<>();
        for(Stop stop : Stops) {
            addNode(stop);
        }
    }

    public void makeEdges(){
        NodeSet[][] nodeSetMatrix = nodeMatrix.getMatrix();
        int matrixSize = nodeSetMatrix.length;
        for(int row = 0; row < matrixSize; row++) {
            for(int col = 0; col < matrixSize; col++) {
                if(nodeSetMatrix[row][col] == null) continue;
                connectNodes(row, col);
            }
        }
    }

    public static double calculateWeight(double distance, FormOfTransport transport) { return distance/ transport.getSPEED(); }

    public void addNode(Stop stop) {
        nodeMatrix.insertBusStop(stop);
        nodes.putIfAbsent(stop, new Node(stop));
    }

    public void addEdge(Node fromNode, Node toNode, double weight, FormOfTransport transport) {
        if (fromNode == null || toNode == null) return;
        fromNode.getEdges().add(new Edge(weight, toNode, transport));
    }

    private void connectNodes(int row, int col) {
        NodeSet[][] nodeSet = nodeMatrix.getMatrix();
        connectSets(nodeSet[row][col], nodeSet[row][col]);
        boolean connecting = true;

        int delta = 1;
        while(connecting) {
            List<Integer> rowList = new ArrayList<>();
            rowList.add(delta);
            rowList.add(-delta);
            for(Integer delta_row : rowList) {
                if(row + delta_row >= 0 && row + delta_row < 100) {
                    for(int i = -delta; i < delta + 1; i++) {
                        if(col + i >= 0 && col + i < 100 && nodeSet[row+delta_row][col+i] != null) {
                            connecting = connectSets(nodeSet[row][col], nodeSet[row + delta_row][col + i]);
                        }
                    }
                }
            }

            List<Integer> colList = new ArrayList<>();
            colList.add(-delta);
            colList.add(delta);
            for(Integer delta_col : colList) {
                if(col + delta_col >= 0 && col + delta_col < 100) {
                    for(int i = -delta; i < delta + 1; i++) {
                        if(row + i >= 0 && row + i < 100 && nodeSet[row+i][col+delta_col] != null) {
                            connecting = connectSets(nodeSet[row][col], nodeSet[row+i][col+delta_col]);
                        }
                    }
                }
            }

            if(row - delta < 0 && row + delta > 100 && col - delta < 0 && col + delta > 100) break;
            delta += 1;
        }
    }

    private boolean connectSets(NodeSet origin, NodeSet target) {
        boolean isConnected = false;
        Set<Node> originSet = origin.getNodeSet();
        Set<Node> targetSet = target.getNodeSet();
        for (Node currentNode : originSet) {
            Stop currentStop = currentNode.getStopInfo();
            for(Node targetNode : targetSet) {
                if(currentNode.equals(targetNode)) continue;
                Stop targetStop = targetNode.getStopInfo();
                double distance = currentStop.distance(targetStop);
                if(currentStop.stopType==targetStop.stopType && currentStop.getRoute().equals(targetStop.getRoute())){
                    if(currentStop.stopType==StopType.BUS)
                        nodes.get(currentStop).addEdge(new Edge(calculateWeight(distance, FormOfTransport.BUS), nodes.get(targetStop), FormOfTransport.BUS));
                    else
                        nodes.get(currentStop).addEdge(new Edge(calculateWeight(distance, FormOfTransport.SUBWAY), nodes.get(targetStop), FormOfTransport.SUBWAY));

                    isConnected = true;
                } else if(currentStop.distance(targetStop) <= 350) {
                    if(currentStop.stopType==StopType.SUBWAY && targetStop.stopType==StopType.SUBWAY)
                        nodes.get(currentStop).addEdge(new Edge(calculateWeight(distance, FormOfTransport.WALK) + FormOfTransport.SUBWAY.getFIXED_PENALTY(), nodes.get(targetStop), FormOfTransport.WALK));
                    else
                        nodes.get(currentStop).addEdge(new Edge(calculateWeight(distance, FormOfTransport.WALK) + FormOfTransport.BUS.getFIXED_PENALTY(), nodes.get(targetStop), FormOfTransport.WALK));
                    isConnected = true;
                }
            }
        }
        return isConnected;
    }

    public void computePath(Node sourceNode) {
        sourceNode.setMinDistance(0);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceNode);

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            if (node.isVisited()) {
                for (Edge edge : node.getEdges()) {
                    Node v = edge.getTargetNode();
                    double weight = edge.getWeight();
                    double minDistance = 0;
                    if (edge.getTransport() == FormOfTransport.BUS) {
                        minDistance = (node.getMinDistance() + weight) * FormOfTransport.BUS.getPENALTY();
                    } else if (edge.getTransport() == FormOfTransport.WALK) {
                        minDistance = (node.getMinDistance() + weight) * FormOfTransport.WALK.getPENALTY();
                    } else if(edge.getTransport()==FormOfTransport.SUBWAY) {
                        minDistance=(node.getMinDistance()+weight)*FormOfTransport.SUBWAY.getPENALTY();
                    }
                    if (minDistance < v.getMinDistance()) {
                        priorityQueue.remove(node);
                        v.setPreviousNode(node);
                        v.setMinDistance(minDistance);
                        priorityQueue.add(v);
                    }
                }
            }
        }
    }

    public void removeNode(Node node) {
        List<Edge> edges = node.getEdges();
        for(Edge edge : edges) {
            Node targetNode = edge.getTargetNode();
            targetNode.getEdges().remove(new Edge(edge.getWeight(), node, edge.getTransport()));
        }
        nodes.remove(node.getStopInfo());
    }

    public List<Node> getShortestPathTo(Node targetNode) {
        List<Node> path = new ArrayList<>();

        for (Node node = targetNode; node != null; node = node.getPreviousNode()) {
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }

    public void resetPreviousNodes() { nodes.values().forEach(Node::setPreviousNodeNull);}
    public void resetMinDistanceNodes() { nodes.values().forEach(Node::setMinDistanceMaxValue);}
    public void resetVisitedNodes() { nodes.values().forEach(Node::unSetVisited);}


}

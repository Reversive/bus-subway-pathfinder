import model.BusInPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Graph;
import utils.PathSolver;
import utils.Stop;


import java.util.ArrayList;
import java.util.List;

public class GraphTest {


    @Test
    void testBusUnreachablePathThrowsWalkable() {
        List<Stop> stopList = new ArrayList<>();
        ResourceReader.getBusStops(stopList, ResourceReader.getFileNameFromResource("reduced_bus_stops.csv"));
        Graph graph = new Graph(stopList);
        graph.makeEdges();
        PathSolver pathSolver = new PathSolver(graph);
        List<BusInPath> pathList = pathSolver.findPath(34.538377000000004, 58.488752000000005, -34.081035, -59.205288);
        Assertions.assertEquals("Caminable", pathList.get(0).name);
    }

    @Test
    void testSameBusPathThrows21F() {
        List<Stop> stopList = new ArrayList<>();
        ResourceReader.getBusStops(stopList, ResourceReader.getFileNameFromResource("reduced_bus_stops.csv"));
        Graph graph = new Graph(stopList);
        graph.makeEdges();
        PathSolver pathSolver = new PathSolver(graph);
        List<BusInPath> pathList = pathSolver.findPath( -34.534727000000004,-58.498642000000004, -34.53586,-58.50102);
        Assertions.assertEquals("21F", pathList.get(0).name);
    }

    @Test
    void testSameOriginDestinationThrowsWalkable() {
        List<Stop> stopList = new ArrayList<>();
        ResourceReader.getBusStops(stopList, ResourceReader.getFileNameFromResource("reduced_bus_stops.csv"));
        Graph graph = new Graph(stopList);
        graph.makeEdges();
        PathSolver pathSolver = new PathSolver(graph);
        List<BusInPath> pathList = pathSolver.findPath( -34.534727000000004,-58.498642000000004, -34.534727000000004,-58.498642000000004);
        Assertions.assertEquals("Caminable", pathList.get(0).name);
    }

    @Test
    void testBusCombinationThrows180A67A() {
        List<Stop> stopList = new ArrayList<>();
        ResourceReader.getBusStops(stopList, ResourceReader.getFileNameFromResource("reduced_bus_stops.csv"));
        Graph graph = new Graph(stopList);
        graph.makeEdges();
        PathSolver pathSolver = new PathSolver(graph);
        List<BusInPath> pathList = pathSolver.findPath( -34.603708902293235, -58.3962200528341, -34.56059364349761, -58.47730182438541);
        Assertions.assertEquals(pathList.get(0).name.equals("180A"), pathList.get(1).name.equals("67A"));
    }




}

import model.BusInPath;
import model.PlaceLocation;
import utils.Graph;
import utils.PathSolver;
import utils.Stop;

import java.util.Arrays;
import java.util.List;

public class Controller {

    PathSolver pathSolver;

  public Controller() {
      List<Stop> stops = ResourceReader.getStops();
      Graph graph = new Graph(stops);
      graph.makeEdges();
      this.pathSolver = new PathSolver(graph);
  }

  public List<BusInPath> findPath(double fromLat, double fromLng, double toLat, double toLng) {
    return pathSolver.findPath(fromLat, fromLng, toLat, toLng);
  }

  public List<PlaceLocation> findPlaces(String searchTerm) {
    return Arrays.asList(ResourceReader.getTop10(searchTerm,ResourceReader.getSites()));
  }
}

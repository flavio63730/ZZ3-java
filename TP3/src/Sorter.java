import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static void sortMetroStopByID(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparingInt(o -> o.ID));
    }

    public static void sortMetroStopByRoad(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparing(o -> o.road));
    }

    public static void sortMetroStopByCity(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparing(o -> o.city));
    }
}

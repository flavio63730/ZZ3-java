import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static void sortMetroStop(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparingInt(o -> o.ID));
    }

    public static void sortMetroStopRoad(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparing(o -> o.road));
    }

    public static void sortMetroStopCity(List<MetroStop> metroStops) {
        metroStops.sort(Comparator.comparing(o -> o.city));
    }
}

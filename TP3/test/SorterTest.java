import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class SorterTest extends TestCase {
    private final MetroStop metroStop1 = new MetroStop(1, 1.3, -0.34, "NY", "Times square", "metro");
    private final MetroStop metroStop2 = new MetroStop(2, 0.2, 0.57, "Paris", "Champs Elysée", "bus");
    private final MetroStop metroStop3 = new MetroStop(3, 1.2, 1.57, "Londres", "Madison Square Garden", "bus");

    public void testSortMetroStop() {
        // Assert
        List<MetroStop> expectedMetroStops = Arrays.asList(metroStop1, metroStop2, metroStop3);

        // Act
        List<MetroStop> metroStops = Arrays.asList(metroStop3, metroStop1, metroStop2);
        Sorter.sortMetroStop(metroStops);

        // Assert
        testSortMetroStop(expectedMetroStops, metroStops);
    }

    public void testSortMetroStopRoad() {
        // Assert
        List<MetroStop> expectedMetroStops = Arrays.asList(metroStop2, metroStop3, metroStop1);

        // Act
        List<MetroStop> metroStops = Arrays.asList(metroStop1, metroStop2, metroStop3);
        Sorter.sortMetroStopRoad(metroStops);

        testSortMetroStop(expectedMetroStops, metroStops);
    }

    public void testSortMetroStopCity() {
        // Assert
        List<MetroStop> expectedMetroStops = Arrays.asList(metroStop3, metroStop1, metroStop2);

        // Act
        List<MetroStop> metroStops = Arrays.asList(metroStop1, metroStop2, metroStop3);
        Sorter.sortMetroStopCity(metroStops);

        testSortMetroStop(expectedMetroStops, metroStops);
    }

    private void testSortMetroStop(List<MetroStop> expectedMetroStops, List<MetroStop> metroStops) {
        // Assert
        Assert.assertEquals(expectedMetroStops.size(), metroStops.size());
        for (int i=0; i < metroStops.size(); ++i) {
            Assert.assertEquals(expectedMetroStops.get(i).ID, metroStops.get(i).ID);
            Assert.assertEquals(expectedMetroStops.get(i).longitude, metroStops.get(i).longitude, 0);
            Assert.assertEquals(expectedMetroStops.get(i).latitude, metroStops.get(i).latitude, 0);
            Assert.assertEquals(expectedMetroStops.get(i).city, metroStops.get(i).city);
            Assert.assertEquals(expectedMetroStops.get(i).road, metroStops.get(i).road);
            Assert.assertEquals(expectedMetroStops.get(i).type, metroStops.get(i).type);
        }
    }
}
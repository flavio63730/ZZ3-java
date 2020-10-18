import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class FilesTest extends TestCase {
    private final MetroStop metroStop1 = new MetroStop("11347700;2.31681633207684;48.8773968555895;LISBONNE - MAIRIE DU 8E;PARIS-08EME;bus".split(";"));
    private final MetroStop metroStop2 = new MetroStop("11337907;2.27322573845239;48.8683132038243;LONGCHAMP;PARIS-16EME;bus".split(";"));
    private final MetroStop metroStopTiny2 = new MetroStop("11337907;2.27322573845239".split(";"));
    private final MetroStop metroStop3 = new MetroStop("1975;2.33871281165883;48.8844176451841;Abbesses;PARIS-18EME;metro".split(";"));
    private final MetroStop metroStopTiny3 = new MetroStop("1975;2.33871281165883;48.8844176451841;Abbesses".split(";"));

    public void testReadNoFile() {
        // Arrange
        String fileName = "in/metroStopEmpty.csv";
        List<MetroStop> expectedMetroStops = new ArrayList<>();

        // Act & Assert
        testMetroStop(fileName, expectedMetroStops);
    }

    public void testReadFileEmpty() {
        // Arrange
        String fileName = "in/metroStopEmpty.csv";
        List<MetroStop> expectedMetroStops = new ArrayList<>();

        // Act & Assert
        testMetroStop(fileName, expectedMetroStops);
    }

    public void testReadFileTiny() {
        // Arrange
        String fileName = "in/metroStopTiny.csv";
        List<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(metroStop1);
        expectedMetroStops.add(metroStopTiny2);
        expectedMetroStops.add(metroStopTiny3);

        // Act & Assert
        testMetroStop(fileName, expectedMetroStops);
    }

    public void testMetroStop() {
        // Arrange
        String fileName = "in/metroStop.csv";
        List<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(metroStop1);
        expectedMetroStops.add(metroStop2);
        expectedMetroStops.add(metroStop3);

        // Act & Assert
        testMetroStop(fileName, expectedMetroStops);
    }

    private void testMetroStop(String fileName, List<MetroStop> expectedMetroStops) {
        try {
            // Act
            List<MetroStop> metroStops = Files.read(fileName, ";");

            // Assert
            Assert.assertEquals(expectedMetroStops.size(), metroStops.size());
            for (int i = 0; i < expectedMetroStops.size(); ++i) {
                Assert.assertEquals(expectedMetroStops.get(i).ID, metroStops.get(i).ID);
                Assert.assertEquals(expectedMetroStops.get(i).longitude, metroStops.get(i).longitude, 0);
                Assert.assertEquals(expectedMetroStops.get(i).latitude, metroStops.get(i).latitude, 0);
                Assert.assertEquals(expectedMetroStops.get(i).city, metroStops.get(i).city);
                Assert.assertEquals(expectedMetroStops.get(i).road, metroStops.get(i).road);
                Assert.assertEquals(expectedMetroStops.get(i).type, metroStops.get(i).type);
            }
        }
        catch (Exception e) {
            Assert.fail();
        }
    }
}
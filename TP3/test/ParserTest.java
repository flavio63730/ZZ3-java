import junit.framework.TestCase;
import org.junit.Assert;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParserTest extends TestCase {
    private final String separator = ",";
    private final String metroStop1 = "1,1.3,-0.34,NY,Central Park,bus";
    private final String metroStop2 = "2,0.2,0.57,Paris,Champs Elysée,metro";
    private final String metroStop3 = "3,1.2,1.57";

    public void testEmpty() {
        try {
            // Arrange
            StringReader stream = new StringReader("");
            List<MetroStop> expectedMetroStops = new ArrayList<>();

            // Act & Assert
            testMetroStop(separator, expectedMetroStops, stream);
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    public void testMetroStop() {
        // Arrange
        StringReader stream = new StringReader(metroStop1 + System.lineSeparator() + metroStop2);
        List<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(new MetroStop(metroStop1.split(separator)));
        expectedMetroStops.add(new MetroStop(metroStop2.split(separator)));

        // Act & Assert
        testMetroStop(separator, expectedMetroStops, stream);
    }

    public void testMetroStopOtherSeparator() {
        // Arrange
        String hashTagSeparator = "#";
        StringReader stream = new StringReader(metroStop1.replace(separator, hashTagSeparator) + System.lineSeparator() + metroStop2.replace(separator, hashTagSeparator));
        List<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(new MetroStop(metroStop1.split(separator)));
        expectedMetroStops.add(new MetroStop(metroStop2.split(separator)));

        // Act & Assert
        testMetroStop(hashTagSeparator, expectedMetroStops, stream);
    }

    public void testMetroStopTiny() {
        // Arrange
        StringReader stream = new StringReader(metroStop3);
        List<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(new MetroStop(metroStop3.split(separator)));

        // Act & Assert
        testMetroStop(separator, expectedMetroStops, stream);
    }

    private void testMetroStop(String separator, List<MetroStop> expectedMetroStops, StringReader stream) {
        try {
            // Act
            List<MetroStop> metroStops = Parser.parse(stream, separator);

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
        catch (Exception e) {
            Assert.fail();
        }
    }
}

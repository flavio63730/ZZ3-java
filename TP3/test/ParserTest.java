import junit.framework.TestCase;
import org.junit.Assert;

import java.io.StringReader;
import java.util.ArrayList;

public class ParserTest extends TestCase {
    private final String separator = ",";
    private final String metroStop1 = "1,1.3,-0.34,NY,Central Park,metro";
    private final String metroStop2 = "2,0.2,0.57,Paris,Champs Elysée,bus";

    public void testEmpty() {
        try {
            // Act
            ArrayList<MetroStop> metroStops = Parser.parse(new StringReader(""), separator);

            // Assert
            Assert.assertEquals(0, metroStops.size());
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    public void testMetroStop() {
        // Arrange
        ArrayList<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(new MetroStop(metroStop1.split(separator)));
        expectedMetroStops.add(new MetroStop(metroStop2.split(separator)));

        // Act & Assert
        testMetroStop(separator, expectedMetroStops, new StringReader(metroStop1 + System.lineSeparator() + metroStop2));
    }

    public void testMetroStopOtherSeparator() {
        // Arrange
        ArrayList<MetroStop> expectedMetroStops = new ArrayList<>();
        expectedMetroStops.add(new MetroStop(metroStop1.split(separator)));
        expectedMetroStops.add(new MetroStop(metroStop2.split(separator)));

        // Act & Assert
        testMetroStop("#", expectedMetroStops, new StringReader(metroStop1.replace(",", "#") + System.lineSeparator() + metroStop2.replace(",", "#")));
    }

    private void testMetroStop(String separator, ArrayList<MetroStop> expectedMetroStops, StringReader stream) {
        try {
            // Act
            ArrayList<MetroStop> metroStops = Parser.parse(stream, separator);

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

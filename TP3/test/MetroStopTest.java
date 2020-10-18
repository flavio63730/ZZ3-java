import junit.framework.TestCase;
import org.junit.Assert;

public class MetroStopTest extends TestCase {
    private final int ID = 10;
    private final double longitude = 2.3f;
    private final double latitude = 1.29f;
    private final String city = "Los Angeles";
    private final String road = "Hollywood avenue";
    private final String type = "metro";

    public void testMetroStop() {
        // Act
        MetroStop metroStop = new MetroStop();

        // Assert
        Assert.assertEquals(0, metroStop.ID);
        Assert.assertEquals(0, metroStop.longitude, 0);
        Assert.assertEquals(0, metroStop.latitude, 0);
        Assert.assertEquals("", metroStop.city);
        Assert.assertEquals("", metroStop.road);
        Assert.assertEquals("", metroStop.type);
    }

    public void testMetroStopArgs() {
        // Act
        MetroStop metroStop = new MetroStop(ID, longitude, latitude, city, road, type);

        // Assert
        Assert.assertEquals(ID, metroStop.ID);
        Assert.assertEquals(longitude, metroStop.longitude, 0);
        Assert.assertEquals(latitude, metroStop.latitude, 0);
        Assert.assertEquals(city, metroStop.city);
        Assert.assertEquals(road, metroStop.road);
        Assert.assertEquals(type, metroStop.type);
    }

    public void testMetroStopWithArrayArgs() {
        // Arrange
        String[] args = { String.valueOf(ID), String.valueOf(longitude), String.valueOf(latitude), city, road, type };

        // Act
        MetroStop metroStop = new MetroStop(args);

        // Assert
        Assert.assertEquals(ID, metroStop.ID);
        Assert.assertEquals(longitude, metroStop.longitude, 0);
        Assert.assertEquals(latitude, metroStop.latitude, 0);
        Assert.assertEquals(city, metroStop.city);
        Assert.assertEquals(road, metroStop.road);
        Assert.assertEquals(type, metroStop.type);
    }

    public void testMetroStopWithShortArrayArgs() {
        // Arrange
        String[] args = { String.valueOf(ID), String.valueOf(longitude), String.valueOf(latitude) };

        // Act
        MetroStop metroStop = new MetroStop(args);

        // Assert
        Assert.assertEquals(ID, metroStop.ID);
        Assert.assertEquals(longitude, metroStop.longitude, 0);
        Assert.assertEquals(latitude, metroStop.latitude, 0);
        Assert.assertEquals("", metroStop.city);
        Assert.assertEquals("", metroStop.road);
        Assert.assertEquals("", metroStop.type);
    }
}
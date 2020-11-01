import junit.framework.TestCase;
import org.junit.Assert;

import java.io.IOException;

public class WeatherAPITest extends TestCase {
    public void testConnectBadCityName() {
        // Arrange
        String cityName = "Plauzzzzz";

        // Act & Assert
        try {
            City city = WeatherAPI.connect(cityName);

            Assert.fail();
        } catch (IOException e) {
            Assert.assertTrue(true);
        }
    }

    public void testConnect() {
        // Arrange
        String cityName = "Plauzat";
        City cityExpected = new City();
        cityExpected.id = 2986827;
        cityExpected.name = "Plauzat";

        // Act
        City city = null;
        try {
            city = WeatherAPI.connect(cityName);
        } catch (IOException e) {
            Assert.fail();
        }

        // Assert
        Assert.assertEquals(cityExpected.id, city.id);
        Assert.assertEquals(cityExpected.name, city.name);
        Assert.assertTrue(city.main.temp >= city.main.temp_min && city.main.temp <= city.main.temp_max);
        Assert.assertTrue(city.main.humidity >= 0 && city.main.humidity <= 100);
    }
}
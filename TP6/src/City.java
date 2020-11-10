import java.io.Serializable;

public class City implements Serializable {
    private static class main {
        private float temp;
        private float temp_min;
        private float temp_max;
        private float humidity;

        private main() {}

        private main(float temp, float temp_min, float temp_max, float humidity) {
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.humidity = humidity;
        }
    }

    private int id;
    private String name;
    private main main;

    public City() {}

    public City(int ID, String name, float temp, float temp_min, float temp_max, float humidity) {
        this.id = ID;
        this.name = name;
        this.main = new main(temp, temp_min, temp_max, humidity);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getTemp() {
        return main.temp;
    }

    public float getTempMin() {
        return main.temp_min;
    }

    public float getTempMax() {
        return main.temp_max;
    }

    public float getHumidity() {
        return main.humidity;
    }

    public void display(String separator) {
        System.out.println(separator);
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "ID : " + id + System.lineSeparator() +
               "Name : " + name + System.lineSeparator() +
               "Temp (°C) : " + main.temp + System.lineSeparator() +
               "Temp min (°C) : " + main.temp_min + System.lineSeparator() +
               "Temp max (°C) : " + main.temp_max + System.lineSeparator() +
               "Humidity : " + main.humidity + System.lineSeparator();
    }
}

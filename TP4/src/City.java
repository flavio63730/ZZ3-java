import java.io.Serializable;

public class City implements Serializable {
    public static class main {
        public double temp;
        public double temp_min;
        public double temp_max;
        public double humidity;
    }
    
    public int id;
    public String name;
    public main main;

    @Override
    public String toString() {
        return "=========" + System.lineSeparator() +
                "ID : " + id + System.lineSeparator() +
                "Name : " + name + System.lineSeparator() +
                "Temp (°C) : " + main.temp + System.lineSeparator() +
                "Temp min (°C) : " + main.temp_min + System.lineSeparator() +
                "Temp max (°C) : " + main.temp_max + System.lineSeparator() +
                "Humidity : " + main.humidity + System.lineSeparator() +
                "=========";
    }
}

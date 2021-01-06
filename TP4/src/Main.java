import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Bad arguments");
            throw new IllegalArgumentException();
        }

        City city = null;

        try {
            city = WeatherAPI.connect(args[0]);
        } catch (IOException e) {
            System.err.println("Error");
        }

        System.out.println(city);
    }
}

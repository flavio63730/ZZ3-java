public class MetroStop {
    public int ID;
    public double longitude;
    public double latitude;
    public String city;
    public String road;
    public String type;

    public MetroStop() {
        ID = 0;
        longitude = 0;
        latitude = 0;
        city = "";
        road = "";
        type = "";
    }

    public MetroStop(int ID, double longitude, double latitude, String city, String road, String type) {
        this.ID = ID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.road = road;
        this.type = type;
    }

    public MetroStop(String[] args) {
        this.ID = args.length > 0 ? InputFormatter.stringToInt(args[0], 0) : 0;
        this.longitude = args.length > 1 ? InputFormatter.stringToDouble(args[1], 0) : 0;
        this.latitude = args.length > 2 ? InputFormatter.stringToDouble(args[2], 0) : 0;
        this.city = args.length > 3 ? args[3] : "";
        this.road = args.length > 4 ? args[4] : "";
        this.type = args.length > 5 ? args[5] : "";
    }

    @Override
    public String toString() {
        return "######################" + System.lineSeparator()
                + "Metro Stop :" + System.lineSeparator()
                + "ID => " + ID + System.lineSeparator()
                + "longitude => " + longitude + System.lineSeparator()
                + "latitude => " + latitude + System.lineSeparator()
                + "city => " + city + System.lineSeparator()
                + "road => " + road + System.lineSeparator()
                + "type => " + type + System.lineSeparator()
                + "######################";
    }
}

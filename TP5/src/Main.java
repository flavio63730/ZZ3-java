public class Main {
    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            System.err.println("Il n'y a pas le bon nombre de paramètres.");
        }

        try {
            Converter.convertClass(args[0], (args.length != 2) ? args[0] : args[1]);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}

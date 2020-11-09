import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 3) {
            System.err.println("Il n'y a pas le bon nombre de paramètres.");
            System.err.println("Exemple d'utilisation : java Main javaClass [cppClass] [--stdout]");
            return;
        }

        boolean isSecondArgStdout = (args.length == 2 && args[1].equals("--stdout"));
        boolean isThirdArgStdout = (args.length == 3 && args[2].equals("--stdout"));

        String javaClass = args[0];
        String cppClass = args.length == 1 || isSecondArgStdout ? args[0] : args[1];
        PrintStream out = isSecondArgStdout || isThirdArgStdout ? System.out : new PrintStream("cpp/" + cppClass + ".hpp", StandardCharsets.UTF_8);

        try {
            Converter.convertClass(javaClass, cppClass, out);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}

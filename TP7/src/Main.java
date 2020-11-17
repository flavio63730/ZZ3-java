import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

public class Main {
    private static double timeTotal = 0;
    private static double timeMax = -1;
    private static double timeMin = -1;

    public static void main(String[] args) {
        int nbEtudiants = (args.length > 0 && NumberUtils.isCreatable(args[0])) ? Integer.parseInt(args[0]) : 150000,
            averageCount = (args.length > 1 && NumberUtils.isCreatable(args[1])) ? Integer.parseInt(args[1]) : 10,
            cores = Runtime.getRuntime().availableProcessors();
        double startTime;

        try {
            for (int i = 0; i < averageCount; ++i) {
                startTime = System.currentTimeMillis();

                /*List<Etudiant> etudiants = */ThreadGenerator.etudiants(cores, nbEtudiants);

                updateTimes((System.currentTimeMillis() - startTime) / 1000f);
            }

            displayTimes(averageCount);
        } catch (Exception e) {
            displayException(e);
        }
    }

    private static void updateTimes(double estimatedTime) {
        timeTotal += estimatedTime;
        if (estimatedTime < timeMin || timeMin == -1)
            timeMin = estimatedTime;
        if (estimatedTime > timeMax)
            timeMax = estimatedTime;
    }

    private static void displayTimes(int averageCount) {
        System.out.println("Nombre de lancements => "+ averageCount);
        System.out.println("Le plus rapide       => " + timeMin + " seconds");
        System.out.println("Le plus lent         => " + timeMax + " seconds");
        System.out.println("La moyenne           => " + (timeTotal / averageCount) + " seconds");
    }

    private static void displayException(Exception e) {
        System.err.println("**********************");
        System.err.println("ERROR : " + e.getClass());
        System.err.println("ERROR MESSAGE : " + e.getMessage());
        System.err.println("**********************");
    }
}

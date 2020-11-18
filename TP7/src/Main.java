import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;

public class Main {
    private static double timeTotal = 0;
    private static double timeMax = -1;
    private static double timeMin = -1;

    public static void main(String[] args) {
        int nbEtudiants  = (args.length > 0 && NumberUtils.isCreatable(args[0])) ? Integer.parseInt(args[0]) : 150000,
            averageCount = (args.length > 1 && NumberUtils.isCreatable(args[1])) ? Integer.parseInt(args[1]) :     10,
            cores        = Runtime.getRuntime().availableProcessors();
        double startTime;
        SQLManager sqlManager = null;

        try {
            sqlManager = new SQLManager("jdbc:sqlite:db/TP7.db");

            // Execute many time to have a good empirical average
            for (int i = 1; i <= averageCount; ++i) {
                // Chrono start
                startTime = chronoStart();

                // Payload
                Collection<Etudiant> etudiants = ThreadGenerator.etudiants(cores, nbEtudiants);
                sqlManager.insertAll(etudiants);

                // Chrono stop
                double estimatedTime = chronoStop(startTime);

                // Display stats
                updateTimes(estimatedTime);
                displayLines(i, estimatedTime, sqlManager.selectAll().size(), etudiants.size());

                // Clear SQL table
                sqlManager.deleteAll();
            }

            // Display Average stats
            displayTimes(averageCount);
        } catch (Exception e) {
            displayException(e);
        } finally {
            if (sqlManager != null) {
                sqlManager.dispose();
            }
        }
    }

    private static double chronoStart() {
        return System.currentTimeMillis();
    }

    private static double chronoStop(double startTime) {
        return (System.currentTimeMillis() - startTime) / 1000f;
    }

    private static void updateTimes(double estimatedTime) {
        timeTotal += estimatedTime;
        if (estimatedTime < timeMin || timeMin == -1)
            timeMin = estimatedTime;
        if (estimatedTime > timeMax)
            timeMax = estimatedTime;
    }

    private static void displayLines(int i, double estimatedTime, int sqlSize, int listSize) {
        System.out.println("***** N°" + i + " *****");
        System.out.println("Temps d'execution => " + estimatedTime + " seconds");
        System.out.println("Taille table SQL  => " + sqlSize + " elements");
        System.out.println("Taille List       => " + listSize + " elements");
        System.out.println();
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

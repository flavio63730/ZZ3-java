import java.util.LinkedList;
import java.util.List;

public class ThreadGenerator {
    private static class SingleThreadGenerator implements Runnable {
        private int limit;
        private List<Etudiant> etudiants;

        public SingleThreadGenerator(int limit, List<Etudiant> etudiants) {
            this.limit = limit;
            this.etudiants = etudiants;
        }

        @Override
        public void run() {
            etudiants.addAll(Generator.etudiants(limit));
        }
    }

    public static List<Etudiant> etudiants(int cores, int limit) throws InterruptedException {
        List<Etudiant> etudiants = new LinkedList<>();
        List<Thread> threads = new LinkedList<>();

        // Create all threads with optimal number of students per thread
        for (int i=0; i < cores; ++i) {
            threads.add(new Thread(new SingleThreadGenerator((limit / cores) + (limit % cores > i ? 1 : 0), etudiants)));
        }

        // Start all thrads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait all threads
        for (Thread thread : threads) {
            thread.join();
        }

        return etudiants;
    }
}

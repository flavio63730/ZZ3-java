import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadGenerator {
    private static class SingleThreadGenerator implements Runnable {
        private int limit;
        private Collection<Etudiant> etudiants;

        public SingleThreadGenerator(int limit, Collection<Etudiant> etudiants) {
            this.limit = limit;
            this.etudiants = etudiants;
        }

        @Override
        public void run() {
            etudiants.addAll(Generator.etudiants(limit));
        }
    }

    public static Collection<Etudiant> etudiants(int cores, int limit) throws InterruptedException {
        Collection<Etudiant> etudiants = new ConcurrentLinkedQueue<>();
        List<Thread> threads = new LinkedList<>();

        // Create all threads with optimal number of students per thread
        for (int i=0; i < cores; ++i) {
            threads.add(new Thread(new SingleThreadGenerator((limit / cores) + (limit % cores > i ? 1 : 0), etudiants)));
        }

        // Start all threads
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

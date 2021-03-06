import com.github.javafaker.Faker;

import java.util.Collection;
import java.util.Locale;
import java.util.LinkedList;

public class Generator {
    private static Faker faker = new Faker(new Locale("fr"));

    public static Etudiant etudiant() {
        return new Etudiant(faker.number().numberBetween(1, 99999),
                            faker.dragonBall().character(),
                            faker.name().lastName(),
                            faker.date().birthday(),
                            faker.address().cityName(),
                            faker.lorem().paragraph());
    }

    public static Collection<Etudiant> etudiants(int limit) {
        Collection<Etudiant> etudiants = new LinkedList<>();

        for (int i=0; i < limit; ++i) {
            etudiants.add(etudiant());
        }

        return etudiants;
    }
}

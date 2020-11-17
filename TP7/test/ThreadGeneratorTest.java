import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ThreadGeneratorTest extends TestCase {
    private static void assertEtudiant(Etudiant etudiant) {
        assertTrue(etudiant.ID > 0 && etudiant.ID < 100000);
        assertFalse(StringUtils.isEmpty(etudiant.firstName));
        assertFalse(StringUtils.isEmpty(etudiant.lastName));
        assertNotNull(etudiant.birthDay);
        assertFalse(StringUtils.isEmpty(etudiant.birthPlace));
        assertFalse(StringUtils.isEmpty(etudiant.description));
    }

    private static void assertEtudiants(int cores, int limit) {
        try {
            // Act
            List<Etudiant> etudiants = ThreadGenerator.etudiants(cores, limit);

            // Assert
            assertEquals(limit, etudiants.size());
            for (int i=0; i < limit; ++i) {
                assertEtudiant(etudiants.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testEtudiantsSimpleCores() {
        assertEtudiants(1, 8);
    }

    public void testEtudiantsMultipleCores() {
        assertEtudiants(4, 8);
    }

    public void testEtudiantsWithNoMultiplableLimit() {
        assertEtudiants(4, 9);
    }
}

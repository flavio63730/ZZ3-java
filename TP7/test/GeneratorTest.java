import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class GeneratorTest extends TestCase {
    private static void assertEtudiant(Etudiant etudiant) {
        assertTrue(etudiant.ID > 0 && etudiant.ID < 100000);
        assertFalse(StringUtils.isEmpty(etudiant.firstName));
        assertFalse(StringUtils.isEmpty(etudiant.lastName));
        assertNotNull(etudiant.birthDay);
        assertFalse(StringUtils.isEmpty(etudiant.birthPlace));
        assertFalse(StringUtils.isEmpty(etudiant.description));
    }

    public void testEtudiant() {
        // Act
        Etudiant etudiant = Generator.etudiant();

        // Assert
        assertEtudiant(etudiant);
    }

    public void testEtudiants() {
        // Arrange
        int limit = 10;

        // Act
        Collection<Etudiant> etudiants = Generator.etudiants(limit);

        // Assert
        assertEquals(limit, etudiants.size());
        for (Etudiant etudiant : etudiants) {
            assertEtudiant(etudiant);
        }
    }
}

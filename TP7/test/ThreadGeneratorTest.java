import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.Collection;

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
            Collection<Etudiant> etudiants = ThreadGenerator.etudiants(cores, limit);

            // Assert
            assertEquals(limit, etudiants.size());
            for (Etudiant etudiant : etudiants) {
                assertEtudiant(etudiant);
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private static void assertEtudiantsDb(int cores, int limit) {
        try {
            // Arrange
            SQLManager sqlManager = new SQLManager("jdbc:sqlite:db/TP7_test.db");
            sqlManager.deleteAll();

            // Act
            Collection<Etudiant> etudiants = ThreadGenerator.etudiants(cores, limit);
            sqlManager.insertAll(etudiants);

            // Assert
            assertEquals(limit, etudiants.size());
            assertEquals(limit, sqlManager.selectAll().size());
            for (Etudiant etudiant : etudiants) {
                assertEtudiant(etudiant);
            }

            sqlManager.dispose();
        } catch (Exception e) {
            Assert.fail();
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

    public void testEtudiantsSimpleCoresDb() {
        assertEtudiantsDb(1, 8);
    }

    public void testEtudiantsMultipleCoresDb() {
        assertEtudiantsDb(4, 8);
    }

    public void testEtudiantsWithNoMultiplableLimitDb() {
        assertEtudiantsDb(4, 9);
    }
}

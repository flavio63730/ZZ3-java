import java.util.Date;

public class Etudiant {
    public int ID;
    public String firstName;
    public String lastName;
    public Date birthDay;
    public String birthPlace;
    public String description;

    public Etudiant(int ID, String firstName, String lastName, Date birthDay, String birthPlace, String description) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ID => " + ID + System.lineSeparator()
                + "Prenom => " + firstName + System.lineSeparator()
                + "Nom => " + lastName + System.lineSeparator()
                + "Date de naissance => " + birthDay + System.lineSeparator()
                + "Lieu de naissance => " + birthPlace + System.lineSeparator()
                + "Description du parcours => " + description;
    }
}

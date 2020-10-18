public class Exceptions {
    public static void displayError(Exception e) {
        System.out.println();
        System.out.println("**********************");
        System.out.println("ERROR : " + e.getClass());
        System.out.println("ERROR MESSAGE : " + e.getMessage());
        System.out.println("**********************");
        System.out.println();
    }
}

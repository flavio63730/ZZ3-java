public class Toto extends Mom implements IToto {
    private String stringPrivate;
    protected int intProtected;
    public double doublePublic;

    private Toto(String stringPrivate) {}
    protected Toto(int intProtected) {}
    public Toto(double doublePublic) {}

    private String getStringPrivate() { return stringPrivate; }
    protected int getIntProtected() { return intProtected; }
    public double getDoublePublic() { return doublePublic; }

    private void setStringPrivate(String stringPrivate) {}
    protected void setIntProtected(int intProtected) {}
    public void setDoublePublic(double doublePublic) {}
}

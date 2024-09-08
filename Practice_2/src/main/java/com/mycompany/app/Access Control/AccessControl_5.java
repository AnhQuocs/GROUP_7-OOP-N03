public class AccessControl_5 {
    public int publicField = 1;
    private int privateField = 2;
    protected int protectedField = 3;
    int packageField = 4;  // Package-access (default)

    public void publicMethod() {
        System.out.println("Public method");
    }

    private void privateMethod() {
        System.out.println("Private method");
    }

    protected void protectedMethod() {
        System.out.println("Protected method");
    }

    void packageMethod() {
        System.out.println("Package-access method");
    }
}

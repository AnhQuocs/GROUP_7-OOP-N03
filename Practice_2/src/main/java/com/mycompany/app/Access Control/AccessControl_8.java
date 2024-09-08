public class AccessControl_8 {
    private static int count = 0;

    private AccessControl_8() {
        System.out.println("Connection " + count++ + " created.");
    }

    static AccessControl_8 create() {
        return new AccessControl_8();
    }
}

public class AccessControl_4 
{
    public void tryToCallProtectedMethod() {
        // Parent parent = new Parent();
        // parent.protectedMethod();  // Lỗi biên dịch, không thể truy cập vì không kế thừa và khác package
    }

    public static void main(String[] args) {
        // OtherClass other = new OtherClass();
        // other.tryToCallProtectedMethod();  // Lỗi biên dịch nếu dòng trên không bị comment
    }
}
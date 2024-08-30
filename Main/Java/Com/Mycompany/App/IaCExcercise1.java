public class IaCExcercise1 
{
    private String uninitializedString;

    public void displayStringValue()
    {
        System.out.println("Gia tri cua bien chua duoc khoi tao la: " + uninitializedString);
    }
    public static void main(String[] args) 
    {
        IaCExcercise1 demo = new IaCExcercise1();
        demo.displayStringValue();

    }
}

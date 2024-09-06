public class IaCExercise_14 
{
    static String s1 = "Khoi tao dinh nghia";

    static String s2;

    static 
    {
        s2 = "Khoi tao boi khoi tinh";
    }

    static void print()
    {
        System.out.println(s1);
        System.out.println(s2);
    }

    public static void main(String[] args) {
        print();
    }
}

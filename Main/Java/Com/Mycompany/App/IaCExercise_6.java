public class IaCExercise6 
{
    public IaCExercise6 (int num, String str)
    {
        System.out.println("Int and String");
    }

    public IaCExercise6 (String str, int num)
    {
        System.out.println("String and Int");
    }

    public static void main(String[] args) 
    {
        IaCExercise6 obj1 = new IaCExercise6(96, "Hello");
        IaCExercise6 obj2 = new IaCExercise6("Holle", 69);
    }
}

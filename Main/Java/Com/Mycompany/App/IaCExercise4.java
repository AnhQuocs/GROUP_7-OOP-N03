public class IaCExercise4 
{
    public IaCExercise4 ()
    {
        System.out.println("Hehehe");
    }

    public IaCExercise4(String str)
    {
        System.out.println("Constructor: " + str);
    }
    public static void main(String[] args) 
    {
        IaCExercise4 obj1 = new IaCExercise4();
        IaCExercise4 obj2 = new IaCExercise4("Hohoho");
    }
}

public class IaCExercise8
{
    void second_method()
    {
        System.out.println("Hehehe");
    }

    void first_method()
    {
        second_method();

        this.second_method();
    }

    public static void main(String[] args) 
    {
        IaCExercise8 obj = new IaCExercise8();
        obj.first_method();
    }
}
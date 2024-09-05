class exercise
{
    protected void finalize()
    {
        System.out.println("Hahahahahahaha");
    }
}

public class IaCExercise10 
{
   public static void main(String[] args) 
   {
        exercise obj = new exercise();
        obj.finalize();
        System.gc();
   }
}

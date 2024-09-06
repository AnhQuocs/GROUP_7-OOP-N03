class Tank
{
    protected void finalize()
    {
        System.out.println("Hahaha");
    }
}


public class IaCExercise_12 
{
    public static void main(String[] args) 
    {
        Tank obj = new Tank();
        obj = null;
        System.gc();
    }
}

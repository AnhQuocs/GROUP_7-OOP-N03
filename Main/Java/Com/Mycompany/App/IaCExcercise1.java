public class IaCExcercise1 
{
    static class Rock 
    {
        Rock ()
        {
            System.out.print("Rock ");
        }
    }
    public static void main(String[] args) 
    {
        for ( int i = 0 ; i < 10 ; i++)
        {
            new Rock();
        }
    }
}

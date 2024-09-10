public class Radar 
{
    public double calculateXn(int n) 
    {
        if (n >= 0 && n <= 15) 
        {
            return 1 - (double)n / 15;
        }
        return 0;
    }

    public void displaySignal() 
    {
        int n = 4;
        double Xn = calculateXn(n);
        System.out.println("X(" + n + ") = " + Xn);
    }
}

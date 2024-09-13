public class Radar 
{
    public double calculateXn(int n) 
    {
        //discrete signal sample, phai dua vao trong discreteSignal, 
        //file rieng ke thua discrete signal
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

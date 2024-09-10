
import java.util.ArrayList;
import java.util.List;

public class DiscreteSignal implements Signal 
{
    private List<Integer> signalValues;

    public DiscreteSignal ()
    {
        signalValues = new ArrayList<>();
    }

    public void addSignalValue(int value) 
    {
        signalValues.add(value);
    }
    
    
    public int calculateSignal(int n)
    {
        int result = 0;
        for (int i = 0 ; i < signalValues.size() ; i++)
        {
            result += signalValues.get(i) * delta(n - i);
        }
        return result;
    }
    
    private int delta(int n) 
    {
        return n == 0 ? 1 : 0;
    }

    @Override 
    public void transmit() 
    {
        for (int i  = 0 ; i < signalValues.size() ; i++)
        {
            System.out.println("x(" + i + ") = " + calculateSignal(i));
        }
    }

}
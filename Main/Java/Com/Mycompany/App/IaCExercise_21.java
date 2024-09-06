public class IaCExercise_21 
{
    public enum Currency
    {
        VND, DOLLAR, EURO, RUBY
    }    

    public static void main(String[] args) 
    {
        for(Currency currency : Currency.values())
        {
            System.out.println("Currency: " + currency + ", Ordinal: " + currency.ordinal());
        }
    }
}

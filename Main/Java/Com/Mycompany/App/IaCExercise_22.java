public class IaCExercise_22 
{
    public enum Currency
    {
        VND, RUBY, DOLLAR, NDT
    }    

    public static void main(String[] args) 
    {
        for(Currency currency : Currency.values())
        {
            switch (currency) {
                case VND:
                    System.out.println("Vietnam Dong (VND)");
                    break;
                case RUBY:
                    System.out.println("Ngoai te RUBY");
                    break;
                case DOLLAR:
                    System.out.println("Dong dollar cua meo");
                    break;
                case NDT:
                    System.out.println("NHAN DAN TE");
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }
}

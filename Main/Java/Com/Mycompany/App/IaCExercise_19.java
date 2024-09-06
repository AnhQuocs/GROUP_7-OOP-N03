public class IaCExercise_19 
{
    public static void printStrings(String... strings)
    {
        for(String s : strings)
        {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        printStrings("lemon", "apple", "orange");

        String[] fruits = {"watermelon", "orange", "water"};
        printStrings(fruits);
    }
}

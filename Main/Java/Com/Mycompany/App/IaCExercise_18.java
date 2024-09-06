public class IaCExercise_18 
{
    public IaCExercise_18(String type)
    {
        System.out.println("Doi so duoc truyen vao la: " + type);
    }    

    public static void main(String[] args) {
        IaCExercise_18[] arr = new IaCExercise_18[10];
        for (int i = 0 ; i < arr.length ; i++)
        {
            arr[i] = new IaCExercise_18("Doi so thu " + (i+1));
        }
    }
}

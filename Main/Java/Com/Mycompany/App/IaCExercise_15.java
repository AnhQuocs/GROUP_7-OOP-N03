public class IaCExercise_15 
{
    String instanceField;
    {
        instanceField = "Khoi tao thong qua instance";
    }

    public void printField()
    {
        System.out.println("instanceField: " + instanceField);
    }

    public static void main(String[] args) {
        IaCExercise_15 obj = new IaCExercise_15();
        obj.printField();
    }
}

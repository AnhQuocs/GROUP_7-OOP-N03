public class IaCExercise9 
{
    String name;
    int age;

    IaCExercise9 (String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    IaCExercise9 (String name)
    {
        this(name, 25);
    }

    void In ()
    {
        System.out.println("Ten: " + name + "-Tuoi: " + age);
    }

    public static void main(String[] args) 
    {
        IaCExercise9 obj1 = new IaCExercise9("Quoc", 19);
        IaCExercise9 obj2 = new IaCExercise9("Vu", 14);
        obj1.In();
        obj2.In();
    }
}

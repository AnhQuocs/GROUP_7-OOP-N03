class FirstClass {
    protected int protectedData = 10;

    public void showData() {
        System.out.println("Protected data: " + protectedData);
    }
}

class SecondClass {
    public void manipulateData(FirstClass obj) {
        obj.protectedData *= 2;  
        System.out.println("Data after manipulation: " + obj.protectedData);
    }
}

public class AccessControl_6 {
    public static void main(String[] args) {
        FirstClass firstObj = new FirstClass();
        firstObj.showData();  

        SecondClass secondObj = new SecondClass();
        secondObj.manipulateData(firstObj); 
    }
}

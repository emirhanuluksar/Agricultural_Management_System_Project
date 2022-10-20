import Test.Test;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\pytho\\OneDrive\\Masaüstü\\Agricultural_Management_System\\Suppliers.txt");
        File file2 = new File("C:\\Users\\pytho\\OneDrive\\Masaüstü\\Agricultural_Management_System\\Crops.txt");
        File file3 = new File("C:\\Users\\pytho\\OneDrive\\Masaüstü\\Agricultural_Management_System\\Stores.txt");
        Test test = new Test();
        test.start(file, file2, file3);
        System.out.println("Agricultural Management System sonlandırılıyor!");
    }
}
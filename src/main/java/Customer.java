import java.io.*;
import java.util.*;
import com.opencsv.*;
public class Customer {
    private String tableID;
    private String Remarks;
    private String foodID;

    public static Scanner inp = new Scanner(System.in);

    Customer() {
        tableID = "";
        Remarks = "";
        foodID = "";
    }

    Customer(String tID, String r, String fID) {
        tableID = tID;
        Remarks = r;
        foodID = fID;
    }

    public void viewMenu() {

    }

    public void confirmFood(String tID, String fID, String r) {

    }

    public void updateFood(String tID, String fID, String r) {

    }

    public void removeFood(String tID, String fID) {

    }

    public void addFood(String tID, String fID, String r) {

    }
}

    public static void main(String args[]) {
//        viewMenu();
//        addFood();
//        removeFood();
//        confirmFood();
//        updateFood();
    }
}
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

    public void viewMenu() throws IOException{

    }

    public void confirmFood(String tID, String fID, String r) throws IOException{

    }

    public void updateFood(String tID, String fID, String r) throws IOException{

    }

    public void removeFood(String tID, String fID) throws IOException{

    }

    public void addFood(String tID, String fID, String r) throws IOException{

    }


    public static void main(String args[]) throws IOException{
        Scanner menu_file = new Scanner(new File("C:\\Users\\user\\Documents\\GitHub\\Food-Ordering-System\\src\\main\\java\\menu.csv"));
        menu_file.useDelimiter(",");
        while (menu_file.hasNext()) {
          System.out.print(menu_file.next());
        }
        menu_file.close();
//        viewMenu();
//        addFood();
//        removeFood();
//        confirmFood();
//        updateFood();
    }
}

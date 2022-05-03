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

    /*public void viewMenu() throws IOException{

    }*/

    public void confirmFood(String tID, String fID, String r) throws IOException{

    }

    public void updateFood(String tID, String fID, String r) throws IOException{

    }

    public void removeFood(String tID, String fID) throws IOException{

    }

    public void addFood(String tID, String fID, String r) throws IOException{

    }


    public static void main(String args[]) throws IOException{
        Scanner selection = new Scanner(System.in);

        System.out.println("Welcome to ABC Restaurant!");
        System.out.println("Here is the menu of our restaurant!");
        System.out.println(" ");

        Scanner menu_file = new Scanner(new File("src/main/java/menu.csv"));
        menu_file.useDelimiter(",");
        while (menu_file.hasNext()) {
          System.out.print(menu_file.next());
        }
        menu_file.close();
        System.out.println(" ");

        System.out.println("What do you want to do next?");
        System.out.println("1. Add food");          //addFood();
        System.out.println("2. Remove food");       //removeFood();
        System.out.println("3. Confirm food");      //confirmFood();
        System.out.println("4. Update food");       //updateFood();
        System.out.println("5. Exit");
        System.out.println(" ");
        System.out.print("Selection: ");
        String select = selection.nextLine();


    }
}

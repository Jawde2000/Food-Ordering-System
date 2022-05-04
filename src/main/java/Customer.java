import java.io.*;
import java.util.*;
import com.opencsv.*;
public class Customer {
    private String tableID;
    private String Remarks;
    private String foodID;

    public static Scanner inp = new Scanner(System.in);
    Scanner Table = new Scanner(System.in);
    private static final File customer_file = new File("customer.csv");

    Customer() {
        tableID = "";
        Remarks = "";
        foodID = "";
    }

    Customer(String tID, String r, String fID) {
        this.tableID = tID;
        this.Remarks = r;
        this.foodID = fID;
    }

    private static void readFile() {
        try {
            FileReader filereader = new FileReader("customer.csv");
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            } csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmFood(String tID, String fID, String r) throws IOException{
        readFile();
        System.out.println("What is your table ID? ");
        int table = Table.nextInt();
        if (table >= 1 && table <= 50) {
            
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        }
    }

    public void updateFood(String tID, String fID, String r) throws IOException{
        readFile();
        System.out.println("What is your table ID? ");
        int table = Table.nextInt();
        if (table >= 1 && table <= 50) {
            
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        }
    }

    public void removeFood(String tID, String fID) throws IOException{
        readFile();

    }

    public void addFood(String tID, String fID, String r) throws IOException{
        readFile();
        System.out.println("What is your table ID? ");
        int table = Table.nextInt();
        if (table >= 1 && table <= 50) {
            
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        }
    }


    public static void main(String args[]) throws Exception{
        readFile();
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

            switch (select) {
                case "1":
                    //addFood();
                    break;

                case "2":
                    //removeFood();
                    break;

                case "3":
                    //confirmFood();
                    break;

                case "4":
                    //updateFood();
                    break;

                case "5":
                    System.out.println("Thank you and have a nice day!");
                    break;
        
                default:
                    System.out.println("404 ERROR NOT FOUND!");
                    break;
            } selection.close();
    } 
}

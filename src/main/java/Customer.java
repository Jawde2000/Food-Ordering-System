import java.io.*;
import java.text.BreakIterator;
import java.util.*;
import javax.swing.*;

import javax.swing.JOptionPane;

import com.opencsv.*;
public class Customer {
    private String tableID;
    private String Remarks;
    private String foodID;

    public static Scanner inp = new Scanner(System.in);
    private static Scanner cusScanner;
    static Scanner Table = new Scanner(System.in);
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
            FileReader filereader = new FileReader("src/main/java/customer.csv");
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

    public static void confirmFood(/*String tID, String fID, String r*/) throws IOException{
        readFile();
        System.out.println("What is your table ID? ");
        int table = Table.nextInt();
        if (table >= 1 && table <= 50) {
            
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        }
    }

    public static void updateFood(/*String tID, String fID, String r*/) throws IOException{
        readFile();
        System.out.println("What is your table ID? ");
        int table = Table.nextInt();
        if (table >= 1 && table <= 50) {
            
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        }
    }

    public static void removeFood(String filepath,String removeTerm) throws IOException{
        //readFile();
        String tempFile = "tempCustomer.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String tID = "";
        String fID = "";
        String r = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            cusScanner = new Scanner(new File(filepath));
            cusScanner.useDelimiter("[,\n]");

            while (cusScanner.hasNext()) {
                tID = cusScanner.next();
                fID = cusScanner.next();
                r = cusScanner.next();

                if (!tID.equals(removeTerm)) {
                    pw.println(tID + "," + fID + "," + r);
                }
            }
            cusScanner.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public static void addFood(/*String tID, String fID, String r*/) throws IOException{
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
                    addFood();
                    break;

                case "2":
                    Scanner sc= new Scanner(System.in);
                    String filepath = "src/main/java/Customer.csv";
                    System.out.println("What do you want to remove?");
                    String removeTerm = sc.nextLine();
                    removeFood(filepath, removeTerm);
                    break;

                case "3":
                    confirmFood();
                    break;

                case "4":
                    updateFood();
                    break;

                case "5":
                    System.out.println("Thank you and have a nice day!");
                    break;
        
                default:
                    System.out.println("404 ERROR NOT FOUND!");
                    System.out.println("PLEASE TRY AGAIN!");
                    break;
            } selection.close();
    } 
}

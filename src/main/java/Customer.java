import java.io.*;
import java.text.BreakIterator;
import java.util.*;
import javax.swing.*;

import javax.swing.JOptionPane;

import com.opencsv.*;

import java.nio.file.*;
public class Customer {
    private String tableID;
    private String Remarks;
    private static String foodID;

    public static Scanner inp = new Scanner(System.in);
    private static Scanner cusScanner;
    static Scanner Table = new Scanner(System.in);
    private static final File customer_file = new File("src/main/java/customer.csv");

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

    public String getTableID() {return tableID;}
    public String getFoodID() {return foodID;}
    public String getRemarks() {return Remarks;}


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

    public static void confirmFood() throws IOException{
        readFile();
        System.out.println("Press enter to submit your order...");
        try{
            System.in.read();
            writeFileAsAppend();
        }
        catch(Exception e){}
    }

    private static void writeFileAsAppend() throws IOException {
        List<String> lines = readFileAsSequencesOfLines();
        Path path = getWriteFilePath();         
        Files.write(path, lines, StandardOpenOption.APPEND);
    }

    private static List<String> readFileAsSequencesOfLines() throws IOException {
        Path path = getReadFilePath();          
        List<String> lines = Files.readAllLines(path);    
        return lines;
    }

    private static Path getReadFilePath() {
        Path path = Paths.get("src/main/java/customer.csv");    
        return path.normalize();
    }

    private static Path getWriteFilePath() {
        Path path = Paths.get("src/main/java/order.csv");    
        return path;
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
        String tempFile = "src/main/java/tempCustomer.csv";
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
        Scanner id = new Scanner(System.in);
        System.out.println("What is your table ID?");
        int tID = id.nextInt();
        if (tID >= 1 && tID <= 50) {
            System.out.println("Enter the food ID you want to order: ");
            String fID = id.next();
            System.out.println("Please enter your remarks or put a dash (-) if you do not have any remarks: ");
            String r = id.next();
            String filepath = "src/main/java/customer.csv";
            
            saveCustomer(tID, fID, r, filepath);
        } else {
            System.out.println("Table ID not found!");
            System.out.println("Please try again!");
        } 
    }

    public static void saveCustomer(int tID, String fID, String r, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(tID + "," + fID + "," + r);
            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null, "Your order has been saved.");
            option();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Your order has NOT been saved.");
        } 
    }

    private static void option() throws IOException {
        try {
            System.out.println("1. Add food");          //addFood();
            System.out.println("2. Remove food");       //removeFood();
            System.out.println("3. Confirm food");      //confirmFood();
            System.out.println("4. Update food");       //updateFood();
            System.out.println("5. Exit");
            System.out.println(" ");
            System.out.print("Selection: ");
            String select = inp.next();
            switch (select) {
                case "1":
                    addFood();
                    break;

                case "2":
                    Scanner sc= new Scanner(System.in);
                    String filepath = "src/main/java/customer.csv";
                    readFile();
                    System.out.println("What do you want to remove?");
                    String removeTerm = sc.next();
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
                    option();
                }
        } catch (Exception e) {
            System.out.print("\nPlease enter a valid number!\n");
            System.out.println("(Range: 1-5)");
            e.printStackTrace();
            option();
        }
    }


    public static void main(String args[]) throws Exception{
        Scanner selection = new Scanner(System.in);

        System.out.println("Welcome to ABC Restaurant!");
        System.out.println("Here is the menu of our restaurant!");
        System.out.println(" ");

            FileReader filereader = new FileReader("src/main/java/menu.csv");
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            } csvReader.close();
      
            System.out.println("");
            System.out.println("What do you want to do next?");
            option();
    } 
}

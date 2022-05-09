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
    private static Scanner cusScanner = new Scanner(System.in);
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

    public static boolean isNewCustomerFile() {
        File menu = new File("customer.csv");
        return menu.length() == 0;
    }

    public static boolean isNewOrderFile() {
        File menu = new File("order.csv");
        return menu.length() == 0;
    }

    public static boolean isNewMenuFile() {
        File menu = new File("menu.csv");
        return menu.length() == 0;
    }

    private static void addCustomerFood(String tID, String fID, String _r) throws IOException {
        File menu_file = new File("src/main/java/customer.csv");

        try {
            FileWriter output_adminFile = new FileWriter(menu_file, true);
            CSVWriter writer = new CSVWriter(output_adminFile);

            if (isNewCustomerFile()) {
                String [] header = {"TableID", "FoodID", "Remarks"};
                writer.writeNext(header);
            }

            String [] user = {tID, fID, _r};
            writer.writeNext(user);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        option();
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

    private static void addConfirmFood(String fID, String _q) throws IOException {
        File menu_file = new File("src/main/java/order.csv");

        try {
            FileWriter output_adminFile = new FileWriter(menu_file, true);
            CSVWriter writer = new CSVWriter(output_adminFile);

            if (isNewCustomerFile()) {
                String [] header = {"FoodID", "Quantity"};
                writer.writeNext(header);
            }

            String [] user = {fID, _q};
            writer.writeNext(user);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        option();
    }

    public static void confirmFood() throws IOException{
        readFile();
        System.out.println("Press enter to submit your order...");
        try{
            String fID = "";
            String _q = "";
            addConfirmFood(fID, _q);
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

    public static void updateFood() throws IOException{
        
    }

    private static void removeFood() throws IOException{
        File menu_file = new File("customer.csv");
        File tempFile = new File("customer.csv");

        String tID, fID, _r;
        Scanner scan = new Scanner(customer_file);
        scan.useDelimiter("[,\n]");

        System.out.print("Please enter the FoodID you want to remove:  ");
        String idf = inp.next();

        try {
            FileWriter output_customerFile = new FileWriter(tempFile, true);
            CSVWriter writer = new CSVWriter(output_customerFile);

            while (scan.hasNext()) {
                tID = scan.next().replace("\"", "");
                fID = scan.next().replaceAll("[\",]", "");
                _r = scan.next().replaceAll("[\",]", "");

                if(tID.compareTo(idf) != 0) {
                    String [] food = {tID, fID, _r};
                    writer.writeNext(food);
                }
            }

            writer.close();
            scan.close();
            menu_file.delete();
            File dummies = new File("customer.csv");
            tempFile.renameTo(dummies);
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        option();
    }

    public static void addFood() throws IOException{
        try {
            readFile();
            Scanner id = new Scanner(System.in);
            System.out.println("What is your table ID?");
            String tID = id.next();
            System.out.println("Enter the food ID you want to order: ");
            String fID = id.next();
            System.out.println("Please enter your remarks or put a dash (-) if you do not have any remarks: ");
            String _r = id.next();
            String filepath = "src/main/java/customer.csv";
            
            addCustomerFood(tID, fID, _r);
            
        } catch (Exception e) {
            e.printStackTrace();
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
                    removeFood();
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

import java.io.*;
import java.util.*;
import javax.swing.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class Customer {
  private String tableID;
  private String Remarks;
  private String foodID;
  private int Quantity;
  public static Scanner inp = new Scanner(System.in);
  private static final File admin_file = new File("src/main/java/customer.csv");

  Customer() {

  }

  Customer(String tID, String fID, String r, int q) {
    this.tableID = tID;
    this.foodID = fID;
    this.Remarks = r;
    this.Quantity = q;
  }

  private String returnTableID() {
    return this.tableID;
  }

  private String returnFoodID() {
    return this.foodID;
  }

  private String returnRemarks() {
    return this.Remarks;
  }

  private int returnQuantity() {
    return this.Quantity;
  }

  private static String generateID() {
    Random rand = new Random();
    int max = 2000;
    int min = 1000;
    int roll = 9;
    return "customer"
            + rand.nextInt(max - min)
            + rand.nextInt(roll)
            + rand.nextInt(max - min)
            + rand.nextInt(roll);
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

  public static boolean isNewCustomerFile() {
    File customer = new File("src/main/java/customer.csv");
    return customer.length() == 0;
  }

    public static boolean isNewOrderFile() {
      File order = new File("src/main/java/order.csv");
      return order.length() == 0;
    }

    public static boolean isNewMenuFile() {
      File menu = new File("src/main/java/menu.csv");
      return menu.length() == 0;
    }

    private static String generateOID() {
      Random rand = new Random();
      int max = 200;
      int min = 100;
      int roll = 9;
      return "OID"
              + rand.nextInt(max - min)
              + rand.nextInt(roll)
              + rand.nextInt(max - min)
              + rand.nextInt(roll);
  }

  private static void addFood(String tID, String fID,String q) throws IOException {
    File menu_file = new File("src/main/java/customer.csv");
    String oid = generateOID();

    try {
        FileWriter output_customerFile = new FileWriter(order_file, true);
        CSVWriter writer = new CSVWriter(output_customerFile);

        if (isNewCustomerFile()) {
            String [] header = {"TableID", "FoodID", "Quantity"};
            writer.writeNext(header);
        }

        if(findFIDMatch(oid)) {
            addFood(tID, fID, r);
        }

        String [] user = {oid, tID, fID, r};
        writer.writeNext(user);
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    option();
}

private static boolean findFIDMatch(String id) throws FileNotFoundException {
    File menu_file = new File("src/main/java/customer.csv");
    Scanner scan = new Scanner(customer_file);
    scan.useDelimiter(",");
    Vector menuID = new Vector();

    try {
        for (int i = 0; i < 4; i++) {
            scan.next();
        }

        while(scan.hasNext()) {
            customerID.add(scan.next());
            scan.next();
            scan.next();
            scan.next();
        }
    } catch (Exception e) {

    }

    scan.close();

    if (isNewCustomerFile()) {
        return true;
    }
  }

  private static void deleteFood() throws IOException {
    File menu_file = new File("src/main/java/customer.csv");
    File tempFile = new File("src/main/java/customer.csv");

    String oid, tID, fID, r;
    Scanner scan = new Scanner(customer_file);
    scan.useDelimiter("[,\n]");

    System.out.print("Please Enter the ID of food that you wish to delete : ");
    String fid = inp.next();

    try {
        FileWriter output_customerFile = new FileWriter(tempFile, true);
        CSVWriter writer = new CSVWriter(output_customerFile);

        while (scan.hasNext()) {
            oid = scan.nextLine().replace("\"", "");
            tID = scan.nextLine().replaceAll("[\",]", "");
            fID = scan.nextLine().replaceAll("[\",]", "");
            r = scan.nextLine().replaceAll("[\",]", "");

            if(oid.compareTo(fid) != 0) {
                String [] food = {oid, tID, fID, r};
                writer.writeNext(food);
            }
        }

        writer.close();
        scan.close();
        customer_file.delete();
        File dummies = new File("src/main/java/customer.csv");
        tempFile.renameTo(dummies);
        tempFile.delete();
    } catch (Exception e) {
        e.printStackTrace();
    }

    option();
}

  private static void showMenu() throws IOException, CsvValidationException {
    FileReader filereader = new FileReader("src/main/java/menu.csv");
    CSVReader csvReader = new CSVReader(filereader);
    String[] nextRecord;

    while ((nextRecord = csvReader.readNext()) != null) {
      for (String cell : nextRecord) {
        System.out.print(cell + "\t");
      }

      System.out.println();
    } csvReader.close();
  }

/*
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
        File menu_file = new File("customer.csv"); 
        File tempFile = new File("customer.csv");

        String tID = "";
        String fID = "";
        String _r = "";
        Scanner scan = new Scanner(customer_file);
        scan.useDelimiter("[,\n]"); 

        System.out.print("Please enter the FoodID you want to update:  ");
        String idf = inp.next();

        String newtID = tID;
        String newfID = fID;
        String new_r = _r;

        try {
            FileWriter output_customerFile = new FileWriter(tempFile, true);
            CSVWriter writer = new CSVWriter(output_customerFile);

            System.out.print("Please choose which part to update(1- tableID, 2- FoodID, 3- Remarks): ");
            String idf2 = inp.next();

            if (idf2.equals("1")) {
                System.out.print("Please enter new tableID: ");
                newtID = inp.next();
                System.out.print("TableID has been updated!");
            }
            else if (idf2.equals("2")) {
                System.out.print("Please enter new FoodID: ");
                newfID = inp.next();
                System.out.print("FoodID has been updated!");
            }
            else if (idf2.equals("3")) {
                System.out.print("Please enter new remarks: ");
                new_r = inp.next();
                System.out.print("Remarks has been updated!");
            }
            else {
                System.out.println("Invalid choice!");
            }

            while (scan.hasNext()) {
                tID = scan.next().replace("\"", newtID);
                fID = scan.next().replaceAll("[\",]", newfID);
                _r = scan.next().replaceAll("[\",]", new_r);

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

    }
  */

  private static void option() throws IOException {
    try {
      System.out.print("1. Add food");
      System.out.print("2. Delete food");
      System.out.print("3. Update food");
      System.out.print("4. Confirm food");
      System.out.print("Please choose an option [1 - 4]: ");
      String opt = inp.next();
      switch (opt) {
          case "1" -> {
              addFood();
              break;
          }
          case "2" -> {
              deleteFood();
              break;
          }
          case "3" -> {
              updateFood();
              break;
          }
          case "4" -> {
              confirmFood();
              break;
          }
          
          default -> {
              System.out.print("\nPlease enter a valid number from\n");
              System.out.println("range [1 - 4]");
              option();
          }
      }
    } catch (Exception e) {
      System.out.println("\nPlease enter a valid number from\n");
      System.out.println("range [1 - 4] & do not enter any");
      System.out.println("alphabet from [a - z]");
      e.printStackTrace();
      option();
      }
  }

  public static void main(String args[]) throws Exception{
    System.out.println("Welcome to ABC Restaurant!");
    System.out.println("Here is our menu:");
    showMenu();
    option();
  }
}
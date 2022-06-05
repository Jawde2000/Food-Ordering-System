import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer extends Menu{
    private static String tableID;
    private static String OrderID;
    private static String Remarks;
    private static String cusName;
    private static String Fprice;
    private static double totalPrice = 0.0;
    private static ArrayList<String> foodD = new ArrayList<>();
    private static ArrayList<String> foodQ = new ArrayList<>();
    private static ArrayList<String> foodS = new ArrayList<>();
    private static ArrayList<String> foodP = new ArrayList<>();
    private static ArrayList<String> foodN = new ArrayList<>();
    private static ArrayList<String> getPrice = new ArrayList<>();
    private static String order_status = "false";
    public static Scanner inp = new Scanner(System.in);
    public static DecimalFormat df = new DecimalFormat("0.00");

    Customer() {
        tableID = "";
        OrderID = "";
        Remarks = "";
        cusName = "";
        Fprice = "0.0";
        totalPrice = 0.0;
        foodD.clear();
        foodQ.clear();
        foodS.clear();
        foodP.clear();
        foodN.clear();
        getPrice.clear();
        order_status = "false";
    }

    Customer(String tID, String r, String cus, String oid) {
        tableID = tID;
        Remarks = r;
        cusName = cus;
        OrderID = oid;
    }

    public static String getTable() {
        return tableID;
    }

    public static String getRemark() {
        return Remarks;
    }

    public static String getCusName() {
        return cusName;
    }

    public static String getOrderID() {
        return OrderID;
    }

    public static double getTotalPrice() throws FileNotFoundException {
        totalPrice = 0.0;
        foodP.clear();
        if (!isNewFoodList()) {
            loadFoodList();
        }
        for (String s : foodP) {
            totalPrice += Double.parseDouble(s);
        }

        return (totalPrice * 0.06) + totalPrice;
    }

    public static String getFinalPrice(double p) {
        return df.format(p);
    }

    private static boolean checkMatch(String oid) {
        File all_order  = new File("src/main/java/AllOrder.csv");

        try {
            Scanner scan = new Scanner(all_order);
            scan.useDelimiter("[\n,]");

            while (scan.hasNext()) {
                String id = scan.next().replaceAll("[\",]", "");
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();

                if (id.equals(oid)) {
                    return false;
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {

        }
        return true;
    }

    public static void viewMenu() throws FileNotFoundException {
        displayMenuList();
        System.out.print("Please enter your option : ");
        String opt = inp.next();

        if (opt.equals("5")) {
            mainMenu();
        } else if (opt.equals("1") || opt.equals("2") || opt.equals("3") || opt.equals("4")) {
            load_menu(opt);
        } else {
            System.out.println("Option not exist...");
            viewMenu();
        }

    }

    public static void confirmFinalFood() throws FileNotFoundException {
//        File order = new File("src/main/java/" + getOrderID() + "_" + tableID + ".csv");
        File All_order = new File("src/main/java/AllOrder.csv");

        StringBuilder conID = new StringBuilder();
        StringBuilder conQ = new StringBuilder();
        StringBuilder conS = new StringBuilder();
        StringBuilder conP = new StringBuilder();
        StringBuilder conF = new StringBuilder();

        foodD.clear();
        foodQ.clear();
        foodS.clear();
        foodP.clear();

        if (!isNewFoodList()) {
            loadFoodList();
        }

        for (String id: foodD) {
            conID.append(id);
            conID.append("|");
        }

        for (String qs: foodQ) {
            conQ.append(qs);
            conQ.append("|");
        }

        for (String s: foodS) {
            conS.append(s);
            conS.append("|");
        }

        for (String p: foodP) {
            conP.append(df.format(Double.parseDouble(p)));
            conP.append("|");
        }

        for (String f: foodN) {
            conF.append(f);
            conF.append("|");
        }

        try {
//            FileWriter output_order = new FileWriter(order);
//            CSVWriter writer = new CSVWriter(output_order);
            FileWriter output_orderAll = new FileWriter(All_order, true);
            CSVWriter writerAll = new CSVWriter(output_orderAll);

//            String [] orderDetails = {getTable(), getOrderID(), getCusName(),String.valueOf(conID), String.valueOf(conQ), String.valueOf(conS),
//                    String.valueOf(conP), String.valueOf(Fprice), "false", getRemark()};
            String [] orderDetailsCopy = {getOrderID(), getTable(), String.valueOf(conF),String.valueOf(conID), String.valueOf(conQ), String.valueOf(conS),
                    String.valueOf(conP), String.valueOf(Fprice), "false", getRemark()};
//            writer.writeNext(orderDetails);
            writerAll.writeNext(orderDetailsCopy);
//            writer.close();
            writerAll.close();

            System.out.println("Order Success...Please wait for order");
        } catch (IOException e) {
            System.out.println("Order failed...");
        }

        waitingList();
    }

    public static void updateFood() {
        String food_size;
        String food_price = "0.0";
        String large_price = "0.0";
        String medium_price = "0.0";
        String small_price = "0.0";

        try {
            if (!isNewFoodList()) {
                loadFoodList();
                loadID();
                loadSize();
                loadPrice();
                loadName();
            }
            File foodList = new File("src/main/java/foodList.csv");
            File tempFLFile = new File("src/main/java/tempFoodList.csv");
            Scanner scan = new Scanner(foodList);
            scan.useDelimiter("[\n,]");
            FileWriter output_FLFile = new FileWriter(tempFLFile, true);
            CSVWriter writer = new CSVWriter(output_FLFile);
            System.out.println("---------------------------------------------");
            food_draft();
            System.out.println("---------------------------------------------");

            System.out.print("Please enter the number that you want to modify: ");
            String idf = inp.next();

            while (scan.hasNext()) {
                String id = scan.next().replaceAll("[\",]", "");
                String foodName = scan.next().replaceAll("[\",]", "");
                String quantity = scan.next().replaceAll("[\",]", "");
                String size = scan.next().replaceAll("[\",]", "");
                String tp = scan.next().replaceAll("[\",]", "");
                if (id.equals(foodD.get(Integer.parseInt(idf) - 1))) {
                    //modify based on number quantity or size
                    System.out.println("You can only modify quantity and size");
                    System.out.println("Press 1 for quantity");
                    System.out.println("Press 2 for size");
                    System.out.print("Please Choose the food information you want to modify : ");
                    String mo_num = inp.next();

                    switch (mo_num) {
                        case "1" -> {
                            String mo_value = updateQuantity();
                            double total = Double.parseDouble(tp);
                            int q = Integer.parseInt(quantity);
                            int modify = Integer.parseInt(mo_value);
                            String ttp = df.format(total/q * modify);
                            String[] food = {id, foodName, mo_value, size, ttp};
                            writer.writeNext(food);
                            System.out.println(foodName + " Quantity Modified...");
                        }
                        case "2" -> {
                            if (size.equals("None")) {
                                System.out.println("Item selected has no size");
                                System.out.println("Please select again");
                                updateFood();
                            } else {
                                for (int i = 0; i < fid.size(); i++) {
                                    if (fid.get(i).equals(id)) {
                                        boolean _2Size = false;

                                        loadSize();

                                        if (size.equals("Large") || size.equals("Medium") || size.equals("Small")) {
                                            String [] price_ = price.get(i).split(" ");
                                            for (int j = 0; j < price_.length; j++) {
                                                if (price_[j].equals("0")) {
                                                    _2Size = true;
                                                } else {
                                                    getPrice.add(price_[j]);
                                                }
                                            }

                                            if (getPrice.get(0).equals("0")&& !getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")) {
                                                large_price = getPrice.get(0);
                                                small_price = getPrice.get(1);
                                                System.out.println("1. Large : RM " + getPrice.get(0));
                                                System.out.println("2. Small : RM " + getPrice.get(1));
                                            } else if (getPrice.get(0).equals("0") && !getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")){
                                                large_price = getPrice.get(1);
                                                small_price = getPrice.get(2);
                                                System.out.println("1. Large : RM " + getPrice.get(1));
                                                System.out.println("2. Small : RM " + getPrice.get(2));
                                            } else if (!getPrice.get(0).equals("0") && getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")) {
                                                large_price = getPrice.get(0);
                                                small_price = getPrice.get(2);
                                                System.out.println("1. Large : RM " + getPrice.get(0));
                                                System.out.println("2. Small : RM " + getPrice.get(2));
                                            }else if (!getPrice.get(0).equals("0") && !getPrice.get(1).equals("0") && getPrice.get(2).equals("0")) {
                                                large_price = getPrice.get(0);
                                                small_price = getPrice.get(1);
                                                System.out.println("1. Large : RM " + getPrice.get(0));
                                                System.out.println("2. Small : RM " + getPrice.get(1));
                                            } else {
                                                large_price = getPrice.get(0);
                                                medium_price = getPrice.get(1);
                                                small_price = getPrice.get(2);
                                                System.out.println("1. Large : RM " + getPrice.get(0));
                                                System.out.println("2. Medium : RM " + getPrice.get(1));
                                                System.out.println("3. Small : RM " + getPrice.get(2));
                                            }

                                            System.out.print("Please enter the new size: ");
                                            food_size = inp.next();

                                            switch (food_size) {
                                                case "1" -> {
                                                    food_price = large_price;
                                                    food_size = "Large";
                                                }
                                                case "2" -> {
                                                    if (!_2Size) {
                                                        food_price = medium_price;
                                                        food_size = "Medium";
                                                    } else {
                                                        food_price = small_price;
                                                        food_size = "Small";
                                                    }
                                                }
                                                case "3" -> {
                                                    food_price = small_price;
                                                    food_size = "Small";
                                                }
                                                default -> {
                                                    System.out.println("Option not exist...");
                                                    mainMenu();
                                                }
                                            }

                                            double fp = Double.parseDouble(food_price);
                                            int quan = Integer.parseInt(quantity);
                                            String ttp = df.format(fp * quan);

                                            String[] food = {id, foodName, quantity, food_size, ttp};
                                            writer.writeNext(food);
                                            System.out.println(foodName + " Quantity Modified...");
                                            break;
                                        }
                                    }
                                }

                            }
                        }
                    }
                } else {
                    String[] food = {id, foodName, quantity, size, tp};
                    writer.writeNext(food);
                }
            }
            writer.close();
            scan.close();
            foodList.delete();
            File dummies = new File("src/main/java/foodList.csv");
            tempFLFile.renameTo(dummies);
            tempFLFile.delete();
            mainMenu();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public static String updateQuantity() {
        int quantity = 0;
        System.out.println("---------------------------------------------");
        try {
            System.out.print("Please enter the new quantity : ");
            quantity = inp.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Only number accepted...");
        }
        System.out.println("---------------------------------------------");
        return Integer.toString(quantity);
    }

    public static void removeFood() {
        try {
            if (!isNewFoodList()) {
                loadFoodList();
            }

            File foodList = new File("src/main/java/foodList.csv");
            File tempFLFile = new File("src/main/java/tempFoodList.csv");
            Scanner scan = new Scanner(foodList);
            scan.useDelimiter("[\n,]");
            FileWriter output_FLFile = new FileWriter(tempFLFile, true);
            CSVWriter writer = new CSVWriter(output_FLFile);
            System.out.println("---------------------------------------------");
            food_draft();
            System.out.println("---------------------------------------------");

            System.out.print("Please enter the number that you want to remove: ");
            String idf = inp.next();

            while (scan.hasNext()) {
                String id = scan.next().replaceAll("[\",]", "");
                String foodName = scan.next().replaceAll("[\",]", "");
                String quantity = scan.next().replaceAll("[\",]", "");
                String size = scan.next().replaceAll("[\",]", "");
                String tp = scan.next().replaceAll("[\",]", "");
                if (!id.equals(foodD.get(Integer.parseInt(idf) - 1))) {
                    String[] food = {id, foodName, quantity, size, tp};
                    writer.writeNext(food);
                }
            }
            writer.close();
            scan.close();
            foodList.delete();
            File dummies = new File("src/main/java/foodList.csv");
            tempFLFile.renameTo(dummies);
            tempFLFile.delete();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public static boolean isNewFoodList() {
        File foodList = new File("src/main/java/foodList.csv");

        return foodList.length() == 0;
    }

    private static void loadStatus() throws FileNotFoundException {
        File order_file = new File("src/main/java/AllOrder.csv");
        Scanner scan = new Scanner(order_file);
        scan.useDelimiter("[,\n]");

        while (scan.hasNext()) {
            String id = scan.next().replaceAll("[\"]", "");
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            String status = scan.next().replaceAll("[\"]", "");
            if (id.equals(OrderID)) {
                order_status = status;
                break;
            }
            scan.next();
        }

        scan.close();
    }

    public static void Order_menu() throws FileNotFoundException {
        loadStatus();
        String status = order_status.equals("true")? "Ready" : "Not Ready";
        System.out.println("---------------------------------------------");
        System.out.println("Track Order Status : " + status);
        System.out.println("Table    : " + getTable());
        System.out.println("Customer : Mr/Ms " + getCusName());
        System.out.println("Order ID : " + getOrderID());
        System.out.println("TOTAL    : " + Fprice);
        System.out.println("1. View your order");
        System.out.println("2. Refresh Status");
        System.out.println("3. Log out");
        System.out.println("Reminder :Please log out");
        System.out.println("          when your order");
        System.out.println("          arrived...");
    }

    public static void waitingList() throws FileNotFoundException {
        Order_menu();
        System.out.print("Please Enter your option : ");
        String opt = inp.next();

        if (opt.equals("1")) {
            System.out.println("---------------------------------------------");
            System.out.println("Hi Mr/Ms " + getCusName() + ", here's your order");
            System.out.println("---------------------------------------------");
            food_draft();
            System.out.println("Type any key to back to waiting list");
            System.out.print("Please enter your option : ");
            String optw = inp.next();

            if (optw.equals(optw)) {
                waitingList();
            }
        } else if (opt.equals("2")) {
            waitingList();
        } else if (opt.equals("3")) {
            loadStatus();
            if (order_status.equals("true")) {
                File foodList = new File("src/main/java/foodList.csv");
                foodList.delete();
                new Customer();
                mainMenu();
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Your order is not ready yet. Please wait your order");
                System.out.println("status change to [Ready] then you can proceed to log");
                System.out.println("out..");
                waitingList();
            }
        } else {
            System.out.println("Option not exist");
            waitingList();
        }
    }

    public static void addFood(String fid, int q, String sz, double tp, String foodName) throws FileNotFoundException {
        File foodList = new File("src/main/java/foodList.csv");

        try {
            FileWriter output_foodList = new FileWriter(foodList, true);
            CSVWriter writer = new CSVWriter(output_foodList);

            String [] food = {fid, foodName,String.valueOf(q), sz, String.valueOf(tp)};
            writer.writeNext(food);
            writer.close();
            if (!isNewFoodList()) {
                loadFoodList();
            }
            System.out.println("Food added successfully...");
        } catch (IOException e) {
            System.out.println("Food added failed...");
        }
    }

    public static void find_addFood(String opt) throws FileNotFoundException {
        System.out.println("Press 0 to back to main menu");
        if (opt.equals("0")) {
            viewMenu();
        } else {
            findfood();
        }

    }

    public static void displayOpt() throws FileNotFoundException {
        System.out.println("1. Order Food");
        System.out.println("2. View Menu");
        if (!isNewFoodList()) {
            System.out.println("3. Update Food");
            System.out.println("4. Remove Food");
            System.out.println("5. Confirm Food");
        }
    }

    private static String generateOID() {
        Random rand = new Random();
        int max = 200;
        int min = 100;
        int roll = 9;

        String oid = "OID"
                + rand.nextInt(max - min)
                + rand.nextInt(roll)
                + rand.nextInt(max - min)
                + rand.nextInt(roll);

        if (!checkMatch(oid)) {
            generateOID();
        }

        return oid;
    }

    private static void mainMenu() throws FileNotFoundException {
        if(!isNewFoodList()) {
            System.out.println("---------------------------------------------");
            System.out.println("\t\t\tYour Current Order");
            System.out.println("---------------------------------------------");
            food_draft();
            System.out.println("---------------------------------------------");
        }

        Admin title = new Admin();
        if (!title.isNewRestaurant()) {
            title.retrieveResName("2");
        } else {
            title.retrieveResName("0");
        }
        displayOpt();
        System.out.print("Please enter your option: ");
        String opt = inp.next();

        if (opt.equals("1")) {
            viewMenu();
            find_addFood(opt);
        } else if (opt.equals("2")) {
            viewMenu();
            System.out.println("1. Back to Category menu");
            System.out.println("2. Order Food");
            System.out.print("Please enter your option : ");
            String option = inp.next();
            if (option.equals("1")) {
                viewMenu();
            } else if (option.equals("2") || option.equals("3") || option.equals("4")) {
                find_addFood("1");
            } else {
                System.out.println("Option not exist...");
                mainMenu();
            }
        } else if (opt.equals("3")) {
            updateFood();
        } else if (opt.equals("4")) {
            removeFood();
        } else if (opt.equals("5")) {
            confirmFood();
        }
    }

    private static void food_draft() throws FileNotFoundException {
        Fprice = "0.0";
        if (!isNewFoodList()) {
            loadFoodList();
        }

        for (int i = 0; i < foodQ.size(); i++) {
            System.out.format((i + 1) + ") %-1s x %-20s %-9s %-11s" ,foodQ.get(i), foodN.get(i), foodS.get(i), df.format(Double.parseDouble(foodP.get(i))));
            System.out.println();
        }

        Fprice = getFinalPrice(getTotalPrice());
        System.out.println("---------------------------------------------");
        System.out.format("%-37s " + Fprice + "\n", "TOTAL (Including GST 6%)");
    }

    private static void confirmFood() throws FileNotFoundException {
        loadName();
        loadID();

        System.out.println("---------------------------------------------");
        System.out.println("Hi customer, here's your draft order list");
        System.out.println("---------------------------------------------");
        food_draft();
        System.out.println("---------------------------------------------");
        System.out.println("Do you have any remarks?");
        System.out.println("Press 1 for yes");
        System.out.println("Press 2 for no");
        System.out.println("Press 0 for back to main menu");
        System.out.print("Enter your choice : ");
        String opt = inp.next();

        String remarks = "-";

        if (opt.equals("1")) {
            System.out.println("*Remarks: ");
            System.out.println("---------------------------------------------");
            inp.nextLine();
            remarks = inp.nextLine();
            optionToFinal(remarks);
        } else if (opt.equals("2")) {
            optionToFinal(remarks);
        } else if (opt.equals("0")) {
            mainMenu();
        } else {
            System.out.println("Option not exist...");
            mainMenu();
        }
    }

    private static void optionToFinal(String remarks) throws FileNotFoundException {
        System.out.println("---------------------------------------------");
        System.out.println("Are you sure this is your final order?");
        System.out.println("Order are final decision and not refundable");
        System.out.println("Press 1 for final confirmation");
        System.out.println("Press 2 for back to main menu");
        System.out.print("Enter your option : ");
        String fo = inp.next();

        if (fo.equals("1")) {
            String oid = generateOID();
            System.out.print("Please enter your table number : ");
            String tableID = inp.next();
            inp.nextLine();
            System.out.print("Please enter your Name         : Ms/Mr ");
            String name = inp.nextLine();
            System.out.println("---------------------------------------------");
            Customer cus = new Customer(tableID, remarks, name, oid);
            cus.confirmFinalFood();
        } else if (fo.equals("2")) {
            mainMenu();
        } else {
            System.out.println("Option not exist...");
            confirmFood();
        }
    }

    private static void loadFoodList() throws FileNotFoundException {
        File foodList = new File("src/main/java/foodList.csv");
        Scanner scanNew = new Scanner(foodList);
        scanNew.useDelimiter("[,\n]");
        foodS.clear();
        foodP.clear();
        foodQ.clear();
        foodD.clear();
        foodN.clear();

        while (scanNew.hasNext()) {
            foodD.add(scanNew.next().replaceAll("[\"]", ""));
            foodN.add(scanNew.next().replaceAll("[\"]", ""));
            foodQ.add(scanNew.next().replaceAll("[\"]", ""));
            foodS.add(scanNew.next().replaceAll("[\"]", ""));
            foodP.add(scanNew.next().replaceAll("[\"]", ""));
        }

        scanNew.close();
    }

    public static void findfood() throws FileNotFoundException {
        boolean found_food = false;
        boolean food_inFile = false;
        String food_size = "None";
        int quantity = 0;
        String food_price = "0.0";
        String large_price = "0.0";
        String medium_price = "0.0";
        String small_price = "0.0";
        System.out.print("Please enter the food(ID) you wish to add: ");
        String foodID = inp.next();
        if (foodID.equals("0")) {
            mainMenu();
        }

        loadID();
        loadStatus();
        for (String Fid: fid) {
            if(foodID.equals(Fid)) {
                found_food = true;
            }
        }

        if(!found_food) {
            System.out.println("Food not found...");
            mainMenu();
        }

        try {
            System.out.print("Please enter the quantity: ");
            quantity = inp.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Only number is accepted...");
            findfood();
        }

        loadSize();
        loadPrice();
        getPrice.clear();

        for (int i = 0; i < fid.size(); i++) {
            if (fid.get(i).equals(foodID)) {
                boolean _2Size = false;
                if (size.get(i).equals("Large|Medium|Small")) {
                    String [] price_ = price.get(i).split("\\s+");
                    for (int j = 0; j < price_.length; j++) {
                        if (price_[j].equals("0")) {
                            _2Size = true;
                        }
                        getPrice.add(price_[j]);
                    }

                    if (!getPrice.get(0).equals("0")&& !getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")) {
                        large_price = getPrice.get(0);
                        small_price = getPrice.get(1);
                        System.out.println("1. Large : RM " + getPrice.get(0));
                        System.out.println("2. Small : RM " + getPrice.get(1));
                    } else if (getPrice.get(0).equals("0") && !getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")){
                        large_price = getPrice.get(1);
                        small_price = getPrice.get(2);
                        System.out.println("1. Large : RM " + getPrice.get(1));
                        System.out.println("2. Small : RM " + getPrice.get(2));
                    } else if (!getPrice.get(0).equals("0") && getPrice.get(1).equals("0") && !getPrice.get(2).equals("0")) {
                        large_price = getPrice.get(0);
                        small_price = getPrice.get(2);
                        System.out.println("1. Large : RM " + getPrice.get(0));
                        System.out.println("2. Small : RM " + getPrice.get(2));
                    }else if (!getPrice.get(0).equals("0") && !getPrice.get(1).equals("0") && getPrice.get(2).equals("0")) {
                        large_price = getPrice.get(0);
                        small_price = getPrice.get(1);
                        System.out.println("1. Large : RM " + getPrice.get(0));
                        System.out.println("2. Small : RM " + getPrice.get(1));
                    } else {
                        large_price = getPrice.get(0);
                        medium_price = getPrice.get(1);
                        small_price = getPrice.get(2);
                        System.out.println("1. Large : RM " + getPrice.get(0));
                        System.out.println("2. Small : RM " + getPrice.get(1));
                        System.out.println("3. Large : RM " + getPrice.get(2));
                    }
                    System.out.print("Please enter size number: ");
                    food_size = inp.next();

                    switch (food_size) {
                        case "1" -> {
                            food_price = large_price;
                            food_size = "Large";
                        }
                        case "2" -> {

                            if (!_2Size) {
                                food_price = medium_price;
                                food_size = "Medium";
                            } else {
                                food_price = small_price;
                                food_size = "Small";
                            }
                        }
                        case "3" -> {
                            food_price = small_price;
                            food_size = "Small";
                        }
                        default -> {
                            System.out.println("Option not exist...");
                            mainMenu();
                        }
                    }
                    break;
                } else {
                    food_size = "None";
                    food_price = price.get(i);
                }
            }
        }

        double totalP = Double.parseDouble(food_price) * quantity;

        if (!isNewFoodList()) {
            loadFoodList();
        }

        String n = "";

        for (String id: foodD) {
            if(foodID.equals(id)) {
                food_inFile = true;
            }
        }

        if (food_inFile) {
            System.out.println("You can only modify the food selected");
            System.out.println("because it is already in the draft order");
        } else {
            for (int j = 0; j < fid.size(); j++) {
                if (foodID.equals(fid.get(j))) {
                    n = FoodName.get(j);
                    break;
                }
            }

            if(found_food) {
                addFood(foodID, quantity, food_size, totalP, n);
            } else {
                System.out.println("Food not exist...");
            }
        }

        mainMenu();
    }

    public static void main(String [] args) throws FileNotFoundException {
        mainMenu();
    }
}
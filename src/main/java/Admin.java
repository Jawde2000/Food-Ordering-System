import java.io.*;
import java.util.*;
import com.opencsv.*;

public class Admin {
    private String adminPass;
    private String cfPass;
    public static Scanner inp = new Scanner(System.in);
    private static final File admin_file = new File("src/main/java/admin.csv");

    Admin() {

    }

    Admin(String ps, String cfPass) {
        this.adminPass = ps;
        this.cfPass = cfPass;
    }

    private String returnPass() {
        return this.adminPass;
    }

    private String returnRPass() {
        return this.cfPass;
    }

    private boolean checkMatch() {
        if (returnPass().equals(returnRPass())) {
            return true;
        }

        return false;
    }

    private static boolean findIDMatch(String id) throws FileNotFoundException {
        Scanner scan = new Scanner(admin_file);
        scan.useDelimiter(",");
        Vector userID = new Vector();

        while(scan.hasNext()) {
            userID.add(scan.next());
            scan.next();
            scan.nextLine();
        }

        if (isNewAdminFile()) {
            return true;
        }

        int last = userID.size() - 1;
        Collections.sort(userID);
        System.out.println(userID);
        return searchID(userID, id);
    }

    private static boolean searchID(Vector id, String Id) {
        for (int i = 0; i < id.size(); i++) {
            if (id.get(i).toString().equals(Id)) {
                return true;
            }
        }

        return false;
    }

    private static String generateID() {
        Random rand = new Random();
        int max = 2000;
        int min = 1000;
        int roll = 9;
        return "admin"
                + rand.nextInt(max - min)
                + rand.nextInt(roll)
                + rand.nextInt(max - min)
                + rand.nextInt(roll);
    }

    private void deleteFood(String ID) {

    }

    private void modifyFood() {

    }

    public static boolean isNewAdminFile() {
        File admin = new File("src/main/java/admin.csv");

        return admin.length() == 0;
    }

    public static boolean isNewMenuFile() {
        File menu = new File("src/main/java/menu.csv");

        return menu.length() == 0;
    }

    public static void addAdmin(String name, String pass) throws FileNotFoundException {
        String id = generateID();

        try {
            if(findIDMatch(id)) {
                addAdmin(name, pass);
            } else {
                FileWriter output_adminFile = new FileWriter(admin_file, true);
                CSVWriter writer = new CSVWriter(output_adminFile);

                if(isNewAdminFile()) {
                    String [] header = {"UserID", "Username", "Password"};
                    writer.writeNext(header);
                }

                String [] user = {id, name, pass};
                writer.writeNext(user);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login() throws IOException {
        if(isNewAdminFile()) {
            new_admin_page();
        } else {
            adminLogin();
        }
    }

    public static void new_admin_page() throws IOException {
        System.out.println("===============================");
        System.out.println("\tWELCOME NEW ADMIN USER");
        System.out.println("===============================");
        System.out.println("First new user are required to ");
        System.out.println("write down the information below ");
        System.out.print("Username         : ");
        String username = inp.nextLine();
        System.out.print("Password         : ");
        String password = inp.nextLine();
        System.out.print("Confirm Password : ");
        String confirm_match = inp.nextLine();

        Admin admin = new Admin(password, confirm_match);
        if(!admin.checkMatch()) {
            System.out.println("Password are not matching with confirm password");
            login();
        } else {
            addAdmin(username, password);
        }
    }

    public static void adminLogin() throws IOException {
        System.out.println("===============================");
        System.out.println("\t\tWELCOME ADMIN");
        System.out.println("===============================");
        System.out.println("Admins are required to login");
        System.out.println("every times for security issue");
        System.out.print("User ID/username : ");
        String userId = inp.nextLine();
        System.out.print("Password         : ");
        String password = inp.nextLine();

        if (loginProcedure(userId, password)) {
            option();
        } else {
            System.out.println("\nInvalid UserID/Username/password");
            adminLogin();
        }
    }

    private static String food() {
        System.out.print("Food Name         : ");
        String name = inp.nextLine();
        return name;
    }

    private static String foodStatus() {
        System.out.println("a: Available  na: Not Available");
        System.out.print("Food Status[a/na] : ");
        String status = inp.nextLine();

        if (!(status.equals("a") || status.equals("na"))) {
            System.out.println("Must be either a or na");
            foodStatus();
        }

        return status;
    }

    private static String foodSize() {
        System.out.println("s: [Large, Medium, Small]");
        System.out.println("o: None");
        System.out.print("Size[s/o]        : ");
        String size = inp.nextLine();

        if (!(size.equals("s") || size.equals("o"))) {
            System.out.println("Must be range in s/o");
            foodSize();
        }

        if (size.equals("s")) {
            size = "Large, Medium, Small";
        } else if (size.equals("o")) {
            size = "None";
        }

        return size;
    }

    private static Vector price(String size) {
        Vector s_price = new Vector();
        String [] f_size = {"Large", "Medium", "Small"};

        if (size.equals("None")) {
            System.out.print("Food Price       : RM");
            String price = inp.nextLine();
            s_price.add(price);
        } else if (size.equals("Large, Medium, Small")) {
            System.out.println("Please enter price 0 when");
            System.out.println("specific size is not exist");
            for (int i = 0; i < 3; i++) {
                System.out.print("Food Price [" + f_size[i] + "] : RM");
                String price = inp.next();
                s_price.add(price);
            }
        }

        return s_price;
    }

    private static String category() {
        String [] category = {"Food", "Beverage", "Dessert"};
        String cate = null;

        try {
            System.out.println("1. " + category[0] + " 2. " + category[1] + " 3. " + category[2]);
            System.out.print("Category       : ");
            cate = inp.nextLine();

            if (!(cate.equals("1") || cate.equals("2") || cate.equals("3"))) {
                System.out.println("Please enter [1 - 3] only...");
                category();
            }
        } catch (Exception e) {
            System.out.println("Please enter [1 - 3] only...");
            category();
        }

        return category[Integer.parseInt(cate) - 1];
    }

    private static String bhv() {
        String bhv_s = null;
        String permit = null;
        String [] bhvStatus = {"Beef", "Halal", "Vegetarian"};

        System.out.println("Enter 1 if permissible exist");
        for (int i = 0; i < bhvStatus.length; i++) {
            System.out.print("Food Permissible [" + bhvStatus[i] + "] : ");
            permit = inp.nextLine();

            if (permit.equals("1")) {
                bhv_s = bhvStatus[i];
                break;
            }
        }

        if (permit.equals("0")) {
            bhv_s = "None";
        }

        return bhv_s;
    }

    private static void addNewMenu() throws IOException {
        Vector s_price;

        System.out.println("===============================");
        System.out.println("\t\t\tNEW MENU");
        System.out.println("===============================");
        String name = food();
        String status = foodStatus();
        String cate = category();
        String size = foodSize();
        s_price = price(size);
        String bhv = bhv();

        addFood(name, status, cate, size, bhv, s_price);
    }

    private static String generateFID() {
        Random rand = new Random();
        int max = 200;
        int min = 100;
        int roll = 9;
        return "FID"
                + rand.nextInt(max - min)
                + rand.nextInt(roll)
                + rand.nextInt(max - min)
                + rand.nextInt(roll);
    }

    private static void addFood(String FoodName, String status, String category, String size, String BHV, Vector price) throws IOException {
        File menu_file = new File("src/main/java/menu.csv");
        String fid = generateFID();

        try {
            FileWriter output_adminFile = new FileWriter(menu_file, true);
            CSVWriter writer = new CSVWriter(output_adminFile);

            if (isNewMenuFile()) {
                String [] header = {"FoodID", "FoodName", "Status", "Category", "Size", "BHV Status", "Price"};
                writer.writeNext(header);
            }

            if(findFIDMatch(fid)) {
                addFood(FoodName, status, category, size, BHV, price);
            }

            String [] user = {fid, FoodName, status, category, size, BHV, String.valueOf(price)};
            writer.writeNext(user);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        option();
    }

    private static boolean findFIDMatch(String id) throws FileNotFoundException {
        File menu_file = new File("src/main/java/menu.csv");
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter(",");
        Vector menuID = new Vector();

        try {
            for (int i = 0; i < 7; i++) {
                scan.next();
            }

            while(scan.hasNext()) {
                menuID.add(scan.next());
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
                scan.next();
            }
        } catch (Exception e) {

        }

        scan.close();

        if (isNewMenuFile()) {
            return true;
        }

        return searchID(menuID, id);
    }

    private static void modifyMenu() {

    }

    private static void deleteMenu() throws IOException {
        File menu_file = new File("src/main/java/menu.csv");
        File tempFile = new File("src/main/java/tempMenu.csv");

        String fid, FoodName, status, category, size, BHV, price;
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter(",");

        System.out.print("Please Enter the ID of food that you wish to delete : ");
        String idf = inp.nextLine();

        File new_menu_file = new File("src/main/java/menu.csv");

        try {
            FileWriter output_adminFile = new FileWriter(tempFile, true);
            CSVWriter writer = new CSVWriter(output_adminFile);

            while (scan.hasNext()) {
                fid = scan.next().replace("\"", "");
                FoodName = scan.next().replaceAll("[\",]", "");
                status = scan.next().replaceAll("[\",]", "");
                category = scan.next().replaceAll("[\",]", "");
                size = scan.next().replaceAll("[\",]", "");
                BHV = scan.next().replaceAll("[\",]", "");
                price = scan.nextLine().replaceAll("[\",]", "");

//                System.out.println(fid + FoodName + status + category + size + BHV + price);

                if(fid.compareTo(idf) != 0) {
                    String [] food = {fid, FoodName, status, category, size, BHV, price};
                    writer.writeNext(food);
                }
            }

            writer.close();
            scan.close();
            menu_file.delete();
            File dummies = new File("src/main/java/menu.csv");
            tempFile.renameTo(dummies);
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        option();
    }

    private static void logout() {
        System.out.println("Thank you for using the system");
        System.out.println("The system logged out.........");
    }

    private static void option() throws IOException {
        try {
            adminMenu();
            System.out.print("Please choose an option [1 - 5] : ");
            String opt = inp.nextLine();
            switch (opt) {
                case "1" -> addNewMenu();
                case "2" -> modifyMenu();
                case "3" -> deleteMenu();
                case "4" -> new_admin_page();
                case "5" -> logout();
                default -> {
                    System.out.print("\nPlease enter a valid number from\n");
                    System.out.println("range [1 - 5]");
                    option();
                }
            }
        } catch (Exception e) {
            System.out.print("\nPlease enter a valid number from\n");
            System.out.println("range [1 - 5] & do not enter any");
            System.out.println("alphabet from [a - z]");
            option();
        }
    }

    private static void adminMenu() {
        System.out.println("===============================");
        System.out.println("\t\t\tMenu");
        System.out.println("===============================");
        System.out.println("1) Add new menu");
        System.out.println("2) Modify menu");
        System.out.println("3) Delete menu");
        System.out.println("4) Add new admin");
        System.out.println("5) Log out");
    }

    private static boolean loginProcedure(String id, String pass) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/main/java/admin.csv"));
        scan.useDelimiter("[,\n]");
        String u_id;
        String u_name;
        String u_pass;

        try {
            for (int i =0; i < 1; i++) {
                scan.next();
                scan.next();
                scan.next();
            }

            while(scan.hasNext()) {

                u_id = scan.next();
                u_name = scan.next();
                u_pass = scan.next();

                u_id = u_id.replace("\"", "");
                u_name = u_name.replaceAll("[\",]", "");
                u_pass = u_pass.replaceAll("[\",]", "");

                if (id.equals(u_id) && pass.equals(u_pass)) {
                    return true;
                }

                if (id.equals(u_name) && pass.equals(u_pass)) {
                    return true;
                }
            }

            scan.close();
        } catch (Exception e) {
            System.out.println("ERROR 404");
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        //Admin interface
        //from here we are going to find if the admin file is empty, if the system found that admin is new user
        // the admin is required to type in admin name & admin password twice
        //if the admin login, he/she will have 4 options to choose which are view menu,
        // add menu, delete menu by id, and modify food information by id
        login();
    }
}

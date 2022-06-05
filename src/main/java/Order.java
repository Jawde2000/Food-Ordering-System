import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Order extends Customer{
    private static ArrayList<String> orderID = new ArrayList<>();
    private static ArrayList<String> tableID = new ArrayList<>();
    private static ArrayList<String> foodName = new ArrayList<>();
    private static ArrayList<String> menuID = new ArrayList<>();
    private static ArrayList<String> quantity = new ArrayList<>();
    private static ArrayList<String> size = new ArrayList<>();
    private static ArrayList<String> price_per = new ArrayList<>();
    private static ArrayList<String> totalP = new ArrayList<>();
    private static ArrayList<String> status = new ArrayList<>();
    private static ArrayList<String> remarks = new ArrayList<>();

    // default constructor all value 0
    Order() {}

    protected static boolean isNewOrderList() {
        File All_order = new File("src/main/java/AllOrder.csv");

        return All_order.length() == 0;
    }

    private static void loadOrder() {
        orderID.clear();
        tableID.clear();
        menuID.clear();
        quantity.clear();
        price_per.clear();
        totalP.clear();
        status.clear();
        remarks.clear();

        try {
            File All_order = new File("src/main/java/AllOrder.csv");
            Scanner scan = new Scanner(All_order);
            scan.useDelimiter("[,\n]");

            while (scan.hasNext()) {
                orderID.add(scan.next().replaceAll("[\",]", ""));
                tableID.add(scan.next().replaceAll("[\",]", ""));
                foodName.add(scan.next().replaceAll("[\",]", ""));
                menuID.add(scan.next().replaceAll("[\",]", ""));
                quantity.add(scan.next().replaceAll("[\",]", ""));
                size.add(scan.next().replaceAll("[\",]", ""));
                price_per.add(scan.next().replaceAll("[\",]", ""));
                totalP.add(scan.next().replaceAll("[\",]", ""));
                status.add(scan.next().replaceAll("[\",]", ""));
                remarks.add(scan.next().replaceAll("[\",]", ""));
            }

            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Database not found...");
        }
    }

    protected static void viewOrderDetails(String opt){
        loadOrder();
        String status_ = "true";
        String text = "";
        String textSpace = "";
        String text2 = "";

        if (opt.equals("1")) {
            status_ = "false";
            text2 = "Top Order will be prioritize";
            text = "ESTIMATE TOTAL PROFITS ";
            textSpace = "%-61s";
        } else if (opt.equals("2")) {
            status_ = "true";
            text = "TOTAL PROFITS ";
            textSpace = "%-70s";
        } else {
            System.out.println("Option not exist...");
            mainOrder();
        }
        if (isNewOrderList()) {
            System.out.println("=======================================");
            System.out.println("\t\tNo Order yet :(");
            System.out.println("=======================================");
        } else {
            try {
                loadName();
                loadID();
                loadOrder();
                String n = "";
                String q = "";
                String d = "";
                double total = 0.0;
                System.out.println("\n---------------------------------------------------" +
                        "-------------------------------" +
                        "---------------------------------------------------------------");
                System.out.format("%-60s" + "ORDER LIST", "");
                System.out.println("\n---------------------------------------------------" +
                        "-------------------------------" +
                        "---------------------------------------------------------------");
                System.out.println("----------------------------------------" +
                        "-------------------------------------" +
                        "--------------------------------------------------------------------");
                System.out.format("%-4s %-15s %-17s %-25s %-15s %-35s %-20s", "Table", "OrderID", "FoodID","Order", "Status", "Total Price",
                        "Remarks");
                System.out.println("\n---------------------------------------------------" +
                        "-------------------------------" +
                        "---------------------------------------------------------------");
                for (int i = 0; i < orderID.size(); i++) {
                    if (status.get(i).equals(status_)) {
                        String[] foodID = menuID.get(i).split("\\|");
                        String[] Quantity = quantity.get(i).split("\\|");
                        String[] name = foodName.get(i).split("\\|");
                        System.out.format("%-5s %-13s- ", tableID.get(i), orderID.get(i));
                        for (int x = 0; x < foodID.length; x++) {
                            d = foodID[x];
                            n = name[x];
                            q = Quantity[x];
                            String s = status.get(i).equals("false") ? "Not Ready(NR)" : "Completed(C)";
                            if (x == 0) {
                                total += Double.parseDouble(totalP.get(i));
                                System.out.format(d + "|   " + q + " x %-25s %-20s %-15s %-40s\n", n, s, totalP.get(i),
                                        remarks.get(i));
                            } else if (x > 0){
                                System.out.format("%-21s" + d + "|   " + q + " x %-25s\n", "", n);
                            }
                        }
                        System.out.println("----------------------------------------------------------------------------------" +
                                "---------------------------------------------------------------");
                    }
                }

                System.out.format(textSpace + " %-6s", text2, text + df.format(total));
                System.out.println("\n----------------------------------------------------------------------" +
                        "---------------------------------" +
                        "------------------------------------------");
            } catch (FileNotFoundException ignored) {

            }
        }
    }

    private static void mainOrder() {
        boolean idExist = false;
        boolean open = false;
        if (!open) {
            loadOrder();
            viewOrderDetails("1");
        }

        System.out.println("Press 1 to confirm completed order");
        System.out.println("Press 2 to refresh order list");
        System.out.println("Press 3 to view order list and total profits");
        System.out.println("Press 4 to quit the order system");
        System.out.print("Enter your option : ");
        String opt = inp.next();


        if (opt.equals("1")) {
            System.out.println("Enter the completed order OrderID");
            System.out.print("Enter the Order ID : ");
            String oid = inp.next();
            for (String id: orderID) {
                if (oid.equals(id)) {
                    idExist = true;
                    break;
                }
            }

            if (idExist) {
                completeOrder(oid);
            } else {
                System.out.println("Order ID not exist...");
                mainOrder();
            }
        } else if (opt.equals("2")) {
            loadOrder();
            mainOrder();
        } else if (opt.equals("3")) {
            open = true;
            loadOrder();
            viewOrderDetails("2");
            System.out.print("Press any key to back to main order : ");
            String opts = inp.next();

            if (opts.equals(opts)) {
                open = false;
                mainOrder();
            }
        } else if(opt.equals("4")) {
            System.out.println("Thank you for using the system...");
            System.exit(0);
        } else {
            System.out.println("Option not exist...");
            mainOrder();
        }

        mainOrder();
    }

    private static void completeOrder(String oid) {
        try {
            File All_order = new File("src/main/java/AllOrder.csv");
            File temp_order = new File("src/main/java/TempOrder.csv");
            Scanner scan = new Scanner(All_order);
            scan.useDelimiter("[\n,]");
            FileWriter output_orderFile = new FileWriter(temp_order, true);
            CSVWriter writer = new CSVWriter(output_orderFile);

            while (scan.hasNext()) {
                String id = scan.next().replaceAll("[\",]", "");
                String table = scan.next().replaceAll("[\",]", "");
                String name = scan.next().replaceAll("[\",]", "");
                String menuD = scan.next().replaceAll("[\",]", "");
                String q = scan.next().replaceAll("[\",]", "");
                String siz = scan.next().replaceAll("[\",]", "");
                String price = scan.next().replaceAll("[\",]", "");
                String total = scan.next().replaceAll("[\",]", "");
                String status = scan.next().replaceAll("[\",]", "");
                String remarks = scan.next().replaceAll("[\",]", "");

                String[] order;
                if (oid.equals(id)) {
                    order = new String[]{id, table, name, menuD, q, siz, price, total, "true", remarks};
                } else {
                    order = new String[]{id, table, name, menuD, q, siz, price, total, status, remarks};
                }
                writer.writeNext(order);
            }

            writer.close();
            scan.close();
            All_order.delete();
            File dummies = new File("src/main/java/AllOrder.csv");
            temp_order.renameTo(dummies);
            temp_order.delete();
        } catch (FileNotFoundException ignored) {

        } catch (IOException ignored) {

        }
    }

    public static void main(String [] args) {
        mainOrder();
    }
}

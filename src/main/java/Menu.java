import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static File menu_file = new File("src/main/java/menu.csv");
    public static DecimalFormat df = new DecimalFormat("0.00");
    public static ArrayList<String> fid = new ArrayList<>();
    public static ArrayList<String> FoodName = new ArrayList<>();
    public static ArrayList<String> status = new ArrayList<>();
    public static ArrayList<String> category = new ArrayList<>();
    public static ArrayList<String> size = new ArrayList<>();
    public static ArrayList<String> BHV = new ArrayList<>();
    public static ArrayList<String> price = new ArrayList<>();

    public Menu() {

    }

    protected static void loadID() throws FileNotFoundException {
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter("[,\n]");
        fid.clear();

        while (scan.hasNext()) {
            fid.add(scan.next().replaceAll("[\"]", ""));
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
        }

        scan.close();
    }

    protected static void loadName() throws FileNotFoundException {
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter("[,\n]");
        FoodName.clear();

        while (scan.hasNext()) {
            scan.next();
            FoodName.add(scan.next().replaceAll("[\"]", ""));
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
        }
        scan.close();
    }

    protected static void loadSize() throws FileNotFoundException {
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter("[,\n]");
        size.clear();

        while (scan.hasNext()) {
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            size.add(scan.next().replaceAll("[\"]", ""));
            scan.next();
            scan.next();
        }

        scan.close();
    }

    protected static void loadPrice() throws FileNotFoundException {
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter("[,\n]");
        price.clear();

        while (scan.hasNext()) {
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            scan.next();
            price.add(scan.next().replaceAll("[\"\\[\\]]", ""));
        }

        scan.close();
    }

    protected static void load_menu(String opt) throws FileNotFoundException {
        Scanner scan = new Scanner(menu_file);
        scan.useDelimiter("[,\n]");
        int i = 0;
        fid.clear();
        FoodName.clear();
        status.clear();
        category.clear();
        size.clear();
        BHV.clear();
        price.clear();

        try {
            String deco = "=";
            String tab = "\t";

            if ((opt.equals("1") || opt.equals("2") || opt.equals("3") || opt.equals("4"))) {
                System.out.println(deco.repeat(100));
                System.out.format(
                        "%-11s %-20s %-13s %-11s %-25s %-11s"
                        , "FoodID", "Food Name", "Status", "Size",
                        "Permit Status", "Price(RM)");
                System.out.println();
                System.out.println(tab.repeat(20) + "Large Medium Small");
                System.out.println(deco.repeat(100));
            }

            while (scan.hasNext()) {
                fid.add(scan.next().replaceAll("[\"]", ""));
                FoodName.add(scan.next().replaceAll("[\"]", ""));
                status.add(scan.next().replaceAll("[\"]", ""));
                category.add(scan.next().replaceAll("[\"]", ""));
                size.add(scan.next().replaceAll("[\"]", ""));
                BHV.add(scan.next().replaceAll("[\"]", ""));
                price.add(scan.next().replaceAll("[\"\\[\\]]", ""));
                String st = (status.get(i).equals("a")) ? "Available" : "Not Available";

                switch (opt) {
                    case "1":
                        if (category.get(i).equals("Food")) {
                            if (size.get(i).equals("Large|Medium|Small")) {
                                ArrayList<String> mo_price = new ArrayList<>();
                                String[] prices = price.get(i).split("\\s+");
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-18s"
                                        , fid.get(i), FoodName.get(i), st, "Available",
                                        BHV.get(i));

                                for (int j = 0; j < prices.length; j++) {
                                    mo_price.add(df.format(Double.parseDouble(prices[j])));
                                    if (!mo_price.get(j).equals("0.00")) {
                                        System.out.format("%-5s", mo_price.get(j) + " ");
                                    }
                                }
                            } else {
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-23s %-11s"
                                        , fid.get(i), FoodName.get(i), st, size.get(i),
                                        BHV.get(i), df.format(Double.parseDouble(price.get(i))));
                            }
                            System.out.println();
                        }
                        break;
                    case "2":
                        if (category.get(i).equals("Dessert")) {
                            if (size.get(i).equals("Large|Medium|Small")) {
                                ArrayList<String> mo_price = new ArrayList<>();
                                String[] prices = price.get(i).split("\\s+");
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-18s"
                                        , fid.get(i), FoodName.get(i), st, "Available",
                                        BHV.get(i));

                                for (int j = 0; j < prices.length; j++) {
                                    mo_price.add(df.format(Double.parseDouble(prices[j])));
                                    if (!mo_price.get(j).equals("0.00")) {
                                        System.out.format("%-5s", mo_price.get(j) + " ");
                                    }
                                }
                            } else {
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-23s %-11s"
                                        , fid.get(i), FoodName.get(i), st, size.get(i),
                                        BHV.get(i), df.format(Double.parseDouble(price.get(i))));
                            }
                            System.out.println();
                        }
                        break;
                    case "3":
                        if (category.get(i).equals("Beverage")) {
                            if (size.get(i).equals("Large|Medium|Small")) {
                                ArrayList<String> mo_price = new ArrayList<>();
                                String[] prices = price.get(i).split("\\s+");
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-18s"
                                        , fid.get(i), FoodName.get(i), st, "Available",
                                        BHV.get(i));

                                for (int j = 0; j < prices.length; j++) {
                                    mo_price.add(df.format(Double.parseDouble(prices[j])));
                                    if (!mo_price.get(j).equals("0.00")) {
                                        System.out.format("%-5s", mo_price.get(j) + " ");
                                    }
                                }
                            } else {
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-23s %-11s"
                                        , fid.get(i), FoodName.get(i), st, size.get(i),
                                        BHV.get(i), df.format(Double.parseDouble(price.get(i))));
                            }
                            System.out.println();
                        }
                        break;
                    case "4":
                        if (!category.get(i).equals("Category")) {
                            if (size.get(i).equals("Large|Medium|Small")) {
                                ArrayList<String> mo_price = new ArrayList<>();
                                String[] prices = price.get(i).split("\\s+");
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-18s"
                                        , fid.get(i), FoodName.get(i), st, "Available",
                                        BHV.get(i));

                                for (int j = 0; j < prices.length; j++) {
                                    mo_price.add(df.format(Double.parseDouble(prices[j])));
                                    if (!mo_price.get(j).equals("0.00")) {
                                        System.out.format("%-5s", mo_price.get(j) + " ");
                                    }
                                }
                            } else {
                                System.out.format(
                                        "%-11s %-20s %-13s %-15s %-23s %-11s"
                                        , fid.get(i), FoodName.get(i), st, size.get(i),
                                        BHV.get(i), df.format(Double.parseDouble(price.get(i))));
                            }
                            System.out.println();
                        }
                        break;
                }
                i++;
            }

            scan.close();

            System.out.println(deco.repeat(100));
        } catch (Exception ignored) {

        }
    }

    protected static void displayMenuList() {
        System.out.println("====================");
        System.out.println("\tCategory");
        System.out.println("====================");
        System.out.println("1. Food");
        System.out.println("2. Dessert");
        System.out.println("3. Beverage");
        System.out.println("4. Display All");
        System.out.println("5. Back to main menu");
    }

    protected static void details(String id) {
        String equal = "=";

        for (int i = 0; i < fid.size(); i++) {
            if (id.equals(fid.get(i))) {
                String st = (status.get(i).equals("a")) ? "Available":"Not Available";
                System.out.println(equal.repeat(30));
                System.out.println("\tFood Details");
                System.out.println(equal.repeat(30));
                System.out.println("Food ID : " + fid.get(i));
                System.out.println("1. Food Name   : " + FoodName.get(i));
                System.out.println("2. Status      : " + st);
                System.out.println("3. Category    : " + category.get(i));
                System.out.println("4. Size        : " + size.get(i));
                System.out.println("5. Permissible : " + BHV.get(i));
                System.out.print("6. Price       : RM");
                if (size.get(i).equals("Large|Medium|Small")) {
                    ArrayList<String> mo_price = new ArrayList<>();
                    String[] prices = price.get(i).split("\\s+");

                    for (int j = 0; j < prices.length; j++) {
                        mo_price.add(df.format(Double.parseDouble(prices[j])));
                        if (!mo_price.get(j).equals("0.00")) {
                            System.out.print(mo_price.get(j) + " ");
                        }
                    }
                } else {
                    System.out.print(price.get(i) + " ");
                }
                System.out.println();
                break;
            }
        }
    }
}

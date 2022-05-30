import java.io.*;
import java.util.*;

public class Menu {
    public static ArrayList<String> FoodID = new ArrayList<String>();
    public static ArrayList<String> FoodN = new ArrayList<String>();
    public static ArrayList<String> FoodStat = new ArrayList<String>();
    public static ArrayList<String> Price = new ArrayList<String>();
    public static ArrayList<String> Category = new ArrayList<String>();
    public static ArrayList<String> Size = new ArrayList<String>();
    public static ArrayList<String> BHV = new ArrayList<String>();
    static File menufile = new File("src/main/java/menu.csv");


    public Menu(){
    }
    
    
    public static void loadMenu(int in) throws FileNotFoundException{
    	int x = 0;
    	String d1 = "-"; //design
    	Scanner scan = new Scanner(menufile);
    	scan.useDelimiter("[^A-Za-z]+");
    	System.out.println(d1.repeat(100));
    	System.out.println("FoodID" + "Food Name" + "Status" + "Size" + "Permit Status" + "Price(RM)");
    	System.out.println(d1.repeat(100));
    	
    	switch(in) {
    	case 1:
    		if (Category.get(x).equals("Food")) {
                if (Size.get(x).equals("Large|Medium|Small")) {
                    String[] fdprice = Price.get(x);
                    }
    		}else {
                System.out.println(
                        FoodID.get(x) + FoodN.get(x) + Size.get(x) + BHV.get(x));
    		}
                    break;
        case 2:
        	if (Category.get(x).equals"Beaverage")) {
                if (Size.get(x).equals("Large|Medium|Small")) {
                    String[] fdprice = price.get(x);
                }
    		}else {
                System.out.println(
                        FoodID.get(x) + FoodN.get(x) + Size.get(x) + BHV.get(x));
    		}
                    break;
        case 3:
        	if (Category.get(x).equals("Desert")) {
                if (Size.get(x).equals("Large|Medium|Small")) {
                    String[] fdprice = Price.get(x);
                }
    		}else {
                System.out.println(
                        FoodID.get(x) + FoodN.get(x) + Size.get(x) + BHV.get(x));
                    break;
    		}
            
    	}
        scan.close();
    }
 
    public static void viewMenu(){
    	System.out.println("-------------------------");
        System.out.println("Category");
        System.out.println("-------------------------");
        System.out.println("1.Food");
        System.out.println("2.Dessert");
        System.out.println("3.Beverage");
   }
}

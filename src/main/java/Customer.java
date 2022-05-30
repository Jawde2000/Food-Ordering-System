import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class Customer {
    private static final File menu_file = new File("src/main/java/menu.csv");

    private static void displayMenu() throws FileNotFoundException{
        System.out.println("Welcome to Banana Restaurant!");
        System.out.println("Here is the menu: ");
        Scanner sc = new Scanner(menu_file);  
        sc.useDelimiter(",");   //sets the delimiter pattern  
        while (sc.hasNext()) { //returns a boolean value    
            System.out.print(String.format("|%-19s|", sc.next()));  //find and returns the next complete token from this scanner  
        }   
        sc.close();  //closes the scanner 
    }

    public static String option(){
        Scanner inp = new Scanner(System.in); 

        System.out.println("\n");
        System.out.println("What would you like to do?");
        System.out.println("1. Order more food");
        System.out.println("2. Remove an order");
        System.out.println("3. Edit my order");
        System.out.println("4. Final confirmation on my order");
        System.out.print("Please choose an option [1 - 4]: ");

        String choice = inp.nextLine();
        inp.close();

        return choice;     
    }

    private static void addFood(){
        System.out.print("Please enter the foodID of your choice: ");
    }

    private static void deleteFood(){
        System.out.print("Please enter the foodID of your choice: ");
    }

    private static void updateFood(){
        System.out.print("Please enter the foodID of your choice: ");
    }

    private static void confirmFood(){
        System.out.print("Please enter the foodID of your choice: ");
    }

    public static void main(String args[]) throws FileNotFoundException{
        displayMenu();
        while (option() != "1" || option() != "2" || option() != "3" || option() != "4"){
            if (option() == "1"){
                addFood();
            }
            else if (option() == "2"){
                deleteFood();
            }
            else if (option() == "3"){
                updateFood();
            }
            else if (option() == "4"){
                confirmFood();
            }
            else {
                System.out.println("Wrong input! Please try again!");
            }
        }

        
    }    
}
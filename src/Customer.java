public class Customer {
  private String tableID;
  private String Remarks;

  Customer() {
   tableID = "";
   Remarks = "";
  }

  Customer(String tID, String r) {
   tableID = tID;
   Remarks = r;
  }

  public void viewMenu() {
    
  }

  public void confirmFood(String FoodName, String itemPrice, String category, String BHV) {

  }

  public void updateFood(String ID) {

  }

  public void removeFood(String ID) {

  } 

  public void addFood(String FoodName, String itemPrice, String category, String BHV) {
  
  }

  public static void main(String args[]) {
    addFood();
    removeFood();
    confirmFood();
    updateFood();
  }
}
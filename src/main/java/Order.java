public class Order {
    private boolean status;
    private String orderID;
    private String menuID;
    private String tableID;
    private double order_price;
    private int quantity;

    // default constructor all value 0
    Order() {
        status = false;
        orderID = "";
        menuID = "";
        tableID = "";
        order_price = 0.00;
        quantity = 0;
    }

    // constructor with passing parameters
    Order(boolean s, String oID, String mID, String tID, double price, int q) {
        status = s;
        orderID = oID;
        menuID = mID;
        tableID = tID;
        order_price = price;
        quantity = q;
    }

    // return status of food whether it is served or not
    public boolean getStatus() {
        return status;
    }

    // return orderID of current customer
    public String getOrderID() {
        return orderID;
    }

    // return menuID of current customer
    public String getMenuID() {
        return menuID;
    }

    // return tableID of current customer
    public String getTableID() {
        return tableID;
    }

    // return total price of all food ordered
    public double getPrice() {
        return order_price;
    }

    // return quantity of food ordered
    public int getQuantity() {
        return quantity;
    }

    // showing details of order
    public void viewOrderDetails(){
        System.out.println(getTableID() + " " + getOrderID() + " " + getMenuID() + " " + getStatus() + " " + getPrice() + " " + getQuantity());
    }
}
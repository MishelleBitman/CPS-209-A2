import java.util.ArrayList;
// NAME: Mishelle Bitman
// Student number: 501091629
public class Shoes extends Product {

    private ArrayList<String> validOptionsBrown = new ArrayList<>();
    private ArrayList<String> validOptionsBlack = new ArrayList<>();

    //stocks related variables
    int brownSixStock;
    int brownSevenStock;
    int brownEightStock;
    int brownNineStock;
    int brownTenStock;
    int blackSixStock;
    int blackSevenStock;
    int blackEightStock;
    int blackNineStock;
    int blackTenStock;





    public Shoes(String name, String id, double price, int brownSixStock,int brownSevenStock,int brownEightStock,int brownNineStock, int brownTenStock, int blackSixStock, int blackSevenStock, int blackEightStock, int blackNineStock, int blackTenStock) {
        // Make use of the constructor in the super class Product. Initialize additional Book instance variables.
        // Set category to BOOKS
        super(name, id, price, 100000, Product.Category.CLOTHING);
        this.brownSixStock=brownSixStock;
        this.brownSevenStock=brownSevenStock;
        this.brownEightStock=brownEightStock;
        this.brownNineStock=brownNineStock;
        this.brownTenStock=brownTenStock;
        this.blackSixStock=blackSixStock;
        this.blackSevenStock=blackSevenStock;
        this.blackEightStock=blackEightStock;
        this.blackNineStock=blackNineStock;
        this.blackTenStock=blackTenStock;

        validOptionsBrown.add("6 Brown"); //adding all possible options to arraylist validOptionsBrown
        validOptionsBrown.add("7 Brown");
        validOptionsBrown.add("8 Brown");
        validOptionsBrown.add("9 Brown");
        validOptionsBrown.add("10 Brown");
        validOptionsBlack.add("6 Black"); //adding all possible options to arraylist validOptionsBlack
        validOptionsBlack.add("7 Black");
        validOptionsBlack.add("8 Black");
        validOptionsBlack.add("9 Black");
        validOptionsBlack.add("10 Black");
    }



    public boolean validOptions(String productOptions) {

        for (int i=0; i<validOptionsBrown.size();i++) //looping through validOptionsBrown arraylist
            if (validOptionsBrown.get(i).equals(productOptions)) { //if an item fits the productOptions
                return true; //return true
            }
        for (int j=0; j<validOptionsBlack.size();j++)  //looping through validOptionsBlack arraylist
            if (validOptionsBlack.get(j).equals(productOptions)) { //if an item fits the productOptions
                return true; //return true
        }
    return false;
    }

    public int getStockCount(String productOptions)
    {
        // Use the productOptions to check for (and return) the number of stock for each option
        if (productOptions.equals("6 Brown")) {
            return brownSixStock;
        }
        if (productOptions.equals("7 Brown")) {
            return brownSevenStock;
        }
        if (productOptions.equals("8 Brown")) {
            return brownEightStock;
        }
        if (productOptions.equals("9 Brown")) {
            return brownNineStock;
        }
        if (productOptions.equals("10 Brown")) {
            return brownTenStock;
        }
        if (productOptions.equals("6 Black")) {
            return blackSixStock;
        }
        if (productOptions.equals("7 Black")) {
            return blackSevenStock;
        }
        if (productOptions.equals("8 Black")) {
            return blackEightStock;
        }
        if (productOptions.equals("9 Black")) {
            return blackNineStock;
        }
        if (productOptions.equals("10 Black")) {
            return blackTenStock;
        }
        return 0;
    }

    public void setStockCount(String productOptions)
    {
        // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("6 Brown")) {
             this.brownSixStock=brownSixStock;
        }
        if (productOptions.equals("7 Brown")) {
             this.brownSevenStock=brownSevenStock;
        }
        if (productOptions.equals("8 Brown")) {
            this.brownEightStock=brownEightStock;
        }
        if (productOptions.equals("9 Brown")) {
            this.brownNineStock=brownNineStock;
        }
        if (productOptions.equals("10 Brown")) {
            this.brownTenStock=brownTenStock;
        }
        if (productOptions.equals("6 Black")) {
            this.blackSixStock=blackSixStock;
        }
        if (productOptions.equals("7 Black")) {
            this.blackSevenStock=blackSevenStock;
        }
        if (productOptions.equals("8 Black")) {
            this.blackEightStock=blackEightStock;
        }
        if (productOptions.equals("9 Black")) {
            this.blackNineStock=blackNineStock;
        }
        if (productOptions.equals("10 Black")) {
            this.blackTenStock=blackTenStock;
        }
    }

    /*
     * When a book is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions)
    {
        // Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("6 Brown")) {
            brownSixStock--; //decrement brownSixStock by 1
        }
        if (productOptions.equals("7 Brown")) {
            brownSevenStock--; //decrement brownSevenStock by 1
        }
        if (productOptions.equals("8 Brown")) {
            brownEightStock--;
        }
        if (productOptions.equals("9 Brown")) {
            brownNineStock--;
        }
        if (productOptions.equals("10 Brown")) {
            brownTenStock--;
        }
        if (productOptions.equals("6 Black")) {
            blackSixStock--;
        }
        if (productOptions.equals("7 Black")) {
            blackSevenStock--;
        }
        if (productOptions.equals("8 Black")) {
            blackEightStock--;
        }
        if (productOptions.equals("9 Black")) {
            blackNineStock--;
        }
        if (productOptions.equals("10 Black")) {
            blackTenStock--;
        }
    }
    /*
     * Print product information in super class and append Book specific information title and author
     */
    public void print()
    {
        super.print(); // super method print() from products
    }


}

import java.util.ArrayList;

// NAME: Mishelle Bitman
// Student number: 501091629
public class Cart {
    private ArrayList<CartItem> CartItem_list = new ArrayList<>(); //arraylist of cartitems called CartItem_list


    public Cart() {

    }


    public ArrayList<CartItem> getCartItem_list() //method to return CartItem_list
    {
        return CartItem_list;
    }
    public void print() { //printing each element in CartItem_list
        for (int i=0; i<CartItem_list.size();i++) {
            CartItem_list.get(i).print();
        }
    }


    public void addItem(Product product,String productOptions) { //add a new item to the cart
        CartItem_list.add(new CartItem(product,productOptions));
    }
    public void removeItem(Product product) { //removing a new item to the cart
        for (int i=0;i< CartItem_list.size();i++) { //looping through the list
            if (CartItem_list.get(i).getProduct()==product) { //get the product user wants to remove
                CartItem_list.remove(i); //remove the product from CartItem_list
            }
        }
    }


}


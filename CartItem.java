// NAME: Mishelle Bitman
// Student number: 501091629

public class CartItem {

    private Product product;
    private String productOptions;

    public CartItem(Product p,String productOptions) {
        this.product = p;
        this.productOptions = productOptions;
    }


    public Product getProduct() //return the product of each cart item
    {
        return product;
    }
    public String getProductOptions() //return the product options of each cart item
    {
        return productOptions;
    }
    public void print() { //print the product
        product.print();
    }

}

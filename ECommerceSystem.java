import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.* ;
import java.util.Comparator;
import java.util.TreeMap;
// NAME: Mishelle Bitman
// Student number: 501091629
/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */

public class ECommerceSystem {
    //private ArrayList<Product> products = new ArrayList<Product>();
    private Map<Product,Integer> timesOrdered = new HashMap<>();
    private Map<Product,Integer> bonusOneStar = new HashMap<>();
    private Map<Product,Integer> bonusTwoStar = new HashMap<>();
    private Map<Product,Integer> bonusThreeStar = new HashMap<>();
    private Map<Product,Integer> bonusFourStar = new HashMap<>();
    private Map<Product,Integer> bonusFiveStar = new HashMap<>();

    private TreeMap<String, Product> products = new TreeMap<>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();


    // These variables are used to generate order numbers, customer id's, product id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)
    String errMsg = null;

    // Random number generator
    Random random = new Random();

    public ECommerceSystem() {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products
        try {
            products = productsTextFileReader("products.txt");
        } catch (IOException exception) {
            System.exit(1);
        }
        for (Map.Entry<String, Product> product : products.entrySet()) {
            timesOrdered.put(product.getValue(), 0);


        }







        // Create some products. Notice how generateProductId() method is used
        /*
        products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
        products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
        products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney",2020));
        products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
        products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
        products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
        products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast",1999));
        products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive",1999));
        products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney",1999));
        products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
        products.add(new Shoes ("Jordans", generateProductId(), 10.0,1,2,3,4,5,6,7,8,9,10));
*/
        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    private TreeMap<String, Product> productsTextFileReader(String filename) throws FileNotFoundException {
        ArrayList<Product> products_list = new ArrayList<>();
        TreeMap<String, Product> products_map = new TreeMap<>();
        try {
            File inputFile = new File("products.txt");
            Scanner in = new Scanner(inputFile);
            int stock = 0;
            int paper_stock = 0;
            int hard_stock = 0;
            String book_name = "";
            String book_author = "";
            int book_year = 0;

            while (in.hasNextLine()) {
                String word = in.nextLine(); //get the category (first line)
                String cat = word; //set 'cat' to category
                String name_file = in.nextLine(); //get the name (second line)
                String name = name_file; //set 'name' to name
                String price_file = in.nextLine(); //get the price (third line)
                String price = price_file; //set 'price' to price
                String eachStock = in.nextLine();
                if (cat.equals("BOOKS")) { //if it's a book
                    String[] bookStocks = eachStock.split(" "); //split the line by " " and save as a String[]
                    paper_stock = Integer.parseInt(bookStocks[0]); //first string is the paperback and convert to int
                    hard_stock = Integer.parseInt(bookStocks[1]); //second string is the hardcover and convert to int
                } else {
                    stock = Integer.parseInt(eachStock); //if it's not a book, only a single stock
                }
                String options_file = in.nextLine(); //get product's options
                String options = options_file; //set options to equal it

                if (cat.equals("BOOKS")) { //if the product's category is a book
                    String[] book_info = options.split(":"); // split the line by " " and save as a String[]
                    book_name = book_info[0]; //first string is the name of the book
                    book_author = book_info[1]; //second string is the author
                    book_year = Integer.parseInt(book_info[2]); //third string is a year and convert it to integer
                }

                if (!cat.equals("BOOKS")) { //if the product is not a book, add a new regular product
                    products_list.add(new Product(name, generateProductId(), Double.parseDouble(price), stock, Product.Category.valueOf(word)));
                } else { //if the product is a book, add a new book with all of its additional info
                    products_list.add(new Book(name, generateProductId(), Double.parseDouble(price), paper_stock, hard_stock, book_name, book_author, book_year));
                }
            }
            //return products_list;
        } catch (FileNotFoundException exception) {
            System.out.print(exception.getMessage());
        }
        for (Product p : products_list) {
            products_map.put(p.getId(), p); //put all the products in the arraylist to products_map
        }
        return products_map;
    }



    public void addRating(String productId,int rating) {
        boolean exists=false;
        for (Product prd : products.values()) {
            if (prd.getId().equals(productId)) {
                exists=true;
                if (rating==1) {
                    bonusOneStar.put(prd, 1);
                }
                else if (rating==2) {
                    bonusTwoStar.put(prd, 2);
                }
                else if (rating==3) {
                    bonusThreeStar.put(prd,3);
                }
                else if (rating==4) {
                    bonusFourStar.put(prd,4);
                }
                else if (rating==5) {
                    bonusFiveStar.put(prd,5);
                }
                else {
                    throw new RuntimeException("Rating are 1-5 only");
                }
            }
        }
        if (exists==false) {
            throw new RuntimeException("Product Id Was Not Found");
        }
    }
    public void thresholdRating(int ratingThreshold, String category) {
        Product.Category cate= Product.Category.GENERAL; //just for now
        if (category.equals("GENERAL")) {
            cate= Product.Category.GENERAL;
        }
        else if (category.equals("CLOTHING")) {
            cate= Product.Category.CLOTHING;
        }
        else if (category.equals("COMPUTERS")) {
            cate= Product.Category.COMPUTERS;
        }
        else if (category.equals("FURNITURE")) {
            cate = Product.Category.FURNITURE;
        }
        else if (category.equals("BOOKS")) {
            cate= Product.Category.BOOKS;
        }
        else {
            throw new RuntimeException("This Category Does Not exist");
        }
        if (ratingThreshold == 1) {
            for (Product p : bonusOneStar.keySet()) {
                if (p.getCategory().equals(cate)) {
                    p.print();
                }
            }
            for (Product p : bonusTwoStar.keySet()) {
                if (p.getCategory().equals(cate)) {
                    p.print();
                }
            }
            for (Product p : bonusThreeStar.keySet()) {
                if (p.getCategory().equals(cate)) {
                    p.print();
                }
            }
            for (Product pr : bonusFourStar.keySet()) {
                if (pr.getCategory().equals(cate)) {
                    pr.print();
                }
            }
            for (Product pr : bonusFiveStar.keySet()) {
                if (pr.getCategory().equals(cate)) {
                    pr.print();
                }
            }

        }
        else if (ratingThreshold == 2) {
            for (Product p : bonusTwoStar.keySet()) {
                if (p.getCategory().equals(category)) {
                    p.print();
                }
            }
            for (Product p : bonusThreeStar.keySet()) {
                if (p.getCategory().equals(category)) {
                    p.print();
                }
            }
            for (Product pr : bonusFourStar.keySet()) {
                if (pr.getCategory().equals(category)) {
                    pr.print();
                }
            }
            for (Product pr : bonusFiveStar.keySet()) {
                if (pr.getCategory().equals(category)) {
                    pr.print();
                }
            }
        }
        else if (ratingThreshold == 3) {
                for (Product p : bonusThreeStar.keySet()) {
                    if (p.getCategory().equals(category)) {
                        p.print();
                    }
                }
                for (Product pr : bonusFourStar.keySet()) {
                    if (pr.getCategory().equals(category)) {
                        pr.print();
                    }
                }
                for (Product pr : bonusFiveStar.keySet()) {
                    if (pr.getCategory().equals(category)) {
                        pr.print();
                    }
                }
            }
        else if (ratingThreshold == 4) {
            for (Product pr : bonusFourStar.keySet()) {
                if (pr.getCategory().equals(category)) {
                    pr.print();
                }
            }
            for (Product pr : bonusFiveStar.keySet()) {
                if (pr.getCategory().equals(category)) {
                    pr.print();
                }
            }
        }
        else if (ratingThreshold == 5) {
            for (Product pr : bonusFiveStar.keySet()) {
                if (pr.getCategory().equals(category)) {
                    pr.print();
                }
            }
        }
    }

    public void printRating(String productId) {
        int counter=0;
        int constant=0;
        int total=0;
        for (Product prd : bonusOneStar.keySet()) {
            if (prd.getId().equals(productId)) {
                counter++;
                constant=1;
                total+=1;
            }
            }
        for (Product prd : bonusTwoStar.keySet()) {
            if (prd.getId().equals(productId)) {
                counter++;
                constant=2;
                total+=2;
            }
        }
        for (Product prd : bonusThreeStar.keySet()) {
            if (prd.getId().equals(productId)) {
                counter++;
                constant=3;
                total+=3;
            }
        }
        for (Product prd : bonusFourStar.keySet()) {
            if (prd.getId().equals(productId)) {
                counter++;
                constant=4;
                total+=4;
            }
        }
        for (Product prd : bonusFiveStar.keySet()) {
            if (prd.getId().equals(productId)) {
                counter++;
                constant=5;
                total+=5;
            }
        }
        if (counter>1) {
            System.out.print("The product has more than one rating, the average rating is " +total/counter);
        }
        else if (counter==1) {
            System.out.print("The product's rating is " +constant);
        }
        else if (counter==0) {
            throw new RuntimeException("Product Does Not Exist Or Does Not Have Reviews Yet");
        }
    }
    private String generateOrderNumber() {
        return "" + orderNumber++;
    }

    private String generateCustomerId() {
        return "" + customerId++;
    }

    private String generateProductId() {
        return "" + productId++;
    }

    public String getErrorMessage() {
        return errMsg;
    }

    public void printAllProducts() {
        for (Product p : products.values()) {
            p.print();
        }
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks() {
        for (Product r : products.values()) { //looping through every item in ArrayList products
            if (r.getCategory().equals(Product.Category.BOOKS)) { // if the product is a book
                r.print(); //print it using print() method
            }
        }
    }

    // Print all current orders
    public void printAllOrders() {
        for (ProductOrder pOrder : orders) { //for every item in ArrayList orders
            pOrder.print(); //print it using print() method
        }
    }

    // Print all shipped orders
    public void printAllShippedOrders() {
        for (ProductOrder sOrder : shippedOrders) {
            sOrder.print();
        }
    }

    // Print all customers
    public void printCustomers() {
        for (Customer f : customers) { //looping through every item in ArrayList customers
            f.print(); //using print() method to print each customer
        }
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId) {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        boolean exists = false;
        int ind = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                ind = i;
                exists = true;
            }
        }
        if (exists == false) {
            throw new unknownCustomerException("Customer " + customerId + " Not Found");
        }
        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (int x = 0; x < orders.size(); x++) {
            if (orders.get(x).getCustomer() == customers.get(ind)) {
                orders.get(x).print();
            }
        }
        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId);
        //enter code here
        for (int j = 0; j < shippedOrders.size(); j++) {
            if (shippedOrders.get(j).getCustomer() == customers.get(ind)) {
                shippedOrders.get(j).print();
            }
        }
        return true;
    }

    public String orderProduct(String productId, String customerId, String productOptions) {
        // First check to see if customer object with customerId exists in array list customers
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Customer object
        boolean exists = false;
        int ind = 0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                ind = i;
                exists = true;
            }
        }
        if (exists == false) {
            throw new unknownCustomerException("Customer " + customerId + " Not Found");
        }
        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        boolean prod_exists = false;
        Product prod_ind = new Product("ignore", generateProductId(), 30.0, 2, Product.Category.GENERAL);
        for (Product p : products.values()) {
            if (p.getId().equals(productId)) {
                prod_exists = true;
                prod_ind = p;
            }
        }
        if (prod_exists == false) {
            throw new unknownProductException("Product " + productId + " Not Found");
        }
        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method vaidOptions()
        // If options are not valid, set errMsg string and return null;
        if (prod_ind.getCategory() == Product.Category.BOOKS) {
            if (!(prod_ind.validOptions(productOptions))) {
                throw new invalidProductOptionsException("Product Book ProductId " + prod_ind.getId() + " Invalid Option: " + productOptions);
            }
        }

        if (prod_ind.getCategory() == Product.Category.CLOTHING) {
            if (!(prod_ind.validOptions(productOptions))) {
                throw new invalidProductOptionsException("Product Shoes ProductId " + prod_ind.getId() + " Invalid Option: " + productOptions);
            }
        }

        if (!prod_ind.validOptions(productOptions)) {
            throw new invalidProductOptionsException("You Can Not Order This Here");
        }
        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null
        if (prod_ind.getStockCount(productOptions) == 0) {
            throw new productOutOfStockException("Product #" + productId + " Is Out Of Stock");
        }
        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string

        orders.add(new ProductOrder(generateOrderNumber(), prod_ind, customers.get(ind), productOptions));
        prod_ind.reduceStockCount(productOptions);
        timesOrdered.put(prod_ind, timesOrdered.get(prod_ind) + 1);
        return "Order # " + orders.get(orders.size() - 1).getOrderNumber();
    }


    //Create a new Customer object and add it to the list of customers
    public void createCustomer(String name, String address) {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter
        if (name.isEmpty()) { //if name string is null
            throw new invalidCustomerNameException("Invalid Customer Name");
        } else if (address.isEmpty()) { //if address string is null
            throw new invalidCustomerAddressException("Invalid Customer Address");
        } else { //if both strings are not empty
            Customer new_cust = new Customer(generateCustomerId(), name, address); //create a new customer object
            customers.add(new_cust); //add the new customer to customers Arraylist
        }
        // Create a Customer object and add to array list
    }

    public ProductOrder shipOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
        // return a reference to the order
        boolean exists = false;
        int ind = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber().equals(orderNumber)) {
                ind = i;
                exists = true;
                break;
            }
        }
        if (exists == false) {
            throw new invalidOrderNumberException("Order " + orderNumber + " Not Found");
        }
        orders.get(ind).print(); //print a reference of the order using print() method from ProductOrder
        shippedOrders.add(orders.get(ind));
        orders.remove(orders.get(ind));
        return shippedOrders.get(shippedOrders.size() - 1);
    }

    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        boolean exists = false;
        int ind = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber().equals(orderNumber)) { //if an order's order number equals an order from the arraylist
                exists = true; //it exists
                ind = i; //saving the index of the order from arraylist orders to access it later
            }
        }
        if (exists == false) { //if it doesn't exist give an error message
            throw new invalidOrderNumberException("Order " + orderNumber + " Not Found");
        }
        orders.remove(orders.get(ind));
        return true;
    }
    // Sort products by increasing price

    public void PRINTBYPRICE() {
        ArrayList<Product> products_newArray = new ArrayList<>(); //new arraylist called products_newArray
        for (Product p : products.values()) { //loop through the products map values
            products_newArray.add(p); //add to the new arraylist products_newArray each product
        }
        class priceComparator implements Comparator<Product> {
            @Override
            public int compare(Product a, Product b) {
                return (int) (a.getPrice() - b.getPrice());
            }
        }
        products_newArray.sort(new priceComparator()); //sorting
        for (Product p : products_newArray) {  //for each sorted product
            p.print(); //print each product with print() method
        }
    }

    // Sort products alphabetically by product name
    public void PRINTBYNAME() {
        ArrayList<Product> products_newArray = new ArrayList<>(); //new arraylist called products_newArray
        for (Product p : products.values()) { //loop through the products map values
            products_newArray.add(p);//add to the new arraylist products_newArray each product
        }
        class NameComparator implements Comparator<Product> {
            public int compare(Product s1, Product s2) {
                return s1.getName().compareTo(s2.getName());
            }
        }
        products_newArray.sort(new NameComparator()); //sorting
        for (Product p : products_newArray) { //for each sorted product
            p.print(); //print it
        }
    }

    public void sortCustomersByName() {
        Collections.sort(customers);
    }

    public void ADDTOCART(String productId, String customerId, String productOptions) {
        int index = customers.indexOf(new Customer(customerId)); //find the index from the customers of the right customer
        if (index == -1) { //if doesn't exist
            throw new unknownCustomerException("Customer " + customerId + " Not Found"); //error
        }
        Customer customer = customers.get(index); //save the customer as customer

        // Get product
        boolean prod_exists = false;
        Product prod_ind = new Product("ignore", generateProductId(), 30.0, 2, Product.Category.GENERAL);
        for (Product p : products.values()) {
            if (p.getId().equals(productId)) {
                prod_exists = true;
                prod_ind = p;
            }
        }
        if (prod_exists == false) {
            throw new unknownProductException("Product " + productId + " Not Found");
        }
        if (prod_ind.getCategory().equals(Product.Category.BOOKS)) { //if the product is a book
            if (!prod_ind.validOptions(productOptions)) { //check if the product's options are valid
                throw new invalidProductOptionsException(productOptions + " Option Not Valid"); //if not, error
            }
        }
        Cart custCart = customer.getCustCart(); //find the customer's cart
        prod_ind.print(); //print the product
        custCart.addItem(prod_ind, productOptions); //add it to the cart
    }

    public void REMCARTITEM(String productId, String customerId) {
        int index = customers.indexOf(new Customer(customerId)); //get index of the customer from customers arraylist
        if (index == -1) { //if index does not exist
            throw new unknownCustomerException("Customer " + customerId + " Not Found"); //error
        }
        Customer customer = customers.get(index); //find the customer and save it as 'customer'

        // Get product
        boolean exists = false;
        Product prod_ind = new Product("ignore", generateProductId(), 30.0, 2, Product.Category.GENERAL); //create a new product for now
        for (Product p : products.values()) { //loop through the values in map products
            if (p.getId().equals(productId)) { //if the product's id matches productId
                exists = true; //exists is true now
                prod_ind = p; //prod_ind is the product found
            }
            if (!exists) { //if exists is still false
                throw new unknownProductException("Product " + productId + " Not Found"); //error
            }
            Cart custCart = customer.getCustCart(); //custCart is the customer's cart
            custCart.removeItem(prod_ind); //remove the item from custCart
            break;
        }
    }

    public String PRINTCART(String customerId) {
        int index = customers.indexOf(new Customer(customerId)); //get index of the customer from customers arraylist
        if (index == -1) { //if index does not exist
            throw new unknownCustomerException("Customer " + customerId + " Not Found"); //error
        }
        Customer customer = customers.get(index); //find the customer and save it as 'customer'
        Cart custCart = customer.getCustCart(); //find the customer's cart and save it as 'custCart'
        custCart.print(); //print the cart
        return "";
    }

    public String ORDERITEMS(String customerId) {
        int index = customers.indexOf(new Customer(customerId)); //get index of the customer from customers arraylist
        if (index == -1) { //if customer does not exist
            throw new unknownCustomerException("Customer " + customerId + " Not Found"); //error
        }
        Customer customer = customers.get(index); // find the customer and save it as 'customer'
        Cart custCart = customer.getCustCart(); //find the customer's cart and save it as 'custCart'
        for (int i = 0; i < custCart.getCartItem_list().size(); i++) { //looping through the cart
            CartItem curr_cartItem = custCart.getCartItem_list().get(i); //every item will be curr_cartItem
            Product idk = custCart.getCartItem_list().get(i).getProduct(); //the product in curr_cartItem
            if (idk.getStockCount(curr_cartItem.getProductOptions()) == 0) { //if stock of product is 0, error
                throw new productOutOfStockException("Item Is Out Of Stock");
            }
            orders.add(new ProductOrder(generateOrderNumber(), idk, customer, curr_cartItem.getProductOptions())); //if no errors
            //add a new order for every new item in the cart
            idk.reduceStockCount(curr_cartItem.getProductOptions()); //reduce stock count by 1
            timesOrdered.put(idk, timesOrdered.get(idk) + 1); // increment timeordered value by 1
        }
            custCart.getCartItem_list().clear(); //clean the cart after ordering items
            return "Items Ordered successfully";
        }

        public void stats () {
            List<String> list = new ArrayList<>(); //create a new arraylist called list
            LinkedHashMap<Product, Integer> reverseSortedMap = new LinkedHashMap<>(); //create a new LinkedHashMap called reverseSortedMap

            timesOrdered.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            for (Product p :reverseSortedMap.keySet()) { //looping through every key in reverseSortedMap
                list.add("Name " +p.getName()+" Id: "+p.getId()+" Number Of Times Ordered: "+reverseSortedMap.get(p)); //add name, id and times ordered

            }
            for (String s : list) { //looping through list
                System.out.println(s); //printing every element
            }

        }
}

class unknownCustomerException extends RuntimeException { //When customerid does not exist
    public unknownCustomerException() {}
    public unknownCustomerException(String message)
    { super(message);
    }
}

class unknownProductException extends RuntimeException { //When productid does not exist
    public unknownProductException() {}
    public unknownProductException(String message)
    { super(message);
    }
}
class invalidProductOptionsException extends RuntimeException { //When product options are invalid
    public invalidProductOptionsException() {}
    public invalidProductOptionsException(String message)
    { super(message);
    }
}
class productOutOfStockException extends RuntimeException { //When the product is out of stock
    public productOutOfStockException() {}
    public productOutOfStockException(String message)
    { super(message);
    }
}
class invalidCustomerNameException extends RuntimeException { //Invalid customer name
    public invalidCustomerNameException() {}
    public invalidCustomerNameException(String message)
    { super(message);
    }
}

class invalidCustomerAddressException extends RuntimeException { //Invalid customer address
    public invalidCustomerAddressException() {}
    public invalidCustomerAddressException(String message)
    { super(message);
    }
}
class invalidOrderNumberException extends RuntimeException { //Invalid order number
    public invalidOrderNumberException() {}
    public invalidOrderNumberException(String message)
    { super(message);
    }
}



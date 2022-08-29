import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
// NAME: Mishelle Bitman
// Student number: 501091629
// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				try {
					String name = "";
					String address = "";

					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();
					amazon.createCustomer(name, address); //let success be false/true depending on if the customer was created
				}
				catch (invalidCustomerNameException exception) {
					System.out.print(exception.getMessage());
			}
				catch (invalidCustomerAddressException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
				try {
					String orderNumber = "";
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					//ProductOrder shipping = amazon.shipOrder(orderNumber);
					amazon.shipOrder(orderNumber); // use ship order method with order number from user
				} catch (invalidOrderNumberException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				try {
					String customerId = "";
					System.out.print("Customer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					// Print all current orders and all shipped orders for this customer
					amazon.printOrderHistory(customerId);
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				try {
					String productId = "";
					String customerId = "";
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
					// Print Order Number string returned from method in ECommerceSystem
					String productOptions = "";
					String order = amazon.orderProduct(productId, customerId, productOptions);
					System.out.println(order); //print string order
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (unknownProductException exception) {
					System.out.print(exception.getMessage());
				}
				catch (invalidProductOptionsException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				try {
					String productId = "";
					String customerId = "";
					String options = "";
					System.out.print("Product Id: ");
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nCustomer Id: ");
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					if (scanner.hasNextLine())
						options = scanner.nextLine();
					// Order product. Check for error mesage set in ECommerceSystem
					// Print order number string if order number is not null
					String succ = amazon.orderProduct(productId, customerId, options);
					System.out.println(succ);
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (unknownProductException exception) {
					System.out.print(exception.getMessage());
				}
				catch (invalidProductOptionsException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				try {
					String productId = "";
					String customerId = "";
					String options = "";
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and store in options
					if (scanner.hasNextLine())
						options = scanner.nextLine(); // add shoe sizes to options
					System.out.print("\nColor: \"Black\" \"Brown\": ");
					// get shoe color and append to options
					if (scanner.hasNextLine())
						options += " " + scanner.nextLine(); //add shoe colour to options
					String succ = amazon.orderProduct(productId, customerId, options);
					System.out.println(succ); //print the order details
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (unknownProductException exception) {
					System.out.print(exception.getMessage());
				}
				catch (invalidProductOptionsException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ADDTOCART")) // Add to cart
			{
				try {
					String productId = "";
					String customerId = "";
					String productOptions = "";
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					System.out.print("\nProduct Options: ");
					// get product options
					if (scanner.hasNextLine())
						productOptions = scanner.nextLine();
					amazon.ADDTOCART(productId, customerId, productOptions);
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (unknownProductException exception) {
					System.out.print(exception.getMessage());
				}
				catch (invalidProductOptionsException exception) {
					System.out.print(exception.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("REMCARTITEM")) // remove an item from cart
			{
				try {
					String productId = "";
					String customerId = "";
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					amazon.REMCARTITEM(productId, customerId);
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (unknownProductException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PRINTCART")) // print the cart
			{
				try {
					String customerId = "";
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					System.out.print(amazon.PRINTCART(customerId));
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERITEMS")) // order the items
			{
				try {
					String customerId = "";
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();
					System.out.print(amazon.ORDERITEMS(customerId));
				}
				catch (unknownCustomerException exception) {
					System.out.print(exception.getMessage());
				}
				catch (productOutOfStockException exception) {
					System.out.print(exception.getMessage());
				}

			}

			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				try {
					String orderNumber = "";
					System.out.print("Order Number: ");
					// get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					// cancel order. Check for error
					amazon.cancelOrder(orderNumber);
				}
				catch (invalidOrderNumberException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("STATS")) // sort products by price
			{
				amazon.stats();
			}
			else if (action.equalsIgnoreCase("ADDRATING")) // sort products by price
			{
				try {
					String productId = "";
					int rating = 0;

					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					System.out.print("\nRating [1-5]: ");
					// get customer id
					if (scanner.hasNextLine())
						rating = scanner.nextInt();
					amazon.addRating(productId, rating);
				}
				catch (RuntimeException exception) {
					System.out.print(exception.getMessage());
					}

			}
			else if (action.equalsIgnoreCase("PRINTRATING")) // sort products by price
			{
				try {
					String productId = "";

					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();
					amazon.printRating(productId);
				}
				catch (RuntimeException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("thresholdrating")) // sort products by price
			{
				try {
					int rating = 0;
					String category = "";
					System.out.print("Category: ");
					if (scanner.hasNextLine())
						category = scanner.nextLine();
					System.out.print("Threshold Rating: ");
					if (scanner.hasNextLine())
						rating = scanner.nextInt();

					amazon.thresholdRating(rating, category);

				}
				catch (RuntimeException exception) {
					System.out.print(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.PRINTBYPRICE();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.PRINTBYNAME();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
		}
	}
}

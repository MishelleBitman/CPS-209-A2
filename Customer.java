/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address.
 *  A unique id is generated when when a new customer is created.
 *
 *  Implement the Comparable interface and compare two customers based on name
 */
// NAME: Mishelle Bitman
// Student number: 501091629
import java.util.ArrayList;
import java.util.Comparator;
public class Customer implements Comparable<Customer>
{
	private String id;
	private String name;
	private String shippingAddress;
	private Cart cusCart;

	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";


	}

	public Customer(String id, String name, String address)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.cusCart = new Cart();


	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getShippingAddress()
	{
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}

	public Cart getCustCart()
	{
		return cusCart;
	}

	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

	@Override
	public int compareTo(Customer o) {
		if (this.getName().compareTo(o.getName())==0) {
			return 0;
		}
		else if (this.getName().compareTo(o.getName())<0) {
			return -1;
		}
		return 1;
	}
}

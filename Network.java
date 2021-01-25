// Name: Kaushal Banthia
// Roll Number: 19CS10039 
//Project

import java.util.*;

public class Network
{
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args)
	{	
		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>(); 
		
		while(true)
		{
			System.out.println("\nEnter 1 for entering a new node\nEnter 2 for deleting a node\nEnter 3 for to search for a node\nEnter 4 to print all linked nodes of a given node\nEnter 5 to create content for a node\nEnter 6 to display contents posted by a node\nEnter 7 to search for content posted by any node\nEnter 8 to display all content posted by nodes linked to a given node\nEnter 0 to exit the program");
			int exiter = Integer.parseInt(s.nextLine());
			
			if (exiter == 0)
			{	
				break;
			}
			
			else if (exiter == 1)
			{
				create(nodes);
			}
			
			else if (exiter == 2)
			{
				delete(nodes);
			}
			
			else if (exiter == 3)
			{
				search(nodes);
			}
			
			else if (exiter == 4)
			{
				linked(nodes);
			}
			
			else if (exiter == 5)
			{
				create_content(nodes);
			}
			
			else if (exiter == 6)
			{
				display_content(nodes);
			}
			
			else if (exiter == 7)
			{
				search_content(nodes);
			}
			
			else if (exiter == 8)
			{
				display_linked_posts(nodes);
			}
			
			else
			{
				System.out.println("Wrong Input");
			}
		}
	}
	
	public static void create(HashMap<Integer, Node> nodes) //function for creating a node
	{
		int unique_id;
		while(true)
		{
			System.out.println("Enter the unique ID");
			unique_id = Integer.parseInt(s.nextLine());
			if (!(nodes.containsKey(unique_id)))
				break;
			else
				System.out.println("This key is already present");
		}
		
		System.out.println("Enter the name (String)");
		String name = s.nextLine();
			
		System.out.println("Enter the creation date in the format DD-MM-YYYY (Along with the hyphens)");
		String creation_date = s.nextLine();
		
		int content_entry_validation = 0;
		Set<String> content = new HashSet<String>();
		while(true)
		{
			System.out.println("To Enter new content press 1 and hit. Else press -1 and hit ENTER");
			content_entry_validation = Integer.parseInt(s.nextLine());
			if (content_entry_validation == -1)
				break;
			else if (content_entry_validation == 1)
			{
				System.out.println("Enter the content");
				String content_text = s.nextLine();
				content.add(content_text);
			}
		}
		
		int type;
		do
		{
			System.out.println("Enter 1 for Individual\nEnter 2 for Group\nEnter 3 for Business\nEnter 4 for Organisation");
			type = Integer.parseInt(s.nextLine());
			switch(type)
			{
				case 1:
				{
					System.out.println("Enter the birthday in DD-MM-YYYY (String)");
					String birthday = s.nextLine();
					int link_node = 0;
					
					HashMap<Integer, String> link = new HashMap<Integer, String>();
					while(true)
					{
						display_available_nodes(nodes, unique_id);
						
						link_node = Integer.parseInt(s.nextLine());
						if (link_node == -1)
							break;
						
						if (link_node != unique_id && nodes.containsKey(link_node) && !(link.containsKey(link_node)))
						{
							System.out.println("Enter the type of link:\nEnter the word 'member' if this node is a member of " + nodes.get(link_node).name + "\nEnter the word 'owner' if this node is a owner of "+ nodes.get(link_node).name + "\nEnter the word 'customer' if this node is a customer of " + nodes.get(link_node).name);
							String relation = s.nextLine();
							
							if ((nodes.get(link_node).type == 2 && relation.equals("member")) || (nodes.get(link_node).type == 3 && relation.equals("owner")) || (nodes.get(link_node).type == 3 && relation.equals("customer")) || (nodes.get(link_node).type == 4 && relation.equals("member")))
							{
								link.put(link_node, relation);
								(nodes.get(link_node)).link.put(unique_id, relation);
								System.out.println("Link created!");
								continue;
							} 
							
							System.out.println("This link is not allowed");
						}
						
						else
						{
							System.out.println("This unique ID could not be linked");
						}
					}
					
					Individual details = new Individual(unique_id, link, name, creation_date, content, birthday, type);
					nodes.put(unique_id, details);
					break;
				}
				case 2:
				{
					float x, y;
					while (true)
					{
						System.out.println("Enter the X - Coordinate");
						x = Float.parseFloat(s.nextLine());
						System.out.println("Enter the Y - Coordinate");
						y = Float.parseFloat(s.nextLine());
						
						if (x >= -180.0 && x <= 180.0 && y >= -90.0 && y <= 90.0)
						{
							break;
						}
						System.out.println("Wrong coordinates");
					}
					
					int link_node = 0;
					
					HashMap<Integer, String> link = new HashMap<Integer, String>();
					while(true)
					{
						display_available_nodes(nodes, unique_id);
						
						link_node = Integer.parseInt(s.nextLine());
						if (link_node == -1)
							break;
						
						if (link_node != unique_id && nodes.containsKey(link_node) && !(link.containsKey(link_node)))
						{
							System.out.println("Enter the type of link:\nEnter the word 'member' if " + nodes.get(link_node).name + " is a member of this node");
							String relation = s.nextLine();
							
							if ((nodes.get(link_node).type == 1 && relation.equals("member")) || (nodes.get(link_node).type == 3 && relation.equals("member")))
							{
								link.put(link_node, relation);
								(nodes.get(link_node)).link.put(unique_id, relation);
								System.out.println("Link created!");
								continue;
							} 
							
							System.out.println("This link is not allowed");
						}
						
						else
						{
							System.out.println("This unique ID could not be linked");
						}
					}
					
					Business details = new Business(unique_id, link, name, creation_date, content, x, y, type);
					nodes.put(unique_id, details);
					break;
				}
				case 3:
				{
					float x, y;
					while (true)
					{
						System.out.println("Enter the X - Coordinate");
						x = Float.parseFloat(s.nextLine());
						System.out.println("Enter the Y - Coordinate");
						y = Float.parseFloat(s.nextLine());
						
						if (x >= -180.0 && x <= 180.0 && y >= -90.0 && y <= 90.0)
						{
							break;
						}
						System.out.println("Wrong coordinates");
					}
					
					int link_node = 0;
					
					HashMap<Integer, String> link = new HashMap<Integer, String>();
					while(true)
					{
						display_available_nodes(nodes, unique_id);
						
						link_node = Integer.parseInt(s.nextLine());
						if (link_node == -1)
							break;
						
						if (link_node != unique_id && nodes.containsKey(link_node) && !(link.containsKey(link_node)))
						{
							System.out.println("Enter the type of link:\nEnter the word 'member' if this node is a member of " + nodes.get(link_node).name + "\nEnter the word 'owner' if "+ nodes.get(link_node).name + " is the owner of this node\nEnter the word 'customer' if " + nodes.get(link_node).name + " is a customer of this node");
							String relation = s.nextLine();
							
							if ((nodes.get(link_node).type == 1 && relation.equals("customer")) || (nodes.get(link_node).type == 1 && relation.equals("owner")) || (nodes.get(link_node).type == 2 && relation.equals("member")))
							{
								link.put(link_node, relation);
								(nodes.get(link_node)).link.put(unique_id, relation);
								System.out.println("Link created!");
								continue;
							} 
							
							System.out.println("This link is not allowed");
						}
						
						else
						{
							System.out.println("This unique ID could not be linked");
						}
					}
					
					Organisation details = new Organisation(unique_id, link, name, creation_date, content, x, y, type);
					nodes.put(unique_id, details);
					break;
				}
				case 4:
				{
					int link_node = 0;
					
					HashMap<Integer, String> link = new HashMap<Integer, String>();
					while(true)
					{
						display_available_nodes(nodes, unique_id);
						
						link_node = Integer.parseInt(s.nextLine());
						if (link_node == -1)
							break;
						
						if (link_node != unique_id && nodes.containsKey(link_node) && !(link.containsKey(link_node)))
						{
							System.out.println("Enter the type of link:\nEnter the word 'member' if " + nodes.get(link_node).name + " is a member of this node");
							String relation = s.nextLine();
							
							if ((nodes.get(link_node).type == 1 && relation.equals("member")))
							{
								link.put(link_node, relation);
								(nodes.get(link_node)).link.put(unique_id, relation);
								System.out.println("Link created!");
								continue;
							} 
							
							System.out.println("This link is not allowed");
						}
						
						else
						{
							System.out.println("This unique ID could not be linked");
						}
					}
					Group details = new Group(unique_id, link, name, creation_date, content, type);
					nodes.put(unique_id, details);
					break;
				}
				default:
				{
					System.out.println("Wrong Type Input");
					break;
				}
			}
		}while (type != 1 && type != 2 && type != 3 && type != 4);
		
		System.out.println("Node created!");
	}
	
	public static void delete(HashMap<Integer, Node> nodes) //function for deleting a node
	{
		System.out.println("Enter the Unique ID of the node that should be deleted");
		int unique_id = Integer.parseInt(s.nextLine());
		
		if (nodes.containsKey(unique_id))
		{
			String name = nodes.get(unique_id).name; 
			for (Integer i: nodes.get(unique_id).link.keySet())
			{
				nodes.get(i).link.remove(unique_id);
			}
			nodes.remove(unique_id);
			System.out.println("Node for " + name + " has been deleted");
		}
		
		else
		{
			System.out.println("No node with such an ID present");
		}
	}
	
	public static void search(HashMap<Integer, Node> nodes) //function for searching a node
	{
		int results = 0;
		System.out.println("Enter 1 to search using Name\nEnter 2 to search by Type\nEnter 3 to search by Birthday");
		int choice = Integer.parseInt(s.nextLine());
		switch(choice)
		{
			case 1:
			{
				System.out.println("Enter the name");
				String name = s.nextLine();
				for (Integer i: nodes.keySet())
				{
					if(nodes.get(i).name.toLowerCase().startsWith(name.toLowerCase().trim()))
					{	
						results += 1;
						nodes.get(i).display(nodes);
						System.out.println();
					}
				}
				break;
			}
			
			case 2:
			{
				System.out.println("Enter 1 for Individual\nEnter 2 for Group\nEnter 3 for Business\nEnter 4 for Organisation");
				int type = Integer.parseInt(s.nextLine());
				for (Integer i: nodes.keySet())
				{
					if(nodes.get(i).type == type)
					{	
						results += 1;
						nodes.get(i).display(nodes);
						System.out.println();
					}
				}
				break;
			}
			
			case 3:
			{
				System.out.println("Enter the birthday in the format DD-MM-YYYY");
				String birthday = s.nextLine();
				for (Integer i: nodes.keySet())
				{
					if(nodes.get(i).get_birthday().equals(birthday))
					{
						results += 1;
						nodes.get(i).display(nodes);
						System.out.println();
					}
				}
				break;
			}
			
			default:
			{
				System.out.println("Entered wrong number");
				search(nodes);
			}
		}
		
		if (results == 0)
		{
			System.out.println("No results found");
		}
	}
	
	public static void linked(HashMap<Integer, Node> nodes) //function for seeing linked nodes
	{
		System.out.println("Enter the Unique ID of the node whose linked nodes you want to be displayed.");
		int id = Integer.parseInt(s.nextLine());
		
		if (nodes.containsKey(id))
		{
			System.out.println("Nodes linked to " + nodes.get(id).name + "\n");
			nodes.get(id).print_links(nodes);
		}
		
		else
		{
			System.out.println("No such node found");
		}
	}

	public static void create_content(HashMap<Integer, Node> nodes) //function for creating new content
	{
		System.out.println("Enter ID of the node for which you want to create content");
		int id = Integer.parseInt(s.nextLine());
		
		if (nodes.containsKey(id))
		{
			nodes.get(id).add_content();
		}
		
		else
		{
			System.out.println("No such node present");
		}
	}

	public static void display_available_nodes(HashMap<Integer, Node> nodes, int unique_id) //function for displaying all nodes that are not linked to current node
	{
		System.out.println("To Enter the links to other nodes, enter their unique ID. If you want to stop entering the IDs, press -1 and hit ENTER");
		System.out.println("Available nodes for linking:");
		int count = 0;
		for (Integer i: nodes.keySet())
		{
			if (i != unique_id && !(nodes.get(i).link.containsKey(unique_id)))
			{
				System.out.println(i + ": " + nodes.get(i).name);
				count += 1;
			}
					
		}
		if (count == 0)
		{
			System.out.println("\nNo node available for linking. Press -1 to continue");
		}
	}

	public static void display_content(HashMap<Integer, Node> nodes) //function to display the content
	{
		System.out.println("Enter ID of the node for which you want to display content");
		int id = Integer.parseInt(s.nextLine());
		
		if (nodes.containsKey(id))
		{
			nodes.get(id).display_content();
		}
		
		else
		{
			System.out.println("No such node present");
		}
	}

	public static void search_content(HashMap<Integer, Node> nodes) //function for searching a content
	{
		System.out.println("Enter the content that you want to search for");
		String content_text = s.nextLine();
		int count = 0;
		System.out.println("Search Results: \n");
		
		for (Integer i: nodes.keySet())
		{
			for (String j: nodes.get(i).content)
			{
				if (j.toLowerCase().contains(content_text.toLowerCase()))
				{
					count += 1;
					System.out.println(nodes.get(i).name + " wrote: " + j + "\n");
				}
			}
		}
		
		System.out.println("Number of contents found = " + count);
	}

	public static void display_linked_posts(HashMap<Integer, Node> nodes) //function for displaying linked nodes' contents
	{
		System.out.println("Enter ID of the node for which the contents posted by its linked nodes will get displayed");
		int id = Integer.parseInt(s.nextLine());
		
		if (nodes.containsKey(id))
		{
			if (nodes.get(id).link.size() == 0)
			{
				System.out.println(nodes.get(id).name + " has no linked nodes");
			}
			else
			{
				System.out.println("These are the linked nodes of " + nodes.get(id).name + "\n");
				nodes.get(id).display_linked(nodes);
			}
		}
		
		else
		{
			System.out.println("No such node present");
		}
	}
}

class Node //parent class
{
	int unique_id;
	HashMap<Integer, String> link = new HashMap<Integer, String>();
	String name;
	String creation_date;
	Set<String> content = new HashSet<String>();
	int type;
	
	public Node(int unique_id, HashMap<Integer, String> link, String name, String creation_date, Set<String> content, int type) //constructor
	{
		this.unique_id = unique_id;
		this.link = link;
		this.name = name;
		this.creation_date = creation_date;
		this.content = content;
		this.type = type;
	}
	
	public String get_birthday() //getter method for getting birthday (will come in handy for individual class
	{
		return "None";
	}
	
	public void display(HashMap<Integer, Node> nodes) //function for displaying a node
	{
		System.out.println("Unique ID: " + unique_id);
		System.out.println("Name: " + name);
		
		for (Integer i: link.keySet())
		{
			System.out.println("Link with " + nodes.get(i).name + " as a " + link.get(i));
		}
		System.out.println("Creation Date: " + creation_date);
		for (String i: content)
		{
			System.out.println("Content: " + i);
		}
		switch(type)
		{
			case 1:
			{
				System.out.println("Type: Individual");
				break;
			}
			case 2:
			{
				System.out.println("Type: Group");
				break;
			}
			case 3:
			{
				System.out.println("Type: Business");
				break;
			}
			case 4:
			{
				System.out.println("Type: Organisation");
				break;
			}
		}
		
	}
	
	public void print_links(HashMap<Integer, Node> nodes) //function for displaying all the linked nodes of a current node.
	{
		int count = 0;
		for (Integer i: link.keySet())
		{
			nodes.get(i).display(nodes);
			System.out.println();
			count += 1;
		}
		
		if (count == 0)
		{
			System.out.println("No nodes are linked to " + name);
		}
	}

	public void add_content() //function for adding to the current node
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter content for " + name);
		String content_text = s.nextLine();
		content.add(content_text);
		
		System.out.println("Content Added");
	}

	public void display_content() //function for displaying the contents of the current node
	{
		if (content.size() == 0)
		{
			System.out.println("No content posted by " + name);
		}
		else
		{
			System.out.println("Content posted by " + name + ":");
			for (String i: content)
			{
				System.out.println("-> " + i);
			}
		}
	}

	public void display_linked(HashMap<Integer, Node> nodes) //function for displaying the contents of all the linked nodes of the current node
	{
		for (Integer i: link.keySet())
		{
			nodes.get(i).display_content();
		}
	}
}

class Individual extends Node //sub class
{
	String birthday;
	
	public Individual(int unique_id, HashMap<Integer, String> link, String name, String creation_date, Set<String> content, String birthday, int type) //constructor
	{
		super(unique_id, link, name, creation_date, content, type); //calling the constructor of the parent class
		this.birthday = birthday;
	}
	
	public String get_birthday() //getter method for getting the birthday of an individual

	{
		return birthday;
	}
	
	public void display(HashMap<Integer, Node> nodes) //display the Individual's node
	{
		super.display(nodes); //calling the display function of the parent class
		System.out.println("Birthday: " + birthday);
	}
}

class Business extends Node //sub class
{
	float x, y;
	
	public Business(int unique_id, HashMap<Integer, String> link, String name, String creation_date, Set<String> content, float x, float y, int type) //constructor
	{
		super(unique_id, link, name, creation_date, content, type); //calling the constructor of the parent class
		this.x = x;
		this.y = y;
	}
	
	public void display(HashMap<Integer, Node> nodes)
	{
		super.display(nodes); //calling the display function of the parent class
		System.out.println("X - Coordinate: " + x);
		System.out.println("Y - Coordinate: " + y);
	}
}

class Organisation extends Node //sub class
{
	float x, y;
	
	public Organisation(int unique_id, HashMap<Integer, String> link, String name, String creation_date, Set<String> content, float x, float y, int type) //constructor
	{
		super(unique_id, link, name, creation_date, content, type); //calling the constructor of the parent class
		this.x = x;
		this.y = y;
	}
	
	public void display(HashMap<Integer, Node> nodes)
	{
		super.display(nodes); //calling the display function of the parent class
		System.out.println("X - Coordinate: " + x);
		System.out.println("Y - Coordinate: " + y);
	}
}

class Group extends Node //sub class
{	
	public Group(int unique_id, HashMap<Integer, String> link, String name, String creation_date, Set<String> content, int type) //constructor
	{
		super(unique_id, link, name, creation_date, content, type); //calling the constructor of the parent class
	}
}
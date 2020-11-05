package entities;

public class Author {

	private int id;
	private String name;
	private String fname;
	
	public Author(int aId, String aName, String aFname) {
		this.id = aId;
		this.name = aName;
		this.fname = aFname;
	}
	
	public String getFname() {
		return fname;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: " + name + "\nPrimeiro nome: " + fname + "\n";
	}
	
}

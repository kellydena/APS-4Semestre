package entities;

public class Publisher {
	private int id;
	private String name;
	private String url;
	
	public Publisher(int aId, String aName, String url) {
		this.id = aId;
		this.name = aName;
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: " + name + "\nUrl: " + url + "\n";
	}
}

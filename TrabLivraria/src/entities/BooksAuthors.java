package entities;

public class BooksAuthors {

	private String isbn;
	private int autorID;
	private int seqNo;

	public BooksAuthors(String isbn, int autorID, int seqNo) {
		this.isbn = isbn;
		this.autorID = autorID;
		this.seqNo = seqNo;
		
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public int getAuthorID() {
		return autorID;
	}
	
	public int getSeqNo() {
		return seqNo;
	}
}

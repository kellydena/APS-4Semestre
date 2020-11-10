package entities;

public class BookAndPublisher {
	
	private String titulo;
	private String isbn;
	private String editora;
	private double preco;
	
	public BookAndPublisher(String titulo, String isbn, String editora, double preco2) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.editora = editora;
		this.preco = preco2;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editoraIdFk) {
		this.editora = editoraIdFk;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}


}

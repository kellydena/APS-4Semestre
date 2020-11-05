package entities;

public class Book {
	
	private String titulo;
	private String isbn;
	private int editoraIdFk;
	private double preco;
	
	public Book(String titulo, String isbn, int editoraIdFk, double preco2) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.editoraIdFk = editoraIdFk;
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

	public int getEditoraIdFk() {
		return editoraIdFk;
	}

	public void setEditoraIdFk(int editoraIdFk) {
		this.editoraIdFk = editoraIdFk;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", isbn=" + isbn + ", editoraIdFk=" + editoraIdFk + ", preco=" + preco + "]";
	}
	
}


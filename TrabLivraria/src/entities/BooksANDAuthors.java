package entities;

public class BooksANDAuthors {
	private String titullo;
	private String isbnLivro;
	private String autorNome;
	
	public BooksANDAuthors(String titullo, String isbnLivro, String autorNome) {
		super();
		this.titullo = titullo;
		this.isbnLivro = isbnLivro;
		this.autorNome = autorNome;
	}

	public String getTitullo() {
		return titullo;
	}

	public void setTitullo(String titullo) {
		this.titullo = titullo;
	}

	public String getIsbnLivro() {
		return isbnLivro;
	}

	public void setIsbnLivro(String isbnLivro) {
		this.isbnLivro = isbnLivro;
	}

	public String getAutorNome() {
		return autorNome;
	}

	public void setAutorNome(String autorNome) {
		this.autorNome = autorNome;
	}

	@Override
	public String toString() {
		return "AutorLivro [titullo=" + titullo + ", isbnLivro=" + isbnLivro + ", autorNome=" + autorNome + "]";
	}
}

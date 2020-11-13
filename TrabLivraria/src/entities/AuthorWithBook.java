package entities;

public class AuthorWithBook {
	private String titulo;
	private String isbnLivro;
	private String autorNome;
	
	public AuthorWithBook(String titulo, String isbnLivro, String autorNome) {
		super();
		this.titulo = titulo;
		this.isbnLivro = isbnLivro;
		this.autorNome = autorNome;
	}

	public String getTitullo() {
		return titulo;
	}

	public void setTitullo(String titullo) {
		this.titulo = titullo;
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
		return "AutorLivro [titullo=" + titulo + ", isbnLivro=" + isbnLivro + ", autorNome=" + autorNome + "]";
	}
}

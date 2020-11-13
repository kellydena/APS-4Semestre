package model.dao.buscar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

//import com.mysql.jdbc.Statement;

import entities.Author;
import entities.Book;
import entities.PublisherWithBook;
import entities.AuthorWithBook;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoBusca;
import model.dao.DaoUtilConnection;



public class Busca implements DaoBusca{
	
	private static String USER = "";
    private static String PASS = "";
    private static String URL = "";
    
    public Busca() {
        Properties propri = DaoUtilConnection.readProperties("src/db.properties");
        
    	USER = propri.getProperty("USER");
        PASS = propri.getProperty("PASSWORD");
        URL = propri.getProperty("URL");
    }

	@Override
	public ArrayList<Book> buscaLivros(String title) {
		ArrayList<Book> livros = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Books WHERE LOWER(title) LIKE LOWER(?) ORDER BY title";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + title + "%");
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("title");
				String isbn = rs.getString("isbn"); 
				int editoraIdFk = rs.getInt("publisher_id");
        	    double preco = rs.getDouble("price");			      
        	    
        	    Book livro = new Book(titulo, isbn, editoraIdFk, preco);
				livros.add(livro);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  
		return livros;
	}
	
	@Override
	public Book buscaLivroPorISBN(String isbn) {
		Book livro = null;
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Books WHERE isbn = (?) ORDER BY title";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1,isbn);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("title");
				String isbnLivro = rs.getString("isbn"); 
				int editoraIdFk = rs.getInt("publisher_id");
        	    double preco = rs.getDouble("price");			      
        	    
        	    livro = new Book(titulo, isbnLivro, editoraIdFk, preco);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  
		return livro;
	}
	
	@Override
	public ArrayList<PublisherWithBook> buscaLivroComEditora(String isbnOrName,String editoraNome) {
		ArrayList<PublisherWithBook> livros = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			PreparedStatement pstm;
			
			if(editoraNome.equals("")) { //Se nao passarmos a editora, significa que ele ira buscar pelo isbn ou o nome do livro
			final String query = "SELECT Books.Title, Books.isbn, Publishers.name, Books.price FROM Books INNER JOIN Publishers ON "
					+ "Books.publisher_id = Publishers.publisher_id"
					+ " WHERE Books.isbn LIKE(?) OR Books.title LIKE (?)"
					+ " ORDER BY books.title";
			
			pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + isbnOrName + "%");
			pstm.setString(2, "%" + isbnOrName + "%");

			} else { //Caso o programador tenha passado a editora, fica implicito que quer pesquisar pelo nome dela
				final String query = "SELECT Books.Title, Books.isbn, Publishers.name, Books.price FROM Books INNER JOIN Publishers ON "
						+ "Books.publisher_id = Publishers.publisher_id"
						+ " WHERE Publishers.name LIKE(?)"
						+ " ORDER BY books.title";
				
				pstm = con.prepareStatement(query); 

				pstm.setString(1, "%" + editoraNome + "%");
			}
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("title");
				String isbnLivro = rs.getString("isbn"); 
				String editora = rs.getString("name");
        	    double preco = rs.getDouble("price");				      
        	    
        	    PublisherWithBook livro = new PublisherWithBook(titulo, isbnLivro, editora, preco);
				livros.add(livro);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  
		return livros;
	}

	@Override
	public ArrayList<Publisher> buscaEditora(String nome) {
		ArrayList<Publisher> editoras = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Publishers WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + nome + "%");
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int id_editora = rs.getInt("publisher_id");
				String name = rs.getString("name");
				String url = rs.getString("url"); 	
				
				Publisher editora = new Publisher(id_editora, name, url);
				editoras.add(editora);
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return editoras;
	}		
	

	@Override
	public ArrayList<Author> buscaAutor(String nome, String sobrenome) {
		ArrayList<Author> autores = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Authors WHERE LOWER(name) LIKE LOWER((?)) OR LOWER(fname) LIKE LOWER((?)) ORDER BY fname, name";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + nome + "%");
			pstm.setString(2, '%' + sobrenome + '%');
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int autor_id = rs.getInt("author_id");
		        String name = rs.getString("name");
				String fnome = rs.getString("fname");
				      	
				Author autor = new Author(autor_id, name, fnome);
				autores.add(autor);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return autores;
	}

	@Override
	public ArrayList<AuthorWithBook> buscaAuthorWithBook(String titulo, String nomeAutor) {
		ArrayList<AuthorWithBook> autorLivros = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT Books.title, Books.isbn, Authors.name, Authors.fname from BooksAuthors inner join Books"
					+ " on BooksAuthors.isbn = Books.isbn "
					+ "inner join Authors on BooksAuthors.author_id = Authors.author_id "
					+ "where Books.title like(?) or LOWER(name) LIKE LOWER((?)) OR LOWER(fname) LIKE LOWER((?)) ORDER BY title, fname, name";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + titulo + "%");
			pstm.setString(2, '%' + nomeAutor + '%');
			pstm.setString(3, "%" + nomeAutor + "%");
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String tituloLivro = rs.getString("title"); 
				String isbn = rs.getString("isbn");
				String nome=  rs.getString("fname") + " " + rs.getString("name");				      

				AuthorWithBook autorLivro = new AuthorWithBook(tituloLivro, isbn, nome);
				
				autorLivros.add(autorLivro);		
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return autorLivros;
	}

	@Override
	public ArrayList<BooksAuthors> buscaLivrosAutores(int author_id) {
		ArrayList<BooksAuthors> livrosAutores = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM BooksAuthors WHERE author_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, author_id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int autor_id = rs.getInt("author_id");
		        String isbn = rs.getString("isbn");
				int seq_number = rs.getInt("seq_no");
				      	
				BooksAuthors livroAutor = new BooksAuthors(isbn, autor_id, seq_number);
				livrosAutores.add(livroAutor);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return livrosAutores;
	}

	@Override
	public ArrayList<Book> buscaLivroPorEditora(Publisher editora) {
		ArrayList<Book> livros = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Books WHERE publisher_id = (?);";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, editora.getId());
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("title");
				String isbn = rs.getString("isbn"); 
				int editoraIdFk = rs.getInt("publisher_id");
        	    double preco = rs.getDouble("price");			      
        	    
        	    Book livro = new Book(titulo, isbn, editoraIdFk, preco);
				livros.add(livro);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
}

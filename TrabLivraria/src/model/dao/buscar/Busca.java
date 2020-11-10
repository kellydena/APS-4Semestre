package model.dao.buscar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.mysql.jdbc.Statement;

import entities.Author;
import entities.Book;
import entities.BookAndPublisher;
import entities.BooksANDAuthors;
import entities.Publisher;
import model.dao.DaoBusca;



public class Busca implements DaoBusca{
	
	private static final String USER = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/Livraria?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    public static void fazConexao(){
		Connection con= null;
		try {
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASS); 
		System.out.println("Conexão Feita");
		
		}catch(SQLException e) {
			System.out.println("Conexão não foi Feita");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Book> buscaLivros(String title) {
		ArrayList<Book> livros = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Books WHERE LOWER(title) LIKE LOWER(?)";
			
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
			
			final String query = "SELECT * FROM Books WHERE isbn = (?)";
			
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
<<<<<<< HEAD
	
	@Override
	public ArrayList<BookAndPublisher> buscaLivroComEditora(String isbn) {
		ArrayList<BookAndPublisher> livros = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT Books.Title, Books.isbn, Publishers.name, Books.price FROM Books INNER JOIN Publishers ON Books.publisher_id = Publishers.publisher_id WHERE Books.isbn LIKE(?);";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + isbn + "%");
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("title");
				String isbnLivro = rs.getString("isbn"); 
				String editora = rs.getString("name");
        	    double preco = rs.getDouble("price");				      
        	    
        	    BookAndPublisher livro = new BookAndPublisher(titulo, isbnLivro, editora, preco);
				livros.add(livro);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}  
		return livros;
	}
=======
>>>>>>> 61b74d8cf2a82187e6035b42fc1102455932ac25

	@Override
	public ArrayList<Publisher> buscaEditora(String nome) {
		ArrayList<Publisher> editoras = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT * FROM Publishers WHERE LOWER(name) LIKE LOWER(?)";
			
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
			
			final String query = "SELECT * FROM Authors WHERE LOWER(name) LIKE LOWER((?)) OR LOWER(fname) LIKE LOWER((?))";
			
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
	public ArrayList<BooksANDAuthors> buscaAutorANDLivro(String titulo, String nomeAutor) {
		ArrayList<BooksANDAuthors> autorLivros = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "SELECT Books.title, Books.isbn, Authors.name from BooksAuthors inner join Books on BooksAuthors.isbn_books_fk = Books.isbn inner join Authors on BooksAuthors.authorID_authors_fk = Authors.author_id where Books.title like(?) or Authors.name like (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, "%" + titulo + "%");
			pstm.setString(2, '%' + nomeAutor + '%');
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String tituloLivro = rs.getString("title"); 
				String isbn = rs.getString("isbn");
				String nome=  rs.getString("name");				      
				
//<<<<<<< HEAD
//				BooksAuthors autorLivro = new BooksAuthors("", 0, 0);
//=======
//				BooksANDAuthors autorLivro = new BooksANDAuthors(tituloLivro, isbn, nome);
//>>>>>>> cfd50280d1035bff57b3bc25f7c3fbfaa96a43ca
//				autorLivros.add(autorLivro);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return autorLivros;
	}
}

# 4S-ALPOO

###### [ENGLISH]
This is a project made to manage a bookstore. You can delete, change, add and search for authors, books and publishers. There is a connection with MySQL database.
_**Make sure to change the database acess in file db.properties in src folder**_
Database structure can be found below the portuguese text.

###### [PORTUGUÊS]
Esse é um projeto desenvolvido para gerenciar uma livraria. Nele podemos apagar, alterar, incluir e buscar autores, livros e editoras. Há conexão com um banco de dados desenvolvido em MySQL. 
_**Não se esqueça de trocar o acesso ao banco de dados no arquivo db.properties que se encontra na pasta src.**_
A estrutura do banco se encontra abaixo:

```mysql
CREATE TABLE Authors (
  author_id INT(8) AUTO_INCREMENT PRIMARY KEY, 
  name CHAR(25) NOT NULL, 
  fname CHAR(25) NOT NULL
);

CREATE TABLE Publishers (
  publisher_id INT(8) AUTO_INCREMENT PRIMARY KEY, 
  name CHAR(30) NOT NULL, 
  url CHAR(80) NOT NULL
);

CREATE TABLE Books (
  title CHAR(60) NOT NULL, 
  isbn CHAR(13) PRIMARY KEY, 
  publisher_id INT NOT NULL, 
  price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (publisher_id) REFERENCES Publishers (publisher_id)
);

CREATE TABLE BooksAuthors (
  isbn CHAR(13) NOT NULL, 
  author_id INT NOT NULL, 
  seq_no INT NOT NULL,
  FOREIGN KEY (isbn) REFERENCES Books (isbn),
  FOREIGN KEY (author_id) REFERENCES Authors (author_id),
  PRIMARY KEY (isbn, author_id)
);
```

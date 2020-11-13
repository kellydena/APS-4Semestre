package model.dao;

public interface DaoExcluir {
	public void excluiLivro(String key);
	public void excluirAutor(int key);
	public void excluirEditora(int key);
	public void excluirLivroAutor(int autorID, String isbn);

}

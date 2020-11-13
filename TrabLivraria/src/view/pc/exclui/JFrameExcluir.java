package view.pc.exclui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import entities.Author;
import entities.Book;
import entities.Publisher;
import view.pc.FrameBase;
import view.pc.util.list.JFrameListEditoras;
import view.pc.util.list.JFrameListLivros;
import view.pc.util.list.JFrameListSomenteUmAutor;


public class JFrameExcluir extends FrameBase implements ViewExclui{
	private static final long serialVersionUID = 1L;

	private JButton buttonSubmit;
	
	private JButton verificaLivro;
	private JButton verificaAutor;
	private JButton verificaEditora;
	
	private ArrayList<Author> listaAutores;
	private ArrayList<Publisher> listaEditoras;
	private ArrayList<Book> listaLivros;
	
	private Publisher editoraEscolhida;
	private Book livroEscolhido;
	private Author autorEscolhido;
	
	private JFrameListLivros janelaLivros;
	private JFrameListSomenteUmAutor janelaAutores;
	private JFrameListEditoras janelaEditoras;
	private JComboBox<String> cb;

	public JFrameExcluir(){
		super("excluir");
		setTitle("Excluir");
			
		PanelExcluir panelEscolhas = new PanelExcluir();
		super.add(panelEscolhas);
			
		pack();
		setLocationRelativeTo(null);
		setVisible(true);		
	}
	
	class PanelExcluir extends JPanel implements ItemListener{
		private static final long serialVersionUID = 1L;
		
		JPanel cards; //a panel that uses CardLayout
	    final static String AutoresPanel = "Autores";
	    final static String EditorasPanel = "Editoras";
	    final static String LivrosPanel = "Livros";
	    
	    public PanelExcluir(){
	    	setLayout(new BorderLayout(0,15));
	    	this.addComponentToPane(this);
	    }
	    
	    public void addComponentToPane(Container pane) {
	    	//Criando uma mascara que so aceita numeros
	    	NumberFormat number = NumberFormat.getInstance();
	    	number.setGroupingUsed(false); //Tira vírgula
	        NumberFormatter mascaraNumero = new NumberFormatter(number);
	        mascaraNumero.setValueClass(Integer.class);
	        mascaraNumero.setMinimum(0);
	        mascaraNumero.setMaximum(Integer.MAX_VALUE);
	        mascaraNumero.setAllowsInvalid(false);
	        
	    	
	        //Put the JComboBox in a JPanel to get a nicer look.
	        JPanel comboBoxPane = new JPanel(); //use FlowLayout
	        String comboBoxItems[] = { AutoresPanel, LivrosPanel, EditorasPanel };
	        cb = new JComboBox<String>(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        comboBoxPane.add(cb);
	        
/*----------------------------------------AUTORES----------------------------------------------------------------------*/	        

	        JPanel card1 = new JPanel();
            card1.add(new JLabel("Autor(a) a ser excluido(a): ", JLabel.TRAILING));
            verificaAutor = new JButton("Escolha o autor");
            verificaAutor.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					janelaAutores = new JFrameListSomenteUmAutor(listaAutores);
					janelaAutores.addEscolheAutor(new EscolheAutor());
				}
			});
	        card1.add(verificaAutor);
	        
/*----------------------------------------LIVROS----------------------------------------------------------------------*/
	        
	        JPanel card2 = new JPanel();
            card2.add(new JLabel("Livro a ser excluido: ", JLabel.TRAILING));
            verificaLivro = new JButton("Escolher livro");
            verificaLivro.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					janelaLivros = new JFrameListLivros(listaLivros);
					janelaLivros.addEscolheLivro(new EscolheLivro());
				}
			});
	        card2.add(verificaLivro);
	        
/*----------------------------------------EDITORAS----------------------------------------------------------------------*/
	        
	        JPanel card3 = new JPanel();
            card3.add(new JLabel("Editora a ser excluida: ", JLabel.TRAILING));
            verificaEditora = new JButton("Escolher editora");
            verificaEditora.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					janelaEditoras = new JFrameListEditoras(listaEditoras);
					janelaEditoras.addEscolheEditora(new EscolheEditora());
				}
			});
	        card3.add(verificaEditora);
	        
	        
	        //painel que guardara os cards
	        cards = new JPanel(new CardLayout());
	        cards.add(card1, AutoresPanel);
	        cards.add(card2, LivrosPanel);
	        cards.add(card3,EditorasPanel);
	        
	        buttonSubmit = new JButton("Enviar");
	       
	        pane.add(comboBoxPane, BorderLayout.PAGE_START);
	        pane.add(cards, BorderLayout.CENTER);
	        pane.add(buttonSubmit, BorderLayout.SOUTH);
	        
	    }
	    
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
	    }
	}
	
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
	

    
    class EscolheLivro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			livroEscolhido = janelaLivros.getLivro();
			verificaLivro.setEnabled(false);
		}  	
    }
    
    class EscolheAutor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			autorEscolhido = janelaAutores.getAutor();
			verificaAutor.setEnabled(false);
		}
    }
    
    class EscolheEditora implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			editoraEscolhida = janelaEditoras.getEditora();
			verificaEditora.setEnabled(false);
		}	
    }

	@Override
	public void addSubmitBehavior(ActionListener al) {buttonSubmit.addActionListener(al);}

	@Override
	public void setAutores(ArrayList<Author> autores) {listaAutores = autores;}

	@Override
	public void setEditoras(ArrayList<Publisher> editoras) {listaEditoras = editoras;}

	@Override
	public void setLivros(ArrayList<Book> livros) {listaLivros = livros;}

	@Override
	public Author getAutor() {return autorEscolhido;}

	@Override
	public Publisher getEditora() {return editoraEscolhida;}

	@Override
	public Book getLivro() {return livroEscolhido;}

	@Override
	public Object getComboBoxSelected() {return cb.getSelectedItem();}

	@Override
	public void disposeFrame() {dispose();}
	
	
	
}

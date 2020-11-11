package view.pc.altera;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import entities.Author;
import entities.Book;
import entities.Publisher;
import view.pc.FrameBase;
import view.pc.util.list.JFrameListAutores;
import view.pc.util.list.JFrameListEditoras;
import view.pc.util.list.JFrameListLivros;
import view.pc.util.list.JFrameListSomenteUmAutor;

public class JFrameAlterar extends FrameBase implements ViewAltera{
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> cb;
	private JFormattedTextField txtISBN;
	
	private JButton bVerificaNomeLivro;
	private JTextField txtTitulo;
	private JTextField txtBookPrice;
	private JButton buttonChooseAuthors;
	private JButton buttonChoosePublishers;
	private ArrayList<Author> autoresEscolhidosParaLivro;
	private Publisher editoraEscolhidaParaLivro;
	private Book livroEscolhido;
	
	private JButton bVerificaNomeAutor;
	private ArrayList<Author> listaAutoresAutores;
	private JTextField txtNomeAutor;
	private JTextField txtSobrenomeAutor;
	private Author NomeAuthorEscolhido;
	
	private JButton bVerificaNomeEditora;
	private JTextField txtNomeEditora;
	private JTextField txtUrlEditora;
	private Publisher editoraEscolhida;
	
	private ArrayList<Author> listaAutores;
	private ArrayList<Publisher> listaEditoras;
	private ArrayList<Book> listaLivros;
	
	private JButton buttonSubmit;

	
	private JFrameListEditoras janelalistaEditorasParaLivro;
	private JFrameListAutores janelalistaAutores;
	
	private ArrayList<Author> listaAutoresAutor;
	private JFrameListLivros janelalistaLivrosLivros;
	private JFrameListEditoras janelalistaEditorasEditora;
	private JFrameListSomenteUmAutor JanelalistaAutoresAutor;
	
	public JFrameAlterar() {
		super("alterar");
		setTitle("Alterar");
		PanelInclui panelEscolhas = new PanelInclui();
		add(panelEscolhas);

		setSize(400,350);
		setLocationRelativeTo(null);
		setVisible(true);
	}
		
		class PanelInclui extends JPanel implements ItemListener{
			private static final long serialVersionUID = 1L;
			JPanel cards; //a panel that uses CardLayout
		    final static String AutoresPanel = "Autores";
		    final static String EditorasPanel = "Editoras";
		    final static String LivrosPanel = "Livros";  
		    
		    public PanelInclui() {
		    	setLayout(new BorderLayout(0,15));
		    	this.addComponentToPane(this);
		    }
		    
		    public void addComponentToPane(Container pane) {
		        
		        //Put the JComboBox in a JPanel to get a nicer look.
		        JPanel comboBoxPane = new JPanel(); //use FlowLayout
		        String comboBoxItems[] = { AutoresPanel, LivrosPanel, EditorasPanel };
		        cb = new JComboBox<String>(comboBoxItems);
		        cb.setEditable(false);
		        cb.addItemListener(this);
		        comboBoxPane.add(cb);
		        
/*----------------------------------------AUTORES----------------------------------------------------------------------*/
		        JPanel card1 = new JPanel();
		        card1.setLayout(new FlowLayout(FlowLayout.TRAILING));
		        
		        card1.add(new JLabel("Autor(a) a ser alterado(a): ", JLabel.TRAILING));
		        bVerificaNomeAutor = new JButton("Escolher autor(a)");
		        bVerificaNomeAutor.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						JanelalistaAutoresAutor = new JFrameListSomenteUmAutor(listaAutores);
						JanelalistaAutoresAutor.addEscolheAutor(new EscolherAutoresAutor());
					}
				});
		        card1.add(bVerificaNomeAutor);
		     
	            card1.add(new JLabel("           Novo nome: ", JLabel.TRAILING));
	            txtNomeAutor = new JTextField(20);
		        card1.add(txtNomeAutor);
		        
		        card1.add(new JLabel("Novo sobrenome: ", JLabel.TRAILING));
		        txtSobrenomeAutor = new JTextField(20);
		        card1.add(txtSobrenomeAutor);
		        
/*----------------------------------------LIVROS----------------------------------------------------------------------*/		        
		        JPanel card2 = new JPanel();
		        card2.setLayout(new GridLayout(5,2));
		        //txtISBN = new JFormattedTextField(createFormatter("#-###-#####-#"));
	            card2.add(new JLabel("Livro a ser alterado: ", JLabel.TRAILING));
		        //txtISBN.setPreferredSize( new Dimension( 50, 30 ));
	            bVerificaNomeLivro = new JButton("Escolher livro");
	            bVerificaNomeLivro.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						janelalistaLivrosLivros = new JFrameListLivros(listaLivros);
						janelalistaLivrosLivros.addEscolheLivro(new EscolherLivro());
					}
				});
	            
		        card2.add(bVerificaNomeLivro);
		        
		        
		        txtTitulo = new JTextField();
	            card2.add(new JLabel("Titulo: ", JLabel.TRAILING));
	            txtTitulo.setPreferredSize( new Dimension( 50, 30 ));
		        card2.add(txtTitulo);
		        
		        txtBookPrice = new JTextField();
		        txtBookPrice.addKeyListener(new VerificadorNumero());

		        
	            card2.add(new JLabel("Preço: ", JLabel.TRAILING));
	            txtBookPrice.setPreferredSize( new Dimension( 50, 30 ));
		        card2.add(txtBookPrice);
		        
		        card2.add(new JLabel("Escolher Autores: ", JLabel.TRAILING));
	            buttonChooseAuthors = new JButton("Autores");
	            buttonChooseAuthors.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {						
						janelalistaAutores = new JFrameListAutores(listaAutores);
						janelalistaAutores.addEscolheAutores(new EscolherAutores());
					}
				});
	            card2.add(buttonChooseAuthors);
	            
		        card2.add(new JLabel("Escolher Editora: ", JLabel.TRAILING));
		        buttonChoosePublishers = new JButton("Editora");
		        buttonChoosePublishers.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						janelalistaEditorasParaLivro = new JFrameListEditoras(listaEditoras);
						janelalistaEditorasParaLivro.addEscolheEditora(new EscolherEditoraParaLivro());
						
					}
				});
	            card2.add(buttonChoosePublishers); 

/*----------------------------------------EDITORAS----------------------------------------------------------------------*/
		        JPanel card3 = new JPanel();
		        card3.setLayout(new FlowLayout(FlowLayout.TRAILING));
		        bVerificaNomeEditora = new JButton("Escolher editora");
		        bVerificaNomeEditora.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						janelalistaEditorasEditora = new JFrameListEditoras(listaEditoras);
						janelalistaEditorasEditora.addEscolheEditora(new EscolherEditora());
					}
				});
		        card3.add(new JLabel("Escolha a editora a ser alterada: ", JLabel.TRAILING));
		        card3.add(bVerificaNomeEditora);
		        
		        txtNomeEditora = new JTextField(20);
		        card3.add(new JLabel("Nome da Editora: ", JLabel.TRAILING));
		        card3.add(txtNomeEditora);
		        
		        txtUrlEditora = new JTextField(20);
		        card3.add(new JLabel("URL da editora: ", JLabel.TRAILING));
		        card3.add(txtUrlEditora);	        
		        
		        //Create the panel that contains the "cards".
		        cards = new JPanel(new CardLayout());
		        cards.add(card1, AutoresPanel);
		        cards.add(card2, LivrosPanel);
		        cards.add(card3,EditorasPanel);
		        
		        pane.add(comboBoxPane, BorderLayout.PAGE_START);
		        pane.add(cards, BorderLayout.CENTER);
		        buttonSubmit = new JButton("Enviar");
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
	    
	    private void verificaNumero(KeyEvent evt, JTextField txtField) {
	    	 char c=evt.getKeyChar();
	         if(!Character.isDigit(c) && c != '.'){
	             evt.consume();
	         }
	         if(txtField.getText().contains(".") && c == '.') {
	        	 evt.consume();
	         }
	    }

	    
	    class VerificadorNumero implements KeyListener {

			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {verificaNumero(e, txtBookPrice);}
	    }
	    
	    
		class EscolherEditora implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				editoraEscolhida = janelalistaEditorasEditora.getEditora();
				bVerificaNomeEditora.setEnabled(false);
			}
			
		}
		
		class EscolherEditoraParaLivro implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				editoraEscolhidaParaLivro = janelalistaEditorasParaLivro.getEditora();
				buttonChoosePublishers.setEnabled(false);
			}
		}
		
		class EscolherAutores implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				autoresEscolhidosParaLivro = janelalistaAutores.getAutores();
				buttonChooseAuthors.setEnabled(false);
			}
		}
		
		class EscolherLivro implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				livroEscolhido = janelalistaLivrosLivros.getLivro();
				bVerificaNomeLivro.setEnabled(false);
			}
		}
		
		class EscolherAutoresAutor implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				NomeAuthorEscolhido = JanelalistaAutoresAutor.getAutor();
				bVerificaNomeAutor.setEnabled(false);
			}
		}
		

		@Override
		public void setAutores(ArrayList<Author> a) {listaAutores = a; listaAutoresAutores = a;}

		@Override
		public void setEditoras(ArrayList<Publisher> p) {listaEditoras = p;}
		
		@Override
		public void setLivros(ArrayList<Book> b) {listaLivros = b;}

		@Override
		public void addSubmitBehavior(ActionListener al) {buttonSubmit.addActionListener(al);}

		@Override
		public Object getComboBoxSelected() {return cb.getSelectedItem();}

		@Override
		public String getFirstName() {return txtNomeAutor.getText();}

		@Override
		public String getLastName() {return txtSobrenomeAutor.getText();}

		@Override
		public String getEditoraName() {return txtNomeEditora.getText();}

		@Override
		public String getUrl() {return txtUrlEditora.getText();}

		@Override
		public String getTitleBook() {return txtTitulo.getText();}
		
		@Override
		public double getPrice() {
			double price;
			try{
				price = Double.parseDouble(txtBookPrice.getText());
			} catch(NumberFormatException  e){
				price = 0.00;
				
			}
			return price;
		}

		@Override
		public Publisher getPublisherBook() {return editoraEscolhidaParaLivro;}

		@Override
		public ArrayList<Author> getAuthorsBook() {return autoresEscolhidosParaLivro;}

		@Override
		public void addVerificaNomeBehavior(ActionListener al) {}


		@Override
		public Author getAuthor() {return NomeAuthorEscolhido;}

		@Override
		public Publisher getPublisher() {return editoraEscolhida;}
		

		@Override
		public void disposeFrame() {dispose();}

		@Override
		public Book getBook() {return livroEscolhido;}




		
	    
		
	
}





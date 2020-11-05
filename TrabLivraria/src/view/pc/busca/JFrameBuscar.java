package view.pc.busca;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import entities.Author;
import entities.Book;
import entities.Publisher;
import view.pc.FrameBase;
import view.pc.busca.tables.JTableAutores;
import view.pc.busca.tables.JTableEditoras;

public class JFrameBuscar extends FrameBase implements ViewBusca{

	private JRadioButton bTodosLivros;
	private JRadioButton bTodosAutores;
	private JRadioButton bTodasEditoras;
	private JRadioButton bLivrosPorAutor;
	private JRadioButton bAutorPorEditora;
	
	private JTextFieldPersonalizado txtSubimit;
	private JButton bSubmit;
	
	private final String pesquisaLivro = "Escreva um livro";
	private final String pesquisaAutor = "Escreva um autor";
	private final String pesquisaEditora = "Escreva uma editora";
	private String NomeDaDica = pesquisaEditora;
	private ButtonGroup bg;
	
	public JFrameBuscar() {
		super("Buscar");
		setTitle("Buscar");
		
		setSize(500,400);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(new PanelPrincipal());
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	class PanelPrincipal extends JPanel{
		public PanelPrincipal() {
			setLayout(new BorderLayout());
			
			add(new PanelButtons(), BorderLayout.CENTER);
			
			add(new PanelTxt(), BorderLayout.PAGE_END);
		}
	}
	
	class PanelButtons extends JPanel{
		
		public PanelButtons() {
			ActionListener al = new JButtonBehavior();
			bg = new ButtonGroup();
			bTodasEditoras = new JRadioButton("Todas Editoras");
			bTodasEditoras.addActionListener(al);
			bTodasEditoras.setActionCommand(pesquisaEditora);
			bTodosAutores = new JRadioButton("Todos Autores");
			bTodosAutores.addActionListener(al);
			bTodosAutores.setActionCommand(pesquisaAutor);
			bTodosLivros = new JRadioButton("Todos livros");
			bTodosLivros.addActionListener(al);
			bTodosLivros.setActionCommand(pesquisaLivro);
			bLivrosPorAutor = new JRadioButton("Livros por Autor");
			bLivrosPorAutor.addActionListener(al);
			bLivrosPorAutor.setActionCommand(pesquisaAutor);
			bAutorPorEditora = new JRadioButton("Autores por Editora");
			bAutorPorEditora.addActionListener(al);
			bAutorPorEditora.setActionCommand(pesquisaEditora);
			bg.add(bTodasEditoras);
			bg.add(bTodosAutores);
			bg.add(bTodosLivros);
			bg.add(bLivrosPorAutor);
			bg.add(bAutorPorEditora);
			add(bTodasEditoras);
			add(bTodosAutores);
			add(bTodosLivros);
			add(bLivrosPorAutor);
			add(bAutorPorEditora);
			bTodasEditoras.setSelected(true);
			
		}
	}
	
	class PanelTxt extends JPanel {
		
		public PanelTxt() {
//			setBackground(Color.yellow);
			
			txtSubimit = new JTextFieldPersonalizado();
			txtSubimit.setHorizontalAlignment(txtSubimit.CENTER);
			bSubmit = new JButton("Enviar");
			//bSubmit.addActionListener(new ButtonSubmitBehavior());
			
			add(txtSubimit);
			add(bSubmit);
		}
	}
	
	class JTextFieldPersonalizado extends JTextField implements FocusListener{
		 private boolean showingHint;
		  
		public JTextFieldPersonalizado() {
			setText(NomeDaDica);
			super.addFocusListener(this);
			setColumns(25);
		    this.showingHint = true;  
		}
		
		@Override
		public void focusGained(FocusEvent arg0) {
			if(this.getText().isEmpty()) {
			      super.setText("");
			      showingHint = false;
			      }
		}

		@Override
		public void focusLost(FocusEvent arg0) {
		    if(this.getText().isEmpty()) {
		        super.setText(NomeDaDica);
		        showingHint = true;
		      }
		}
		
		@Override
		public String getText() {
			if(showingHint) {
				return "";
			} else {
				return super.getText();
			}
			//return showingHint ? "" : super.getText(); outro jeito de escrever, isso se chama operator ternario
		}
		
	}
	class JButtonBehavior implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			NomeDaDica = e.getActionCommand();
			txtSubimit.focusLost(null);
			
		}
		
	}

	@Override
	public void addSubmitBehavior(ActionListener al) {
		bSubmit.addActionListener(al);
		
	}

	@Override
	public void mostrarListaEditora(ArrayList<Publisher> editoras) {
		new JTableEditoras(editoras);
	}

	@Override
	public String getJRadioButton() {
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();

	        if (button.isSelected()) {
	            return button.getText();
	        }
	    }
		return null;
	}

	@Override
	public String getJTextFieldText() {
		return txtSubimit.getText();
	}

	@Override
	public void mostrarListaAutor(ArrayList<Author> autores) {
		new JTableAutores(autores);
		
	}

	@Override
	public void mostrarListaLivro(ArrayList<Book> livros) {
		// TODO Auto-generated method stub
		
	}
	
	


	
}

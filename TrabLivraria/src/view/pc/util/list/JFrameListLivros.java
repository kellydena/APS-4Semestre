package view.pc.util.list;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import entities.Book;
import entities.Publisher;

public class JFrameListLivros extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JComboBox<String> lista;
	JPanel panelEscolhas;
	
	JComboBox<String> cb;
    private JList<String> list;
    private DefaultListModel<String> listModel;
 
    private static final String finishString = "Terminar";
    private JButton buttonFinish;
    
	ArrayList<Book> livros;
	Publisher editoraEscolhida;
    
	
	public JFrameListLivros(ArrayList<Book> livros) {
		super("Escolhe Livro");
		setSize(400,400);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setResizable(false);
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		
		this.livros = livros;
		listModel = new DefaultListModel<String>();

		
		for(Book b : this.livros) {
			listModel.addElement(b.getTitulo());
		}
 
        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        //list.setCellRenderer(new AuthorRender()); //Mostra as celulas bonitinhas na lista 
// 
        buttonFinish = new JButton(finishString);
        //buttonFinish.addActionListener(TerminaListener);
 
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        
        
        buttonPane.add(buttonFinish);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
	
	public void addEscolheLivro(ActionListener escolheLivro) {
        buttonFinish.addActionListener(escolheLivro);
	}
    
	public Book getLivro() {
		int index = list.getSelectedIndex();
		dispose();
		return livros.get(index);
		
		
	}
}

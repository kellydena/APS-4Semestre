package view.pc.exclui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import view.pc.FrameBase;


public class JFrameExcluir extends FrameBase implements ViewExclui{

	PanelExcluir panelEscolhas;
	JComboBox<String> lista;
	
	JFormattedTextField txtIDAutor;
	JFormattedTextField txtIDEditora;
	JButton buttonSubmit;
	JComboBox cb;
	JFormattedTextField txtISBN;
	public JFrameExcluir(){
		super("excluir");
		setTitle("Excluir");
		
		
		panelEscolhas = new PanelExcluir();
		super.add(panelEscolhas);
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	class PanelExcluir extends JPanel implements ItemListener{
		
		JPanel cards; //a panel that uses CardLayout
	    final static String AutoresPanel = "Autores";
	    final static String EditorasPanel = "Editoras";
	    final static String LivrosPanel = "Livros";
	    
	    public PanelExcluir(){
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
	        cb = new JComboBox(comboBoxItems);
	        cb.setEditable(false);
	        cb.addItemListener(this);
	        comboBoxPane.add(cb);
	        
	        
	        //Create the "cards".
	        JPanel card1 = new JPanel();
	        card1.setLayout(new GridLayout(1,3));
	        txtIDAutor = new JFormattedTextField(mascaraNumero);
            card1.add(new JLabel("ID do Autor: ", JLabel.TRAILING));
            txtIDAutor.setPreferredSize( new Dimension( 50, 30 ));
	        card1.add(txtIDAutor);
	        buttonSubmit = new JButton("Enviar");
	        buttonSubmit.addActionListener(new testBehavior());
	        card1.add(buttonSubmit);
	        
	        
	        JPanel card2 = new JPanel();
	        card2.setLayout(new GridLayout(1,3));
	        txtISBN = new JFormattedTextField(createFormatter("#-###-#####-#"));
            card2.add(new JLabel("ISBN: ", JLabel.TRAILING));
	        txtISBN.setPreferredSize( new Dimension( 50, 30 ));
	        card2.add(txtISBN);
	        buttonSubmit = new JButton("Enviar");
	        buttonSubmit.addActionListener(new testBehavior());
	        card2.add(buttonSubmit);

	        JPanel card3 = new JPanel();
	        card3.setLayout(new GridLayout(1,3));
	        txtIDEditora = new JFormattedTextField(mascaraNumero);
            card3.add(new JLabel("ID da Editora: ", JLabel.TRAILING));
            txtIDEditora.setPreferredSize( new Dimension( 50, 30 ));
	        card3.add(txtIDEditora);
	        buttonSubmit = new JButton("Enviar");
	        buttonSubmit.addActionListener(new testBehavior());
	        card3.add(buttonSubmit);
	        
	        //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout());
	        cards.add(card1, AutoresPanel);
	        cards.add(card2, LivrosPanel);
	        cards.add(card3,EditorasPanel);
	        
	        pane.add(comboBoxPane, BorderLayout.PAGE_START);
	        pane.add(cards, BorderLayout.CENTER);
	        
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
	
    class testBehavior implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(cb.getSelectedItem().equals("Livros")) {
				if(!txtISBN.getText().equals(" -   -     - ")) {
					JOptionPane.showMessageDialog(null, "ISBN: " + txtISBN.getText());
				} else
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente");
			}else if(cb.getSelectedItem().equals("Autores")) {
				if(!txtIDAutor.getText().equals("")) {
					int input = JOptionPane.showConfirmDialog(null,
			                "Caso esse autor esteja relacionado a um livro, também será excluido.\nDeseja continuar?",
			                "Selecione uma opção...",JOptionPane.YES_NO_CANCEL_OPTION);
					if(input == 0)
						JOptionPane.showMessageDialog(null, "ID do autor: " + txtIDAutor.getText());
				}else
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente");
			}else if(cb.getSelectedItem().equals("Editoras")) {
				if(!txtIDEditora.getText().equals("")) {
					int input = JOptionPane.showConfirmDialog(null,
			                "Caso essa editora esteja relacionado a um autor, ele também será excluido.\nDeseja continuar?",
			                "Selecione uma opção...",JOptionPane.YES_NO_CANCEL_OPTION);
					if(input == 0)
						JOptionPane.showMessageDialog(null, "ID da editora: " + txtIDEditora.getText());
				}else
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente");
			}
		}
    	
    }
	
	
}

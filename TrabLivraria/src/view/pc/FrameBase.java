package view.pc;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameBase extends JFrame{
	
	public JPanel panelLabels;
	
	public FrameBase(String tipoDeOperacao) {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		panelLabels = new JPanel(); //Panel que guarda as labels
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.PAGE_AXIS));
		panelLabels.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelLabels.add(new JLabel("Você está prestes a " + tipoDeOperacao + " algo", JLabel.CENTER));
		panelLabels.add(new JLabel("Escolha a categoria e preencha os campos", JLabel.CENTER));
		panelLabels.getComponent(0).setFont(new Font("Serif", Font.PLAIN, 30));
		panelLabels.getComponent(1).setFont(new Font("Serif", Font.PLAIN, 17));
		
		add(panelLabels);
		
	}
}

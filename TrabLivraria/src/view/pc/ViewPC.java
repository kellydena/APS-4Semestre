package view.pc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Publisher;
import view.pc.altera.JFrameAlterar;
import view.pc.busca.JFrameBuscar;
import view.pc.exclui.JFrameExcluir;
import view.pc.inclui.JFrameIncluir;

public class ViewPC extends JFrame implements View{

	private JButton buttonBusca;
	private JButton buttonAltera;
	private JButton buttonInclui;
	private JButton buttonExclui;
	
	private JFrameExcluir janelaExclui;
	private JFrameAlterar janelaAltera;
	private JFrameIncluir janelaInclui;
	private JFrameBuscar janelaBusca;
	
	
	public ViewPC() {
		super("APS Livraria");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		
		JPanel panelLabels = new JPanel();
		add(panelLabels);
		panelLabels.setLayout(new GridLayout(2,1));
		
		JLabel labelWellcome = new JLabel("Bem vindo a biblioteca!", JLabel.CENTER);
		panelLabels.add(labelWellcome);
		labelWellcome.setFont(new Font("Serif", Font.PLAIN, 40));
		
		JLabel labelWellcome2 = new JLabel("O que deseja fazer com os livros?", JLabel.CENTER);
		panelLabels.add(labelWellcome2);
		labelWellcome2.setFont(new Font("Serif", Font.PLAIN, 20));
		
		JPanel panelPrinButton = new PanelButtons();
		add(panelPrinButton);
		

	}
	
	class PanelButtons extends JPanel{
		
		public PanelButtons(){
			setLayout(new GridLayout(2,2, 30, 30));
			
			//Inicializando os botoes
			buttonBusca = new JButton("Buscar");
			buttonAltera = new JButton("Alterar");
			buttonInclui = new JButton("Incluir");
			buttonExclui = new JButton("Excluir");
	
			buttonBusca.setPreferredSize(new Dimension(150, 80));
			buttonBusca.setFont(new Font("Serif", Font.PLAIN, 25));
			buttonAltera.setFont(new Font("Serif", Font.PLAIN, 25));
			buttonInclui.setFont(new Font("Serif", Font.PLAIN, 25));
			buttonExclui.setFont(new Font("Serif", Font.PLAIN, 25));
			
			add(buttonBusca);
			add(buttonAltera);
			add(buttonInclui);
			add(buttonExclui);
		}
	}

	@Override
	public JButton getButtonBusca() {
		return buttonBusca;
	}

	@Override
	public JButton getButtonAltera() {
		return buttonAltera;
	}

	@Override
	public JButton getButtonInclui() {
		return buttonInclui;
	}

	@Override
	public JButton getButtonExclui() {
		return buttonExclui;
	}

	@Override
	public void EscolheJanelaBehavior(ActionListener al) {
		buttonExclui.addActionListener(al);
		buttonAltera.addActionListener(al);
		buttonBusca.addActionListener(al);
		buttonInclui.addActionListener(al);		
		
	}
	
	
}














































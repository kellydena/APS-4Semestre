package view.pc.util.list;

import javax.swing.JOptionPane;

public class JOptionFrameConfirmacao extends JOptionPane{
	
	private static final long serialVersionUID = 1L;
	private String txt;
	
	public JOptionFrameConfirmacao(String txt) {
		this.txt = txt;
	}


	private int input = showConfirmDialog(null,txt,"Selecione uma opção...",JOptionPane.YES_NO_CANCEL_OPTION);
	
	public int getInput() {
		return input;
	}
}

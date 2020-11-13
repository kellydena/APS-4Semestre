package view.pc.util.messages;

import javax.swing.JOptionPane;

public class JOptionFrameConfirmacao extends JOptionPane{
	
	private static final long serialVersionUID = 1L;
	private int input;
	public JOptionFrameConfirmacao(String txt) {
		input = showConfirmDialog(null,txt,"Selecione uma opção...",JOptionPane.YES_NO_CANCEL_OPTION);
	}


	
	
	public int getInput() {
		return input;
	}
}

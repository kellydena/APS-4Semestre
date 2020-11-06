package view.pc.util.messages;

import javax.swing.JOptionPane;

public class FrameReturnToUser extends JOptionPane{
	
	private static final long serialVersionUID = 1L;

	public FrameReturnToUser(String msg) {
		showMessageDialog(null, msg);
	}
}

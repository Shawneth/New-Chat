package client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClientChatWindow extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField message_input;
	private static JTextArea mainBoard;

	public static JTextArea getBoard() {
		return mainBoard;
	}
	public static JTextField getField(){
		return message_input;
	}

	// Stuff to be added to another class afterwards
	private final SettingsWindow sc = new SettingsWindow();

	public ClientChatWindow() {
		this.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200,
				400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		getContentPane().setLayout(null);

		mainBoard = new JTextArea();
		mainBoard.setEditable(false);
		mainBoard.setBounds(10, 11, 364, 309);
		getContentPane().add(mainBoard);

		message_input = new JTextField();
		message_input.addKeyListener(this);
		message_input.setBounds(10, 331, 195, 20);
		getContentPane().add(message_input);
		message_input.setColumns(10);

		JButton send_button = new JButton("Send");
		send_button.addActionListener(this);
		send_button.setActionCommand(Actions.SEND_MESSAGE);
		send_button.setBounds(215, 331, 70, 20);
		getContentPane().add(send_button);

		JButton connection_info_button = new JButton("Connect...");
		connection_info_button.addActionListener(this);
		connection_info_button.setActionCommand(Actions.POPUP_MENU);
		connection_info_button.setBounds(289, 331, 85, 20);
		getContentPane().add(connection_info_button);
		setVisible(true);
	}

	public static void main(String args[]) {
		new ClientChatWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Actions.POPUP_MENU:
			sc.setVisible(true);
			break;
		case Actions.SEND_MESSAGE:
			Network.sendMessage(message_input.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyChar() == KeyEvent.VK_ENTER){
			Network.sendMessage(message_input.getText());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}

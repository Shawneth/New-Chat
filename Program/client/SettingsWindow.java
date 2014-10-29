package client;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SettingsWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField ip_input;
	private final JTextField username_input;
	private final JTextField port_input;
	private static JProgressBar progressBar;

	public static JProgressBar getBar() {
		return progressBar;
	}

	public SettingsWindow() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		getContentPane().setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 36, 273, 20);
		getContentPane().add(progressBar);

		JButton connect = new JButton("Connect");
		connect.setActionCommand(Actions.CONNECT);
		connect.addActionListener(this);
		connect.setBounds(172, 140, 111, 20);
		getContentPane().add(connect);

		JLabel lblNewLabel = new JLabel("Connection Status");
		lblNewLabel.setBounds(103, 11, 111, 14);
		getContentPane().add(lblNewLabel);

		ip_input = new JTextField();
		ip_input.setBounds(76, 109, 207, 20);
		getContentPane().add(ip_input);
		ip_input.setColumns(10);

		username_input = new JTextField();
		username_input.setColumns(10);
		username_input.setBounds(76, 78, 207, 20);
		getContentPane().add(username_input);

		port_input = new JTextField();
		port_input.setBounds(76, 140, 86, 20);
		getContentPane().add(port_input);
		port_input.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 81, 55, 14);
		getContentPane().add(lblUsername);

		JLabel lblIpAddress = new JLabel("IP Address");
		lblIpAddress.setBounds(10, 112, 55, 14);
		getContentPane().add(lblIpAddress);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 143, 55, 14);
		getContentPane().add(lblPort);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 65, 273, 2);
		getContentPane().add(separator);
		this.setEnabled(true);
		this.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 154,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 103,
				309, 206);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Actions.CONNECT)) {
			@SuppressWarnings("unused")
			ClientInformation client = new ClientInformation(username_input.getText(), ip_input.getText(),port_input.getText());
			Network net = new Network(client);
			getBar().setValue(50);
			if (Network.client.isConnected()) {
				Utils.writeWarning("Connected.");
				getBar().setValue(100);
			}
			else{
				Utils.writeWarning("Can't connect to server.");
			}
			this.setVisible(false);
		}
	}
}

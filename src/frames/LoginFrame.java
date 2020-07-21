package frames;

import main.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	private Controller ctrl;

	public LoginFrame(Controller control) {
		setResizable(false);
		setTitle("O'Style");
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\UNI\\progetto\\logo_size_invert.jpg"));
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 702);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Frame becomes visible at the center of the screen
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Username");
		label.setForeground(new Color(0, 204, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label.setBounds(65, 390, 161, 48);
		contentPane.add(label);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setBounds(303, 393, 323, 46);
		contentPane.add(UsernameTextField);
		UsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		UsernameTextField.setColumns(10);
		
		
		//login button
		JButton LoginButton = new JButton("Login");
		LoginButton.setBounds(376, 576, 250, 55);
		contentPane.add(LoginButton);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckValidProps();
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		
		//register button
		JButton RegisterButton = new JButton("Register \r\nnew user");
		RegisterButton.setBounds(65, 578, 250, 55);
		contentPane.add(RegisterButton);
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.RegisterFrameOpen();
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(303, 477, 323, 47);
		contentPane.add(PasswordField);
		PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setForeground(new Color(0, 204, 255));
		PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordLabel.setBounds(65, 481, 161, 37);
		contentPane.add(PasswordLabel);
		PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/Login Frame.png")));
		backgroundLbl.setForeground(new Color(0, 204, 255));
		backgroundLbl.setBounds(0, 0, 1069, 675);
		contentPane.add(backgroundLbl);
		
	
		
		
	}
	

	
	//checks if the username and password fields are empty
	private void CheckValidProps() {
		if((UsernameTextField.getText().length()<=0 || PasswordField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid Username and Password","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			UsernameTextField.setText("");
			PasswordField.setText("");
		}else
			if((UsernameTextField.getText().length()>0 || PasswordField.getText().length()>0)) {
				ctrl.Login(UsernameTextField.getText(),PasswordField.getText());
				UsernameTextField.setText("");
				PasswordField.setText("");
			}
	}
	
	
	//user not registered
	public void UnregisteredUser() {
		JOptionPane.showMessageDialog(new JFrame(), "Incorrect username or password","", JOptionPane.ERROR_MESSAGE);
		UsernameTextField.setText("");
		PasswordField.setText("");
	}
}

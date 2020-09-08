package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Controller;

public class UserLoginFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTextField UsernameTextField;
	private JPasswordField PasswordField;
	
	public UserLoginFrame(Controller control) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserLoginFrame.class.getResource("/images/logo_size_invert.jpg")));
		setResizable(false);
		setTitle("O'Style");
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1073, 702);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel Usernamelbl = new JLabel("Username");
		Usernamelbl.setHorizontalAlignment(SwingConstants.CENTER);
		Usernamelbl.setForeground(new Color(0, 204, 255));
		Usernamelbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Usernamelbl.setBounds(78, 392, 145, 47);
		contentPane.add(Usernamelbl);
		
		JLabel Passwordlbl = new JLabel("Password");
		Passwordlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Passwordlbl.setForeground(new Color(0, 204, 255));
		Passwordlbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		Passwordlbl.setBounds(65, 477, 166, 47);
		contentPane.add(Passwordlbl);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		UsernameTextField.setColumns(10);
		UsernameTextField.setBounds(303, 393, 323, 47);
		contentPane.add(UsernameTextField);
		
		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PasswordField.setBounds(303, 477, 323, 47);
		contentPane.add(PasswordField);
		
		JButton RegisterButton = new JButton("Register \r\nnew user");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.RegisterUserFrameOpen();
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		RegisterButton.setBounds(65, 578, 250, 55);
		contentPane.add(RegisterButton);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CheckValidProps();
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
		LoginButton.setBounds(376, 576, 250, 55);
		contentPane.add(LoginButton);
		
		JButton EmplAccessBTN = new JButton("Employees Access");
		EmplAccessBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.EmployeeLoginFrameOpen();
			}
		});
		EmplAccessBTN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		EmplAccessBTN.setBounds(777, 41, 256, 47);
		contentPane.add(EmplAccessBTN);
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(EmployeeLoginFrame.class.getResource("/images/Login Frame.png")));
		backgroundLbl.setForeground(new Color(0, 204, 255));
		backgroundLbl.setBounds(0, -16, 1081, 689);
		contentPane.add(backgroundLbl);
		
	
		
		
	}
	
	private void CheckValidProps() {
		if((UsernameTextField.getText().length()<=0 || PasswordField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid Username and Password","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			UsernameTextField.setText("");
			PasswordField.setText("");
		}else
			if((UsernameTextField.getText().length()>0 || PasswordField.getText().length()>0)) {
				ctrl.UserLogin(UsernameTextField.getText(),PasswordField.getText());
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

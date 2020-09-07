package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import main.Controller;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class RegisterNewUserFrame extends JFrame {
	
	private Controller ctrl;
	private JPanel contentPane;
	private JTextField NameTF;
	private JTextField SurnameTF;
	private JTextField UsernameTF;
	private JTextField PasswordTF;
	private JTextField EmailTF;
	private JTextField AddressTF;
	private JFormattedTextField CardNTF;

	public RegisterNewUserFrame(Controller control) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterNewUserFrame.class.getResource("/images/logo_size_invert.jpg")));
		ctrl = control;
		setResizable(false);
		setTitle("Register New User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		NameTF = new JTextField();
		NameTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NameTF.setColumns(10);
		NameTF.setBounds(63, 77, 300, 40);
		contentPane.add(NameTF);
		
		SurnameTF = new JTextField();
		SurnameTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SurnameTF.setColumns(10);
		SurnameTF.setBounds(63, 158, 300, 40);
		contentPane.add(SurnameTF);
		
		UsernameTF = new JTextField();
		UsernameTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		UsernameTF.setColumns(10);
		UsernameTF.setBounds(63, 245, 300, 40);
		contentPane.add(UsernameTF);
		
		PasswordTF = new JTextField();
		PasswordTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PasswordTF.setColumns(10);
		PasswordTF.setBounds(63, 329, 300, 40);
		contentPane.add(PasswordTF);
		
		EmailTF = new JTextField();
		EmailTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		EmailTF.setColumns(10);
		EmailTF.setBounds(471, 76, 300, 40);
		contentPane.add(EmailTF);
		
		AddressTF = new JTextField();
		AddressTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		AddressTF.setColumns(10);
		AddressTF.setBounds(471, 158, 300, 40);
		contentPane.add(AddressTF);
		
		//formatter for Id and InStock TF that only accept Integers, with max in range
		NumberFormat Intformat = NumberFormat.getInstance();
		Intformat.setGroupingUsed(false);//Remove comma from number greater than 4 digit
		NumberFormatter IntFormatter = new NumberFormatter(Intformat);
		IntFormatter.setValueClass(Integer.class);
		IntFormatter.setMinimum(0);
		IntFormatter.setMaximum(2147483647);
		IntFormatter.setAllowsInvalid(false);
		IntFormatter.setCommitsOnValidEdit(true);// committ value on each keystroke instead of focus lost
		
		CardNTF = new JFormattedTextField(IntFormatter);
		CardNTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CardNTF.setColumns(10);
		CardNTF.setBounds(471, 245, 300, 40);
		contentPane.add(CardNTF);
		
		JLabel InsertNameLbl = new JLabel("Insert Name");
		InsertNameLbl.setForeground(new Color(0, 206, 209));
		InsertNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertNameLbl.setBounds(63, 33, 249, 55);
		contentPane.add(InsertNameLbl);
		
		JLabel InsertSurNameLbl = new JLabel("Insert Surname");
		InsertSurNameLbl.setForeground(new Color(0, 206, 209));
		InsertSurNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertSurNameLbl.setBounds(63, 113, 249, 55);
		contentPane.add(InsertSurNameLbl);
		
		JLabel InsertusernameLbl = new JLabel("Insert Username");
		InsertusernameLbl.setForeground(new Color(0, 206, 209));
		InsertusernameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertusernameLbl.setBounds(63, 199, 249, 55);
		contentPane.add(InsertusernameLbl);
		
		JLabel InsertPasswordLbl = new JLabel("Insert Password");
		InsertPasswordLbl.setForeground(new Color(0, 206, 209));
		InsertPasswordLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertPasswordLbl.setBounds(63, 288, 249, 55);
		contentPane.add(InsertPasswordLbl);
		
		JLabel InsertEmailLbl = new JLabel("Insert Email");
		InsertEmailLbl.setForeground(new Color(0, 206, 209));
		InsertEmailLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertEmailLbl.setBounds(471, 33, 249, 55);
		contentPane.add(InsertEmailLbl);
		
		JLabel InsertAddressLbl = new JLabel("Insert Address");
		InsertAddressLbl.setForeground(new Color(0, 206, 209));
		InsertAddressLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertAddressLbl.setBounds(471, 113, 249, 55);
		contentPane.add(InsertAddressLbl);
		
		JLabel InsertCardNLbl = new JLabel("Insert CardNumber");
		InsertCardNLbl.setForeground(new Color(0, 206, 209));
		InsertCardNLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertCardNLbl.setBounds(471, 199, 249, 55);
		contentPane.add(InsertCardNLbl);
		
		JButton ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		ConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ConfirmButton.setBounds(776, 562, 303, 80);
		contentPane.add(ConfirmButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.UserLoginFrameOpen();
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CancelButton.setBounds(416, 562, 303, 80);
		contentPane.add(CancelButton);
		
		JLabel BackgroundLbl = new JLabel("");
		BackgroundLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		BackgroundLbl.setIcon(new ImageIcon(RegisterNewUserFrame.class.getResource("/images/Main Admin Frame.png")));
		BackgroundLbl.setBounds(0, 0, 1154, 731);
		contentPane.add(BackgroundLbl);
		setLocationRelativeTo(null);
	}
	
	//register user call
		private void Register(){
			if((UsernameTF.getText().length()<=0 || PasswordTF.getText().length()<=0 || NameTF.getText().length()<=0 || SurnameTF.getText().length()<=0 || EmailTF.getText().length()<=0 || AddressTF.getText().length()<=0 || CardNTF.getText().length()<=0)) {
				JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
				//NUsernameTextField.setText("");
				//NPasswordTextField.setText("");
				//NNameTextField.setText("");
				//NSurnameTextField.setText("");
				//CodIFTF.setValue(null);
			}else												
				ctrl.RegisterUser(NameTF.getText(),SurnameTF.getText(),UsernameTF.getText(),PasswordTF.getText(),EmailTF.getText(),AddressTF.getText(),(int)CardNTF.getValue());
		}
		
		//user already registered
		public void UserNameAlreadyRegistered() {
			JOptionPane.showMessageDialog(new JFrame(), "Username already registered, choose a different one","", JOptionPane.ERROR_MESSAGE);
//			NUsernameTextField.setText("");
//			NPasswordTextField.setText("");
//			NNameTextField.setText("");
//			NSurnameTextField.setText("");
//			CodIFTF.setValue(null);
		}
		public void EmailAlreadyUsed() {
			JOptionPane.showMessageDialog(new JFrame(), "Email already used, choose a different one","", JOptionPane.ERROR_MESSAGE);
//			NUsernameTextField.setText("");
//			NPasswordTextField.setText("");
//			NNameTextField.setText("");
//			NSurnameTextField.setText("");
//			CodIFTF.setValue(null);
		}
		
		//user has been registered
		public void UserHasBeenRegistered() {
			JOptionPane.showMessageDialog(new JFrame(), "User has been registered","", JOptionPane.INFORMATION_MESSAGE);
			NameTF.setText("");
			SurnameTF.setText("");
			UsernameTF.setText("");
			PasswordTF.setText("");
			EmailTF.setText("");
			AddressTF.setText("");
			CardNTF.setValue(null);
		}
}

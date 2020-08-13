package frames;

import main.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RegisterNewEmployeeFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTextField NUsernameTextField;
	private JTextField NPasswordTextField;
	private JRadioButton AdminRdBtn;
	private JTextField NSurnameTextField;
	private JTextField NNameTextField;
	private JFormattedTextField CodIFTF;


	public RegisterNewEmployeeFrame(Controller control) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterNewEmployeeFrame.class.getResource("/images/logo_size_invert.jpg")));
		setTitle("O'Style");
		setFont(new Font("Tahoma", Font.PLAIN, 24));
		ctrl = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 611);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel InsertCodILbl = new JLabel("Insert CodI");
		InsertCodILbl.setForeground(new Color(75, 0, 130));
		InsertCodILbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertCodILbl.setBounds(659, 331, 134, 46);
		contentPane.add(InsertCodILbl);
		
		//formatter for Id and InStock TF that only accept Integers, with max in range
		NumberFormat Intformat = NumberFormat.getInstance();
		Intformat.setGroupingUsed(false);//Remove comma from number greater than 4 digit
		NumberFormatter IntFormatter = new NumberFormatter(Intformat);
		IntFormatter.setValueClass(Integer.class);
		IntFormatter.setMinimum(0);
		IntFormatter.setMaximum(2147483647);
		IntFormatter.setAllowsInvalid(false);
		IntFormatter.setCommitsOnValidEdit(true);// committ value on each keystroke instead of focus lost
				
		CodIFTF = new JFormattedTextField(IntFormatter);
		CodIFTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CodIFTF.setBounds(659, 373, 148, 40);
		CodIFTF.setColumns(10);
		contentPane.add(CodIFTF);
		
		JLabel InsertSurnameLbl = new JLabel("Insert Surname");
		InsertSurnameLbl.setForeground(new Color(75, 0, 130));
		InsertSurnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertSurnameLbl.setBounds(580, 91, 256, 38);
		contentPane.add(InsertSurnameLbl);
		
		JLabel InsertNameLbl = new JLabel("Insert Name");
		InsertNameLbl.setForeground(new Color(75, 0, 130));
		InsertNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		InsertNameLbl.setBounds(580, 11, 249, 55);
		contentPane.add(InsertNameLbl);
		
		NNameTextField = new JTextField();
		NNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NNameTextField.setColumns(10);
		NNameTextField.setBounds(512, 53, 300, 40);
		contentPane.add(NNameTextField);
		
		NSurnameTextField = new JTextField();
		NSurnameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NSurnameTextField.setColumns(10);
		NSurnameTextField.setBounds(512, 127, 300, 40);
		contentPane.add(NSurnameTextField);
		
		JLabel InsertUsernameLabel = new JLabel("Insert Username");
		InsertUsernameLabel.setForeground(new Color(75, 0, 130));
		InsertUsernameLabel.setBounds(580, 151, 249, 55);
		contentPane.add(InsertUsernameLabel);
		InsertUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		NUsernameTextField = new JTextField();
		NUsernameTextField.setBounds(512, 200, 300, 40);
		contentPane.add(NUsernameTextField);
		NUsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NUsernameTextField.setColumns(10);
		
		NPasswordTextField = new JTextField();
		NPasswordTextField.setBounds(512, 274, 300, 40);
		contentPane.add(NPasswordTextField);
		NPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NPasswordTextField.setColumns(10);
		
		AdminRdBtn = new JRadioButton("Admin");
		AdminRdBtn.setBounds(512, 375, 104, 38);
		contentPane.add(AdminRdBtn);
		AdminRdBtn.setBackground(Color.WHITE);
		AdminRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel InsertPasswordLabel = new JLabel("Insert Password");
		InsertPasswordLabel.setForeground(new Color(75, 0, 130));
		InsertPasswordLabel.setBounds(580, 240, 256, 38);
		contentPane.add(InsertPasswordLabel);
		InsertPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		//		cancel button
			JButton CancelButton = new JButton("Cancel");
			CancelButton.setBounds(46, 451, 303, 80);
			contentPane.add(CancelButton);
			CancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.EmployeeLoginFrameOpen();
				}
			});
			CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
				
//		confirm button
			JButton ConfirmButton = new JButton("Confirm");
			ConfirmButton.setBounds(512, 451, 303, 80);
			contentPane.add(ConfirmButton);
			ConfirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Register();
				}
			});
			ConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
				
			JLabel backgroundLbl = new JLabel("");
			backgroundLbl.setIcon(new ImageIcon(RegisterNewEmployeeFrame.class.getResource("/images/RegisterNewUser Frame.png")));
			backgroundLbl.setBounds(0, 0, 849, 574);
			contentPane.add(backgroundLbl);
		
		//Frame becomes visible at the center of the screen
		setLocationRelativeTo(null);
		
		
	}
	

	//register user call
	private void Register(){
		if((NUsernameTextField.getText().length()<=0 || NPasswordTextField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			NUsernameTextField.setText("");
			NPasswordTextField.setText("");
			NNameTextField.setText("");
			NSurnameTextField.setText("");
			CodIFTF.setValue(null);
		}else
			if((NUsernameTextField.getText().length()>0 || NPasswordTextField.getText().length()>0)) {													
				ctrl.RegisterEmployee(NUsernameTextField.getText(),NPasswordTextField.getText(),AdminRdBtn.isSelected(),NNameTextField.getText(),NSurnameTextField.getText(),(int)CodIFTF.getValue());
			}
	}
	
	//user already registered
	public void UserAlreadyRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User already registered, change ID","", JOptionPane.ERROR_MESSAGE);
		//NUsernameTextField.setText("");
		//NPasswordTextField.setText("");
		//NNameTextField.setText("");
		//NSurnameTextField.setText("");
		CodIFTF.setValue(null);
	}
	
	//user has been registered
	public void UserHasBeenRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User has been registered","", JOptionPane.INFORMATION_MESSAGE);
		NUsernameTextField.setText("");
		NPasswordTextField.setText("");
		NNameTextField.setText("");
		NSurnameTextField.setText("");
		CodIFTF.setValue(null);
	}
}

package frames;

import main.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RegisterNewUserFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTextField NUsernameTextField;
	private JTextField NPasswordTextField;
	private JRadioButton AdminRdBtn;


	public RegisterNewUserFrame(Controller control) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterNewUserFrame.class.getResource("/images/logo_size_invert.jpg")));
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
		
		JLabel InsertUsernameLabel = new JLabel("Insert Username");
		InsertUsernameLabel.setForeground(new Color(75, 0, 130));
		InsertUsernameLabel.setBounds(583, 98, 249, 55);
		contentPane.add(InsertUsernameLabel);
		InsertUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		NUsernameTextField = new JTextField();
		NUsernameTextField.setBounds(515, 147, 300, 40);
		contentPane.add(NUsernameTextField);
		NUsernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NUsernameTextField.setColumns(10);
		
		NPasswordTextField = new JTextField();
		NPasswordTextField.setBounds(515, 221, 300, 40);
		contentPane.add(NPasswordTextField);
		NPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NPasswordTextField.setColumns(10);
		
		AdminRdBtn = new JRadioButton("Admin");
		AdminRdBtn.setBounds(669, 322, 146, 38);
		contentPane.add(AdminRdBtn);
		AdminRdBtn.setBackground(Color.WHITE);
		AdminRdBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel InsertPasswordLabel = new JLabel("Insert Password");
		InsertPasswordLabel.setForeground(new Color(75, 0, 130));
		InsertPasswordLabel.setBounds(583, 187, 256, 38);
		contentPane.add(InsertPasswordLabel);
		InsertPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		//		cancel button
			JButton CancelButton = new JButton("Cancel");
			CancelButton.setBounds(46, 451, 303, 80);
			contentPane.add(CancelButton);
			CancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.LoginFrameOpen();
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
			backgroundLbl.setIcon(new ImageIcon(RegisterNewUserFrame.class.getResource("/images/RegisterNewUser Frame.png")));
			backgroundLbl.setBounds(0, 0, 849, 574);
			contentPane.add(backgroundLbl);
		
		//Frame becomes visible at the center of the screen
		setLocationRelativeTo(null);
		
		
	}
	
	//register user call
	private void Register(){
		if((NUsernameTextField.getText().length()<=0 || NPasswordTextField.getText().length()<=0)){
			JOptionPane.showMessageDialog(new JFrame(), "Please insert valid Username and Password","WRONG VALUES", JOptionPane.ERROR_MESSAGE);
			NUsernameTextField.setText("");
			NPasswordTextField.setText("");
		}else
			if((NUsernameTextField.getText().length()>0 || NPasswordTextField.getText().length()>0)) {
				ctrl.RegisterUser(NUsernameTextField.getText(),NPasswordTextField.getText(),AdminRdBtn.isSelected());
			}
	}
	
	//user already registered
	public void UserAlreadyRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User already registered","", JOptionPane.ERROR_MESSAGE);
		NUsernameTextField.setText("");
		NPasswordTextField.setText("");
	}
	
	//user has been registered
	public void UserHasBeenRegistered() {
		JOptionPane.showMessageDialog(new JFrame(), "User has been registered","", JOptionPane.INFORMATION_MESSAGE);
		NUsernameTextField.setText("");
		NPasswordTextField.setText("");
	}
}

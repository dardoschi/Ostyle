package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import main.Controller;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class AddNewItemFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JFormattedTextField NewIdTF;
	private JComboBox<String> NewSizeCB;
	private JFormattedTextField NewPriceTF;
	private JComboBox<String> NewTypeCB;
	private JComboBox<String> NewColorCB;
	private JLabel lblTitle;
	private JButton AddItemBtn;
	private JButton CancelBtn;
	private JLabel lblInStock;
	private JFormattedTextField NewInStockTF;

	public AddNewItemFrame(Controller c) {
		setResizable(false);
		setTitle("Add a new Item ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewItemFrame.class.getResource("/images/logo_size_invert.jpg")));
		ctrl = c;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 541);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		//formatter for Id and InStock TF that only accept Integers, with max in range
		NumberFormat Intformat = NumberFormat.getInstance();
		Intformat.setGroupingUsed(false);//Remove comma from number greater than 4 digit
		NumberFormatter IntFormatter = new NumberFormatter(Intformat);
		IntFormatter.setValueClass(Integer.class);
		IntFormatter.setMinimum(0);
		IntFormatter.setMaximum(2147483647);
		IntFormatter.setAllowsInvalid(false);
		IntFormatter.setCommitsOnValidEdit(true);// committ value on each keystroke instead of focus lost
		
		JFormattedTextField NewIdTF = new JFormattedTextField(IntFormatter);
		NewIdTF.setBounds(198, 83, 272, 31);
		NewIdTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewIdTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewIdTF.setColumns(10);
		
		NewSizeCB = new JComboBox<String>();
		NewSizeCB.setBackground(new Color(135, 206, 250));
		NewSizeCB.setBounds(198, 132, 272, 33);
		((JLabel)NewSizeCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewSizeCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewSizeCB.addItem("XS");
		NewSizeCB.addItem("S");
		NewSizeCB.addItem("M");
		NewSizeCB.addItem("L");
		NewSizeCB.addItem("XL");
		NewSizeCB.addItem("XXL");
		
		//formatter for the price (double) field
		NumberFormat Doubleformat = DecimalFormat.getInstance();
		Doubleformat.setGroupingUsed(false);
		NumberFormatter DoubleFormatter = new NumberFormatter(Doubleformat);
		DoubleFormatter.setValueClass(Double.class);
		Doubleformat.setMinimumFractionDigits(2);
		Doubleformat.setMaximumFractionDigits(2);
		DoubleFormatter.setAllowsInvalid(false);
		DoubleFormatter.setCommitsOnValidEdit(true);
		Doubleformat.setRoundingMode(RoundingMode.HALF_UP);
		
		NewPriceTF = new JFormattedTextField(DoubleFormatter);
		NewPriceTF.setBounds(198, 183, 272, 31);
		NewPriceTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewPriceTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewPriceTF.setColumns(10);
		
		NewTypeCB = new JComboBox<String>();
		NewTypeCB.setBackground(new Color(135, 206, 250));
		NewTypeCB.setBounds(198, 232, 272, 33);
		((JLabel)NewTypeCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewTypeCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewTypeCB.addItem("Coat");
		NewTypeCB.addItem("Shoe");
		NewTypeCB.addItem("Dress");
		NewTypeCB.addItem("Jeans");
		NewTypeCB.addItem("T-shirt");
		NewTypeCB.addItem("Skirt");
		NewTypeCB.addItem("Bag");
		NewTypeCB.addItem("Briefcase");
		
		NewColorCB = new JComboBox<String>();
		NewColorCB.setBackground(new Color(135, 206, 250));
		NewColorCB.setBounds(198, 336, 272, 33);
		((JLabel)NewColorCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		NewColorCB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewColorCB.addItem("Red");
		NewColorCB.addItem("Blue");
		NewColorCB.addItem("Green");
		NewColorCB.addItem("Yellow");
		NewColorCB.addItem("Black");
		NewColorCB.addItem("White");
		NewColorCB.addItem("Purple");
		NewColorCB.addItem("Orange");
		NewColorCB.addItem("Brown");
		NewColorCB.addItem("Jeans");
		
		JLabel lblID = new JLabel("ID");
		lblID.setForeground(new Color(135, 206, 250));
		lblID.setBounds(54, 85, 126, 26);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(new Color(135, 206, 250));
		lblSize.setBounds(54, 135, 126, 26);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(135, 206, 250));
		lblPrice.setBounds(54, 185, 126, 26);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(new Color(135, 206, 250));
		lblType.setBounds(54, 235, 126, 26);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setForeground(new Color(135, 206, 250));
		lblColour.setBounds(54, 339, 126, 26);
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		lblTitle = new JLabel("Add New Item");
		lblTitle.setBounds(175, 16, 267, 56);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setToolTipText("Add New ItemInDB");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		AddItemBtn = new JButton("Add Item");
		AddItemBtn.setBackground(new Color(135, 206, 250));
		AddItemBtn.setBounds(310, 393, 160, 93);
		AddItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inputCheck()==true) {
					if(ctrl.CheckItemId((int) NewIdTF.getValue())==true) {
						ctrl.AddNewItem((int) NewIdTF.getValue(),NewSizeCB.getSelectedItem().toString(), (double) NewPriceTF.getValue(), NewTypeCB.getSelectedItem().toString(),(int) NewInStockTF.getValue(), NewColorCB.getSelectedItem().toString());
						resetFrame();
					}else
						JOptionPane.showMessageDialog(new JFrame(), "Please change id","ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
					JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			private void resetFrame() {
				NewIdTF.setValue(null);
				NewPriceTF.setValue(null);
				NewInStockTF.setValue(null);
				setVisible(false);	
			}
			//checks for blank spaces, true if ok
			private boolean inputCheck() {
				if(NewIdTF.getValue()==null || NewPriceTF.getValue()==null || NewInStockTF.getValue()==null) { 
					return false;
				}
				return true;
			}
		});
		AddItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		CancelBtn = new JButton("Cancel");
		CancelBtn.setBackground(new Color(135, 206, 250));
		CancelBtn.setBounds(102, 393, 160, 93);
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				setVisible(false);
				NewIdTF.setValue(null);
				NewPriceTF.setValue(null);
				NewInStockTF.setValue(null);

			}
		});
		CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		lblInStock = new JLabel("In Stock");
		lblInStock.setForeground(new Color(135, 206, 250));
		lblInStock.setBounds(54, 282, 126, 26);
		lblInStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblInStock.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		NewInStockTF = new JFormattedTextField(IntFormatter);
		NewInStockTF.setBounds(198, 282, 272, 31);
		NewInStockTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewInStockTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewInStockTF.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(CancelBtn);
		contentPane.add(AddItemBtn);
		contentPane.add(lblType);
		contentPane.add(lblPrice);
		contentPane.add(lblSize);
		contentPane.add(lblID);
		contentPane.add(lblColour);
		contentPane.add(lblInStock);
		contentPane.add(NewColorCB);
		contentPane.add(NewTypeCB);
		contentPane.add(NewPriceTF);
		contentPane.add(NewSizeCB);
		contentPane.add(NewIdTF);
		contentPane.add(NewInStockTF);
		contentPane.add(lblTitle);
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(AddNewItemFrame.class.getResource("/images/Add-Edit Frame.png")));
		backgroundLbl.setBounds(0, 0, 571, 520);
		contentPane.add(backgroundLbl);
	}
}

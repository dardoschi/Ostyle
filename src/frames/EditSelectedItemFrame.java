package frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import main.Controller;
import orders.Item;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;

public class EditSelectedItemFrame extends JFrame {
	
	private Controller ctrl;
	private JPanel contentPane;
	private JFormattedTextField NewIdTF;
	private JComboBox<String> NewSizeCB;
	private JFormattedTextField NewPriceTF;
	private JComboBox<String> NewTypeCB;
	private JComboBox<String> NewColorCB;
	private JLabel lblTitle;
	private JButton EditItemBtn;
	private JButton CancelBtn;
	private JLabel lblInStock;
	private JFormattedTextField NewInStockTF;
	private Item SelectedItem;
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JTextField NewNameTF;

	public EditSelectedItemFrame(Controller c) {
		setTitle("Edit Item");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditSelectedItemFrame.class.getResource("/images/logo_size_invert.jpg")));
		setResizable(false);
		ctrl = c;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 621);
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
		NewIdTF.setBounds(214, 81, 270, 31);
		NewIdTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewIdTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewIdTF.setColumns(10);
		
		NewSizeCB = new JComboBox<String>();
		NewSizeCB.setBackground(new Color(135, 206, 250));
		NewSizeCB.setBounds(214, 130, 270, 33);
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
		NewPriceTF.setBounds(214, 181, 270, 31);
		NewPriceTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewPriceTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewPriceTF.setColumns(10);
		
		NewTypeCB = new JComboBox<String>();
		NewTypeCB.setBackground(new Color(135, 206, 250));
		NewTypeCB.setBounds(214, 223, 270, 33);
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
		NewColorCB.setBounds(214, 322, 270, 33);
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
		lblID.setForeground(new Color(135, 206, 235));
		lblID.setBounds(73, 83, 123, 26);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(new Color(135, 206, 235));
		lblSize.setBounds(73, 133, 123, 26);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(135, 206, 235));
		lblPrice.setBounds(73, 183, 123, 26);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(new Color(135, 206, 235));
		lblType.setBounds(73, 226, 123, 26);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setForeground(new Color(135, 206, 235));
		lblColour.setBounds(73, 325, 123, 26);
		lblColour.setHorizontalAlignment(SwingConstants.CENTER);
		lblColour.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		lblTitle = new JLabel("Edit Item");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(120, 0, 336, 76);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitle.setToolTipText("Edit the selected Item");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		EditItemBtn = new JButton("Edit Item");
		EditItemBtn.setBackground(new Color(135, 206, 250));
		EditItemBtn.setBounds(312, 457, 160, 93);
		EditItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() == true) {
					editSelectedItem();
				}
			}

			private boolean check() {
				if(inputCheck() == true && NewIdCheck() == true) {
				  return true;
				}
				else
					return false;
			}
			
			//checks if Id already exist
			private boolean NewIdCheck() {
				int OldId = SelectedItem.getId();
				if(OldId!=(int) NewIdTF.getValue()) {
					if(ctrl.CheckItemId((int) NewIdTF.getValue())==false) {
						JOptionPane.showMessageDialog(new JFrame(), "Please change id","ERROR", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}return true;
			}
			//checks for blank spaces, pretty much useless
			private boolean inputCheck() {
				if(NewIdTF.getValue()==null || NewPriceTF.getValue()==null || NewInStockTF.getValue()==null) { 
					JOptionPane.showMessageDialog(new JFrame(), "Please insert valid values","ERROR", JOptionPane.ERROR_MESSAGE);
				}return true;
			}

			private void editSelectedItem() {
				int OldId = SelectedItem.getId();
				int Id = (int) NewIdTF.getValue();
				String Size = NewSizeCB.getSelectedItem().toString();
				Double Price = (Double) NewPriceTF.getValue();
				String Type = NewTypeCB.getSelectedItem().toString();
				int InStock = (int) NewInStockTF.getValue();
				String Color = NewColorCB.getSelectedItem().toString();
				String Name = NewNameTF.getText();
				ctrl.updateItemInDB(Id, Size, Price, Type, InStock, Color, Name, OldId);
				dispose();
				SelectedItem = new Item();
			}
		});
		EditItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		CancelBtn = new JButton("Cancel");
		CancelBtn.setBackground(new Color(135, 206, 250));
		CancelBtn.setBounds(99, 457, 160, 93);
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				dispose();
				SelectedItem = new Item();
			}
		});
		CancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		lblInStock = new JLabel("In Stock");
		lblInStock.setForeground(new Color(135, 206, 235));
		lblInStock.setBounds(73, 274, 123, 26);
		lblInStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblInStock.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		NewInStockTF = new JFormattedTextField(IntFormatter);
		NewInStockTF.setBounds(214, 274, 270, 31);
		NewInStockTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewInStockTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewInStockTF.setColumns(10);
		
		//waits for the window to open to execute this code
		addWindowListener(new WindowAdapter() {
	          public void windowOpened(WindowEvent e) {
	        	  fieldSetter();
	          }
	        //fills the frame fields with the SelectedItem current values, so you can modify them
			private void fieldSetter() {
			  	  SelectedItem = ctrl.fetchSelectedItem();
			  	  NewIdTF.setValue(SelectedItem.getId());
			  	  NewPriceTF.setValue(SelectedItem.getPrice());
			  	  NewInStockTF.setValue(SelectedItem.getInStock());
			  	  NewSizeCB.setSelectedIndex(getNewSizeCB());
			  	  NewTypeCB.setSelectedIndex(getNewTypeCB());
			  	  NewColorCB.setSelectedIndex(getNewColorCB());
			  	  NewNameTF.setText(SelectedItem.getName());
					
			}
			private int getNewSizeCB() {
				String size = SelectedItem.getSize();
				switch(size) {
					case "XS ":return 0;
					case "S  ":return 1;
					case "M  ":return 2;
					case "L  ":return 3;
					case "XL ":return 4;
					case "XXL":return 5;
				default:return 0;
				}
			}
			private int getNewTypeCB() {
				String type = SelectedItem.getType();
				switch(type) {
					case "Coat      ":return 0;
					case "Shoe      ":return 1;
					case "Dress     ":return 2;
					case "Jeans     ":return 3;
					case "T-shirt   ":return 4;
					case "Skirt     ":return 5;
					case "Bag       ":return 6;
					case "Briefcase ":return 7;
				default:return 0;
				}
			}
			private int getNewColorCB() {
				String type = SelectedItem.getColour();
				switch(type) {
					case "Red     ":return 0;
					case "Blue    ":return 1;
					case "Green   ":return 2;
					case "Yellow  ":return 3;
					case "Black   ":return 4;
					case "White   ":return 5;
					case "Purple  ":return 6;
					case "Orange  ":return 7;
					case "Brown   ":return 8;
					case "Jeans   ":return 9;
				default:return 0;
				}
			}
	        });
		contentPane.setLayout(null);
		contentPane.add(CancelBtn);
		contentPane.add(EditItemBtn);
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
		
		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(new Color(135, 206, 250));
		lblName.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblName.setBounds(68, 378, 126, 26);
		contentPane.add(lblName);
		
		NewNameTF = new JTextField();
		NewNameTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		NewNameTF.setHorizontalAlignment(SwingConstants.CENTER);
		NewNameTF.setColumns(10);
		NewNameTF.setBounds(212, 378, 272, 31);
		contentPane.add(NewNameTF);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon(EditSelectedItemFrame.class.getResource("/images/Login Frame.png")));
		lblNewLabel.setBounds(0, 0, 567, 586);
		contentPane.add(lblNewLabel);

	}

}

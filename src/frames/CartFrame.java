package frames;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Item.Item;
import TableModels.ItemInCartTableModel;
import main.Controller;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class CartFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTable CartTable;
	private Item SelectedItem;
	private Item CartItem;
	private JLabel TotalLbl;
	public ItemInCartTableModel TModel;
	
	public CartFrame(Controller c) {
		setResizable(false);
		setTitle("O'Style Cart");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CartFrame.class.getResource("/images/logo_size_invert.jpg")));
		ctrl = c;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 715);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 976, 468);
		contentPane.add(scrollPane);
		TModel = new ItemInCartTableModel(ctrl.Cart);
		CartTable = new JTable(TModel);
		CartTable.setShowVerticalLines(false);
		CartTable.setFillsViewportHeight(true);
		CartTable.setSelectionForeground(new Color(255, 255, 255));
		CartTable.setSelectionBackground(new Color(72, 61, 139));
		CartTable.setFont(new Font("SansSerif", Font.PLAIN, 22));
		CartTable.setForeground(new Color(255, 255, 255));
		CartTable.setShowGrid(false);
		CartTable.setShowHorizontalLines(true);
		CartTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		CartTable.setBackground(new Color(44, 5, 72));
		CartTable.setAutoCreateRowSorter(true);
		CartTable.getRowSorter().toggleSortOrder(0);
		CartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CartTable.setRowHeight(35);
		scrollPane.setViewportView(CartTable);
		scrollPane.setViewportBorder(null);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.getViewport().setBackground(new Color(44, 5, 72));
		
		
		//text centered in table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		CartTable.setDefaultRenderer(Integer.class, centerRenderer);
		CartTable.setDefaultRenderer(String.class, centerRenderer);
		CartTable.setDefaultRenderer(Double.class, centerRenderer);
		//column sorting (automatic on id ascending)
		CartTable.setAutoCreateRowSorter(true);
		
		((DefaultTableCellRenderer)CartTable.getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.CENTER);
		
		
		JButton EmptyCartBtn = new JButton("Empty Cart");
		EmptyCartBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		EmptyCartBtn.setBackground(new Color(121, 204, 224));
		EmptyCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0){
					ctrl.emptyCart();
					} else {
						//does nothing
					}
			}
		});
		EmptyCartBtn.setBounds(10, 597, 200, 60);
		contentPane.add(EmptyCartBtn);
		
		JButton BuyBtn = new JButton("Buy");
		BuyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ctrl.Cart.size()==0) {
					JOptionPane.showMessageDialog(new JFrame(), "Cart is empty","ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0) {
						ctrl.BuyandUpdate();
					}else {
						
					}
				}
			}
		});
		BuyBtn.setBackground(new Color(121, 204, 224));
		BuyBtn.setFont(new Font("Tahoma", Font.PLAIN, 70));
		BuyBtn.setBounds(745, 534, 200, 123);
		contentPane.add(BuyBtn);
		
		JButton RemoveOneBtn = new JButton("Remove One");
		RemoveOneBtn.setBackground(new Color(121, 204, 224));
		RemoveOneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = CartTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) CartTable.getValueAt(CartTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Cart);
					CartItem = c.Cart.get(itemindex);
					ctrl.removeOneFromCart(CartItem);
				}
			}
		});
		RemoveOneBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		RemoveOneBtn.setBounds(236, 597, 235, 60);
		contentPane.add(RemoveOneBtn);
		
		JButton RemoveAllBtn = new JButton("Remove All");
		RemoveAllBtn.setBackground(new Color(121, 204, 224));
		RemoveAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = CartTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) CartTable.getValueAt(CartTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Cart);
					CartItem = c.Cart.get(itemindex);
					ctrl.removeAllFromCart(CartItem);
				}
			}
		});
		RemoveAllBtn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		RemoveAllBtn.setBounds(10, 529, 200, 60);
		contentPane.add(RemoveAllBtn);
		
		TotalLbl = new JLabel("Total : "+ ctrl.getTotal() +" €");
		TotalLbl.setForeground(Color.WHITE);
		TotalLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
		TotalLbl.setBounds(236, 542, 373, 43);
		contentPane.add(TotalLbl);
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(CartFrame.class.getResource("/images/Cart Frame.png")));
		backgroundLbl.setBounds(0, 0, 976, 695);
		contentPane.add(backgroundLbl);
		CartTable.getRowSorter().toggleSortOrder(0);
		
		
	}
	
	//updates the total calling the total calculator function in ctrl
	public void updateTotal() {
		TotalLbl.setText("Total : "+ ctrl.getTotal() +" €");
	}
}

package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import TableModels.ItemInDBTableModel;
import main.Controller;
import orders.Item;

public class EmployeeFrame extends JFrame {


	private Controller ctrl;
	private JTable ItemTable;
	private JScrollPane ItemscrollPane;
	private JButton OpenCartBtn;
	private Item SelectedItem;
	private Item CartItem;        
	public ItemInDBTableModel TModel;
	private JTextField SearchTF;
	
	public EmployeeFrame(Controller c) {
		setTitle("O'Style");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo_size_invert.jpg")));
		ctrl = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1385, 825);
		setLocationRelativeTo(null);
		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		//creates an istance of the custom table model with the controller arraylist(warehouse)
		TModel = new ItemInDBTableModel(c.Warehouse);
		ItemTable = new JTable(TModel);
		ItemTable.setShowGrid(false);
		ItemTable.setFillsViewportHeight(true);
		ItemTable.setSelectionForeground(new Color(255, 255, 255));
		ItemTable.setSelectionBackground(new Color(72, 61, 139));
		ItemTable.setFont(new Font("SansSerif", Font.PLAIN, 22));
		ItemTable.setForeground(new Color(255, 255, 255));
		ItemTable.setShowHorizontalLines(true);
		ItemTable.setBorder(null);
		ItemTable.setBackground(new Color(44, 5, 72));
		
		
		
		//inserts the table into the scrollpanel
		ItemscrollPane = new JScrollPane(ItemTable);
		ItemscrollPane.setViewportBorder(null);
		ItemscrollPane.setBackground(new Color(0, 0, 205));
		ItemscrollPane.setForeground(Color.WHITE);
		ItemscrollPane.setBorder(null);
		ItemscrollPane.setBounds(0, 0, 887, 801);
		ItemscrollPane.getViewport().setBackground(new Color(44, 5, 72));
		ItemscrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		
		((DefaultTableCellRenderer)ItemTable.getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.CENTER);
		
		//text centered in table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		ItemTable.setDefaultRenderer(Integer.class, centerRenderer);
		ItemTable.setDefaultRenderer(String.class, centerRenderer);
		ItemTable.setDefaultRenderer(Double.class, centerRenderer);
		//column sorting (automatic on id ascending)
		ItemTable.setAutoCreateRowSorter(true);
		ItemTable.getRowSorter().toggleSortOrder(0);
		ItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ItemTable.setRowHeight(35);
		
		
		
		JButton AddToCartBtn = new JButton("Add To Cart");
		AddToCartBtn.setForeground(Color.BLACK);
		AddToCartBtn.setBounds(958, 21, 400, 100);
		AddToCartBtn.setBackground(new Color(121, 204, 224));
		AddToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = ItemTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					SelectedItem = ctrl.getItem((int) ItemTable.getValueAt(ItemTable.getSelectedRow(), 0));
					int itemindex = SelectedItem.getSelectedItemIndex(c.Warehouse);
					CartItem = c.Warehouse.get(itemindex);
					ctrl.addItemToCart(CartItem);
				}
			}
		});
		AddToCartBtn.setFont(new Font("Dialog", Font.PLAIN, 34));
		
		OpenCartBtn = new JButton("Open Cart");
		OpenCartBtn.setForeground(Color.BLACK);
		OpenCartBtn.setBounds(958, 152, 400, 100);
		OpenCartBtn.setBackground(new Color(121, 204, 224));
		OpenCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.CartFrameOpen();
			}
		});
		OpenCartBtn.setFont(new Font("Dialog", Font.PLAIN, 34));
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.setForeground(Color.BLACK);
		LogOutBtn.setBounds(958, 673, 400, 100);
		LogOutBtn.setBackground(new Color(121, 204, 224));
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.EmployeeLogOut();
			}
		});
		LogOutBtn.setFont(new Font("Dialog", Font.PLAIN, 34));
		MainPanel.setLayout(null);
		
		JLabel UserLbl = new JLabel("Logged as - "+getUser());
		UserLbl.setForeground(Color.BLACK);
		UserLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UserLbl.setBounds(958, 610, 400, 52);
		MainPanel.add(UserLbl);
		

		
		ItemscrollPane.setViewportView(ItemTable);
		MainPanel.add(ItemscrollPane);
		MainPanel.add(AddToCartBtn);
		MainPanel.add(OpenCartBtn);
		MainPanel.add(LogOutBtn);
		
		SearchTF = new JTextField();
		SearchTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchTF.setText("");
			}
		});
		SearchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(TModel);
			    ItemTable.setRowSorter(sorter);
			    String text = SearchTF.getText();
	            if(text.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	                  sorter.setRowFilter(RowFilter.regexFilter(text));
	             }
			}
		});
		SearchTF.setText("Search here");
		SearchTF.setHorizontalAlignment(SwingConstants.CENTER);
		SearchTF.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SearchTF.setColumns(10);
		SearchTF.setBounds(958, 279, 400, 62);
		MainPanel.add(SearchTF);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 34));
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/images/Main Admin Frame.png")));
		lblNewLabel.setBounds(0, 0, 1383, 801);
		MainPanel.add(lblNewLabel);
	}

	private String getUser() {
		String user;
		if(ctrl.getClient()==null) {
			user = ctrl.getCashier().getUsername() +"-Employee";
		}else {
			user = ctrl.getClient().getUsername() + "-User"; 
		}
		return user;
	}

}

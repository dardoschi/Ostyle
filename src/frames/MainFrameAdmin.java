package frames;

import main.Controller;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import TableModels.ItemInDBTableModel;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class MainFrameAdmin extends JFrame {
	
	private Controller ctrl;
	private JScrollPane ItemscrollPane;
	private JTable ItemTable;
	public ItemInDBTableModel TModel;


	public MainFrameAdmin(Controller c){
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrameAdmin.class.getResource("/images/logo_size_invert.jpg")));
		setTitle("O'Style Admin Page");
		ctrl = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1379, 830);
		setLocationRelativeTo(null);
		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		//creates an istance of the custom table model with the controller arraylist(warehouse)
		TModel = new ItemInDBTableModel(c.Warehouse);
		ItemTable = new JTable(TModel);
		ItemTable.setSelectionForeground(new Color(255, 255, 255));
		ItemTable.setSelectionBackground(new Color(72, 61, 139));
		ItemTable.setFont(new Font("SansSerif", Font.PLAIN, 22));
		ItemTable.setForeground(new Color(255, 255, 255));
		ItemTable.setShowGrid(false);
		ItemTable.setShowHorizontalLines(true);
		ItemTable.setBorder(null);
		ItemTable.setBackground(new Color(44, 5, 72));

		((DefaultTableCellRenderer)ItemTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		//inserts the table into the scrollpanel
		ItemscrollPane = new JScrollPane(ItemTable);
		ItemscrollPane.setViewportBorder(null);
		ItemscrollPane.setBackground(new Color(0, 0, 205));
		ItemscrollPane.setForeground(new Color(30, 144, 255));
		ItemscrollPane.setBorder(null);
		ItemscrollPane.setBounds(0, 0, 890, 794);
		ItemscrollPane.getViewport().setBackground(new Color(44, 5, 72));
		ItemscrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		

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
		

		JButton SearchRefreshBtn = new JButton("Refresh");
		SearchRefreshBtn.setBackground(new Color(121, 204, 224));
		SearchRefreshBtn.setBounds(949, 16, 400, 100);
		SearchRefreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		SearchRefreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.ReloadDBTable();							
			}
		});
		
		JButton EditSelectedBtn = new JButton("Edit Selected");
		EditSelectedBtn.setBackground(new Color(121, 204, 224));
		EditSelectedBtn.setBounds(949, 247, 400, 100);
		EditSelectedBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		EditSelectedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = ItemTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					ctrl.getItem((int) ItemTable.getValueAt(ItemTable.getSelectedRow(), 0));
					ctrl.EditSelectedFrameOpen();
				}
			}
		});
		
		JButton AddItemBtn = new JButton("AddNew");
		AddItemBtn.setBackground(new Color(121, 204, 224));
		AddItemBtn.setBounds(949, 128, 400, 100);
		AddItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.AddNewItemFrameOpen();
			}
		});
		AddItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		
		JButton LogOutBtn = new JButton("Log Out");
		LogOutBtn.setBackground(new Color(121, 204, 224));
		LogOutBtn.setBounds(949, 671, 400, 100);
		LogOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.LogOut();
			}
		});
		LogOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		
		JButton RemoveBtn = new JButton("Remove");
		RemoveBtn.setBackground(new Color(121, 204, 224));
		RemoveBtn.setBounds(949, 374, 400, 100);
		RemoveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = ItemTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a line from table","ERROR", JOptionPane.ERROR_MESSAGE);	
				}else {
					if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0) {
						ctrl.removeFromWarehouse((int) ItemTable.getValueAt(ItemTable.getSelectedRow(), 0));
					}else {
						//does nothing
					}
				
				}
			}
		});
		RemoveBtn.setFont(new Font("Tahoma", Font.PLAIN, 34));
		MainPanel.setLayout(null);
		
		
		ItemscrollPane.setViewportView(ItemTable);
		
		MainPanel.add(ItemscrollPane);
		MainPanel.add(EditSelectedBtn);
		MainPanel.add(SearchRefreshBtn);
		MainPanel.add(LogOutBtn);
		MainPanel.add(AddItemBtn);
		MainPanel.add(RemoveBtn);
		
		JLabel backgroundLbl = new JLabel("");
		backgroundLbl.setIcon(new ImageIcon(MainFrameAdmin.class.getResource("/images/Main Admin Frame.png")));
		backgroundLbl.setBounds(0, 0, 1374, 800);
		MainPanel.add(backgroundLbl);
	}
}

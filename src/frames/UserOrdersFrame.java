package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import TableModels.OrderDetailTableModel;
import TableModels.OrderListTableModel;
import main.Controller;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserOrdersFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	public OrderListTableModel OLTModel;
	public OrderDetailTableModel ODTModel;
	private JTable OrderDetailTable;
	private JTable OrderListTable;
	private int CodO;
	
	public UserOrdersFrame(Controller c) {
		ctrl = c;
		if(ctrl.getClient()==null) {
			ctrl.GetUserOrderList(0);
		}else {
			ctrl.GetUserOrderList(ctrl.getClient().getCodU());
		}
		setTitle("User Orders");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1194, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(11, 32, 1157, 612);
		contentPane.add(splitPane);
		splitPane.setDividerLocation(450);
		
		JScrollPane OrderListPanel = new JScrollPane();
		splitPane.setLeftComponent(OrderListPanel);
		
		JScrollPane OrderDetailPanel = new JScrollPane();
		splitPane.setRightComponent(OrderDetailPanel);
		
		ODTModel = new OrderDetailTableModel(ctrl.OrderItems);
		OrderDetailTable = new JTable(ODTModel);
		OrderDetailTable.setShowGrid(false);
		OrderDetailTable.setFillsViewportHeight(true);
		OrderDetailTable.setSelectionForeground(new Color(255, 255, 255));
		OrderDetailTable.setSelectionBackground(new Color(72, 61, 139));
		OrderDetailTable.setFont(new Font("SansSerif", Font.PLAIN, 20));
		OrderDetailTable.setForeground(new Color(255, 255, 255));
		OrderDetailTable.setShowHorizontalLines(true);
		OrderDetailTable.setBorder(null);
		OrderDetailTable.setBackground(new Color(44, 5, 72));
		((DefaultTableCellRenderer)OrderDetailTable.getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		OrderDetailTable.setDefaultRenderer(Integer.class, centerRenderer);
		OrderDetailTable.setDefaultRenderer(String.class, centerRenderer);
		OrderDetailTable.setDefaultRenderer(Double.class, centerRenderer);
		OrderDetailTable.setAutoCreateRowSorter(true);
		OrderDetailTable.getRowSorter().toggleSortOrder(0);
		OrderDetailTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		OrderDetailTable.setRowHeight(35);
		OrderDetailPanel.setViewportView(OrderDetailTable);
		
		OLTModel = new OrderListTableModel(ctrl.OrderList);
		OrderListTable = new JTable(OLTModel);
		OrderListTable.setShowGrid(false);
		OrderListTable.setFillsViewportHeight(true);
		OrderListTable.setSelectionForeground(new Color(255, 255, 255));
		OrderListTable.setSelectionBackground(new Color(72, 61, 139));
		OrderListTable.setFont(new Font("SansSerif", Font.PLAIN, 20));
		OrderListTable.setForeground(new Color(255, 255, 255));
		OrderListTable.setShowHorizontalLines(true);
		OrderListTable.setBorder(null);
		OrderListTable.setBackground(new Color(44, 5, 72));
		((DefaultTableCellRenderer)OrderListTable.getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.CENTER);
		OrderListTable.setDefaultRenderer(Integer.class, centerRenderer);
		OrderListTable.setDefaultRenderer(String.class, centerRenderer);
		OrderListTable.setDefaultRenderer(Double.class, centerRenderer);
		OrderListTable.setAutoCreateRowSorter(true);
		OrderListTable.getRowSorter().toggleSortOrder(0);
		OrderListTable.getRowSorter().toggleSortOrder(0);//2volte per reverse
		OrderListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		OrderListTable.setRowHeight(35);
		OrderListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ctrl.getOrderDetail((int) OrderListTable.getValueAt(OrderListTable.getSelectedRow(), 0));
					CodO = (int) OrderListTable.getValueAt(OrderListTable.getSelectedRow(), 0);
					OLTModel.fireTableDataChanged();
					ODTModel.fireTableDataChanged();
				}catch(java.lang.ArrayIndexOutOfBoundsException ex) {
					System.out.println("failed to get order from table");
				}
			}
		});
		//OrderListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		OrderListPanel.setViewportView(OrderListTable);
		
		JLabel lblNewLabel = new JLabel("Orders List");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 451, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblOrderDetail = new JLabel("Order Detail");
		lblOrderDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderDetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrderDetail.setBounds(466, 0, 702, 32);
		contentPane.add(lblOrderDetail);
		
		JButton ReturnBtn = new JButton("Return Order");
		ReturnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Select an Option...",JOptionPane.YES_NO_OPTION)==0) {
					ctrl.ReturnOrder(CodO);
				}else {
					//nothing
				}
				System.out.print((int) OrderListTable.getValueAt(OrderListTable.getSelectedRow(), 0)+ "returned");
			}
		});
		ReturnBtn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ReturnBtn.setBounds(111, 655, 197, 63);
		contentPane.add(ReturnBtn);
		
		JButton BackBtn = new JButton("Back to shopping");
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BackBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		BackBtn.setBounds(908, 655, 260, 50);
		contentPane.add(BackBtn);
		
		
	}
}

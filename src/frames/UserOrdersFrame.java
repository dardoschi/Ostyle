package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModels.OrderDetailTableModel;
import TableModels.OrderListTableModel;
import main.Controller;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserOrdersFrame extends JFrame {

	private JPanel contentPane;
	private JTable OrderDetailTable;
	private JTable OrderListTable;
	private Controller ctrl;
	public OrderListTableModel OLTModel;
	public OrderDetailTableModel ODTModel;

	public UserOrdersFrame(Controller c) {
		ctrl = c;
		setTitle("User Orders");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1194, 798);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(11, 32, 1157, 612);
		contentPane.add(splitPane);
		splitPane.setDividerLocation(350);
		
		//fare questo on click su ordertable
		OrderDetailTable = new JTable();
		splitPane.setRightComponent(OrderDetailTable);
		
		OLTModel = new OrderListTableModel(ctrl.OrderList);
		OrderListTable = new JTable(OLTModel);
		OrderListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrl.getOrderDetail((int) OrderListTable.getValueAt(OrderListTable.getSelectedRow(), 0));
				ODTModel = new OrderDetailTableModel(ctrl.OrderItems);
				OrderDetailTable = new JTable(ODTModel);
				splitPane.setRightComponent(OrderDetailTable);
			}
		});
		OrderListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		splitPane.setLeftComponent(OrderListTable);
	}
}

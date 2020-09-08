package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import TableModels.StorePurchasesTableModel;
import main.Controller;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StorePurchasesFrame extends JFrame {

	private JPanel contentPane;
	private Controller ctrl;
	private JTable table;
	private StorePurchasesTableModel TModel;
	private JButton backBtn;
	
	public StorePurchasesFrame(Controller c) {
		ctrl = c;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 850, 495);
		contentPane.add(scrollPane);
		
		TModel = new StorePurchasesTableModel(ctrl.StorePurchases);
		
		table = new JTable(TModel);
		scrollPane.setColumnHeaderView(table);
		
		table.setShowVerticalLines(false);
		table.setFillsViewportHeight(true);
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(72, 61, 139));
		table.setFont(new Font("SansSerif", Font.PLAIN, 22));
		table.setForeground(new Color(255, 255, 255));
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setBackground(new Color(44, 5, 72));
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().toggleSortOrder(0);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(35);
		scrollPane.setViewportView(table);
		scrollPane.setViewportBorder(null);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.getViewport().setBackground(new Color(44, 5, 72));
		
		
		//text centered in table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Double.class, centerRenderer);
		//column sorting (automatic on id ascending)
		table.setAutoCreateRowSorter(true);
		
		backBtn = new JButton("BACK");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		backBtn.setBounds(357, 506, 137, 59);
		contentPane.add(backBtn);
		
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
	    .setHorizontalAlignment(JLabel.CENTER);
	}
}

package TableModels;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import orders.Order;

public class OrderListTableModel extends AbstractTableModel{
	private String[] columnNames = { "CodO","TotalePrice","Date"}; 
    private Class<?>[] types = new Class[]{Integer.class, Double.class, Date.class};
    
    //needed as dummy for the constructor(Warehouse arrayList of the controller copied here)
	public ArrayList<Order> Order ;
	
    public OrderListTableModel (ArrayList<Order> OrderList){
        Order = OrderList;
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex){
        return false;
    }
    	
    @Override
    public int getRowCount(){
    	return Order.size();
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override  //Returns the value for the cell at columnIndex and rowIndex.
    public Object getValueAt(int row, int column){
        if(row < 0 || row >= Order.size()) return null;
        Order obj = Order.get(row);
        switch(column){
	        case 0: return obj.getCodO();
	        case 1: return obj.getTotalPrice();
	        case 2: return obj.getOrderDate();
            default: return null;
        }
    }  

}

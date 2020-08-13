package TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import orders.Item;

public class OrderDetailTableModel extends AbstractTableModel{
	
    private String[] columnNames = { "Name","Size","Price","Type","Colour","Amount"}; 
    private Class<?>[] types = new Class[]{ String.class, String.class, Double.class, String.class, String.class, Integer.class};
    
    //needed as dummy for the constructor(Warehouse arrayList of the controller copied here)
	public ArrayList<Item> Order ;
	
    public OrderDetailTableModel (ArrayList<Item> Order){
        this.Order = Order;
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
        Item obj = Order.get(row);
        switch(column){
	        case 0: return obj.getName();
	        case 1: return obj.getSize();
	        case 2: return obj.getPrice();
	        case 3: return obj.getType();
	        case 4: return obj.getColour();
	        case 5: return obj.getInCart();
            default: return null;
        }
    }  

}

package TableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import main.Controller;
import orders.Item;

public class ItemInCartTableModel extends AbstractTableModel{
	
    private String[] columnNames = { "Id","Name","Size","Price","Type","Colour","In Cart"}; 
    private Class<?>[] types = new Class[]{Integer.class, String.class, String.class, Double.class, String.class, String.class, Integer.class};
    
    //needed as dummy for the constructor(Warehouse arrayList of the controller copied here)
	public ArrayList<Item> Cart ;
	
    public ItemInCartTableModel (ArrayList<Item> Cart){
        this.Cart = Cart;
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
    	return Cart.size();
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override  //Returns the value for the cell at columnIndex and rowIndex.
    public Object getValueAt(int row, int column){
        if(row < 0 || row >= Cart.size()) return null;
        Item obj = Cart.get(row);
        switch(column){
	        case 0: return obj.getId();
	        case 1: return obj.getName();
	        case 2: return obj.getSize();
	        case 3: return obj.getPrice();
	        case 4: return obj.getType();
	        case 5: return obj.getColour();
	        case 6: return obj.getInCart();
            default: return null;
        }
    }  

}

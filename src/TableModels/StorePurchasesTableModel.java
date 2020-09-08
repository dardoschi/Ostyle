package TableModels;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import orders.Order;
import orders.Soldinstore;

public class StorePurchasesTableModel extends AbstractTableModel{
	private String[] columnNames = { "Employee","Item Name","Amount","Date"};
	private Class<?>[] types = new Class[]{String.class, String.class, Integer.class, Date.class};
	
	public ArrayList<Soldinstore> Purchases ;
	
    public StorePurchasesTableModel(ArrayList<Soldinstore> PurchasesList){
        Purchases = PurchasesList;
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
    	return Purchases.size();
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override  //Returns the value for the cell at columnIndex and rowIndex.
    public Object getValueAt(int row, int column){
        if(row < 0 || row >= Purchases.size()) return null;
        Soldinstore obj = Purchases.get(row);
        switch(column){
	        case 0: return obj.getEmplName();
	        case 1: return obj.getItemName();
	        case 2: return obj.getAmount();
	        case 3: return obj.getPurDate();
            default: return null;
        }
    }  
}

package TableModels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;

import Item.Item;
import main.Controller;
//**//to reload table, reload controller arraylist and call firetabledatachanged on the ItemInDBTableModel variable//**//
	public class ItemInDBTableModel extends AbstractTableModel {
	
		    private String[] columnNames = {"Id","Size","Price","Type","Colour","InStock"};
		    private Class<?>[] types = {Integer.class, String.class, Double.class, String.class, String.class, Integer.class};

		    //needed as dummy for the constructor(Warehouse arrayList of the controller copied here)
			public ArrayList<Item> Warehouse ;
			
		    public ItemInDBTableModel (ArrayList<Item> CWarehouse){
		        this.Warehouse = CWarehouse;
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
		    	return Warehouse.size();
		    }
		    
		    @Override
		    public int getColumnCount(){
		        return columnNames.length;
		    }
		    
		    @Override  //Returns the value for the cell at columnIndex and rowIndex.
		    public Object getValueAt(int row, int column){
		        if(row < 0 || row >= Warehouse.size()) return null;
		        Item obj = Warehouse.get(row);
		        switch(column){
		            case 0: return obj.getId();
		            case 1: return obj.getSize();
		            case 2: return obj.getPrice();
		            case 3: return obj.getType();
		            case 4: return obj.getColour();
		            case 5: return obj.getInStock();
		            default: return null;
		        }
		    }  
		    
}

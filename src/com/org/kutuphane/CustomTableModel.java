package com.org.kutuphane;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class CustomTableModel implements TableModel {
	
	ResultSet rowSet;
	java.sql.ResultSetMetaData metaData;
	int numCols,numRows;
	
	public CustomTableModel(ResultSet rowSet) throws SQLException {

		this.rowSet = rowSet;
		metaData = rowSet.getMetaData();
		numCols = metaData.getColumnCount();
		this.rowSet.beforeFirst();
		this.numRows=0;
		while(this.rowSet.next()) {
			this.numRows++;
		}
		this.rowSet.beforeFirst();
		
	}
	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return numCols;
	}

	@Override
	public String getColumnName(int arg0) {
		try {
			return this.metaData.getColumnName(arg0+1).toUpperCase();
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	@Override
	public int getRowCount() {
		return numRows;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		try {
			rowSet.absolute(arg0+1);
			Object object = rowSet.getObject(arg1+1);
			if(object == null)
				return null;
			else
				return object.toString();
		}catch(Exception e) { return e.getMessage();}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	

}

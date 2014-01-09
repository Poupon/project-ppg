package com.phipo.PPg.PPgWin;


import java.util.ArrayList;
import java.util.*;
import java.io.*;

import javax.swing.table.*;
import javax.swing.JTable;

//**********************************************************
public class PPgTable extends AbstractTableModel {
		
		protected PPgTableLine cPrototype = null;

		protected List<PPgTableLine>    cTableObj    = new ArrayList<PPgTableLine>();
		public List<PPgTableLine> getList()     { return cTableObj; }
		public void setList(ArrayList<PPgTableLine> pList) {  cTableObj = pList; }



		protected JTable      cTable      = null;
		public JTable getJTable() { return cTable; }

		protected boolean     cIsEditable = true;
		public void setTableEditable( boolean pBool ) { cIsEditable = pBool; }

		//	protected TableRowSorter cRowFilter = null;


		//-------------
		public PPgTable(PPgTableLine pPrototype, List<PPgTableLine> pList){
				
				cPrototype = pPrototype;
				if( pList == null )
						cTableObj = new ArrayList<PPgTableLine>();
				else
						cTableObj = pList;

				cTable = new JTable( this );	
				//		cTable.setRowSorter( (cRowFilter = new TableRowSorter( this) ) );


				cTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		}			
		//-------------
		public void add( PPgTableLine pLine ){
				cTableObj.add( pLine );
		}
		//-------------

		public Iterator<PPgTableLine> iterator()      { return cTableObj.iterator(); }
		//----------------------------------------------------
		//----------------------------------------------------
		//----------------------------------------------------
		public Class getColumnClass( int pCol ){				
				return cPrototype.getColumnClass( pCol );
		}
		//-------------	
		public boolean isCellEditable( int pRow, int col ){
				if( cIsEditable == true
						&& pRow < cTableObj.size() ){
						return cTableObj.get(pRow).isCellEditable(col);
				}
				return false;
		}
		public String getColumnName( int p_col ){		
				return  cPrototype.getColumnName(p_col);
		}
		//-------------
		public void   setValueAt( Object value, int pRow, int pCol ){
				if( pRow < cTableObj.size() ){
						cTableObj.get(pRow).setValueAt(value, pCol);
				}
		}
		//----------------------------------------------------
		//----------------------------------------------------
		//----------------------------------------------------
		public int getRowCount() { return cTableObj.size(); }
		//-------------
		public int  getColumnCount(){
				return cPrototype.getColumnCount();
		}
		//-------------
		public Object getValueAt( int pRow, int pCol ){
				if( pRow < cTableObj.size() ){
								return (cTableObj.get(pRow)).getValueAt(pCol);
				}
				return null;
		}
		//----------------------------------------------------
		//----------------------------------------------------
		//----------------------------------------------------
		// ------------------------------
		public void selectLine( int pLine ){
				if( pLine > -1 ){
						cTable.changeSelection( pLine, 0, false, false );
						cTable.changeSelection( pLine, 2, false, true );						
				}
		}
		//------------------------------

}
//**********************************************************

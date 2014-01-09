package com.phipo.PPg.PPgWin;




import java.awt.*;
import java.beans.PropertyVetoException;


import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.text.DateFormat;

import com.phipo.PPg.PPgUtils.*;


//***********************************
public class PPgFrameChild extends JInternalFrame 
		implements InternalFrameListener {

    static int cOpenFrameCount = 0;
    static final int cXOffset = 30, cYOffset = 30;
		//-------------------------------------
		//		public String getChildType() = 0;

	//-------------------------------------
    public PPgFrameChild( String p_name ) {
        super( p_name + "#" + (++cOpenFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...

        //...Then set the window size or call pack...
        setSize(400,400);

        //Set the  window's location.
        setLocation( cXOffset*cOpenFrameCount, cYOffset*cOpenFrameCount);
				if( cOpenFrameCount > 10 ) cOpenFrameCount=1;

				addInternalFrameListener( this );
					
				if( PPgJFrame.sGlobalForeground != null )
						setForeground(PPgJFrame.sGlobalForeground );
				
				if( PPgJFrame.sGlobalBackground != null  )
						setBackground( PPgJFrame.sGlobalBackground );			 
    }
	//-------------------------------------
		public void front(){
				try{
					setIcon( false );
					show();
					toFront();	
				}
				catch( PropertyVetoException pv ){
				}
		}
		//-------------------------------------
		public void saveConfigIni( PPgIniFile pIni, String pSection ){
				pIni.set( pSection, getName(),
									((int)getLocation().getX())+","+
									((int)getLocation().getY())+","+
									((int)getSize().getWidth())+","+
									((int)getSize().getHeight()) );
		}
		//-------------------------------------
		public void readConfigIni( PPgIniFile pIni, String pSection ){
				Rectangle lRect = PPgIniFile.GetRectangle( pIni.get( pSection,  getName() ), "," );
				if( lRect != null)	setBounds( lRect);
		}
		//-------------------------------------
		//-------------------------------------
		// implements internalFrameListenery

		//-------------------------------------
		//-------------------------------------

		public void internalFrameOpened( InternalFrameEvent pEv){
				//				System.out.println( "internalFrameOpened");
		}
		//-------------------------------------
		public void internalFrameClosing( InternalFrameEvent pEv){
				//				System.out.println( "internalFrameClosing");
		}
		//-------------------------------------
		public void internalFrameClosed(InternalFrameEvent pEv){
				//				System.out.println( "internalFrameClosed");
		}
		//-------------------------------------
		public void internalFrameIconified( InternalFrameEvent pEv){
				//			System.out.println( "internalFrameIconified");
		}
		//-------------------------------------
		public void internalFrameDeiconified( InternalFrameEvent pEv){
				//			System.out.println( "internalFrameDeiconified");
		}
		//-------------------------------------
		public void internalFrameActivated( InternalFrameEvent pEv){
				//			System.out.println( "internalFrameActivated");
		}
		//-------------------------------------
		public void internalFrameDeactivated( InternalFrameEvent pEv){
				//			System.out.println( "internalFrameDeactivated");
		}	
}
//***********************************

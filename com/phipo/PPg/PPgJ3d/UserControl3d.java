package com.phipo.PPg.PPgJ3d;



import java.io.IOException;
import javax.swing.SwingUtilities;
import java.util.*;


import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import com.jogamp.opengl.util .*;
import com.jogamp.newt.event.*;
import com.jogamp.newt.opengl.GLWindow;



//*************************************************

abstract public class UserControl3d 
		implements  KeyListener, MouseListener {


		UserControl3d(){ };

		//------------------------------------------------
		abstract public void initFromKamera( Kamera3d pKam );


		//------------------------------------------------
		public void keyPressed(KeyEvent e) {;}
		public void keyReleased(KeyEvent e){;}
		public void keyTyped(KeyEvent e) {;}
		
		public void 	mouseClicked(MouseEvent e) {;}	
		public void 	mouseDragged(MouseEvent e) {;}		
		public void 	mouseEntered(MouseEvent e) {;}		
		public void 	mouseExited(MouseEvent e) {;}		

		public void 	mousePressed(MouseEvent e) {;	}		
		//------------------------------------------------

		public void 	mouseMoved(MouseEvent e) {
				System.out.println( "UserControl3d.mouseMoved" );
		}		
		//------------------------------------------------
		public void 	mouseReleased(MouseEvent e) {;}		

		//------------------------------------------------
		public void 	mouseWheelMoved(MouseEvent e)  {;}
};
package com.phipo.PPg.PPgJ3d;



import javax.media.opengl.*;

import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLCapabilities;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.newt.event.*;
import com.jogamp.opengl.util.awt.TextRenderer;

import javax.media.opengl.glu.gl2.*;

import javax.media.nativewindow.util.Point;

import java.nio.*;
import java.util.*;
import java.awt.Font;
import java.text.DecimalFormat;

import com.phipo.PPg.PPgUtils.*;
import com.phipo.PPg.PPgMath.*;

//*************************************************

public class PPgJ3dTest  extends DefaultUserControl3d {
		
		PPgJ3d cDemo;

		//------------------------------------------------
		PPgJ3dTest( PPgJ3d pDemo ){
				cDemo = pDemo;
		}
		//------------------------------------------------
		@Override 
				public void keyPressed(KeyEvent e) {
			       

				int lKeyCode    = e.getKeyCode();
				char lKey       = e.getKeyChar();
				boolean lAction = e.isActionKey();

				System.out.println( "PPgJ3dTest.key " + lKeyCode + " " + lKey + " "+ lAction );

		//		if( e.isModifierKey() ){
	//					return; 
	//			}

				if ( lKeyCode ==KeyEvent.VK_0) {
						cDemo.DemoGlu( 2, 0.3f ); 
						cDemo.DemoGlu( 2, 0.5f ); 

				}	else      if ( lKeyCode == KeyEvent.VK_1) {
						System.out.println( "VK_1");
						cDemo.Demo( 1);
				} else      if ( lKeyCode == KeyEvent.VK_2) {
						cDemo.Demo( 2 );
						cDemo.cFlagDemo2 = true;
				} else     	if ( lKeyCode == KeyEvent.VK_3) {
						cDemo.Demo(3 );
				} else      if ( lKeyCode == KeyEvent.VK_4) {
						cDemo.Demo( 4);
				} else 			if ( lKeyCode == KeyEvent.VK_5) {
						cDemo.Demo( 5 );
				} else 			if ( lKeyCode == KeyEvent.VK_6) {
						cDemo.Demo( 6 );
				} else 			if ( lKeyCode == KeyEvent.VK_7) {
						cDemo.Demo( 7 );
				} else 			if ( lKeyCode == KeyEvent.VK_8) {
						cDemo.Demo( 8 );
				} else 			if ( lKeyCode == KeyEvent.VK_9) {
						cDemo.Demo( 9);
				} else 			if ( lKeyCode == KeyEvent.VK_A) {
						cDemo.Demo( 10 );
				} else 			if ( lKeyCode == KeyEvent.VK_B) {
						cDemo.Demo( 11);
				} else 			if ( lKeyCode == KeyEvent.VK_E) {
						cDemo.DemoEngine( 1 );
				} else 	    if ( lKeyCode ==KeyEvent.VK_X) {	

					for( ActorBase lActor: cDemo.cDemoActor )
							lActor.setDeleted();
				}	else  if ( lKeyCode == KeyEvent.VK_G) {
						System.out.println( "Grid");
						cDemo.setViewGrid();
				}   if ( lKeyCode == KeyEvent.VK_R) {
						System.out.println( "Repere");
						cDemo.setViewRepere();
				} else super.keyPressed( e );
		}
}
//*************************************************

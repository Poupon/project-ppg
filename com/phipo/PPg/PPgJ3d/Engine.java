package com.phipo.PPg.PPgJ3d;


import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import com.jogamp.opengl.util .*;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;


//*************************************************
public class Engine implements GLEventListener{
		
		static public GLU    sTheGlu;
		static public GL2  sTheGl ;
		static public GLAutoDrawable sTheGlDrawable;

		World3d cMyWorld;
		UserControl3d cMyUserControl;

		public int cWidth;
		public int cHeight;
		boolean cFullScreen = false;

		GLProfile   cProfile;
		GLWindow    cGlWindow;
		public GLWindow getGLWindow() { return cGlWindow;}
		FPSAnimator cAnimator ;


		boolean cSmooth = false;

		//boolean cInDrawingState = false;

		//------------------------------------------------
		public Engine( String pTitle, int pW, int pH, boolean pFullScreen, boolean pSmooth  ) {
				
				cSmooth = pSmooth;
				if( cSmooth ){
						System.out.println( "SMOOTH ON" );
				}
						
				cWidth  = pW;
				cHeight = pH;
				cFullScreen = pFullScreen;

				System.out.println( "Engine " + pTitle + " w:" + pW + " h:" + pH + " " + pFullScreen );

				cProfile      = GLProfile.getDefault();
        GLCapabilities lCapabilities = new GLCapabilities( cProfile );
				
        cGlWindow = GLWindow.create(lCapabilities);
				
        cGlWindow.setSize( pW, pH);
				
        cGlWindow.setVisible(true);
        cGlWindow.setTitle( pTitle );
				
        cGlWindow.addWindowListener(new WindowAdapter() {
								@Override
										public void windowDestroyNotify(WindowEvent pEvent) {
										// Use a dedicate thread to run the stop() to ensure that the
										// animator stops before program exits.
										new Thread() {
												@Override
														public void run() {
														if( cAnimator != null)
																cAnimator.stop();//stop the animator loop
														cMyWorld.callEngineStop();
														System.exit(0);
 												}
										}.start();
								};
						});
				

				System.out.println( "Engine " );

		}
		//------------------------------------------------
		public void initGl( ){	

				sTheGl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
				sTheGl.glClearDepth(1.0f);      // set clear depth value to farthest
				sTheGl.glDepthFunc(sTheGl.GL_LEQUAL);  // the type of depth test to do //?
				sTheGl.glHint(sTheGl.GL_PERSPECTIVE_CORRECTION_HINT, sTheGl.GL_NICEST); // best perspective correction
				if( cSmooth ){
						sTheGl.glShadeModel(sTheGl.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
				}




				sTheGl.glMatrixMode( sTheGl.GL_PROJECTION);
				sTheGl.glLoadIdentity();
				//	sTheGlglOrtho(0, 800, 0, 600, 1, -1);
				sTheGl.glOrtho(-800, 800, -600, 600, -1, -1);
				sTheGl.glMatrixMode( sTheGl.GL_MODELVIEW);

				//			sTheGlglEnable( sTheGlGL_COLOR_MATERIAL );
			sTheGl.glColorMaterial( sTheGl.GL_FRONT, sTheGl.GL_AMBIENT_AND_DIFFUSE);
  			 
					
					//				sTheGlglEnable( sTheGlGL_AUTO_NORMAL);  //couteux
					//					sTheGlglEnable( GL11.GL_NORMALIZE );   //couteux
					//		sTheGlglFrontFace( sTheGlGL_CCW);

				sTheGl.glClearColor( 0.10f, 0.10f, 0.2f, 0.0f );

				if( cSmooth ){
						sTheGl.glEnable( sTheGl.GL_POINT_SMOOTH );
						sTheGl.glEnable( sTheGl.GL_LINE_SMOOTH );
						sTheGl.glEnable( sTheGl.GL_POLYGON_SMOOTH );
				}

				sTheGl.glEnable( sTheGl.GL_DEPTH_TEST );
				sTheGl.glDisable( sTheGl.GL_CULL_FACE );

				cMyWorld.callEngineInit(sTheGl); 
		}
		//------------------------------------------------
		public void beginDraw( )  {
				if( isOk() == false )
						return ;
				sTheGl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
				sTheGl.glMatrixMode( GL2.GL_MODELVIEW );
				sTheGl.glLoadIdentity();

		}
		//------------------------------------------------
		public void endDraw( )  {
				if( isOk() == false )
						return ;

				sTheGl.glFlush();	
		}
		//------------------------------------------------
		//------------------------------------------------
		//------------------------------------------------
		// Called by the drawable immediately after the OpenGL context is initialized.          
   @Override
			 public void init(GLAutoDrawable pGlDraw ){

				System.out.println( "Engine init" );

				sTheGlDrawable = pGlDraw;
				sTheGl = pGlDraw.getGL().getGL2();
				sTheGlu = new GLU();

				initGl( );

				// can init the scene here 

				/*		
				theTorus = sTheGlglGenLists(1);
				sTheGlglNewList(theTorus, GL.GL_COMPILE);
				drawTorus(gl, 8, 25);
				gl.glEndList();
    
				gl.glShadeModel(GL.GL_FLAT);
				gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
				*/
		}

		//------------------------------------------------
		// Called by the drawable to initiate OpenGL rendering by the client
   @Override
		public void display(GLAutoDrawable pGlDraw){

			 //			 System.out.println( "Engine display" );
							
			 sTheGlDrawable = pGlDraw;
			 sTheGl = pGlDraw.getGL().getGL2();
			 sTheGlu = new GLU();
			 

				beginDraw();
				

				if( cMyWorld != null ) {
						cMyWorld.callEngineDrawBegin(sTheGl );

						cMyWorld.sceneDraw( sTheGl );


						cMyWorld.callEngineDrawEnd(sTheGl );

						////		sTheGl.glPopMatrix();
				}
				
				endDraw();
		}

		//------------------------------------------------
		// Notifies the listener to perform the release of all OpenGL resources per GLContext, such as memory buffers and GLSL programs.
		@Override
		public void 	dispose(GLAutoDrawable drawable){

			 System.out.println( "Engine dispose" );
		}
		//------------------------------------------------
		// Called by the drawable during the first repaint after the component has been resized.        
		@Override
		public void 	reshape(GLAutoDrawable pGlDraw, int x, int y, int width, int height) {
				
			 System.out.println( "Engine reshape" );

  			sTheGlDrawable = pGlDraw;
				sTheGl = pGlDraw.getGL().getGL2();
				sTheGlu = new GLU();
				/*
				GL gl = drawable.getGL();
				GLU glu = new GLU();
				//
				gl.glViewport(0, 0, width, height);
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glLoadIdentity();
				glu.gluPerspective(30, (float) width / (float) height, 1.0, 100.0);
				gl.glMatrixMode(GL.GL_MODELVIEW);
				gl.glLoadIdentity();
				glu.gluLookAt(0, 0, 10, 0, 0, 0, 0, 1, 0); 
				*/
		}
		//------------------------------------------------
		//------------------------------------------------
		//------------------------------------------------
		// Provoque l'emission d'un evenementRedraw

		public void callWinRedraw(){
				cGlWindow.display();
		}
		//------------------------------------------------


		//------------------------------------------< ------
		public void  setWorld( World3d pWorld , UserControl3d pUserControl){

			 System.out.println( "Engine setWorld" );
			 
			 if( cAnimator != null )
					 cAnimator.pause();

			 if( cMyWorld != null )
					 cGlWindow.removeGLEventListener( cMyWorld );

				cMyWorld = pWorld;
				cGlWindow.addGLEventListener( cMyWorld );

				/*
				cGlWindow.addKeyListener( cMyWorld );
				cGlWindow.addMouseListener( cMyWorld );
				*/
				if( cMyUserControl != null ){
						cGlWindow.removeKeyListener(cMyUserControl);
						cGlWindow.removeMouseListener(cMyUserControl);
				}
				cMyUserControl = pUserControl;

				cGlWindow.addKeyListener( pUserControl );
				cGlWindow.addMouseListener( pUserControl );

				cMyWorld.runExtern();
		
				if( cAnimator != null )
						cAnimator.resume();
		}
		//------------------------------------------------
		public void  setUserControl( UserControl3d pUserControl){
			 System.out.println( "Engine setUserControl" );
			 
			 if( cAnimator != null )
					 cAnimator.pause();

				if( cMyUserControl != null ){
						cGlWindow.removeKeyListener(cMyUserControl);
						cGlWindow.removeMouseListener(cMyUserControl);
				}
				cMyUserControl = pUserControl;

				cGlWindow.addKeyListener( pUserControl );
				cGlWindow.addMouseListener( pUserControl );


				if( cAnimator != null )
						cAnimator.resume();
		}
		//------------------------------------------------
		public UserControl3d getUserControl() {
				return cMyUserControl;
		}

		//------------------------------------------------
		public void  start( int pFps){
				cAnimator = new FPSAnimator( cGlWindow, pFps, true );
				cAnimator.start(); // start the animation loop
		}

		//------------------------------------------------
		public void stopEngine(){
				cMyWorld.callEngineStop();
				cAnimator.stop();	
		}
		//------------------------------------------------
		public boolean isOk() {

				return true;
		}
		//------------------------------------------------
		public boolean 	render( Transform3d pTransf, NodeBase pNode ){
				
				if( pTransf != null )		sTheGl.glPushMatrix();

				pTransf.renderGL(sTheGl);	
				pNode.renderGL(sTheGl);									
				
				if( pTransf != null )		sTheGl.glPopMatrix();				
	 						
			return true;
		}
		//------------------------------------------------
		//------------------------------------------------
		//------------------------------------------------
		public Texture loadTexturePng( GL2 pGL, String pPath ){
				
				try {
            InputStream lStream = new FileInputStream( pPath );
            TextureData lData   = TextureIO.newTextureData( cProfile, lStream, false, "png");
            Texture lTexture = TextureIO.newTexture( lData);


						//		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, pFilter );
						//		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, pFilter);//
						//			glTexImage2D(GL_TEXTURE_2D, 0, 4, lImg.width, lImg.height, 0,
												 //								 GL_RGBA, GL_UNSIGNED_BYTE, lImg.makeRGBA() );
						
						return lTexture;
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

				return null;
		}

		//------------------------------------------------
		//------------------------------------------------
		//------------------------------------------------
		public void enableFog( Float4 pFogColor, float pDensity, float pStart, float pEnd ){
				
		}
		//------------------------------------------------
		public void disableFog( Float4 pFogColor, float pDensity, float pStart, float pEnd ){

		}
		//------------------------------------------------
		public void changeFog( Float4 pFogColor, float pDensity, float pStart, float pEnd ){
				
		}	
		//------------------------------------------------
}
//*************************************************

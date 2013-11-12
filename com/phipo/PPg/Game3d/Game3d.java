package com.phipo.PPg.Game3d;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


import org.lwjgl.util.glu.*;


/*
import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
*/
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.*;
import de.lessvoid.nifty.builder.*;
import de.lessvoid.nifty.effects.*;
import de.lessvoid.nifty.controls.*;
import de.lessvoid.nifty.elements.*;
import de.lessvoid.nifty.tools.*;
import de.lessvoid.nifty.screen.*;
import de.lessvoid.nifty.layout.*;
import de.lessvoid.nifty.render.*;

import de.lessvoid.nifty.sound.SoundSystem;


import java.nio.*;
import java.util.*;

import com.phipo.PPg.PPgUtils.*;


import com.phipo.PPg.PPgMath.*;
import com.phipo.PPg.PPgG3d.*;

//*************************************************
public class Game3d extends World{

		PPgRandom cRand = new PPgRandom(666);

		static FloatBuffer sFBuf = ByteBuffer.allocateDirect(256).asFloatBuffer();


		static float sGreyAmbient[]  =   { 0.5f,  0.5f, 0.5f, 1.0f };
		static float sWhiteDiffuse[]  =  { 1.0f,  1.0f, 1.0f, 1.0f };

		static float sPosition[] =       { 1.0f, 1.0f, 0.0f, 0.0f }; //  a l'inini
		protected ArrayList<ActorBase>   cDemoActor = new ArrayList();

		Nifty cNifty ;
		//------------------------------------------------
		public Game3d( int pW, int pH, boolean pFull){
				super( "Demo Game3d", new RectFloat3( new Float3( -5, -4, -2), 
															 new Float3( 10, 8, 4 )),
							 new Engine( pW, pH, pFull) );



		}
		//------------------------------------------------
	//------------------------------------------------

		float cDelta = 0.05f;
		float cKamX = 0f;
		float cKamY = 0f;

		float cKamZ = 0f;

		float cRotX =-45f;
		float cRotY =-10f;
		float cRotZ =0f;

		float cScale = 1f;

		
		int   cMouseX;
		int   cMouseY;

		Float3 cEye = new Float3( 0, 1f, 0 );
		Float3 cCenter = new Float3( 0, 0, 0 );
		Float3 cUp = new Float3( 1, 0, 0 );
		
		boolean nifty_done = false;

		void input(){
				

				//=====================================
				while (Mouse.next()) {		


					int mouseX = Mouse.getEventX();
					int mouseY = Mouse.getEventY();
					int mouseWheel = Mouse.getEventDWheel() ; 
					int button = Mouse.getEventButton();
					boolean buttonDown = Mouse.getEventButtonState();

					System.out.println( "MouseEvent " 
															+ " x:" + mouseX
															+ " y:" + mouseY
															+ " wheel:" + mouseWheel
															+ " button:" + button
															+ " down:" + buttonDown );
															
									// UTILSER LES VALEURS LU
										

					if( Mouse.isButtonDown(0) ){

							float lDx = Mouse.getDX()* cDelta;
							float lDy = Mouse.getDY()* cDelta;
							
							if( lDx > 0.1f )
									lDx = 0.1f;
							else 	if( lDx < -0.1f )
									lDx = -0.1f;
							
							if( lDy > 0.1f )
									lDy = 0.1f;

							else 	if( lDy < -0.1f )
									lDy = -0.1f;
							
							cKamX += lDx ;
							cKamY += lDy ;
							cKamera.getTransf().move( new Float3( cKamX, cKamY, 0 ) );				
							cKamera.getTransf().rotate( new Float3( cRotX, cRotY, cRotZ ) );
					}
					
				
					float dW = Mouse.getDWheel();
					//			System.out.println( "MMMMMMMMMMMM mouse dx : " + dW );
					if( dW != 0 ){
							cScale += dW*cDelta*0.01*cScale;
							if( cScale > 100 )
									cScale = 100;
							else if( cScale < 0.001f )
									cScale =  0.001f;
							
							cKamera.getTransf().scale( new Float3( cScale, cScale,  cScale) );
					}
				}
			//=====================================


			//=====================================
			while( Keyboard.next() ){

					System.out.println( "KeyBoard Event " 
															+" key:" +Keyboard.getEventKey() 
															+" char:" +Keyboard.getEventCharacter()
															+" state:"+Keyboard.getEventKeyState() );
			
				// UTILSER LES VALEURS LUES

		
					if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
							System.exit(0);
					}
					else
					if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
							World.sTheWorld.FlipPause();
					}
					else

					if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
							cKamera.resetOrtho();
							cKamera.getTransf().scale( new Float3( 1f, 1f, 1f ) );
					}
					else
					if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
							cKamera.setOrtho(-0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f);
							cKamera.getTransf().scale( new Float3( 1f, 1f, 1f ) );
					}
					else
		
					if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
							if( cKamera.isOrtho() ) {
									System.out.println( " ********************** PERPECTIVE " );
									cKamera.setPerspective( cEye, cCenter, cUp );
									cKamera.getTransf().move( new Float3( 0, 0, 0 ) );
									cKamera.getTransf().rotate( new Float3( 0, 0, 0 ) );
							}	
					}
					else
							cNifty.keyEvent( Keyboard.getEventKey(), Keyboard.getEventCharacter(), Keyboard.getEventKeyState());
      }
			
							 
			//=====================================

		cKamera.getTransf().move( new Float3( cKamX, cKamY, 0 ) );				
		cKamera.getTransf().scale( new Float3( cScale, cScale,  cScale) );
		cKamera.getTransf().rotate( new Float3( cRotX, cRotY, cRotZ ) );
						

			int mouseX = Mouse.getX();
      int mouseY = Display.getDisplayMode().getHeight() - Mouse.getY();
      if (cNifty.render(true, mouseX, mouseY, Mouse.isButtonDown(0))) {
        nifty_done = true;
      }


		}
		//------------------------------------------------
		@Override
		public void worldCallInputTurn( ){
				//				System.out.println( "worldCallInputTurn " );
				input();
		}
		//------------------------------------------------

		@Override
		public void worldCallBeginTurn( double pTimeDiff){


				//				System.out.println( "QuadExample callEngineUpdate pTimeDiff:" + pTimeDiff );
		}
		//------------------------------------------------
		@Override
		public void callEngineTurnBegin(){		

				//				System.out.println( "PPgG3d callEngineRenderGL.callEngineRenderGL" );
				
				ByteBuffer lTemp = ByteBuffer.allocateDirect(16);
				lTemp.order(ByteOrder.nativeOrder());

				GL11.glLight( GL11.GL_LIGHT0, GL11.GL_AMBIENT, 	(FloatBuffer)lTemp.asFloatBuffer().put(sGreyAmbient).flip());
				GL11.glLight( GL11.GL_LIGHT0, GL11.GL_DIFFUSE, (FloatBuffer)lTemp.asFloatBuffer().put(sWhiteDiffuse).flip());								
				GL11.glLight( GL11.GL_LIGHT0, GL11.GL_POSITION, (FloatBuffer)lTemp.asFloatBuffer().put(sPosition).flip());

				//				sFBuf.put( sModelAmbient, 0, 4 );
				//	GL11.glLightModel( GL11.GL_LIGHT_MODEL_AMBIENT, sFBuf);

				
         float yellowAmbientDiffuse[] = {1.0f, 1.0f, 0f, 1.0f};	
				 float sPosition2[] = {-2.0f, 2.0f, -5.0f, 0.0f}; // a l'inifni

				 GL11.glLight( GL11.GL_LIGHT1, GL11.GL_AMBIENT, (FloatBuffer)lTemp.asFloatBuffer().put(yellowAmbientDiffuse).flip());
				 GL11.glLight( GL11.GL_LIGHT1, GL11.GL_DIFFUSE,  (FloatBuffer)lTemp.asFloatBuffer().put(yellowAmbientDiffuse).flip());
				 GL11.glLight( GL11.GL_LIGHT1, GL11.GL_POSITION,  (FloatBuffer)lTemp.asFloatBuffer().put(sPosition2).flip());


				Primitiv3d.Green();

				Primitiv3d.DrawGrid( getWidth()/2, getDepth()/2, 3 );
				
				Primitiv3d.DrawRepere( 0.5f, 1 );	

				
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glColor3f( 0.0f, 0.0f, 0.9f );

				Primitiv3d lPrim = 	new Primitiv3d();
				Primitiv3d.SubParamDrawing lParam01 = lPrim.GetSubParamDrawing( 0, 1.0f, false, Primitiv3d.SubNormalizeType.NORMALIZE_NONE, GLU.GLU_SILHOUETTE );


				Primitiv3d.Parallelepiped( getWidth(), getHeight(), getDepth(), lParam01 );
				GL11.glEnable(GL11.GL_LIGHTING);
				
				/*

				Color.white.bind();
 
				cFont.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
				if( cFont2 != null )
				cFont2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);
				*/


				Keyboard.enableRepeatEvents(true);

		}
		//------------------------------------------------cd 
		@Override
		public void callEngineInit(){

				System.out.println( "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT PPgG3d.callEngineInit TTTTTTTTTTTTTTTTT" );
				//  glEnable( GL_TEXTURE_2D );
				// FAIRE DES LUMIERE QUI DERIVE DE MODELBASE !!!!


				GL11.glClearColor( 0.20f, 0.20f, 0.2f, 0.0f );

	
				GL11.glEnable( GL11.GL_LIGHTING);
				GL11.glEnable( GL11.GL_LIGHT0);
				GL11.glEnable( GL11.GL_LIGHT1);

				//=============== Add camera =============
				{
						cKamera=  new Kamera3d( null, new Transform3d( null, null, null),
																		-0.5, 0.5, -0.5, 0.5, -0.5, 0.5 );
						//				-800, 800, -600, 600, -1, -1 );
						//				-800, 800, -600, 600, -500, 500 );
						
						
						//				cKamera.=  new Kamera3d( null, new Transform3d( null, null, null),
						//																-800, 800, -600, 600, -1, -1 );
						
						cKamera.getTransf().rotate( new Float3( -45f, -10f, 0f ) );
						World.sTheWorld.setActorKamera(  new ActorLocation( 0.5f, 0.5f, -0.5f, EnumFaction.Neutral, cKamera ));
						
						//		cKam.getTransf().rotate( new Float3( 30f, 30f, 0f ) );
						//		cKam.getTransf().scale( new Float3( 0.1f, 0.1f, 0.1f ) );
						//
						//cKam.getTransf().move( new Float3( 0f, 0f, -10f ) );
				}
						
				//=============== Add camera =============
				//				Mouse.setGrabbed( true );
				
				cMouseX = Mouse.getDX();
				cMouseY = Mouse.getDY();
        Mouse.getDWheel();


				cNifty = new Nifty(	new RenderDeviceLwjgl(),
																	new SoundSystem(new SlickSoundDevice()),
																	LwjglInitHelper.getInputSystem(),
																	new TimeProvider());


				cNifty.fromXml("nifty.xml");

		}
		//------------------------------------------------
    public static void main(String[] args) {
 
				int lW      = PPgParam.GetInteger( args, "-w", 1280 );
				int lH      = PPgParam.GetInteger( args, "-h", 1024);
				
				String lStrFullScreen = PPgParam.GetString( args, "-S", null );
				String lSizeStr = lStrFullScreen;

				String lStrWindown = PPgParam.GetString( args, "-W", null );
				if( lSizeStr == null )
						lSizeStr = lStrWindown;

				if( lSizeStr != null ){

						if( lSizeStr.compareTo( "640x480" ) ==0 || lSizeStr.compareTo("1") ==0 ){
								lW = 640;
								lH = 480;
						}	else	if( lSizeStr.compareTo( "800x600") ==0 || lSizeStr.compareTo("2")==0 ){
								lW = 800;
								lH = 600;
						}	else	if( lSizeStr.compareTo( "1280x1024") ==0 || lSizeStr.compareTo("3")==0 ){
								lW = 1280;
								lH = 1024;
						}	else	if( lSizeStr.compareTo( "1920x1080") ==0 || lSizeStr.compareTo("4")==0 ){
								lW = 1920;
								lH = 1080;
						}	else	if( lSizeStr.compareTo( "1920x1200") ==0 || lSizeStr.compareTo("(5")==0 ){
								lW = 1920;
								lH = 1200;
						}
				}
				
				Game3d lProg = new Game3d( lW, lH,  lStrFullScreen != null );
        lProg.run();
    }
}

//*************************************************

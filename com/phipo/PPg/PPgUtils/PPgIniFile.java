package com.phipo.PPg.PPgUtils;





import java.util.StringTokenizer;

import java.util.*;
import java.io.*;

import java.awt.Rectangle;
import java.awt.Color;

import java.lang.NumberFormatException;

import java.net.*;

import java.awt.Image;
import java.awt.image.*;

import javax.swing.ImageIcon;
import javax.swing.GrayFilter;


import com.phipo.PPg.PPgImg.*;


// *********************************************
public  class PPgIniFile extends Properties{
		
		//		TreeMap  cMap = new TreeMap();
		static final String SEP = new String( "." );
		String cSystemPrefix="";
		
		// ------------------------------
		public	PPgIniFile(){			
				File lFile= null;
		}
		// ------------------------------
		public	PPgIniFile( String pPath, String pFilename ){
				readIni( pPath, pFilename  );
		}
		// ------------------------------
		public	PPgIniFile( String pFilename ){
				readIni( pFilename );
		}
		// ------------------------------
		public	PPgIniFile( InputStream pIStream){			
				readIni(pIStream);
		}
		// ------------------------------
		public void setSystemPrefix(String pPrefix){
				cSystemPrefix = pPrefix ;
		}
		// ------------------------------
		public boolean readIni( String pPath, String pFileName ){	
				
				if( pPath != null && pPath.length() > 0  )
						pFileName = pPath +'/' + pFileName;

				return readIni( pFileName );				
		}
		// ------------------------------
		public boolean readIni( String pFileName ){		
				File lFile= new File( pFileName );
				try {
						if( lFile != null ) {
								FileReader lFread = new FileReader(lFile  );
								return readIni(lFread);	
						}
				}
				catch( Exception e){
						System.err.println("catch " + e + " in PPgIniFile.readIni " + pFileName);
						e.printStackTrace();
				}
				return false;
		}
		// ------------------------------
		public boolean readIni( InputStream pIStream ){		
				InputStreamReader pReader  = new InputStreamReader( pIStream );
				return readIni( pReader );
		}
		// ------------------------------
		public boolean  readIni( InputStreamReader  lFread){		
				System.out.println(  "   ========================= readIni ======================   " );
		try {
			
					String lSbuf;
					BufferedReader lBufread = new BufferedReader(lFread);
			
					String lCurrentSection=null;
					String lCurrentKey=null;
					String lCurrentValeur=null;
					
					int lNumline = 0;
					
					while( (lSbuf=lBufread.readLine()) != null) {
							
							// System.out.println( "ap readLine  " +lNumline + ">>" + lSbuf );
							lNumline++;
							//System.out.println( lNumline + ">>>" + lSbuf );
							// Ligne vide ou commentaire
							if(  lSbuf.length() == 0 || lSbuf.charAt(0) == '#'  || lSbuf.charAt(0) == '\n'
									 || lSbuf.trim().length() == 0  )
									continue;
							
							
							// New phipo 20100629 commande commencant par !
							if( lSbuf.charAt(0) == '!' ){
									try{
											StringTokenizer lTok = new StringTokenizer(lSbuf.substring(1));
											String lCommand = lTok.nextToken(" \t").trim();
											if( lCommand != null && lCommand.equals( "include" ) ){
													String lFileName = lTok.nextToken(" \t").trim();
													if( lFileName != null ) {
															readIni(lFileName ); 
													}
											}
									}	catch( Exception e){
											System.err.println("catch " + e + " in PPgIniFile.readIni <"+lSbuf+"> while reading commande include" + " line:" +lNumline );
											e.printStackTrace();
								}	
									continue;
							}
							
							// Fin New phipo 20100629
							
							
							if( lSbuf.charAt(0) == '[' ){					
									try{
											StringTokenizer lTok = new StringTokenizer(lSbuf.substring(1));
											lCurrentSection = lTok.nextToken("]").trim();
									}				catch( Exception e){
											System.err.println("catch " + e + " in PPgIniFile.readIni <"+lSbuf+"> while reading section"  + " line:" +lNumline);
											e.printStackTrace();
									}								
									continue;
									
									//												System.out.println( "SECTION=" + lCurrentSection );
							}
							
							try{									
									StringTokenizer lTok = new StringTokenizer( lSbuf );
									lCurrentKey         = lTok.nextToken("=").trim();
									lCurrentValeur      = lTok.nextToken("\n").trim();
									lCurrentValeur  = lCurrentValeur.substring( 1 ).trim();
									//												System.out.println( "VAL " + lCurrentKey + "=" + lCurrentValeur );
									if( lCurrentSection == null ){
											System.err.println( "PPgIniFile Error in line:" + lNumline 
																					+ " : No section define for :" + lCurrentKey );
									}
									else{
											String lStr =  lCurrentSection + SEP +lCurrentKey ;
											remove( lStr );
											put( lStr, lCurrentValeur );
											//														System.out.println( "->" + lStr  + "=" + lCurrentValeur );
									}			 					
							} catch( Exception e){
									System.err.println("catch " + e + " in PPgIniFile.readIni while reding values for section "  + lCurrentSection + " line:" +lNumline);
									e.printStackTrace();
							}							
					}
					lFread.close();						

			} catch( Exception e){
					System.err.println("catch " + e + " in PPgIniFile.readini " );
					e.printStackTrace();
					return false;
			}
			return true;
		}
	// ------------------------------
	public String  get( String pSection, String pKey ) { 

			//					System.out.println( pSection + ":" + pKey + "=" +  get( pSection + SEP + pKey ));
			String lKey = pSection + SEP + pKey;

			// Si une property est defini dans le system elle est preponderante
			String lSysStr;
			if( cSystemPrefix != null )
					lSysStr = System.getProperty( cSystemPrefix+SEP+lKey );
			else
					lSysStr = System.getProperty( lKey );
					
			if( lSysStr != null )
					return lSysStr;

			return (String) get( lKey ); 
	}
	// ------------------------------
		public  Collection<String> getCollection( String pSection, String pKey,  Collection<String> lCollect, String pToeat, String pSep  ) { 

			String lStr = get( pSection, pKey );
			if( lStr == null )
					return lCollect;

			return PPgUtils.Split( lStr, lCollect, pToeat, pSep );
		}
	// ------------------------------
	public boolean test( String pSection, String pKey, String pVal) { 

			String  lStr = get( pSection, pKey );
			if( lStr != null && lStr.equals( pVal ) )
					return true;

		return false; 
	}
	// ------------------------------
	public String  get( String pSection, String pKey, String pDefault ) { 

		String lStr = (String) get( pSection, pKey ); 
		if( lStr == null || lStr.length() == 0)
				return pDefault;

		return lStr;
	}
	// ------------------------------
	public Color getColor( String pSection, String pKey, Color pDefault) { 

		String lStr = (String) get( pSection, pKey ); 
		if( lStr == null || lStr.length() == 0)
				return pDefault;

		if( lStr.compareToIgnoreCase("black")==0)
				return Color.black ;
		else 		if( lStr.compareToIgnoreCase("blue")==0)
				return Color.blue ;
		else 		if( lStr.compareToIgnoreCase("cyan")==0)
				return Color.cyan ;
		else 		if( lStr.compareToIgnoreCase("darkgray")==0)
				return Color.darkGray ;
		else 		if( lStr.compareToIgnoreCase("gray")==0)
				return Color.gray ;
		else 		if( lStr.compareToIgnoreCase("green")==0)
				return Color.green ;
		else 		if( lStr.compareToIgnoreCase("lightgray")==0)
				return Color.lightGray ;
		else 		if( lStr.compareToIgnoreCase("magenta")==0)
				return Color.magenta ;
		else 		if( lStr.compareToIgnoreCase("orange")==0)
				return Color.pink ;
		else 		if( lStr.compareToIgnoreCase("pink")==0)
				return Color.red ;
		else 		if( lStr.compareToIgnoreCase("red")==0)
				return Color.red ;
		else 		if( lStr.compareToIgnoreCase("white")==0)
				return Color.white ;
		else 		if( lStr.compareToIgnoreCase("yellow")==0)
				return Color.yellow ;
		else
				if( lStr.startsWith( "rgb(" )) {
				
				StringTokenizer lTok=new StringTokenizer( lStr.substring( 4 ), ",:)" );
				float r = 0;
				float g = 0;
				float b = 0;
				r = Float.parseFloat(lTok.nextToken() );
				g = Float.parseFloat(lTok.nextToken() );
				b = Float.parseFloat(lTok.nextToken() );
				return new Color( r,g, b);
		}		
		else
				if( lStr.startsWith( "rgba(" )) {
						
						StringTokenizer lTok=new StringTokenizer( lStr.substring( 5), ",:)" );
						float r = 0;
						float g = 0;
						float b = 0;
						float a = 0;
						r = Float.parseFloat(lTok.nextToken() );
						g = Float.parseFloat(lTok.nextToken() );
						b = Float.parseFloat(lTok.nextToken() );
						a = Float.parseFloat(lTok.nextToken() );
						return new Color( r, g, b, a);
				}		
		
		
		return pDefault;
	}
	// ------------------------------
	public Integer  getInteger( String pSection, String pKey ) { 
			String lStr = get( pSection, pKey );

			if( lStr != null && lStr.length() !=0) 
					return Integer.decode(lStr ); 

			return null;
	}
	// ------------------------------
	public boolean  getboolean( String pSection, String pKey, boolean pDefaultValue ) { 

			String lStr = get( pSection, pKey );

			//		System.out.println( "getint " + pSection + "." + pKey + " =" + lStr );

			if( lStr != null && lStr.length() != 0 ) {
					if( lStr.equals( "true" ) )
							return true;
					if( lStr.equals( "false" ) )
							return false;
			}
			return pDefaultValue;
	}
	// ------------------------------
	public int  getint( String pSection, String pKey, int pDefaultValue ) { 

			String lStr = get( pSection, pKey );


			if( lStr != null && lStr.length() != 0 ) 
					return Integer.parseInt(lStr ); 

			return pDefaultValue;
	}
	// ------------------------------
	public double  getdouble( String pSection, String pKey, double pDefaultValue ) { 
			String lStr = get( pSection, pKey );

			if( lStr != null  && lStr.length() != 0) 
					return Double.parseDouble(lStr ); 

			return pDefaultValue;
	}
	// ------------------------------
	public float  getfloat( String pSection, String pKey, float pDefaultValue ) { 
			String lStr = get( pSection, pKey );

			if( lStr != null  && lStr.length() != 0) 
					return Float.parseFloat(lStr ); 

			return pDefaultValue;
	}
	// -----------------------------
		public static Rectangle GetRectangle(  String pData, String pSep){
				if( pData == null ){
						return null;
				}

				StringTokenizer lTok = new StringTokenizer( pData, pSep );

				String lX = lTok.nextToken().trim();
				String lY = lTok.nextToken().trim();
				String lW = lTok.nextToken().trim();
				String lH = lTok.nextToken().trim();
				
				if( lX!= null && lY!= null && lW!=null && lH!=null 
						&& Integer.decode(lX)!= null
						&& Integer.decode(lY)!= null
						&& Integer.decode(lW)!= null
						&& Integer.decode(lH)!= null){						
						return new Rectangle ( Integer.decode(lX).intValue(),
																	 Integer.decode(lY).intValue(),
																	 Integer.decode(lW).intValue(),
																	 Integer.decode(lH).intValue() );
				}
				return null;				
		}
	// -----------------------------
	public void set(  String pSection, String pKey, String pVal ){

		String lStr =  pSection + SEP + pKey;
		remove( lStr );
		put( lStr, pVal );
		//		System.out.println( "->" + lStr  + "=" + pVal );
	}
	// -----------------------------
	public void remove(  String pSection, String pKey){

		String lStr =  pSection + SEP + pKey;
		remove( lStr );
	}		
	// ------------------------------
	public void writeIni( String pFilename ){
		File lFile= new File( pFilename );
		if( lFile != null )
			writeIni( lFile);
	}
	// ------------------------------
	public void writeIniUri( String pUri ){
		File lFile= new File( pUri );
		if( lFile != null )
			writeIni( lFile);
	}
	// ------------------------------
	public void writeIni( File pFile  ){

		try {
			PrintStream lFout = new PrintStream( new FileOutputStream( pFile ) );
			writeIni( lFout );
		}	catch(Exception e ) { System.err.println( e );
				e.printStackTrace();
		}
	}
		// ------------------------------
		public HashMap getSection( String pSection ){

				System.out.println( "getSection : " + pSection );

				HashMap lResult = new HashMap();
				
				Iterator lIter  = keySet().iterator();
				while( lIter.hasNext() ){
						String lStr = (String) lIter.next();

						int lPos = lStr.indexOf( SEP );										
						String lSection = lStr.substring( 0, lPos );
						System.out.println( lSection );

						if( lSection.compareTo( pSection ) == 0 ){
								String lVar     = lStr.substring( lPos + 1 );
								String lVal     = (String)get(lStr);
								System.out.println( "Var="+lVar + " Val="+lVal );
								lResult.put( lVar, lVal );
						}
				}
				System.out.println( ">>>>>>>>>>Result:" + lResult.size() );
				return lResult;
		}
	// ------------------------------
	public void writeIni( PrintStream pOut  ){

			// A Modifier il faudrait trier par section !!!

		try {			
			Set      lSet   = keySet();
			TreeSet  lTreeSet = new TreeSet( lSet );

			Iterator lIter  = lTreeSet.iterator(); //lSet.iterator();
			
			String   lCurrentSection="";
			
			while( lIter.hasNext() ){
				String lStr = (String) lIter.next();
				int lPos = lStr.lastIndexOf( SEP );
				
				String lSection = lStr.substring( 0, lPos );
				String lVar     = lStr.substring( lPos + 1 );
				String lVal     = (String)get(lStr);

				//				pOut.println( lStr + "+" + lVal );
				//			String lSys  = System.getProperty( lSection+SEP+lVar );
				
				if( lSection != null && lVar != null && lVal != null ){
					
					// Nouvelle section 
					if( lSection.compareTo( lCurrentSection ) != 0 ){
						pOut.println( "\n["+ lSection + "]" );
						lCurrentSection = lSection;
					}
					
					//				if( lSys != null )
					//							pOut.println( lVar + "=" + lVal + " -> " + lSys );				
					//					else
							pOut.println( lVar + "=" + lVal );				
				}
			}		
		}	catch(Exception e ) { System.err.println( e );
				e.printStackTrace();
		}
	}
	// ------------------------------
	public	void debug(){
		System.out.println( "===================== BEGIN INI ==============================");
		writeIni( System.out );
		System.out.println( "===================== END   INI ==============================");
	}
	// ------------------------------
		public static ImageIcon ReadIcon( PPgIniFile pIni, String pSection, String pKey, String pDefault ){
				
				if( pSection == null || pKey == null )
						return null;
				
				String lStr  = pIni.get( pSection, pKey );
				
							System.out.println(  "****** " +pSection + ":" + pKey +"="+lStr +   "  (" + pDefault +")" );
				
				if( lStr == null )
						lStr = pDefault;
				
				if( lStr == null || lStr.length() == 0 )
						return null;
				
		
				
				int lPosSize = lStr.lastIndexOf( ':' );

				//		System.out.println( "possize:" + lPosSize );
				double lFlipH = 1;
				double lFlipV = 1;

				if( lPosSize == -1 ) {						
						lPosSize = lStr.lastIndexOf( '>' );
					if(lPosSize != -1 ) 
								lFlipH = -1;
				}
				if( lPosSize == -1 ) {						
						lPosSize = lStr.lastIndexOf( 'V' );
						if(lPosSize != -1 ) 
								lFlipV = -1;
				}
				if( lPosSize == -1 ) {						
						lPosSize = lStr.lastIndexOf( 'X' );
						if(lPosSize != -1 ) 
								lFlipH = -1;
								lFlipV = -1;
				}


				int lWidth  = 0;
				int lHeight = 0;
				double lScale = 1;

				if( lPosSize != -1 ) {

						
						String lSize = lStr.substring( lPosSize+1 );
						lStr = lStr.substring( 0, lPosSize );

						
						lPosSize = lSize.indexOf( 'x' );
						if( lPosSize == -1 )
								lPosSize = lSize.indexOf( 'X' );

						try{
								if( lPosSize == -1 ){
										lScale = Float.parseFloat(lSize );
								}
								else {
										lWidth  = Integer.parseInt( lSize.substring( 0, lPosSize ) );
										lHeight = Integer.parseInt( lSize.substring( lPosSize+1 ) );
								}
						}catch(NumberFormatException ex){
								lWidth = lHeight = 0;	
								lScale = 1;
						}				

						//						System.out.println( "ReadIcon Str:" + lStr + " Size:" + lSize  
						//																+ " lWidth:" + lWidth + " lHeight:" + lHeight + " lScale:" + lScale );

				}
				
				if( lStr == null || lStr.length() == 0 ){
						System.err.println( "PPIniFile.ImageIcone return null .size error for " 	+ pSection + ":" + pKey );
						return null;
				}
				
				
					
				ImageIcon lImageIcon = null;
				
				File lFile= new File( lStr );
				FileReader lFread  = null;
				try {
						if( lFile != null ){
								lFread = new FileReader(lFile  );
								lFread = null;
								lImageIcon  = new ImageIcon( lStr ); 
								System.out.println(  " icon from file:" +lStr);
						}
				}
				catch( Exception e){
						System.err.println( "SurvParam.ImageIcone Exception "+ e +" When reading ImageIcone :" 
																+ pSection + ":" + pKey +":"+lStr );
				}
				finally {
						if(	lFread != null )
								try{
										lFread.close();
								}
								catch( Exception e){
								}
				}
				
				if( lImageIcon == null ) {

						ClassLoader lLoader =  PPgIniFile.class.getClassLoader();
						URL lUrl = lLoader.getResource( lStr );
						//						System.out.println( "Url image:" + lUrl );
						if( lUrl == null ){
								System.err.println(  "SurvParam.ImageIcone : Warning no ressource found in jar for "
																		 +  pSection + ":" + pKey +":"  +lStr );
								return null;
						}

						//	System.out.println(  " icon from jar:" +lStr);
						
						lImageIcon  = new ImageIcon( lUrl ); 						
				}				
				if( lImageIcon != null ){	

						
						if( lWidth >0 && lHeight >0 ){																
								lImageIcon = new ImageIcon( lImageIcon.getImage().getScaledInstance( lWidth, lHeight, Image.SCALE_SMOOTH ));								
						}
						else /*
								if( lScale != 1 ){ //|| lFlipV != 1 || lFlipH != 1 ){
										lImageIcon = ImgUtils.CreateFlipScaleImage( lImageIcon, lScale*lFlipH, lScale*lFlipV);
										}
								 */
										if( lScale != 1 ){																
										lImageIcon = new ImageIcon( lImageIcon.getImage().getScaledInstance( (int)(lImageIcon.getIconWidth()*lScale), 
																																									 (int)(lImageIcon.getIconHeight()*lScale),
																																									 Image.SCALE_SMOOTH ));										
								}
								
						if( lFlipH != 1) lImageIcon = ImgUtils.CreateFlipHorImage(lImageIcon  );
				}


				return lImageIcon;
		}
};
// *********************************************

package com.phipo.PPg.PPgJ3d;import javax.media.opengl.*;import com.phipo.PPg.PPgMath.*;//*************************************************public class Float3{				float cVect[] = new float[3];		//------------------------------------------------		public Float3( float A, float B, float C ){				cVect[0] = A;				cVect[1] = B;				cVect[2] = C;		}		//------------------------------------------------		public Float3( double A, double B, double C ){				cVect[0] = (float)A;				cVect[1] = (float)B;				cVect[2] = (float)C;		}		//------------------------------------------------  	public Float3( Float3 pSrc ){				cVect[0] = pSrc.cVect[0];				cVect[1] = pSrc.cVect[1];				cVect[2] = pSrc.cVect[2];		}				//------------------------------------------------  	public Float3( Double3 pSrc ){				cVect[0] = (float)pSrc.cVect[0];				cVect[1] = (float)pSrc.cVect[1];				cVect[2] = (float)pSrc.cVect[2];		}				//------------------------------------------------		public Float3(){				cVect[0] = 0f;				cVect[1] = 0f;				cVect[2] = 0f;		}				//------------------------------------------------		public Float3(float pf){				cVect[0] = pf;				cVect[1] = pf;				cVect[2] = pf;		}				//------------------------------------------------		//------------------------------------------------		//------------------------------------------------		final public float[]  get() { return cVect;}		final public void set(float A, float B, float C) { 				cVect[0]= A; 				cVect[1]= B; 				cVect[2]= C; 		}		//------------------------------------------------		final public void set( Float3 pSrc ) { 				cVect[0]= pSrc.cVect[0]; 				cVect[1]= pSrc.cVect[1]; 				cVect[2]= pSrc.cVect[2]; 		}		//------------------------------------------------		final public void setX( float pVal ) { 				cVect[0]= pVal;		}		//------------------------------------------------		final public void setY( float pVal ) { 				cVect[1]= pVal;		}		//------------------------------------------------		final public void setZ( float pVal ) { 				cVect[2]= pVal;		}		//------------------------------------------------		public float x() { return cVect[0]; }		public float y() { return cVect[1]; }		public float z() { return cVect[2]; }		//------------------------------------------------		final public void add( Float3 pToAdd ) {				cVect[0]+= pToAdd.cVect[0];				cVect[1]+= pToAdd.cVect[1];				cVect[2]+= pToAdd.cVect[2];		}		//------------------------------------------------		final public void sub( Float3 pToAdd ) {				cVect[0]-= pToAdd.cVect[0];				cVect[1]-= pToAdd.cVect[1];				cVect[2]-= pToAdd.cVect[2];		}		//------------------------------------------------		final public void addDelta( Float3 pToAdd, float pDelta ) {				cVect[0]+= pToAdd.cVect[0]*pDelta;				cVect[1]+= pToAdd.cVect[1]*pDelta;				cVect[2]+= pToAdd.cVect[2]*pDelta;		}		//------------------------------------------------		final public void add(  float pVal ) {				cVect[0] += pVal;				cVect[1] += pVal;				cVect[2] += pVal;		}		//------------------------------------------------		final public void sub(  float pVal ) {				cVect[0] -= pVal;				cVect[1] -= pVal;				cVect[2] -= pVal;		}		//------------------------------------------------		final public void multiply(  float pVal ) {				cVect[0] *= pVal;				cVect[1] *= pVal; 				cVect[2] *= pVal;		}		//------------------------------------------------		final public void divide(  float pVal ) {				pVal = 1.0f/pVal;				cVect[0] *= pVal;				cVect[1] *= pVal;				cVect[2] *= pVal;		}		//------------------------------------------------		final public void inverse(){				cVect[0] = -cVect[0];				cVect[1] = -cVect[1];				cVect[2] = -cVect[2];			}		//------------------------------------------------				final public void limitVal( float pMax ) {				for( int i=0; i< 3 ; i++ ){						if( cVect[i] > pMax )								cVect[i] = pMax;						else								if( cVect[i] < -pMax )								cVect[i] = -pMax;				}		}		//------------------------------------------------				final public String toString() {				String lStr = new String();				for( int i=0; i< 3 ; i++ ){												lStr = lStr + Float.toString( cVect[i] ) + ' ';				}				return lStr;		}		//------------------------------------------------		//------------------------------------------------		//------------------------------------------------		final public double distanceSq( double pX, double pY, double pZ ){				double lDx = cVect[0] -pX;				double lDy = cVect[1] -pY;				double lDz = cVect[2] -pZ;				return (lDx*lDx + lDy*lDy + lDz*lDz );		}		//------------------------------------------------		final public double distanceSq( Float3 pF ){				double lDx = cVect[0] -pF.cVect[0];				double lDy = cVect[1] -pF.cVect[1] ;				double lDz = cVect[2] -pF.cVect[2];				return (lDx*lDx + lDy*lDy + lDz*lDz );		}		//------------------------------------------------		final public double distance( double pX, double pY, double pZ  ){				return Math.sqrt( distanceSq( pX, pY, pZ ) );		}		//------------------------------------------------		final public double distance( Float3 pF  ){				return Math.sqrt( distanceSq( pF ) );		}		//------------------------------------------------		//------------------------------------------------		//------------------------------------------------		static final public void Diff( Float3 A, Float3 B ,Float3 C ) {				C.cVect[0] = A.cVect[0] - B.cVect[0]; 				C.cVect[1] = A.cVect[1] - B.cVect[1]; 				C.cVect[2] = A.cVect[2] - B.cVect[2]; 				}		//------------------------------------------------		static final public Float3 Diff( Float3 A, Float3 B ) {				return  new Float3( A.cVect[0] - B.cVect[0], A.cVect[1] - B.cVect[1],  A.cVect[2] - B.cVect[2] ); 		}		//------------------------------------------------		static final public Float3 Add( Float3 A, Float3 B ) {				return  new Float3(A.cVect[0] + B.cVect[0], A.cVect[1] + B.cVect[1], A.cVect[2] + B.cVect[2] ); 		}			//------------------------------------------------		static public void Crossprod( Float3 A, Float3 B, Float3 Prod ) {				Prod.cVect[0] = A.cVect[1] * B.cVect[2] - B.cVect[1] * A.cVect[2];				Prod.cVect[1] = A.cVect[2] * B.cVect[0] - B.cVect[2] * A.cVect[0];				Prod.cVect[2] = A.cVect[0] * B.cVect[1] - B.cVect[0] * A.cVect[1];		}				//------------------------------------------------		static public Float3 Crossprod( Float3 A, Float3 B ) {				return new Float3( A.cVect[1] * B.cVect[2] - B.cVect[1] * A.cVect[2],																	A.cVect[2] * B.cVect[0] - B.cVect[2] * A.cVect[0],																	A.cVect[0] * B.cVect[1] - B.cVect[0] * A.cVect[1] );		}				//------------------------------------------------		static public Float3 Middle( Float3 A, Float3 B ) {								return  new Float3( (float)(A.cVect[0] + B.cVect[0])*0.5f,														(float)(A.cVect[1] + B.cVect[1])*0.5f,														(float)(A.cVect[2] + B.cVect[2])*0.5f);		}				//------------------------------------------------		static public Float3 Middle( Float3 A, Float3 B, Float3 C ) {								return new Float3( (float)((A.cVect[0] + B.cVect[0] + C.cVect[0])*0.33333333333333333),																	(float)((A.cVect[1] + B.cVect[1] + C.cVect[1])*0.33333333333333333),																	(float)((A.cVect[2] + B.cVect[2] + C.cVect[2])*0.33333333333333333));		}				//------------------------------------------------		static public Float3 Middle( Float3 A, Float3 B, Float3 C, Float3 D ) {								return new Float3( (float)((A.cVect[0] + B.cVect[0] + C.cVect[0] + D.cVect[0] )*0.25),													 (float)((A.cVect[1] + B.cVect[1] + C.cVect[1] + D.cVect[1] )*0.25),													 (float)((A.cVect[2] + B.cVect[2] + C.cVect[2] + D.cVect[2] )*0.25) );																		}				//------------------------------------------------		static public Float3 Middle( Float3 A, Float3 B, Float3 C, Float3 D, Float3 E ) {								return new Float3( (float)((A.cVect[0] + B.cVect[0] + C.cVect[0] + D.cVect[0]+ E.cVect[0] )*0.20),													 (float)((A.cVect[1] + B.cVect[1] + C.cVect[1] + D.cVect[1]+ E.cVect[1] )*0.20),													 (float)((A.cVect[2] + B.cVect[2] + C.cVect[2] + D.cVect[2]+ E.cVect[2] )*0.20) );																		}						//------------------------------------------------		static public Float3 Middle( Float3 pArray[] ) {										double X=0;				double Y=0;				double Z=0;								for( Float3 lVal : pArray ){						X += lVal.cVect[0];						Y += lVal.cVect[1];						Z += lVal.cVect[2];				}				double lFactor = 1.0/pArray.length;				X *= lFactor;				Y *= lFactor;				Z *= lFactor;														return  new Float3( X, Y, Z	);																		}				//------------------------------------------------		public void	normalize() {						double d = cVect[0]  * cVect[0] + cVect[1] * cVect[1] + cVect[2] * cVect[2];				if (d == 0.0f) {						d = 1.0f;				} else {						d = (float)(1.0 / Math.sqrt( d ));				}				//	System.out.println( "Float3 normalize x=" + cVect[0] + " y="+ cVect[1] +" z=" + cVect[2] 				//										+ " ->" + d  				//										);							d = 1.0f / d;				cVect[0] *= d;				cVect[1] *= d;				cVect[2] *= d;						//	System.out.println( "Float3 normalize x=" + cVect[0] + " y="+ cVect[1] +" z=" + cVect[2] + " d:" +d );		}		//------------------------------------------------		public void	normalize( float pVal) {								float d = (float) Math.sqrt( cVect[0]  * cVect[0] + cVect[1] * cVect[1] + cVect[2] * cVect[2]);				if (d == 0.0f) {						d = 1.0f;				}				//		System.out.println( "Float3 normalize x=" + cVect[0] + " y="+ cVect[1] +" z=" + cVect[2] 				//												+ " ->" + d   + " pVal:" + pVal );																		d = pVal / d;				cVect[0] *= d;				cVect[1] *= d;				cVect[2] *= d;								//	System.out.println( "Float3 normalize x=" + cVect[0] + " y="+ cVect[1] +" z=" + cVect[2] + " d:" +d );		}		//------------------------------------------------		//------------------------------------------------		//------------------------------------------------		final public Float3 random( PPgRandom pRand, float pMax ){				cVect[0] = pRand.nextFloat( pMax );				cVect[1] = pRand.nextFloat( pMax );				cVect[2] = pRand.nextFloat( pMax );				return this;		}		//------------------------------------------------		final public Float3 randomXY( PPgRandom pRand, float pMax ){				cVect[0] = pRand.nextFloat( pMax );				cVect[1] = pRand.nextFloat( pMax );				return this;		}		//------------------------------------------------		final public Float3 randomX( PPgRandom pRand, float pMax ){				cVect[0] = pRand.nextFloat( pMax );				return this;		}		//------------------------------------------------		final public Float3 randomX( PPgRandom pRand,  float pMin, float pMax ){				cVect[0] = pMin + pRand.nextFloat( pMax-pMin );				return this;		}		//------------------------------------------------		final public Float3 randomY( PPgRandom pRand, float pMax ){				cVect[1] = pRand.nextFloat( pMax );				return this;		}		//------------------------------------------------		final public Float3 randomY( PPgRandom pRand,  float pMin, float pMax ){				cVect[1] = pMin + pRand.nextFloat( pMax-pMin );				return this;		}		//------------------------------------------------		final public Float3 randomZ( PPgRandom pRand, float pMax ){				cVect[2] = pRand.nextFloat( pMax );				return this;		}		//------------------------------------------------		final public Float3 randomZ( PPgRandom pRand,  float pMin, float pMax ){				cVect[2] = pMin + pRand.nextFloat( pMax-pMin );				return this;		}		//------------------------------------------------		final public Float3 randomPositif( PPgRandom pRand, float pMin, float pMax ){				cVect[0] = pMin + pRand.nextFloat( pMax-pMin );				cVect[1] = pMin + pRand.nextFloat( pMax-pMin );				cVect[2] = pMin + pRand.nextFloat( pMax-pMin );				return this;		}		//------------------------------------------------		static public Float3 GetRandom( PPgRandom pRand, float pMax ){				Float3 lTmp = new Float3();				return lTmp.random( pRand, pMax  );		}		//------------------------------------------------		static public Float3 GetRandomXY( PPgRandom pRand, float pMax ){				Float3 lTmp = new Float3();				return lTmp.randomXY( pRand, pMax  );		}		//------------------------------------------------		static public Float3 GetRandomZ( PPgRandom pRand, float pMin, float pMax ){				Float3 lTmp = new Float3();				return lTmp.randomZ( pRand, pMin, pMax  );		}		//------------------------------------------------		static public Float3 GetRandomPositif( PPgRandom pRand, float pMin,  float pMax ){				Float3 lTmp = new Float3();				return lTmp.randomPositif( pRand, pMin, pMax  );		}		//------------------------------------------------		//------------------------------------------------		//------------------------------------------------		final public void glNormal(GL2 pGl) { pGl.glNormal3fv( cVect, 0 );}		final public void glVertex(GL2 pGl) { pGl.glVertex3fv( cVect, 0 );}		final public void glRotate(GL2 pGl)  { 				pGl.glRotatef( cVect[0], 1.0f, 0.0f, 0.0f );				pGl.glRotatef( cVect[1], 0.0f, 1.0f, 0.0f );				pGl.glRotatef( cVect[2], 0.0f, 0.0f, 1.0f );		}		final public void glScaled(GL2 pGl)     { pGl.glScaled( cVect[0], cVect[1], cVect[2] );}		final public void glTranslate(GL2 pGl)  { pGl.glTranslatef( cVect[0], cVect[1], cVect[2] );}		final public void glColor(GL2 pGl) { pGl.glColor3fv( cVect, 0 );}}//*************************************************
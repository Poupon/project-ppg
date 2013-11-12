package com.phipo.PPg.PPgJ3d;



import com.phipo.PPg.PPgMath.*;


import javax.media.opengl.GL2;
import javax.media.opengl.*;

//*************************************************

public interface ParticleInterface  {


		public boolean isDeleted();
		public void    setDeleted();

		public void    setTimeOfLife( double lTime);
		public boolean testTimeOfLife(double lTime);
		

		public void callFactoryDraw( ModelBase pSharedModel, 
																		 Transform3d pSharedTransform, 
																		 Aspect3d pSharedAspect, 
																		 GL2 pGl );
		public void callFactoryAct( float pTimeDelta );
		public void callFactoryClose();
};

//*************************************************

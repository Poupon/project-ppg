package com.phipo.PPg.PPgWin;





//***********************************
class PPgFrameChildMediatorData{

		public JMenuBar cMenuBar = null;
		public JToolBar cToolBar = null;
};

//***********************************
public class PPgFrameChildMediator implements ActionListener{

		PPgFrameChild              cCurrentFrameChild      = null;
		PPgFrameChildMediatorData  cCurrentFrameChildData = null;
		
		HashTable<String, PPgFrameChildMediatorData> cFrameChildTab = new HashTable<String,PPgFrameChild>();
		
		
		PPgFrameChildMediator sTheChildMediator = null;
		
		//------------------------------------------------
		PPgFrameChildMediator() {
				sTheChildMediator = this;
		}
		
		//------------------------------------------------
		public void addFrameChildData( String pKey, JMenuBar pMenuBar, JToolBar pToolBar ){

				// On cherche la data
				PPgFrameChildMediatorData lData = cFrameChildTab.get( pKey );
				if( lData == null ){
						lData = new PPgFrameChildMediatorData();
						cFrameChildTab.add( pKey, lData );
				}
				if( pMenuBar != null )
						lData.cMenuBar = pMenuBar;
				
				if( pMenuBar != null )
						lData.cMenuBar = pMenuBar;
				
		}
		//------------------------------------------------
		
		public void setFrameCurrentChild( PPgFrameChild pChild ){
				
				cCurrentFrameChild 	= pChild;				
				PPgFrameChildMediatorData cCurrentFrameChildData = cFrameChildTab.get( pChild.getChildType() );
		}
		//--------------------------------
		//--------------------------------
		//--------------------------------

		public void actionPerformed( ActionEvent pEv ){
				
				if( cCurrentFrameChild != null ){
						cCurrentFrameChild.actionPerformed( pEv );
				}
		}
		
}
//***********************************

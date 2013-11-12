package com.phipo.PPg.PPgSwarm;

import java.util.*;
import java.awt.*;


import com.phipo.PPg.PPgGame.*;
import com.phipo.PPg.PPgMath.*;
import com.phipo.PPg.PPgSFX.*;

//=====================================
public enum ShipType{

    		VoidShip( Color.white ),
        
    		FieldShip( Color.blue ),        
				LaserShip( Color.red ),     
				MissileShip( Color.orange ),
				SupporShip( Color.green ),
				LeaderShip( Color.yellow );		

		public final Color cColor;
		
		ShipType( Color pColor ) { cColor = pColor; }
};
//=====================================

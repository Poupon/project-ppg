
package com.phipo.PPg.PPgEdImg;




import java.awt.image.*;
import javax.swing.*;
import java.io.File;

import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;

import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;

import java.beans.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;


import com.phipo.PPg.PPgWin.*;
import com.phipo.PPg.PPgImg.*;





//*************************************************

class EdImgUtils{

		static public IconeImage GetStdSzImg( String pName ) {
				return ImgUtils.LoadImageFromFile( new File( PPgPref.sRessourcesPath +'/'+pName ), PPgPref.sToolsSz, PPgPref.sToolsSz , false, 1.0 );
}

//*************************************************

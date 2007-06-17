//GlyphViewManager.java
//COPYRIGHT (C) 2007 Timothee HUNTER
//Contact: sinensis-dictionary@gmail.com
//This file is part of Sinensis.
//
//    Sinensis is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 3 of the License, or
//    (at your option) any later version.
//
//    Sinensis is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Sinensis; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

package org.sinensis;

import org.sinensis.data.*;
import com.trolltech.qt.gui.*;
import java.util.*;

public class GlyphViewManager
{
// 	The view we manage
	private QGraphicsView view;
// 	The glyph which is printed
	private GlyphNode glyph;

// 	The scene which will be associated to the view
	private QGraphicsScene scene=new QGraphicsScene();
	
	private DataStore store;
	
// 	The pen we use to pain polygons
	private QPen polyPen=new QPen();
	
// 	The bursh used to paint polygons
//	private QBrush polyBrush=new QBrush();
//
	
// 	Dummy comment
	
	
//	private QFont displayFont=new QFont();
	
	
	List<MyPolygonItem> flatList=new LinkedList<MyPolygonItem>();
	
	public GlyphViewManager(QGraphicsView v, DataStore s)
	{
		view=v;
		view.setScene(scene);
		store=s;
	}
	
	public void loadGlyph(char c)
	{
		loadGlyph(store.charMap.get((int)c).representation);
	}
	
	public void loadGlyph(GlyphNode n)
	{
// 		We clean up the view
		for(QGraphicsItemInterface item:scene.items())
			scene.removeItem(item);
		
		glyph=n;
		
		if(glyph==null)
		{
			System.out.println("Could not find glyph id "+n);
			return;
			
		}
		
		polyPen.setColor(QColor.black);
		
		addGlyph(glyph);
		
		double dX=view.width()/glyph.boundingRect().width();
		double dY=view.height()/glyph.boundingRect().height();
		double ratio=0.8*(dX>dY?dY:dX);
		
		QMatrix matrix=new QMatrix();
		matrix.scale(ratio,ratio);
		view.matrix().reset();
		view.setMatrix(matrix,false);
// 		view.ensureVisible(glyph.boundingRect());

	}
	
	private void addGlyph(GlyphNode node)
	{
		for(GlyphNode n:node.children)
			addGlyph(n);
			
		if(!node.isAtomic())
			return;
		
		MyPolygonItem item=new MyPolygonItem(node);
		flatList.add(item);
		scene.addItem(item);
	}
	
	
	class MyPolygonItem extends QGraphicsPolygonItem
	{
		public final static int selected=1,normal=0;
		
		public int state=normal;
		
//		private GlyphNode _node;
		public MyPolygonItem(GlyphNode node)
		{
			super(node.polygon());
//			_node=node;
//			setAcceptedMouseButtons(new Qt.MouseButtons(Qt.MouseButton.LeftButton) );
		}
		
		public void mousePressEvent(QGraphicsSceneMouseEvent event) 
		{
// 			selectedText=((QGraphicsTextItem)data(0));
// 			String s=selectedText.toPlainText();
// // 			System.out.println(_node.id+":"+_node.name()+","+s);			
// 			selectedGlyph=_node;
			
// 			main.nameEdit.setText(_node.name());
		}
		
	}

}
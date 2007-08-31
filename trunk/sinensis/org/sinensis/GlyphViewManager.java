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
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import org.sinensis.geom.MyTransfoMatrix;

import java.util.*;

public class GlyphViewManager extends QObject
{
// 	The view we manage
	private QGraphicsView view;
// 	The glyph which is printed
	protected GlyphNode glyph;

// 	The scene which will be associated to the view
	protected QGraphicsScene scene=new QGraphicsScene();
	
	private DataStore store;
	
// 	The pen we use to pain polygons
	protected QPen polyPen=new QPen();
	
// 	The bursh used to paint polygons
	protected QBrush polyBrush=new QBrush();
	
//	We store the leaf items for fast access
	protected List<MyPolygonItem> flatList=new LinkedList<MyPolygonItem>();
	
//	The representation of the glyph as a tree of graphical elements
//	Hurray for Qt to providing this class
	QGraphicsItemGroup glyphGroup;
	
	public GlyphViewManager(QObject parent, QGraphicsView v, DataStore s)
	{
		super(parent);
		view=v;
		view.setScene(scene);
		store=s;
		view.setRenderHints(QPainter.RenderHint.Antialiasing);
	}
	
	public void loadGlyph(char c)
	{
		loadGlyph(store.charMap.get((int)c).representation);
	}
	
	final public void loadGlyph(GlyphNode n)
	{
// 		We clean up the view
		for(QGraphicsItemInterface item:scene.items())
			scene.removeItem(item);
		
//		We remove whatever data we might have kept
		flatList.clear();

		if(n==null)
		{
			System.out.println("Could not find glyph id "+n);
			return;
			
		}
		
		glyph=n;
		glyphGroup=buildGlyphTree(glyph);
		
		
		setupScene();
		
		
//		scene.addRect(glyph.boundingRect());
		
		scaleScene();
		
// 		view.ensureVisible(glyph.boundingRect());

	}
	
	private QMatrix scalingMatrix = new QMatrix();
	private QRectF viewRect=new QRectF();
	final int DEFAULT_MARGIN=20;
	
	protected void scaleScene()
	{
////		MyTransfoMatrix.setMatrixSquare(scalingMatrix, glyph.boundingRect(), scene.);
//		final double h=glyph.boundingRect().height();
//		final double w=glyph.boundingRect().width();
//		if(h>w)
//		{
//			viewRect.setTop(glyph.boundingRect().top())
//		}
//		
		view.ensureVisible(glyph.boundingRect(),1,1);
	}
	
	
	protected QGraphicsItemGroup buildGlyphTree(GlyphNode node)
	{
		QGraphicsItemGroup group=new QGraphicsItemGroup();
		
			
		if(node.isLeaf())
		{
			MyPolygonItem item=new MyPolygonItem(node);
//			TODO
//			group.addToGroup(item);
			flatList.add(item);
		}
		else 
			for(GlyphNode n:node.children())
				group.addToGroup(buildGlyphTree(n));
		
		return group;
	}
	
	
	public class MyPolygonItem extends QGraphicsPolygonItem
	{
		public final static int selected=1,normal=0;
		
		public int state=normal;
		private GlyphNode node;
		
		public GlyphNode glyphNode()
		{
			return node;
		}
		
		public MyPolygonItem(GlyphNode gnode)
		{
			super(gnode.polygon());
			node=gnode;
			setAcceptedMouseButtons(new Qt.MouseButtons(Qt.MouseButton.LeftButton) );
			setAcceptsHoverEvents(false);
		}
		
		public void mousePressEvent(QGraphicsSceneMouseEvent event) 
		{
System.out.println(event);
			handlePressEvent(this,event);
// 			selectedText=((QGraphicsTextItem)data(0));
// 			String s=selectedText.toPlainText();
// // 			System.out.println(_node.id+":"+_node.name()+","+s);			
// 			selectedGlyph=_node;
			
// 			main.nameEdit.setText(_node.name());
		}
		
		
		public boolean sceneEvent(QEvent event)
		{
			
			System.out.println(event);
			if(event instanceof QGraphicsSceneMouseEvent)
			{
				handlePressEvent(this,(QGraphicsSceneMouseEvent)event);
				return true;
			}
			else return super.sceneEvent(event);
		}

		
		public void mouseMoveEvent(QGraphicsSceneMouseEvent event)
		{
			
		}
		
		public void hoverEnterEvent(QGraphicsSceneHoverEvent event)
		{
			handleHoverEnterEvent(this,event);
		}
		
		public void hoverLeaveEvent(QGraphicsSceneHoverEvent event)
		{
			handleHoverLeaveEvent(this,event);
		}
	}
	
	
	
	public void setupScene()
	{
		polyPen.setColor(QColor.black);
		for(MyPolygonItem item:flatList)
			scene.addItem(item);
	}

	public void handlePressEvent(MyPolygonItem item, QGraphicsSceneMouseEvent event)
	{
		
	}

	public void handleHoverEnterEvent(MyPolygonItem item, QGraphicsSceneHoverEvent event)
	{
		
	}
	
	public void handleHoverEvent(MyPolygonItem item, QGraphicsSceneHoverEvent event)
	{
		
	}

	public void handleHoverLeaveEvent(MyPolygonItem item, QGraphicsSceneHoverEvent event)
	{
		
	}
	
	public QGraphicsScene scene()
	{
		return scene;
	}
	
}
 
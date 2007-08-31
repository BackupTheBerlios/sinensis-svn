//GlyphNode.java
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
package org.sinensis.data;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import java.util.*;
import java.awt.Polygon;
import java.nio.ByteBuffer;

import org.sinensis.editor.EditGlyphNode;


public class GlyphNode
{
//	We map all the glyph nodes here
	public static HashMap<Integer,GlyphNode> glyphsMap=new HashMap<Integer,GlyphNode>();
	
// 	A name of some sort (glyph name, unicode)
	protected String name="";
// 	An internal integer id as defined during the buildup of the database
//	1 special value:
//	-1 : unidentified node 
	protected int id=-1;
//	the id of a class representative of the same kind
// 	Basically, it means that the current node has the same shape that the node of following integer id
//	If classId==id, the node is a root node 
	protected int classId=-1;
//	The position of this glyph in the order to draw strokes
//	Set to -1 for the internal glyphs
	protected int orderIndex=-1;
// 	The contour representation of the stroke if the GlyphNode is a leaf
//	For performance reasons, I switch to a pure java-based polygon
//	But we keep a cache of Qt's polygon for performance
	protected Polygon polygon=null;
	protected QPolygonF qdataPoly=null;
	protected QRectF boundRect=null;
// 	The children if the node is an internal node
	protected List<GlyphNode> children=new LinkedList<GlyphNode>();
	
	public GlyphNode(int id, String name)
	{
		this.id=id;
		this.name=name;
		this.classId=id;
		if(!glyphsMap.containsKey(id))
			glyphsMap.put(id, this);
	}
	
	public GlyphNode(GlyphNode node)
	{
		this.id=node.id;
		this.name=node.name;
		this.classId=node.classId;
		this.polygon=node.polygon;
		this.qdataPoly=node.qdataPoly;
//		this.boundRect=node.boundRect;
		this.children=node.children;
		this.orderIndex=node.orderIndex;
	}

	public static GlyphNode createFromBuffer(ByteBuffer bb)
	{

		String name=readString(bb);
//		System.out.println(">>>"+node.name+"<<<");
		int id=bb.getInt();
//		System.out.println("ID=>"+node.id);
		GlyphNode node=new GlyphNode(id,name);
		node.classId=bb.getInt();
//		System.out.println("BMID=>"+node.bestMatchId);
		node.orderIndex=bb.getInt();
//		System.out.println("IDX=>"+node.orderIndex);
		
//		The number of children
		final int cSize=bb.getInt();
//		System.out.println("cSize=>"+cSize);
		
//		Note: the polygon is something we do not need to load at startup
//		The number of polygone vertices
		final int dSize=bb.getInt();
//		System.out.println("dSize=>"+dSize);
		
		int[] xArray=new int[dSize];
		int[] yArray=new int[dSize];
		
		for(int i=0;i<dSize;i++)
		{
			xArray[i]=bb.getInt();
			yArray[i]=bb.getInt();
		}
		
		if(dSize>0)
			node.polygon=new Polygon(xArray, yArray, dSize);
		

		for(int i=0;i<cSize;i++)
			node.children.add(createFromBuffer(bb));
		
		glyphsMap.put(node.id(),node);

		return node;
	}
	
	private final static int MAX_STR_BUFFER=40;
	private static char[] buffer=new char[MAX_STR_BUFFER]; 
	protected static String readString(ByteBuffer bb)
	{
		final int sLength=bb.getInt();
//System.out.println(sLength);		
		for(int i=0;i<sLength;i++)
		{
			buffer[i]=bb.getChar();
//			System.out.print(buffer[i]);
		}
			
		return new String(buffer,0,sLength);
	}

//	We load whatever we can from a byte array
	public static List<GlyphNode> loadAll(byte[] ba)
	{
		ByteBuffer bb=ByteBuffer.wrap(ba);
		List<GlyphNode> res=new LinkedList<GlyphNode>();
		while(bb.hasRemaining())
			res.add(createFromBuffer(bb));
		return res;
	}
	
	public QPolygonF polygon()
	{
		if(polygon==null)
			return null;
		if(qdataPoly==null)
		{
			qdataPoly=new QPolygonF();
			for(int i=0;i<polygon.npoints;i++)
				qdataPoly.add(polygon.xpoints[i], polygon.ypoints[i]);
		}
		return qdataPoly;
	}
	
	public QRectF boundingRect()
	{
		if(boundRect==null)
		{
			
			if(polygon()!=null)
			{
				boundRect=polygon().boundingRect();
//				System.out.println("XXX "+boundRect);
			}
				
			else
			{
				if(children.isEmpty())
				{
					System.out.println(this+"Empty node!");
					return null;
				}
				else
				{

					boundRect=children.get(0).boundingRect();
//					System.out.println("XXXBBBCCC"+boundRect);
					for(GlyphNode node:children)
						unite(boundRect,node.boundingRect());
//						boundRect=boundRect.united(node.boundingRect());
					
				}
			}
		}
		return boundRect;
	}
	
	
	public static void unite(QRectF r1, QRectF r2)
	{
		if(r1==null)
		{
			System.out.println("Warning : r1 null");
			return;
		}
		if(r2==null)
		{
			System.out.println("Warning : r2 null");
			return;
		}
		if(r2.top()<r1.top())
			r1.setTop(r2.top());
		if(r2.left()<r1.left())
			r1.setLeft(r2.left());
		if(r2.bottom()>r1.bottom())
			r1.setBottom(r2.bottom());
		if(r2.right()>r1.right())
			r1.setRight(r2.right());
	}

	
	public void draw(QPainter painter, QMatrix matrix)
	{
		if(polygon()!=null)
			painter.drawPolygon(matrix.map(polygon()));
		for(GlyphNode node : children)
			node.draw(painter,matrix);
	}
	
	final public int id()
	{
		return id;
	}
	
	final public int classId()
	{
		return classId;
	}
	
	public int hashCode()
	{
		if(id==-1)
			throw new Error();
		return id;
	}
	
	public String name()
	{
		if(name!=null&&!name.equals(""))
			return name;
		if(classId()==id())
			return name;
		if(parentClass()==null)
			return "[dangling node]";
		return parentClass().name();
	}
	
	public Iterable<GlyphNode> children()
	{
		return this.children;
	}
	
	public GlyphNode parentClass()
	{
		if(classId()==id())
			return this;
		return glyphsMap.get(classId());
	}
	
	public int rootId()
	{
		if(classId()==id())
			return id();
		else return parentClass().classId();
	}
	
	public GlyphNode rootClass()
	{
		if(classId()==id())
			return this;
		if(parentClass()==null)
			return null;
		return parentClass().rootClass();
	}

	public boolean isLeaf()
	{
		return polygon!=null;
	}
	
	public String toString()
	{
		return "glyph "+id+"\t"+name()+"\t"+(parentClass()!=null?parentClass().name():"");
	}
}
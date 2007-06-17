package org.sinensis.data;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import java.util.*;


public class GlyphNode
{
	protected static HashMap<Integer,GlyphNode> map=new HashMap<Integer,GlyphNode>();
	
// 	An internal integer id as defined during the buildup of the database
	public int id=-1;
// 	The id of the node "very close" to this one
// 	Basically, it means that the current node has the same shape that the node of following integer id
	public int bestMatchId=-1;
// 	The contour representation of the stroke if the GlyphNode is a leaf
	protected QPolygonF dataPoly=null;
// 	The children if the node is an internal node
	public List<GlyphNode> children=new Vector<GlyphNode>();;
// 	A name of some sort (glyph name, unicode)
	public String name="";
	
	public GlyphNode()
	{
	}


	public GlyphNode(QDataStream stream)
	{
		loadFromDataStream(stream);
	}

	protected void loadFromDataStream(QDataStream stream)
	{
		name=stream.readString();
		id=stream.readInt();
		bestMatchId=stream.readInt();
		int dSize=stream.readInt();
		int cSize=stream.readInt();
		
		dataPoly=new QPolygonF();
		for(int i=0;i<dSize;i++)
			dataPoly.append(new QPointF(stream.readInt(),stream.readInt()));
			
		map.put(this.id,this);
		
		for(int i=0;i<cSize;i++)
			children.add(new GlyphNode(stream));
	}
	
	public QPolygonF polygon()
	{
		return dataPoly;
	}
	
	public int newStrokesNbr()
	{
		int res=0;
		if(bestMatchId==-2)
			res++;
		for(GlyphNode n:children)
			res+=n.newStrokesNbr();
		return res;
	}
	
	public QRectF boundingRect()
	{
		QRectF rect=new QRectF();
		if(dataPoly!=null)
			rect=rect.united(dataPoly.boundingRect());
		for(GlyphNode node:children)
			rect=rect.united(node.boundingRect());
		return rect;
	}
	
	public int hashCode()
	{
		if(id==-1)
			throw new Error();
		return id;
	}
	
	public String name()
	{
		if(!name.equals(""))
			return name;
		if(map.get(bestMatchId)==null)
			throw new Error();
		return map.get(bestMatchId).name();
	}



	public boolean isAtomic()
	{
		return !dataPoly.isEmpty();
	}

}
//RadicalDelegate.java
//COPYRIGHT (C) 2007 Timothee HUNTER - All rights reserved.
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

class RadicalDelegate extends QItemDelegate
{
	private static QSettings settings=new QSettings();
	
	private static int fontSize;
	private static QFont font;
	private static QFont fontSmall;
	private static QFontMetrics metrics;
	private static QFontMetrics metricsSmall;
	
	public static void loadFont()
	{
		fontSize=(Integer)settings.value("GUI/radicalFontSize",30);
		font=(QFont)settings.value("GUI/radicalFont",new QFont("Arial Unicode MS", fontSize));
		fontSmall=new QFont(font);
		fontSmall.setPointSizeF(fontSize/2);
		metrics=new QFontMetrics(font);
		metricsSmall=new QFontMetrics(font);
	}
	
	public RadicalDelegate(QWidget parent)
	{
		super(parent);
		if(font==null)
			loadFont();
	}
	
	public void paint(QPainter painter, QStyleOptionViewItem option, QModelIndex index)
	{
		Object data = index.data();

		if (data != null && data instanceof CCharacter) 
		{
				
			painter.save();
			painter.setFont(font);
			painter.drawText(option.rect(),0, ((CCharacter)data).get("ZH"));
			int u=((CCharacter)data).getInt("SelectStroke");
			if(u>0)
			{
				final int dx=metricsSmall.width(""+u)+2;
				final int dy=metricsSmall.width("5")+2;
				QRect rect=new QRect(option.rect().x(),option.rect().y(),dx,dy);
				painter.setFont(fontSmall);
				painter.setBrush(new QColor(0, 0, 255, 127));
				painter.drawRect(rect);
				painter.drawText(rect,0, u+"");
			}
			painter.restore();
		} else
			super.paint(painter, option, index);
        }
        
	public QSize sizeHint(QStyleOptionViewItem option, QModelIndex index)
	{
		return sizeHint();
    }

	public static QSize sizeHint()
	{
		return new QSize(metrics.width("XX"),metrics.height());
    }
}

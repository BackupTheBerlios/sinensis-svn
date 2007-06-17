package org.sinensis;

import org.sinensis.data.*;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

class RadicalDelegate extends QItemDelegate
{
	private static QFont font=new QFont("Arial Unicode MS", 20);
	private static QFont fontSmall=new QFont("Arial Unicode MS", 10);
	private static QFontMetrics metrics=new QFontMetrics(font);
	private static QFontMetrics metricsSmall=new QFontMetrics(font);
	
	public static void setFont(QFont f)
	{
		font=f;
		metrics=new QFontMetrics(font);
	}
	
	public RadicalDelegate(QWidget parent)
	{
		super(parent);
// 		installEventFilter(new MyEventFilter());
	}
	
	public void paint(QPainter painter, QStyleOptionViewItem option, QModelIndex index)
	{
		Object data = index.data();

		if (data != null && data instanceof CCharacter) 
		{
// 			if (option.state().isSet(QStyle.StateFlag.State_Active)) 
// 			System.out.println(";");
// 				painter.fillRect(option.rect(), option.palette().highlight());
				
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

package tema2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JPanel;


public class SimulationPanel extends JPanel{
	
	public void refresh()
	{
		repaint();
	}
	@Override
	protected void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		int xCoord = 0;

		for(int i=0; i<getServerNumber(); i++)
		{
			int yCoord = 0;
			paintQue(graph, xCoord, yCoord,i);
			Iterator it = getServerIterator(i);
			while(it.hasNext())
			{
				yCoord += 25;
				int id = getId(it.next()) + 1;
				paintClient(graph, xCoord + 20, yCoord,  id);
			}
		
			xCoord += 100;
		}
	}
	
	private int getServerNumber()
	{
		Server[] serv = Producer.getServeri();
		return serv.length;
	}
	
	private Iterator getServerIterator(int i)
	{
		Server[] serv = Producer.getServeri();
		LinkedBlockingQueue Q = serv[i].getLinkedQ();
		return Q.iterator();
	}
	
	private int getId(Object it)
	{
		Client c = ((Client)(it));
		return c.getID();
	}
	
	private void paintClient(Graphics graph, int xCoord, int yCoord,  int clientId){
		graph.setColor(Color.green);
		graph.fillRect(yCoord+30, xCoord+20, 20, 20);
		graph.setColor(Color.black);
		graph.drawString( Integer.toString(clientId) , yCoord+35, xCoord+30);
		this.repaint();
	}
	
	
	private void paintQue(Graphics graph, int xCoord, int yCoord,int number){
		graph.setColor(Color.CYAN);
		graph.fillRect(yCoord+10,xCoord+14, 80, 20);
		graph.setColor(Color.black);
		graph.drawString("Server "+Integer.toString(number+1), yCoord+20, xCoord+30);
		this.repaint();
	}
	
}

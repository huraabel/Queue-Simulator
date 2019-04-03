package tema2;

import javax.swing.JFrame;

public class SimulationManager extends Thread{

	public JFrame simulareFrame = new JFrame();
    public static SimulationPanel simulare = new SimulationPanel() ;
	private static int time =0;
	private static int simT =0;
    
	public SimulationManager(int simT)
	{
		this.simT = simT;
		time=0;
		simulareFrame.add(simulare);
		simulareFrame.setBounds(200, 200, 600, 600);
		simulareFrame.setVisible(true);
	}
	
	public void run(){
		
		
		
		for(int i=0; i<simT; i++)
		{
			simulare.refresh();
			try {
				time = i;
				View.setTimeLabel(i);
				sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		}
		try {
			
			
			sleep(0);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		simulareFrame.setVisible(false);
		Producer.setTimpDeAsteptareZero();
		View.setTimeLabel(0);
	}
	
	public static int getTime() {
		return time;
	}
	
	public static int getSimulationTime() {
		return simT;
	}
	
	
}

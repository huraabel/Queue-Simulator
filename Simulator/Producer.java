package tema2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Producer extends Thread {

	private int maxAT, minAT, maxST, minST, nrQ;
	private int nrClienti;

	private int nrClientiVeniti = 0;
	private int peakHour = 0;
	private int timpServerGol = 0;

	private static int timpDeAsteptare = 0;
	private int timpMediuService = 0;

	private PriorityQueue<Client> clienti = new PriorityQueue<Client>(new Comparator() {

		@Override
		public int compare(Object arg0, Object arg1) {

			return ((Client) arg0).getTimpDeSosire() - ((Client) arg1).getTimpDeSosire();
		}

	});

	private static Server[] serveri;
	//public static int runserv=1;
	public static Server[] getServeri() {
		return serveri.clone();
	}

	public Producer(int minAT, int maxAT, int minST, int maxST, int nrQ,int nrClienti) {
		this.maxAT = maxAT;
		this.minAT = minAT;
		this.maxST = maxST;
		this.minST = minST;
		this.nrQ = nrQ;
		this.nrClienti = nrClienti;//SimulationManager.getSimulationTime() / 4;
		serveri = new Server[nrQ];
		for (int i = 0; i < nrQ; i++) {
			serveri[i] = new Server();
		}
	}

	public void run() {

		try {
			startProducing();
			for (int i = 0; i < SimulationManager.getSimulationTime(); i++) {
				for (Client client : clienti) {
					if (client.getTimpDeSosire() == i) {
						int optim = getSmallestQue();
						serveri[optim].enq(client);
						timpMediuService = timpMediuService + client.getTimpDeService();
						View.appendLogArea("Entered:" + client + "\n");
					}
				}
				peakHour(i);
				sleep(1000);
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		timpServerGol();
		stopProducing();
	}

	private void startProducing() throws InterruptedException {
		View.resetLogArea();
		View.appendLogArea("START SIM\n");
		View.disableSim();
		for (int i = 0; i < nrClienti; i++) {
			int timpDeSosire = (int) (Math.random() * (maxAT - minAT) + minAT);
			int timpDeService = (int) (Math.random() * (maxST - minST) + minST);
			Client client = new Client(i, timpDeSosire, timpDeService);
			clienti.add(client);
		}

		for (Server s : serveri) {
			s.start();
		}

	}

	private void peakHour(int secundaI) {
		int contor = 0;
		for (Server s : serveri) {
			contor = contor + s.getSizeQue();
		}
		if (contor > nrClientiVeniti) {
			nrClientiVeniti = contor;
			peakHour = secundaI;
		}
	}

	private synchronized static int getSmallestQue() {
		int min = serveri[0].getSizeQue();
		int ret = 0;

		for (int i = 0; i < serveri.length; i++) {
			if (min > serveri[i].getSizeQue()) {
				min = serveri[i].getSizeQue();
				ret = i;
			}
		}
		return ret;
	}

	public static void adaugaTimpDeAsteptare(int x) {
		timpDeAsteptare += x;
	}
	
	public static void setTimpDeAsteptareZero()
	{
		timpDeAsteptare = 0;
	}

	private void timpServerGol() {
		for (Server s : serveri) {
			timpServerGol = timpServerGol + s.getTimpCoadaGoala();
		}
	}

	private synchronized void stopProducing() {
		//runserv =0;
		//for(Server s : serveri)
		//{
			//if(runserv==0)
			//	s.stop();
			//s.interrupt();
	//	}
		
		View.appendLogArea("END SIM\n");
		View.enableSim();
		String endOfSimulation = "Timp de simulare: " + SimulationManager.getSimulationTime() + " secunde"
				+ "\nTimpul mediu de asteptare: " + ((float) timpDeAsteptare / nrClienti) + 
				 "\nTimpul mediu de service: " + ((float) timpMediuService / nrClienti) + 
				 "\nTimpul maxim de server gol: " + ((float) timpServerGol / serveri.length) + 
				 "\nOra de varf: " + peakHour;

		JOptionPane.showMessageDialog(null, endOfSimulation);
		System.out.println(endOfSimulation);
		System.out.println("END SIM");

	}

}

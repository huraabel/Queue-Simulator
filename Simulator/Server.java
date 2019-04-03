package tema2;

import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread {

	private int serverID;
	private static int count;
	private LinkedBlockingQueue<Client> queue = new LinkedBlockingQueue<Client>();
	private int timpCoadaGoala;

	public Server() {
		serverID = count;
		count++;
	}

	public void run() {
		
		
		while(SimulationManager.getTime() < SimulationManager.getSimulationTime())
		{
			Client client;
			try {
				if (getSizeQue() <= 0) {

					timpCoadaGoala++;

					sleep(1000);
				} else {
					client = queue.element();
					sleep(client.getTimpDeService() * 1000);
					System.out.println(client + " at QUE:" + this);
					client.setTimpDePlecare(SimulationManager.getTime());
					View.appendLogArea("SERVIT:" + client + " at QUE:" + this + "\n");

					Producer.adaugaTimpDeAsteptare(
							SimulationManager.getTime() - client.getTimpDeSosire() - client.getTimpDeService());
					deq();
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public int getTimpCoadaGoala() {
		return timpCoadaGoala;
	}

	public void enq(Client client) throws InterruptedException {

		queue.put(client);
	}

	public Client deq() throws InterruptedException {
		return queue.take();
	}

	public int getSizeQue() {
		return queue.size();
	}

	public static void setCountZero() {
		count = 0;
	}

	public String toString() {
		return this.serverID + "";
	}

	public LinkedBlockingQueue<Client> getLinkedQ() {
		return queue;
	}

}

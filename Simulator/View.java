package tema2;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class View extends JFrame {

	private static JLabel time = new JLabel("TIME");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label1 = new JLabel("Min arrival time");
	private JLabel label2 = new JLabel("Max arrival time");
	private JLabel label3 = new JLabel("Nr of queues");
	private JLabel label4 = new JLabel("Simulation interval");
	private JTextField minArrivalTime;
	private JTextField maxArrivalTime;
	private JTextField nrQues;
	private JTextField simInterval;
	private  static JButton simB = new JButton("Simulate");
	private JButton resetB = new JButton("Clear");
	private static TextArea logArea = new TextArea();
	private final JTextField minServiceTime = new JTextField();
	private final JTextField maxServiceTime = new JTextField();
	private final JLabel label5 = new JLabel("Min service time");
	private final JLabel label6 = new JLabel("Max service time");
	private JLabel label7 = new JLabel("Nr Clienti");
	private JTextField nrClienti = new JTextField();
	
	public View() {
		super("Queue Simulator 2");
		maxServiceTime.setBounds(154, 175, 86, 20);
		maxServiceTime.setColumns(10);
		minServiceTime.setBounds(154, 131, 86, 20);
		minServiceTime.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label1.setBounds(28, 41, 103, 14);
		contentPane.add(label1);

		label2.setBounds(28, 88, 103, 14);
		contentPane.add(label2);

		minArrivalTime = new JTextField();
		minArrivalTime.setBounds(154, 38, 86, 20);
		contentPane.add(minArrivalTime);
		minArrivalTime.setColumns(10);

		maxArrivalTime = new JTextField();
		maxArrivalTime.setBounds(154, 85, 86, 20);
		contentPane.add(maxArrivalTime);
		maxArrivalTime.setColumns(10);

		label3.setBounds(28, 222, 103, 14);
		contentPane.add(label3);

		nrQues = new JTextField();
		nrQues.setBounds(154, 219, 86, 20);
		contentPane.add(nrQues);
		nrQues.setColumns(10);

		simInterval = new JTextField();
		simInterval.setBounds(154, 262, 86, 20);
		contentPane.add(simInterval);
		simInterval.setColumns(10);

		label4.setBounds(28, 265, 116, 14);
		contentPane.add(label4);

		simB.setBounds(154, 343, 89, 23);
		simB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					checkFields();
					checkLogic();
					int maxAT = Integer.parseInt(getMaxArrivalTime());
					int minAT = Integer.parseInt(getMinArrivalTime());
					int maxST = Integer.parseInt(getMaxServiceTime());
					int minST = Integer.parseInt(getMinServiceTime());
					int simT  = Integer.parseInt(getSimInterval());
					int nrQ   = Integer.parseInt(getNrQues());
					int nrClienti =  Integer.parseInt(getNrClienti());
					
					SimulationManager  time= new SimulationManager(simT);
					time.start();
					Producer proc = new Producer(minAT,maxAT,minST,maxST,nrQ,nrClienti);
					proc.start();
					
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			
			public void checkFields() throws Exception
			{
				if(getMaxArrivalTime().isEmpty() || getMinArrivalTime().isEmpty()  ||
				  getMinServiceTime().isEmpty()  || getMaxServiceTime().isEmpty() ||
				  getNrQues().isEmpty() 		  ||getSimInterval().isEmpty() || getNrClienti().isEmpty()
						)
				{
					 JOptionPane.showMessageDialog(new JFrame(), "Completati campurile!", "ERROR", JOptionPane.ERROR_MESSAGE);
					 throw new Exception();
				}
			}
			
			public void checkLogic() throws Exception{
				
				if(Integer.parseInt(getNrQues()) <1 || (Integer.parseInt(getMaxArrivalTime()) < Integer.parseInt(getMinArrivalTime()) ) ||
					(Integer.parseInt(getMaxServiceTime()) < Integer.parseInt(getMinServiceTime())) || 
					(Integer.parseInt(getSimInterval()) < 0) ||  (  Integer.parseInt(getNrClienti() )<0 ) )
						
					
				{
					JOptionPane.showMessageDialog(new JFrame(), "Completati campurile corect", "ERROR", JOptionPane.ERROR_MESSAGE);
					 throw new Exception();
				}
			}
			
			

		});
		contentPane.add(simB);
		time.setBounds(115, 375, 46, 14);
		contentPane.add(time);
		
		resetB.setBounds(28, 343, 89, 23);
		resetB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				resetLogArea();
				Server.setCountZero();
			}
			
			

		});
		contentPane.add(resetB);

		contentPane.add(minServiceTime);

		contentPane.add(maxServiceTime);
		logArea.setEditable(false);

		logArea.setBounds(276, 10, 415, 379);
		contentPane.add(logArea);
		label5.setBounds(28, 134, 103, 14);

		contentPane.add(label5);
		label6.setBounds(28, 178, 103, 14);

		contentPane.add(label6);
		label7.setBounds(28, 310, 103, 14);
		contentPane.add(label7);
		nrClienti.setBounds(154, 310, 86, 20);
		contentPane.add(nrClienti);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		new View();
		

	}
	
	public static void setTimeLabel(int t)
	{
		time.setText(String.valueOf(t));
	}

	public void setMinArrivalTime(String s) {
		minArrivalTime.setText(s);
	}

	public void setMaxArrivalTime(String s) {
		maxArrivalTime.setText(s);
	}

	public void setNrQues(String s) {
		nrQues.setText(s);
	}

	public void setSimInterval(String s) {
		simInterval.setText(s);
	}

	public void setMinServiceTime(String s) {
		minServiceTime.setText(s);
	}

	public void setMaxServiceTime(String s) {
		maxServiceTime.setText(s);
	}

	public String getMinArrivalTime() {
		return minArrivalTime.getText();
	}

	public String getMaxArrivalTime() {
		return maxArrivalTime.getText();
	}

	public String getNrQues() {
		return nrQues.getText();
	}

	public String getSimInterval() {
		return simInterval.getText();
	}

	public String getMinServiceTime() {
		return minServiceTime.getText();
	}

	public String getMaxServiceTime() {
		return maxServiceTime.getText();
	}
	
	
	public String getNrClienti() {
		return nrClienti.getText();
	}

	public void addSimListener(ActionListener ev) {
		simB.addActionListener(ev);
	}

	public void addResetListener(ActionListener ev) {
		resetB.addActionListener(ev);
	}

	public static void appendLogArea(String s) {
		logArea.append(s);
	}

	public static void resetLogArea() {
		logArea.setText("");
	}
	
	public static void disableSim() {
		simB.setEnabled(false);
	}
	
	public static void enableSim() {
		simB.setEnabled(true);
	}
}

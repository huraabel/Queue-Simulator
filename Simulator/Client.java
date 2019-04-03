package tema2;

public class Client {
	
	private int ID;
	private int timpDeSosire ;
	private int timpDeService;
	private int timpDePlecare; // to be set
	
	public Client(int id,int tSos, int tServ)
	{
		this.ID = id;
		this.timpDeSosire = tSos;
		this.timpDeService =tServ;
	}
	
	public int getID()
	{
		return this.ID;
	}
	public void setID(int id)
	{
		this.ID = id;
	}
	
	
	public int getTimpDeSosire()
	{
		return this.timpDeSosire;
	}
	public void setTimpDeSosire(int t)
	{
		this.timpDeSosire = t;
	}
	
	
	public int getTimpDeService()
	{
		return this.timpDeService;
	}
	public void setTimpDeService(int t)
	{
		this.timpDeService = t;
	}
	
	
	public int getTimpDePlecare()
	{
		return this.timpDePlecare;
	}
	public void setTimpDePlecare(int t)
	{
		this.timpDePlecare = t;
	}
	
	
	public String toString() {
		if( this.timpDePlecare == 0)
			return "client ID: " + (this.ID) + "  [sos:" + (this.timpDeSosire) + "  serv:" +
			this.timpDeService + "  plec:" +  "? ]";
		else
			return "client ID: " + (this.ID) + "  [sos:" + (this.timpDeSosire) + "  serv:" +
			this.timpDeService + "  plec:" + this.timpDePlecare + "]";
	}
	
	
}

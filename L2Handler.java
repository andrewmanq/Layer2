/**
* L2Handler utilizes layer1 to send layer 2 frames across the simulated local network.
*
* 
*
* @author	Andrew Quist, Sambridhi Acharya
*/

public class L2Handler {

	BitHandler handler;
	Layer2Listener layer2listener;
	int macAddr;

	public L2Handler(int mac){
		handler = new BitHandler();
		macAddr = mac;
	}

	public L2Handler(String host, int port, int mac){
		handler = new BitHandler(host, port);
		handler.setListener(this);

		macAddr = mac; 
	}

	public void send(L2Frame f){
		String finalString = f.toString();

		try{

		while(true){
			
			Thread.sleep(handler.HALFPERIOD);

			if(handler.isSilent()){
				break;
			}
		}

		handler.broadcast(finalString);

		}catch(Exception e){
			System.out.println("something went wrong while broadcasting :(");
		}
	}

	public String toString(){
		return Integer.toString(macAddr);
	}

	public int getMAC(){
		return macAddr;
	}
}
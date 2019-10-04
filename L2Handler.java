/**
* L2Handler utilizes layer1 to send layer 2 frames across the simulated local network.
*
* @author	Andrew Quist, Sambridhi Acharya
*/

public class L2Handler implements BitListener {

	BitHandler handler;
	Layer2Listener listener;
	int macAddr;

	/**
	* Creates a new l2handler with the designated mac address and default layer 1 port information
	*/
	public L2Handler(int mac){
		handler = new BitHandler();
		handler.setListener(this);
		macAddr = mac;
	}

	/**
	* creates a new l2handler with designated layer 1 port information (for cross-network use)
	*/
	public L2Handler(String host, int port, int mac){
		handler = new BitHandler(host, port);
		handler.setListener(this);

		macAddr = mac; 
	}

	/**
	* Sends the frame information over layer 1
	* @param L2Frame f is the frame being sent
	*/
	public void send(L2Frame f){
		String finalString = f.toString();

		try{

		while(true){
			/**
			 * make sure the wire is not in use
			 */
			Thread.sleep(handler.HALFPERIOD);

			if(handler.isSilent()){
				break;
			}
		}

		/**
		 * sends the frame bits
		 */
		handler.broadcast(finalString);

		}catch(Exception e){
			System.out.println("something went wrong while broadcasting :(");
		}
	}

	public void setListener(Layer2Listener l) {
		listener = l;
	}

	public Layer2Listener getListener(){
		return listener;
	}

	/**
	* Called when bits are sent over layer 1. Tells the current listener what frame was sent
	*/
	public void bitsReceived(BitHandler h, String bits) {

		try{
			L2Frame f = new L2Frame(bits);

			/**
			* checks to see if destination is for my addresss (or being broadcasted) and drops the frame if it's not for me
			*/
			if(f.getDst() == macAddr || f.getDst() == 15){
				listener.frameRecieved(this, f);
			}else{
				//drop frame
			}
		}catch(Exception e){
			System.out.print("there was an error recieving this frame.");
		}
	}

	/**
	* returns the current MAC address
	*/
	public String toString(){
		return Integer.toString(macAddr);
	}

	public int getMAC(){
		return macAddr;
	}
}
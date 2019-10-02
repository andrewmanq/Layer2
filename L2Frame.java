
/**
* L2Frame is a class that utilizes the lightSystem classes (a simulation of layer-1 functionality) to send frames of data.
*
* the contents of these frames are as follows:
*
* | destination address - 4 bits |  src address - 4 bits | type - 2 bits | vlan id - 2 bits | ...
*  ... payload size - 8 bits | checksum - 1 bit | payload data (variable) |
*
* @author	Andrew Quist, Sambridhi Acharya
*/


public class L2Frame {

	//source address
	private int src;
	//destination address
	private int dst;
	//type of message
	private int type;
	//vlan domain
	private int vlanID;
	//length of the payload
	private int length;
	//payload (a bit string)
	private String payload;

	//This is the default broadcast address that is defined in our class protocol
	public static int BCAST_ADDR = 1111;

	/**
	* Creates frame and sets default variables determined above.
	*/
	public L2Frame(int destination, int source, int msgtype, int vID, String contents ){ //String[] args) {

		dst = destination;
		src = source;
		
		type = msgtype;

		vlanID = vID;
		payload = contents;

		length = contents.length();
		

		//TESTS
		//System.out.print(computeErrorCheck("111"));
		//System.out.print(computeErrorCheck("110"));
	}


	/**
	* takes all frame data and converts it to binary sources.
	* compiles all bits into a frame and returns the bitstring.
	*/
	public String toString(){
		String answer = "0";

		answer += toBinary(dst, 4);
		answer += toBinary(src, 4);
		answer += toBinary(type, 2);
		answer += toBinary(vlanID, 2);
		answer += toBinary(length, 8);
		answer += computeErrorCheck(payload);
		answer += payload;

		return answer;
	}

	/**
	* Converts any int into a useable bitstring
	*
	* @param value the integer in question
	* @param length the amount of digits in the bitstring
	*/
	private String toBinary(int value, int length){

		String answer = "";

		//uses java functions to convert integer to 1s and 0s
		answer += Integer.toBinaryString(value);

		//concatenates zeroes for the rest of the digits
		if(answer.length() < length){
			int difference = length - answer.length();

			for(int i = 0; i < difference; i++){
				answer = "0" + answer;
			}
		}else if (answer.length() > length){
			answer = answer.substring(answer.length() - length);
		}

		return answer;
	}

	/**
	* computeErrorCheck takes in a bitstring and counts the number of 1's
	*
	* if 1's are odd, returns "1"
	* if 1's are even, returns "0"
	*
	*/
	private String computeErrorCheck(String input){

		int count = 0;

		for(char c : input.toCharArray()){
			if(c == '1'){
				count ++;
			}
		}

		if(count % 2 == 0){
			return "0";
		}else{
			return "1";
		}

	}
	

	//GETTERS

	public int getSrc(){
		return src;
	}

	public int getDst(){
		return dst;
	}

	public int getType(){
		return type;
	}
	
	public int getVlanID(){
		return vlanID;
	}

	public int getLength(){
		return length;
	}

	public String getPayload(){
		return payload;
	}

}
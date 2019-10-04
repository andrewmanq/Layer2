
/**
* Test runs 2 layer2display windows, and shows a lightbulb that demonstrates the bits being sent over layer 1.
*
* The tester can send l2 messages between the two displays. One can use either the other display's address (1 or 2),
* but he/she can also use the broadcast address to send to both displays (15, which is read on the network as 1111 - protocol for broadcast messages).
*
* @author	Andrew Quist, Sambridhi Acharya
*/

public class Test {

	public static void main(String[] args) {

		//USEFUL DEBUGGING TESTS
		// L2Frame testF = new L2Frame(1, 1, 1, 0, "0111001" );
		// try{
		// 	L2Frame test2 = new L2Frame(testF.toString());

		// 	System.out.println(testF.toString());
		// 	System.out.println(test2.toString());

		// }catch (Exception e){
		// 	System.out.println("there was an error");
		// }
		

		LightSystem system = new LightSystem();
		LightDisplay d1 = new LightDisplay(new LightPanel());
		
		Layer2Display l2d = new Layer2Display(new L2Handler(1));
		Layer2Display l3d = new Layer2Display(new L2Handler(2));
	}

}
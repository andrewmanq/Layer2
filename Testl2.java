public class Testl2 {

	public static void main(String[] args) {

		L2Frame testF = new L2Frame(1, 1, 1, 0, "0111001" );
		System.out.println(testF.toString());

		LightSystem system = new LightSystem();
		LightDisplay d1 = new LightDisplay(new LightPanel());
		
		Layer2Display l2d = new Layer2Display(new L2Handler(1));
	}

}
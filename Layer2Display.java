import java.awt.event.*;
import javax.swing.*;

/**
* This displays a single layer 2 handler and sends/recieves user inputted layer 2 frames.
*
* @author	Andrew Quist, Sambridhi Acharya
*/
public class Layer2Display implements ActionListener, Layer2Listener
{
    private L2Handler handler;
    private JTextField displayField;
    private JTextField addressField;
    private JTextField typeField;
    private JTextField vidField;
    private JTextField payloadField;

	/**
	 * Creates all UI elements
	 */
    public Layer2Display(L2Handler handler)
    {
		this.handler = handler;
		handler.setListener(this);

		/**
		 * specifies the address being used at the top of the window
		 */
		JFrame frame = new JFrame(handler.toString());
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
							       BoxLayout.PAGE_AXIS));

		/**
		 * This is where recieved frame payloads are displayed
		 */
		displayField = new JTextField(20);
		displayField.setEditable(false);
		frame.getContentPane().add(displayField);

		frame.getContentPane().add(new JLabel("Address:"));

		/**
		 * All code below is defining user text input fields for sent frame information
		 * -------------------------------------------------------------------------------------
		 */
		addressField = new JTextField(20);
		addressField.addActionListener(this);
		frame.getContentPane().add(addressField);

		frame.getContentPane().add(new JLabel("Type:"));

		typeField = new JTextField(20);
		typeField.addActionListener(this);
		frame.getContentPane().add(typeField);

		frame.getContentPane().add(new JLabel("Vlan ID:"));

		vidField = new JTextField(20);
		vidField.addActionListener(this);
		frame.getContentPane().add(vidField);

		frame.getContentPane().add(new JLabel("Payload:"));

		payloadField = new JTextField(20);
		payloadField.addActionListener(this);
		frame.getContentPane().add(payloadField);

		/**
		 * shows the frame on the screen
		 */
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * when a frame comes in from the hanlder, it shows the recieved message on the display field
	 */
	public void frameRecieved(L2Handler h, L2Frame f){
		displayField.setText(f.getPayload());
	}

	/**
	 * This sends the frame created by the user input.
	 */
    public void actionPerformed(ActionEvent e)
    {
		displayField.setText("Sending...");
		new Thread()
		{
		    public void run()
		    {
				int dest = Integer.parseInt(addressField.getText());
				int source = Integer.parseInt(handler.toString());
				int type = Integer.parseInt(typeField.getText());
				int vid = Integer.parseInt(vidField.getText());
				String contents = payloadField.getText();

				/**
				 * makes a new frame to send via L2Handler
				 */
				L2Frame frame = new L2Frame(dest, source, type, vid, contents);
				handler.send(frame);
		    }
			}.start();
	    }

}
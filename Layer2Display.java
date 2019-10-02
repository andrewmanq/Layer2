import java.awt.event.*;
import javax.swing.*;

public class Layer2Display implements ActionListener, Layer2Listener
{
    private L2Handler handler;
    private JTextField displayField;
    private JTextField addressField;
    private JTextField typeField;
    private JTextField vidField;
    private JTextField payloadField;

    public Layer2Display(L2Handler handler)
    {
		this.handler = handler;
		//handler.setListener(this);

		JFrame frame = new JFrame(handler.toString());
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
							       BoxLayout.PAGE_AXIS));

		displayField = new JTextField(20);
		displayField.setEditable(false);
		frame.getContentPane().add(displayField);

		frame.getContentPane().add(new JLabel("Address:"));

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

		frame.pack();
		frame.setVisible(true);
    }

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

				L2Frame frame = new L2Frame(dest, source, type, vid, contents);

				handler.send(frame);
		    }
			}.start();
	    }

}
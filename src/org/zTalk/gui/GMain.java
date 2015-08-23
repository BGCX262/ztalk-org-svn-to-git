package org.zTalk.gui;

import java.sql.Connection;
import java.util.Properties;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.SwingUtilities;

import org.zTalk.protocols.icq.contacts.IContactList;
import org.zTalk.protocols.icq.core.OscarConnection;
import org.zTalk.protocols.icq.integration.events.LoginErrorEvent;
import org.zTalk.protocols.icq.integration.events.StatusEvent;
import org.zTalk.protocols.icq.integration.listeners.OurStatusListener;

import static org.zTalk.Tools.Settings.*;
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GMain extends javax.swing.JFrame implements OurStatusListener {
	/**
	 * 
	 */
	private IContactList contactList;
	private static String ICQServer = "login.icq.com";
	private static int ICQPort = 5190;
    private OscarConnection conection;
	
	private static final long serialVersionUID = 5054427794752528915L;
	private JMenuBar jMenuBar;
	private JMenu jMenu1;
	private GSettings SettingsMenu;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GMain inst = new GMain();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GMain() {
		super();
		initGUI();
		connect();
	}

	private void connect(){
		//Properties settings = new Properties();
		//LoadSettings(settings);
		
		//String password = settings.getProperty("Password");
		//String UIN = settings.getProperty("UIN");
		
        //conection = new OscarConnection(ICQServer, ICQPort, UIN, password);

       // conection.addOurStatusListener(this);

       // conection.connect();
		
	}
	
	
	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setTitle("zTalk.org");
			getContentPane().setLayout(null);
			{
				jMenuBar = new JMenuBar();
				setJMenuBar(jMenuBar);
				{
					jMenu1 = new JMenu();
					jMenuBar.add(jMenu1);
					jMenu1.setText("S");
					jMenu1.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							jMenu1StateChanged(evt);
						}
					});

				}
			}
			pack();
			this.setSize(387, 288);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void jMenu1StateChanged(ChangeEvent evt) {
		if (SettingsMenu == null) {
			SettingsMenu = new GSettings();
		}
		SettingsMenu.Show();
	}

	@Override
	public void onLogout(Exception exception) {
		System.out.println("Logout");
		
	}

	@Override
	public void onLogin() {
		
		//contactList = conection.getContactList();
		
		
	}

	@Override
	public void onAuthorizationFailed(LoginErrorEvent e) {
		System.out.println("LoginFailed" + e.getErrorMessage());
		
	}

	@Override
	public void onStatusResponse(StatusEvent e) {
		// TODO Auto-generated method stub
		
	}

}

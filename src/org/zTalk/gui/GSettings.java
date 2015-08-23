package org.zTalk.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zTalk.protocols.icq.core.OscarClient;

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
public class GSettings extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static Log log = LogFactory.getLog(GSettings.class);
	private static final long serialVersionUID = 7466202266384870088L;
	private JLabel jLabelUIN;
	private JButton jButtonSave;
	private JTextField jTextFieldPort;
	private JTextField jTextFieldProxy;
	private JLabel jLabelPort;
	private JLabel jLabelPassword;
	private JPasswordField jPasswordField;
	private JTextField jTextFieldUIN;
	private JCheckBox jCheckBoxUseProxy;
	private JButton jButtonClose;
	private Properties settings;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GSettings inst = new GSettings();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GSettings() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Settings");
			getContentPane().setLayout(null);
			{
				jButtonClose = new JButton();
				getContentPane().add(jButtonClose);
				jButtonClose.setText("Close");
				jButtonClose.setBounds(279, 46, 78, 22);
				jButtonClose.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButtonCloseMouseClicked(evt);
					}
				});
			}
			{
				jCheckBoxUseProxy = new JCheckBox();
				getContentPane().add(jCheckBoxUseProxy);
				jCheckBoxUseProxy.setText("Use proxy");
				jCheckBoxUseProxy.setBounds(12, 83, 350, 19);
			}
			{
				jTextFieldUIN = new JTextField();
				getContentPane().add(jTextFieldUIN);
				jTextFieldUIN.setBounds(99, 17, 161, 22);
			}
			{
				jLabelUIN = new JLabel();
				getContentPane().add(jLabelUIN);
				jLabelUIN.setText("UIN");
				jLabelUIN.setBounds(12, 24, 77, 15);
			}
			{
				jPasswordField = new JPasswordField();
				getContentPane().add(jPasswordField);
				jPasswordField.setBounds(99, 46, 161, 22);
			}
			{
				jLabelPassword = new JLabel();
				getContentPane().add(jLabelPassword);
				jLabelPassword.setText("Password");
				jLabelPassword.setBounds(12, 53, 77, 15);
			}
			{
				jLabelPort = new JLabel();
				getContentPane().add(jLabelPort);
				jLabelPort.setText("port");
				jLabelPort.setBounds(168, 113, 31, 15);
			}
			{
				jTextFieldProxy = new JTextField();
				getContentPane().add(jTextFieldProxy);
				jTextFieldProxy.setBounds(12, 110, 143, 22);
			}
			{
				jTextFieldPort = new JTextField();
				getContentPane().add(jTextFieldPort);
				jTextFieldPort.setBounds(211, 110, 48, 22);
			}
			{
				jButtonSave = new JButton();
				getContentPane().add(jButtonSave);
				jButtonSave.setBounds(279, 17, 78, 24);
				jButtonSave.setText("Save");
				jButtonSave.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButtonSaveMouseClicked(evt);
					}
				});
			}
			pack();
			this.setSize(378, 173);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);			
		}
	}
	public void Show(){
				
		getSettings();
		this.setVisible(true);
	}
	private void getSettings(){
		settings = new Properties();
		if (LoadSettings(settings)) {
			this.jTextFieldUIN.setText(settings.getProperty("UIN"));
			this.jPasswordField.setText(settings.getProperty("Password"));
			String checked = settings.getProperty("UseProxy");
			if (checked.equals("true")){
				this.jCheckBoxUseProxy.setSelected(true);
			}

			this.jTextFieldProxy.setText(settings.getProperty("Proxy"));
			this.jTextFieldPort.setText(settings.getProperty("ProxyPort"));
		}
		
	}
	private void gSaveSettings(){
		settings = new Properties();
		settings.setProperty("UIN", this.jTextFieldUIN.getText());
		settings.setProperty("Proxy",this.jTextFieldProxy.getText());
		settings.setProperty("ProxyPort",this.jTextFieldPort.getText());

		if (this.jCheckBoxUseProxy.isSelected())
			settings.setProperty("UseProxy", "true");
		else
			settings.setProperty("UseProxy", "false");
		
		// convert char[] to String 
		char[] charsPassword = this.jPasswordField.getPassword();
							
		String password = new String();
		for (int i = 0; i< charsPassword.length ; i++){
			password += charsPassword[i];
		}
		settings.setProperty("Password", password);
		SaveSettings(settings);
	}
	
	private void jButtonCloseMouseClicked(MouseEvent evt) {
		this.dispose();
		
	}
	
	private void jButtonSaveMouseClicked(MouseEvent evt) {
		gSaveSettings();
		this.dispose();
	}

}

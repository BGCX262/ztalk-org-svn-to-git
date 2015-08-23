/**
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.zTalk.protocols.icq.examples;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.zTalk.protocols.icq.core.OscarConnection;
import org.zTalk.protocols.icq.integration.OscarInterface;
import org.zTalk.protocols.icq.integration.events.IncomingUserEvent;
import org.zTalk.protocols.icq.integration.events.LoginErrorEvent;
import org.zTalk.protocols.icq.integration.events.OffgoingUserEvent;
import org.zTalk.protocols.icq.integration.events.StatusEvent;
import org.zTalk.protocols.icq.integration.listeners.OurStatusListener;
import org.zTalk.protocols.icq.integration.listeners.UserStatusListener;
import org.zTalk.protocols.icq.setting.enumerations.StatusModeEnum;

/**
 * <p>
 * Created by 22.06.2008
 * 
 * @author Samolisov Pavel
 */
public class OnLogoutEventTest implements OurStatusListener, UserStatusListener {

	private static Log log = LogFactory.getLog(OnLogoutEventTest.class);

	private static final String SERVER = "login.icq.com";
	private static final int PORT = 5190;

	private OscarConnection connection;

	public OnLogoutEventTest(String uin, String password) {
		connection = new OscarConnection(SERVER, PORT, uin, password);

		connection.addUserStatusListener(this);
		connection.addOurStatusListener(this);

		connection.connect();
	}

	public void onIncomingUser(IncomingUserEvent e) {
		// XXX
	}

	public void onOffgoingUser(OffgoingUserEvent e) {
		// XXX
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Use : OnLogoutEventsTest MY_UIN MY_PASSWORD");
		} else {
			new OnLogoutEventTest(args[0], args[1]);
		}
	}

	public void onLogin() {
		OscarInterface.changeStatus(connection, new StatusModeEnum(
				StatusModeEnum.ONLINE));
	}

	public void onLogout(Exception e) {
		connection.close();
		log.error("Logout ", e);
		System.exit(1);
	}

	public void onAuthorizationFailed(LoginErrorEvent e) {
		connection.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onStatusResponse(StatusEvent e) {
		log.info("--> onStatusResponse, status = " + e.getStatusMode());
	}
}

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
package org.zTalk.protocols.icq.packet.received.icbm;

import org.zTalk.protocols.icq.core.OscarConnection;
import org.zTalk.protocols.icq.packet.received.ReceivedPacket;
import org.zTalk.protocols.icq.packet.sent.icbm.ICBMSetParameters;
import org.zTalk.protocols.icq.packet.sent.privacy.BosRightsRequest;

/**
 * <p>
 * Created by
 * 
 * @author Fabrice Michellonet
 * @author Prolubnikov Dmitry
 */
public class ICBMParametersReply__4_5 extends ReceivedPacket {

	public ICBMParametersReply__4_5(byte[] array) {
		super(array, true);
	}

	public void execute(OscarConnection connection) {
		connection.sendFlap(new ICBMSetParameters());
		connection.sendFlap(new BosRightsRequest());
	}
}
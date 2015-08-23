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
package org.zTalk.protocols.icq.packet.received;

import org.zTalk.protocols.icq.Flap;
import org.zTalk.protocols.icq.RawData;
import org.zTalk.protocols.icq.Snac;
import org.zTalk.protocols.icq.core.IReceivable;
import org.zTalk.protocols.icq.core.OscarConnection;
import org.zTalk.protocols.icq.request.Request;
import org.zTalk.protocols.icq.request.RequestKeeper;
import org.zTalk.protocols.icq.request.event.RequestAnswerEvent;

/**
 * <p>
 * Created by
 * 
 * @author Fabrice Michellonet
 * @author Samolisov Pavel
 */
public class ReceivedPacket extends Flap implements IReceivable {

	public ReceivedPacket(byte[] array, boolean hasSnac) {
		super();

		setChannelId(new RawData(array, 1, 1).getValue());
		setSequenceNumber(new RawData(array, 2, 2).getValue());

		if (hasSnac)
			addSnac(new Snac(array, 6));
		else
			addRawDataToFlap(new RawData(array, 6, array.length - 6));
	}

	/**
	 * This cause the callback of RequestListener classes, which have registered
	 * a requestId that match the requestId of the current packet.
	 * 
	 * @param connection
	 *            An active connection with any Oscar server.
	 */
	public void matchRequest(OscarConnection connection) {
		RequestKeeper requestKeeper = connection.getRequestKeeper();
		if (requestKeeper.containsRequest(this.getSnac().getRequestId())) {
			Request request = requestKeeper.getRequest(this.getSnac()
					.getRequestId());
			for (int i = 0; i < request.getNbListeners(); i++) {
				request.getRequestListener(i)
						.onRequestAnswer(
								new RequestAnswerEvent(request
										.getMonitoredFlap(), this));
			}
			request.removeAllListener();
		}
	}

	public void execute(OscarConnection connection) throws Exception {
	};

	public void notifyEvent(OscarConnection connection) {
	};
}
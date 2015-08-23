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
package org.zTalk.protocols.icq.integration.listeners;

import java.util.EventListener;

import org.zTalk.protocols.icq.integration.events.ContactListEvent;
import org.zTalk.protocols.icq.integration.events.SsiAuthReplyEvent;
import org.zTalk.protocols.icq.integration.events.SsiAuthRequestEvent;
import org.zTalk.protocols.icq.integration.events.SsiFutureAuthGrantEvent;
import org.zTalk.protocols.icq.integration.events.SsiModifyingAckEvent;

/**
 * <p>
 * Created by
 * 
 * @author Lo�c Broquet
 */
public interface ContactListListener extends EventListener {

	public void onUpdateContactList(ContactListEvent event);

	public void onSsiModifyingAck(SsiModifyingAckEvent event);

	public void onSsiFutureAuthGrant(SsiFutureAuthGrantEvent event);

	public void onSsiAuthRequest(SsiAuthRequestEvent event);

	public void onSsiAuthReply(SsiAuthReplyEvent event);
}

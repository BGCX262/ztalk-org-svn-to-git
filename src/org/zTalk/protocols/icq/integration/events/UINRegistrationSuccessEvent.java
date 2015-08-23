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
package org.zTalk.protocols.icq.integration.events;

import java.util.EventObject;

import org.zTalk.protocols.icq.packet.received.authorization.UINRegistrationSuccess__23_5;

/**
 * <p>
 * Created by
 * 
 * @author Egor Baranov
 */
public class UINRegistrationSuccessEvent extends EventObject {

	private static final long serialVersionUID = -2690338742682890998L;

	public UINRegistrationSuccessEvent(UINRegistrationSuccess__23_5 source) {
		super(source);
	}

	public String getNewUIN() {
		return ((UINRegistrationSuccess__23_5) this.getSource()).getNewUIN();
	}
}

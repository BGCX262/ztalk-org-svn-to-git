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
package org.zTalk.protocols.icq.metainfo;

import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zTalk.protocols.icq.RawData;
import org.zTalk.protocols.icq.exceptions.ConvertStringException;
import org.zTalk.protocols.icq.integration.events.MetaAffilationsUserInfoEvent;
import org.zTalk.protocols.icq.integration.listeners.MetaInfoListener;
import org.zTalk.protocols.icq.setting.enumerations.AffilationEnum;
import org.zTalk.protocols.icq.setting.enumerations.PostBackgroundEnum;
import org.zTalk.protocols.icq.tool.StringTools;

/**
 * <p>
 * Created by 30.03.2008
 * 
 * @author Samolisov Pavel
 */
public class AffilationsUserInfoParser extends BaseMetaInfoParser {

	private static Log log = LogFactory.getLog(AffilationsUserInfoParser.class);

	private Map postbackgrounds = new HashMap();
	private Map affilations = new HashMap();

	protected EventObject getNewEvent() {
		return new MetaAffilationsUserInfoEvent(this);
	}

	protected void sendMessage(EventListener listener, EventObject e) {
		log.debug("notify listener " + listener.getClass().getName()
				+ " onAffilationUserInfo()");
		((MetaInfoListener) listener)
				.onAffilationsUserInfo((MetaAffilationsUserInfoEvent) e);
	}

	public void parse(byte[] data, int position) throws ConvertStringException {
		position += 3; // skip subtype and success byte (always 0x0A) and data
						// size.

		int len = (new RawData(data, position, RawData.BYTE_LENGHT)).getValue();
		position += RawData.BYTE_LENGHT;

		for (int i = 0; i < len; i++) {
			// Category code
			RawData rStrLen = new RawData(data, position, RawData.WORD_LENGHT);
			rStrLen.invertIndianness();
			position += RawData.WORD_LENGHT;
			int code = rStrLen.getValue();

			// PostBackground length
			rStrLen = new RawData(data, position, RawData.WORD_LENGHT);
			rStrLen.invertIndianness();
			position += RawData.WORD_LENGHT;
			;

			// PostBackground
			String postbackground = StringTools.byteArrayToString(data,
					position, rStrLen.getValue() - 1);
			position += rStrLen.getValue();

			postbackgrounds.put(new PostBackgroundEnum(code), postbackground);
		}

		len = (new RawData(data, position, RawData.BYTE_LENGHT)).getValue();
		position += RawData.BYTE_LENGHT;

		for (int i = 0; i < len; i++) {
			// Category code
			RawData rStrLen = new RawData(data, position, RawData.WORD_LENGHT);
			rStrLen.invertIndianness();
			position += RawData.WORD_LENGHT;
			int code = rStrLen.getValue();

			// Affilation length
			rStrLen = new RawData(data, position, RawData.WORD_LENGHT);
			rStrLen.invertIndianness();
			position += RawData.WORD_LENGHT;
			;

			// Affilation
			String affilation = StringTools.byteArrayToString(data, position,
					rStrLen.getValue() - 1);
			position += rStrLen.getValue();

			affilations.put(new AffilationEnum(code), affilation);
		}
	}

	public Map getPostBackgrounds() {
		return postbackgrounds;
	}

	public Map getAffilations() {
		return affilations;
	}
}

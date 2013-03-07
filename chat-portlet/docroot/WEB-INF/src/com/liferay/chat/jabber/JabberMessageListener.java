/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.chat.jabber;

import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * @author Bruno Farache
 */
public class JabberMessageListener implements MessageListener {

	public JabberMessageListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;
	}

	public void processMessage(Chat chat, Message message) {
		try {
			String body = message.getBody();

			if (Validator.isNull(body)) {
				return;
			}

			String from = message.getFrom();

			String resource = JabberUtil.getResource(from);

			if (resource.equalsIgnoreCase(PortletPropsValues.JABBER_RESOURCE)) {
				return;
			}

			long fromUserId = UserLocalServiceUtil.getUserIdByScreenName(
				_companyId, JabberUtil.getScreenName(from));

			EntryLocalServiceUtil.addEntry(fromUserId, _userId, body);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JabberMessageListener.class);

	private long _companyId;
	private long _userId;

}
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

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

/**
 * @author Bruno Farache
 */
public class JabberChatManagerListener implements ChatManagerListener {

	public JabberChatManagerListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;
	}

	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			MessageListener messageListener = new JabberMessageListener(
				_companyId, _userId);

			chat.addMessageListener(messageListener);
		}
	}

	private long _companyId;
	private long _userId;

}
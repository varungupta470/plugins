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

import com.liferay.chat.util.PortletPropsValues;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class JabberUtil {

	public static void disconnect(long userId) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().disconnect(userId);
	}

	public static String getResource(String jabberId) {
		return getJabber().getResource(jabberId);
	}

	public static String getScreenName(String jabberId) {
		return getJabber().getScreenName(jabberId);
	}

	public static List<Object[]> getStatuses(
		long companyId, long userId, List<Object[]> buddies) {

		if (!PortletPropsValues.JABBER_ENABLED) {
			return buddies;
		}

		return getJabber().getStatuses(companyId, userId, buddies);
	}

	public static void login(long userId, String password) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().login(userId, password);
	}

	public static void sendMessage(
		long fromUserId, long toUserId, String content) {

		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().sendMessage(fromUserId, toUserId, content);
	}

	public static void updatePassword(long userId, String password) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().updatePassword(userId, password);
	}

	public static void updateStatus(long userId, int online) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().updateStatus(userId, online);
	}

	public void setJabber(Jabber jabber) {
		_jabber = jabber;
	}

	protected static Jabber getJabber() {
		return _jabber;
	}

	private static Jabber _jabber;

}
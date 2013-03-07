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

import java.util.List;

/**
 * @author Bruno Farache
 */
public interface Jabber {

	public void disconnect(long userId);

	public String getResource(String jabberId);

	public String getScreenName(String jabberId);

	public List<Object[]> getStatuses(
		long companyId, long userId, List<Object[]> buddies);

	public void login(long userId, String password);

	public void sendMessage(long fromUserId, long toUserId, String content);

	public void updatePassword(long userId, String password);

	public void updateStatus(long userId, int online);

}
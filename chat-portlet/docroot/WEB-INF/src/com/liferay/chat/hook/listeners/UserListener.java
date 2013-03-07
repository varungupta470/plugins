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

package com.liferay.chat.hook.listeners;

import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;

/**
 * @author Scott Lee
 * @author Bruno Farache
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Removing chat entries and status for user " +
						user.getUserId());
			}

			EntryLocalServiceUtil.deleteEntries(user.getUserId());

			Status status = StatusLocalServiceUtil.getUserStatus(
				user.getUserId());

			if (status != null) {
				StatusLocalServiceUtil.deleteStatus(status);
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove chat entries and status for user " +
					user.getUserId());
		}
	}

	@Override
	public void onAfterUpdate(User user) {
		JabberUtil.updatePassword(
			user.getUserId(), user.getPasswordUnencrypted());
	}

	private static Log _log = LogFactoryUtil.getLog(UserListener.class);

}
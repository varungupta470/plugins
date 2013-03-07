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

package com.liferay.chat.service.impl;

import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.base.StatusLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class StatusLocalServiceImpl extends StatusLocalServiceBaseImpl {

	public List<Object[]> getAllStatuses(
			long companyId, long userId, long modifiedDate, int start, int end)
		throws SystemException {

		return statusFinder.findByModifiedDate(
			companyId, userId, modifiedDate, start, end);
	}

	public List<Object[]> getGroupStatuses(
			long userId, long modifiedDate, String[] groupNames, int start,
			int end)
		throws SystemException {

		return statusFinder.findByUsersGroups(
			userId, modifiedDate, groupNames, start, end);
	}

	public List<Object[]> getSocialStatuses(
			long userId, int type, long modifiedDate, int start, int end)
		throws SystemException {

		return statusFinder.findBySocialRelationType(
			userId, type, modifiedDate, start, end);
	}

	public Status getUserStatus(long userId) throws SystemException {
		Status status = statusPersistence.fetchByUserId(userId);

		if (status == null) {
			status = statusLocalService.updateStatus(
				userId, System.currentTimeMillis(), 1, 1, StringPool.BLANK,
				StringPool.BLANK, 1);
		}

		return status;
	}

	public Status updateStatus(long userId, long modifiedDate)
		throws SystemException {

		return updateStatus(userId, modifiedDate, -1, -1, null, null, -1);
	}

	public Status updateStatus(
			long userId, long modifiedDate, int online, int awake,
			String activePanelId, String message, int playSound)
		throws SystemException {

		Status status = statusPersistence.fetchByUserId(userId);

		if (status == null) {
			long statusId = counterLocalService.increment();

			status = statusPersistence.create(statusId);

			status.setUserId(userId);
		}

		if (modifiedDate != -1) {
			status.setModifiedDate(modifiedDate);
		}

		if (online != -1) {
			status.setOnline((online == 1) ? true : false);
		}

		if (awake != -1) {
			status.setAwake((awake == 1) ? true : false);
		}

		if (activePanelId != null) {
			status.setActivePanelId(activePanelId);
		}

		if (message != null) {
			status.setMessage(message);
		}

		if (playSound != -1) {
			status.setPlaySound((playSound == 1) ? true : false);
		}

		try {
			statusPersistence.update(status, false);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Add failed, fetch {userId=" + userId + "}");
			}

			status = statusPersistence.fetchByUserId(userId);

			if (status == null) {
				throw se;
			}
		}

		JabberUtil.updateStatus(userId, online);

		return status;
	}

	private static Log _log = LogFactoryUtil.getLog(
		StatusLocalServiceImpl.class);

}
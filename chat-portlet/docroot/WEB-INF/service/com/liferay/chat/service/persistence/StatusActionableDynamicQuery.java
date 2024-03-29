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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Status;
import com.liferay.chat.service.StatusLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class StatusActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public StatusActionableDynamicQuery() throws SystemException {
		setBaseLocalService(StatusLocalServiceUtil.getService());
		setClass(Status.class);

		setClassLoader(com.liferay.chat.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("statusId");
	}
}
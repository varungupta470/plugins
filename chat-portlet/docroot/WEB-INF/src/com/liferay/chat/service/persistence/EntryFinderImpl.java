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

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderImpl
	extends BasePersistenceImpl<Entry> implements EntryFinder {

	public static final String FIND_BY_EMPTY_CONTENT =
		EntryFinder.class.getName() + ".findByEmptyContent";

	public static final String FIND_BY_NEW =
		EntryFinder.class.getName() + ".findByNew";

	public static final String FIND_BY_OLD =
		EntryFinder.class.getName() + ".findByOld";

	public List<Entry> findByEmptyContent(
			long fromUserId, long toUserId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_EMPTY_CONTENT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromUserId);
			qPos.add(toUserId);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByNew(
			long userId, long createDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NEW);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);
			qPos.add(createDate);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByOld(long createDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_OLD);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}
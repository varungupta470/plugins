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

package com.liferay.chat.util.comparator;

import com.liferay.portal.model.ContactConstants;

import java.util.Comparator;

/**
 * @author Ryan Park
 */
public class BuddyComparator implements Comparator<Object[]> {

	public BuddyComparator() {
		this(false);
	}

	public BuddyComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object[] buddy1, Object[] buddy2) {
		long userId1 = (Long)buddy1[0];
		String firstName1 = (String)buddy1[2];
		String middleName1 = (String)buddy1[3];
		String lastName1 = (String)buddy1[4];
		boolean awake1 = (Boolean)buddy1[6];

		long userId2 = (Long)buddy2[0];
		String firstName2 = (String)buddy2[2];
		String middleName2 = (String)buddy2[3];
		String lastName2 = (String)buddy2[4];
		boolean awake2 = (Boolean)buddy2[6];

		int value = 0;

		if (userId1 == userId2) {
			return value;
		}

		if (awake1 && !awake2) {
			value = 1;
		}
		else if (!awake1 && awake2) {
			value = -1;
		}

		if (value == 0) {
			String fullName1 = ContactConstants.getFullName(
				firstName1, middleName1, lastName1);
			String fullName2 = ContactConstants.getFullName(
				firstName2, middleName2, lastName2);

			value = fullName1.compareTo(fullName2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	private boolean _asc;

}
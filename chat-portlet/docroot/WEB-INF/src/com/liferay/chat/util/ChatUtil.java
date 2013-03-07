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

package com.liferay.chat.util;

import com.liferay.chat.buddies.BuddiesFinder;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Time;

import java.util.List;


/**
 * @author Brian Wing Shun Chan
 */
public class ChatUtil {

	public static final long MAX_POLL_LATENCY = Time.SECOND * 15;

	public static final long ONLINE_DELTA = Time.MINUTE;

	
	public static BuddiesFinder getBuddiesFinder() {

		return _buddiesFinder;
	}
	

	public static List<Object[]> getBuddies(long companyId, long userId)
		throws SystemException {
		
		return getBuddiesFinder().getBuddies(companyId, userId);
		
	}
	
	public void setBuddiesFinder(BuddiesFinder buddiesFinder) {

		_buddiesFinder = buddiesFinder;
	}

	private static BuddiesFinder _buddiesFinder;

}
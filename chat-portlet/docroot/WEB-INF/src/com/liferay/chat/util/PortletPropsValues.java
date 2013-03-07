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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final int BUDDY_LIST_MAX_BUDDIES = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.BUDDY_LIST_MAX_BUDDIES));

	public static final String[] BUDDY_LIST_SITE_EXCLUDES = new String[0];

	public static final String BUDDY_LIST_STRATEGY = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.BUDDY_LIST_STRATEGY));

	public static final boolean JABBER_ENABLED = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.JABBER_ENABLED));

	public static final String JABBER_HOST = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_HOST));

	public static final boolean JABBER_IMPORT_USER_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.JABBER_IMPORT_USER_ENABLED));

	public static final int JABBER_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.JABBER_PORT));

	public static final String JABBER_RESOURCE = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_RESOURCE));

	public static final String JABBER_SERVICE_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.JABBER_SERVICE_NAME));

	public static final boolean JABBER_SOCK5_PROXY_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.JABBER_SOCK5_PROXY_ENABLED));

	public static final int JABBER_SOCK5_PROXY_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.JABBER_SOCK5_PROXY_PORT));
	
	

}
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ryan Park
 */
public class ChatExtensionsUtil {

	public static Map<String, String> getExtensions() {
		return _instance._getExtensions();
	}

	public static void register(String servletContextName, String path) {
		_instance._register(servletContextName, path);
	}

	public static void unregister(String servletContextName) {
		_instance._unregister(servletContextName);
	}

	private ChatExtensionsUtil() {
		_extensions = new ConcurrentHashMap<String, String>();
	}

	private Map<String, String> _getExtensions() {
		return _extensions;
	}

	private void _register(String servletContextName, String path) {
		_extensions.put(servletContextName, path);
	}

	private void _unregister(String servletContextName) {
		_extensions.remove(servletContextName);
	}

	private static ChatExtensionsUtil _instance = new ChatExtensionsUtil();

	private Map<String, String> _extensions;

}
package com.liferay.chat.custom.finder;

import com.liferay.chat.buddies.BuddiesFinder;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class MyBuddiesFinder implements BuddiesFinder {
	
	public static final long MAX_POLL_LATENCY = Time.SECOND * 15;

	public static final long ONLINE_DELTA = Time.MINUTE;
	
	long modifiedDate = System.currentTimeMillis() - ONLINE_DELTA;


	@Override
	public List<Object[]> getBuddies(long companyId, long userId)
			throws SystemException {
		
		// TODO Auto-generated method stub
		List<Object[]> allBuddies = 
				StatusLocalServiceUtil.getAllStatuses
				(companyId, userId, modifiedDate, 0, PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
		
		List<Object[]> customBuddies = new ArrayList<Object[]>();
		
		Organization customerOrg = null;
		
		Organization employeeOrg = null;
		
		long employeeGroupId = 0;
		long customerGroupId = 0;
			try {
				customerOrg = OrganizationLocalServiceUtil.getOrganization
						(companyId, "Customer");
				customerGroupId = customerOrg.getGroupId();
				System.out.println("customerGroupId//"+customerGroupId);
				
				employeeOrg = OrganizationLocalServiceUtil.getOrganization(companyId, "Employee");
				employeeGroupId = employeeOrg.getGroupId();
				System.out.println("employeeGroupId//"+employeeGroupId);
				
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(UserLocalServiceUtil.hasGroupUser(customerGroupId, userId))
			{
				for(Object[] status: allBuddies)
				{
					long myUserId = (Long) status[0];
					System.out.println("myuserId//"+myUserId);
					if(UserLocalServiceUtil.hasGroupUser(employeeGroupId, myUserId))
					{
						System.out.println("I am in emplyee");
						customBuddies.add(status);
					}
				}
				
				return customBuddies;
			}
			else
			{
			
			return allBuddies;
			}
	}

}

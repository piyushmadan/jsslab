package org.openmrs.module.jsslab;

import java.util.List;

import org.openmrs.module.jsslab.db.LabOrder;
import org.openmrs.module.jsslab.db.LabSupplyItem;
import org.openmrs.module.jsslab.PrivilegeConstants;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

public interface LabOrderService extends OpenmrsService {

	/**
	 * Save or update the given <code>LabOrder</code> in the database
	 * 
	 * @param labOrder the LabOrder to save
	 * @return the LabOrder that was saved
	 * @throws APIException
	 * @should not save LabOrder if LabOrder doesn't validate
	 */
	@Authorized( { PrivilegeConstants.EDIT_LAB_ORDER, PrivilegeConstants.ADD_LAB_ORDER })
	public LabOrder saveLabOrder(LabOrder labOrder) throws APIException;
	
	/**
	 * Get the <code>LabOrder</code> with the given uuid from the database
	 * 
	 * @param uuid the uuid to find
	 * @return the LabOrder that was found or null
	 */
	@Authorized( PrivilegeConstants.VIEW_LAB_ORDER )
	public LabOrder getLabOrderByUuid(String uuid);
	
	/**
	 * Completely delete an LabOrder from the database. This should not typically be used unless
	 * desperately needed. Most LabOrders should just be retired. See {@link #retireLabOrder(LabOrder, String)}
	 * 
	 * @param labOrder The LabOrder to remove from the system
	 * @throws APIException
	 */
	@Authorized(PrivilegeConstants.PURGE_LAB_ORDER)
	public void purgeLabOrder(LabOrder labOrder) throws APIException;
	
	/**
	 * Mark an LabOrder as retired. This functionally removes the LabOrder from the system while keeping a
	 * semblance
	 * 
	 * @param retireReason String reason
	 * @param labOrder LabOrder to retire
	 * @return the LabOrder that was retired
	 * @throws APIException
	 */
	@Authorized(PrivilegeConstants.DELETE_LAB_ORDER)
	public LabOrder deleteLabOrder(LabOrder labOrder, String retireReason) throws APIException;
	
	/**
	 * Get all LabOrder, only showing ones not marked as retired if includeRetired is true
	 * 
	 * @param includeRetired true/false whether to include retired LabOrders in this list
	 * @return LabOrders list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_ORDER)
	public List<LabOrder> getAllLabOrders(Boolean includeRetired) throws APIException;
	
	/**
	 * Get all unretired LabOrder
	 * 
	 * @return LabOrders list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_ORDER)
	public List<LabOrder> getAllLabOrders() throws APIException;
	
	/**
	 * Returns a specified number of labOrders starting with a given string from the specified index
	 */
	public List<LabOrder> getLabOrders(String nameFragment, Boolean includeRetired, Integer start, Integer length);
	
	/**
	 * Get count of LabOrder, only showing ones not marked as retired if includeRetired is true
	 * 
	 * @param includeRetired true/false whether to include retired LabOrders in this list
	 * @return LabOrders list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_ORDER)
	public Integer getCountOfLabOrders(Boolean includeRetired) throws APIException;
	
	/**
	 * Get count of unretired LabOrder
	 * 
	 * @return LabOrders list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_ORDER)
	public Integer getCountOfLabOrders() throws APIException;
	
	
}
package org.openmrs.module.jsslab;

import java.util.List;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.jsslab.db.LabReport;
import org.openmrs.module.jsslab.db.LabTestRange;
import org.openmrs.module.jsslab.db.LabTestResult;
import org.openmrs.module.jsslab.db.LabTestSpecimen;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LabTestingService extends OpenmrsService {

	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabTestRange getLabTestRange(Integer labTestRangeId);
	
	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabTestRange getLabTestRangeByUuid(String uuid);
	
	@Authorized( { PrivilegeConstants.EDIT_LAB_TEST, PrivilegeConstants.ADD_LAB_TEST })
	public LabTestRange saveLabTestRange(LabTestRange labTestRange)throws APIException;
	
	@Transactional(readOnly=false)
	@Authorized(PrivilegeConstants.DELETE_LAB_TEST)
	public void deleteLabTestRange(LabTestRange labTestRange, String reason)throws APIException;
	
	@Authorized(PrivilegeConstants.PURGE_LAB_TEST)
	public void purgeLabTestRange(LabTestRange labTestRange)throws APIException;
	
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabTestRange> getAllLabTestRanges(Boolean ifVoided)throws APIException;
	
	/**
	 * Returns a specified number of labTestRanges starting with a given string from the specified index
	 */
	public List<LabTestRange> getLabTestRanges(String nameFragment, Boolean includeVoided, Integer start, Integer length);
	
	/**
	 * Get count of LabTestRange, only showing ones not marked as retired if includeVoided is true
	 * 
	 * @param includeVoided true/false whether to include retired LabTestRanges in this list
	 * @return LabTestRanges list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public Integer getCountOfLabTestRanges(Boolean includeVoided) throws APIException;
	
//-----------------------------------------------------------------------
	
	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabTestSpecimen getLabTestSpecimen(Integer labTestSpecimen);
	
	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabTestSpecimen getLabTestSpecimenByUuid(String uuid);
	
	@Authorized( { PrivilegeConstants.EDIT_LAB_TEST, PrivilegeConstants.ADD_LAB_TEST })
	public LabTestSpecimen saveLabTestSpecimen(LabTestSpecimen labTestSpecimen)throws APIException;
	
	/**
	 * Completely delete an LabTestSpecimen from the database. This should not typically be used unless
	 * desperately needed. Most LabTestSpecimens should just be retired. See {@link #retireLabTestSpecimen(LabTestSpecimen, String)}
	 * 
	 * @param labTestSpecimen The LabTestSpecimen to remove from the system
	 * @throws APIException
	 */
	@Authorized(PrivilegeConstants.PURGE_LAB_TEST)
	public void purgeLabTestSpecimen(LabTestSpecimen labTestSpecimen) throws APIException;
	
	/**
	 * Mark an LabTestSpecimen as retired. This functionally removes the LabTestSpecimen from the system while keeping a
	 * semblance
	 * 
	 * @param retireReason String reason
	 * @param labTestSpecimen LabTestSpecimen to retire
	 * @return the LabTestSpecimen that was retired
	 * @throws APIException
	 */
	@Authorized(PrivilegeConstants.DELETE_LAB_TEST)
	public LabTestSpecimen retireLabTestSpecimen(LabTestSpecimen labTestSpecimen, String retireReason) throws APIException;
	
	/**
	 * Get all LabTestSpecimen, only showing ones not marked as retired if includeVoided is true
	 * 
	 * @param includeVoided true/false whether to include retired LabTestSpecimens in this list
	 * @return LabTestSpecimens list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabTestSpecimen> getAllLabTestSpecimens(Boolean includeVoided) throws APIException;
	
	/**
	 * Get all unretired LabTestSpecimen
	 * 
	 * @return LabTestSpecimens list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabTestSpecimen> getAllLabTestSpecimens() throws APIException;
	
	/**
	 * Returns a specified number of labTestSpecimens starting with a given string from the specified index
	 */
	public List<LabTestSpecimen> getLabTestSpecimens(String nameFragment, Boolean includeVoided, Integer start, Integer length);
	
	/**
	 * Get count of LabTestSpecimen, only showing ones not marked as retired if includeVoided is true
	 * 
	 * @param includeVoided true/false whether to include retired LabTestSpecimens in this list
	 * @return LabTestSpecimens list
	 * @throws APIException
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public Integer getCountOfLabTestSpecimens(Boolean includeVoided) throws APIException;
	
	//-----------------------------------------------------------------------
	
	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabReport getLabReport(Integer labReport);
	
	@Authorized( PrivilegeConstants.VIEW_LAB_TEST )
	public LabReport getLabReportByUuid(String uuid);
	
	@Authorized( { PrivilegeConstants.EDIT_LAB_TEST, PrivilegeConstants.ADD_LAB_TEST })
	public LabReport saveLabReport(LabReport labReport)throws APIException;
	
	/**
	 * Completely delete an LabReport from the database. This should not typically be used unless
	 * desperately needed. Most LabReports should just be retired. See {@link #retireLabReport(LabReport, String)}
	 * 
	 * @param labReport The LabReport to remove from the system
	 * @throws APIException
	 * @should delete given LabReport
	 */
	@Authorized(PrivilegeConstants.PURGE_LAB_TEST)
	public void purgeLabReport(LabReport labReport) throws APIException;
	
	/**
	 * Mark an LabReport as retired. This functionally removes the LabReport from the system while keeping a
	 * semblance
	 * 
	 * @param retireReason String reason
	 * @param labReport LabReport to retire
	 * @return the LabReport that was retired
	 * @throws APIException
	 * @should retire LabReport
	 */
	@Authorized(PrivilegeConstants.DELETE_LAB_TEST)
	public LabReport retireLabReport(LabReport labReport, String retireReason) throws APIException;
	
	/**
	 * Get all <code>LabReport</code>s, only showing ones not marked as retired, unless includeRetired is true
	 * 
	 * @param includeRetired true/false whether to include retired LabReports in this list
	 * @return LabReports list
	 * @throws APIException
	 * @should get all LabReport by includeRetired
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabReport> getAllLabReports(Boolean includeRetired) throws APIException;
	
	/**
	 * Get all unretired LabReport
	 * 
	 * @return LabReports list
	 * @throws APIException
	 * @should get all LabReport
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabReport> getAllLabReports() throws APIException;
	
	/**
	 * Returns a specified number of labReports starting with a given string from the specified index
	 * @should return LabReport by String nameFragment, Boolean includeRetired, Integer start, Integer length
	 */
	public List<LabReport> getLabReports(String nameFragment, Boolean includeRetired, Integer start, Integer length);
	
	/**
	 * Get count of LabReport, only showing ones not marked as retired if includeVoided is true
	 * 
	 * @param includeRetired true/false whether to include retired LabReports in this list
	 * @return LabReports list
	 * @throws APIException
	 * @should get number of LabReport
	 */
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public Integer getCountOfLabReports(Boolean includeRetired) throws APIException;

//--------------------------------------------------------------------------------------------------

	/*
	 * @param id
	 * @return LabTestResult if founded, or null.
	 * @should return LabTestResult by LabTestResultId
	 */
	public LabTestResult getLabTestResult(Integer labTestResultId);
	
	/*
	 * @param uuid
	 * @return LabTestResult if founded, or null.
	 * @should return LabTestResult by uuid
	 * */
	public LabTestResult getLabTestResultByUuid(String uuid);
	
	/*
	 * @return save LabTestResult to database.
	 * @throws APIException
	 * @should save record
	 */
	@Transactional(readOnly=true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public LabTestResult saveLabTestResult(LabTestResult labLabTestResult)throws APIException;
	
	/*
	 *  Remove the LabTestResult from database, most of the time we just voided the LabTestResult.
	 * @param the LabTestResult that should be removed
	 * @throws APIException
	 * @should delete LabTestResult by given LabTestResult
	 */
	@Transactional(readOnly=false)
	@Authorized(PrivilegeConstants.PURGE_LAB_TEST)
	public void purgeLabTestResult(LabTestResult labLabTestResult)throws APIException;
	
	/*
	 * 
	 */
	@Transactional(readOnly=false)
	@Authorized(PrivilegeConstants.DELETE_LAB_TEST)
	public LabTestResult voidLabTestResult(LabTestResult labLabTestResult, String reason)throws APIException;
	
	/*
	 * Get all LabTestResult list including voided if the ifVoided is true
	 * 
	 * @param if include voided LabTestResult
	 * @return LabTestResult list(include voided list if ifVoided is true)
	 * @throws APIException
	 * @should get all LabTestResult
	 */
	@Transactional(readOnly=true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public List<LabTestResult> getAllLabTestResults(Boolean ifVoided)throws APIException;
	
	/*
	 * get the specific list of LabTests from String and index.
	 */
	public List<LabTestResult> getLabTestResults(String displayFragment, Boolean ifVoided, Integer index, Integer length);

	/*
	 * Get the length of the list that LabTestResult return(include voided if ifVoided is true)
	 * @param search is a String that represent DisplayFragment
	 * @param if include voided resources
	 * @return length of LabTestResult(include voided if ifVoided is true) resource
	 * throws Exception
	 * @should get the number of LabTestResult in database
	 */
	@Transactional(readOnly=true)
	@Authorized(PrivilegeConstants.VIEW_LAB_TEST)
	public Integer getCountOfLabTestResult(String search,Boolean ifVoided)throws APIException;
	


}
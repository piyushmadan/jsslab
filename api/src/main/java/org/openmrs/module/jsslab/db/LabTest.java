/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.jsslab.db;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * A lab test. Child of Panel.  Contains test testRanges
 * 
 */
@Root(strict = false)
public class LabTest extends BaseOpenmrsMetadata implements Serializable, Comparable<LabTest> {

	public static final long serialVersionUID = 2L;
	
	private static final Log log = LogFactory.getLog(LabTest.class);
	
	private Integer testId;

	private LabTestPanel testPanel;
	
	private Concept testConcept;
	
	private String testName;
	
	private String resultFormat;
	
	private LabTest confirmTest;
	
	private Double sortWeight;
	
	private Set<LabTestRange> testRanges = new HashSet<LabTestRange>();
	
	public Integer getId() {
		return testId;
	}

	public void setId(Integer testId) {
		this.testId = testId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	/**
	 * @return Returns testPanel.
	 */
	@Attribute(required = true)
	public LabTestPanel getTestPanel() {
		return testPanel;
	}
	
	/**
	 * @param testPanel.  The testPanel to set.
	 */
	@Attribute(required = true)
	public void setTestPanel(LabTestPanel testPanel) {
		this.testPanel = testPanel;
	}

	/**
	 * @return Returns the testConcept concept.
	 */
	@Attribute(required = true)
	public Concept getTestConcept() {
		return testConcept;
	}
	
	/**
	 * @param testConcept The testConcept to set.  
	 */
	@Attribute(required = true)
	public void setTestConcept(Concept testConcept) {
		this.testConcept = testConcept;
		this.testName = null;
	}
	
	/**
	 * @return Returns resultFormat.
	 */
	@Attribute(required = false)
	public String getResultFormat() {
		return resultFormat;
	}
	
	/**
	 * @param resultFormat.  The resultFormat to set.
	 */
	@Attribute(required = false)
	public void setResultFormat(String resultFormat) {
		this.resultFormat = resultFormat;
	}
	
	/**
	 * @return Returns confirmTest.
	 */
	@Attribute(required = false)
	public LabTest getConfirmTest() {
		return confirmTest;
	}
	
	/**
	 * @param confirmTest.  The confirmTest to set.
	 */
	@Attribute(required = false)
	public void setConfirmTest(LabTest confirmTest) {
		this.confirmTest = confirmTest;
	}

	/**
	 * @return Returns sortWeight.
	 */
	@Attribute(required = false)
	public Double getSortWeight() {
		return sortWeight;
	}
	
	/**
	 * @param sortWeight.  The sortWeight to set.
	 */
	@Attribute(required = false)
	public void setSortWeight(Double sortWeight) {
		this.sortWeight = sortWeight;
	}
	
	/**
	 * @return Returns testRanges.
	 */
	@Attribute(required = false)
	public Set<LabTestRange> getTestRanges() {
		return testRanges;
	}
	
	/**
	 * @param testRanges.  The testRanges to set.
	 */
	@Attribute(required = false)
	public void setTestRanges(Set<LabTestRange> testRanges) {
		this.testRanges = testRanges;
	}

	/**
	 * @return Returns lab Test Name.
	 */
	public String getTestName() {
		if (testName == null)
			testName = Context.getConceptService().getConceptName(this.getTestConcept().getId()).getName();
		return testName;
	}
	
	/**
	 * @return Returns int indicating comparison.
	 */
	public int compareTo(LabTest labTest) {
		int i = this.getTestPanel().getName().compareToIgnoreCase(labTest.getTestPanel().getName());
		if (i == 0)
			i = this.getSortWeight().compareTo(labTest.getSortWeight());
		return i;
	}
	
}

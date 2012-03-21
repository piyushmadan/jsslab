package org.openmrs.module.jsslab;


import junit.framework.Assert;

import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.jsslab.db.LabOrder;
import org.openmrs.module.jsslab.db.LabSpecimen;
import org.openmrs.test.BaseModuleContextSensitiveTest;


public class DeltaLabOrderServiceTest extends BaseModuleContextSensitiveTest{
	/**
	 * @see LabOrderService#getLabOrderByUuid(String)
	 * @verifies get by uuid
	 */
	@Test
	public void getLabOrderByUuid_shouldGetByUuid() throws Exception {
		Assert.assertEquals("Global property should not be there yet", 0, Context.getAdministrationService().getGlobalPropertiesByPrefix("jsslab").size());
		executeDataSet("jsslab_data_delta.xml");
		Assert.assertEquals("Global property should be there now", 1, Context.getAdministrationService().getGlobalPropertiesByPrefix("jsslab").size());
		Assert.assertEquals("Global property not set", "33333001", Context.getAdministrationService().getGlobalProperty("jsslab.LabOrder"));
		LabOrder lo = Context.getService(LabOrderService.class).getLabOrderByUuid("ee8b270c-49b9-11e1-812a-0024e8c61285");
		Assert.assertNotNull("GetLabOrderByUuid should not return null",lo);
		Assert.assertEquals("GetLabOrderByUuid should return the right object","ee8b270c-49b9-11e1-812a-0024e8c61285", lo.getUuid());
	}

	/**
	 * @see LabOrderService#getLabSpecimenByUuid(String)
	 * @verifies get by uuid
	 */
	@Test
	public void getLabSpecimenByUuid_shouldGetByUuid() throws Exception {
		executeDataSet("jsslab_data.xml");
		LabSpecimen ls = Context.getService(LabOrderService.class).getLabSpecimenByUuid("cbbf4eae-49bc-11e1-812a-0024e8c61285");
		Assert.assertNotNull("GetLabSpecimenByUuid should not return null",ls);
		Assert.assertEquals("GetLabSpecimenByUuid should return the right object","cbbf4eae-49bc-11e1-812a-0024e8c61285", ls.getUuid());
	}

}
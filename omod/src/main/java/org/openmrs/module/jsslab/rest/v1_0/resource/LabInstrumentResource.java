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
package org.openmrs.module.jsslab.rest.v1_0.resource;

import org.openmrs.Location;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.jsslab.LabManagementService;
import org.openmrs.module.jsslab.db.LabInstrument;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.AlreadyPaged;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.resource.impl.ServiceSearcher;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

/**
 * {@link Resource} for {@link Location}, supporting standard CRUD operations
 */
@Resource("labinstrument")
@Handler(supports = LabInstrument.class, order = 0)
public class LabInstrumentResource extends MetadataDelegatingCrudResource<LabInstrument> {
	
	/**
	 * @see DelegatingCrudResource#getRepresentationDescription(Representation)
	 */
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("propertyTag");
			description.addProperty("location",Representation.REF);
			description.addProperty("retired");
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("propertyTag");
			description.addProperty("manufacturer");
			description.addProperty("model");
			description.addProperty("serialNumber");
			description.addProperty("location",Representation.REF);
			description.addProperty("receivedDate");
			description.addProperty("receivedFrom");
			description.addProperty("receivedCost");
			description.addProperty("receivedValue");
			description.addProperty("conditionDate");
			description.addProperty("conditionConcept",Representation.REF);
			description.addProperty("maintenanceVendor");
			description.addProperty("maintenancePhone");
			description.addProperty("maintenanceDescription");
//			description.addProperty("testRuns", Representation.REF);
			description.addProperty("auditInfo", findMethod("getAuditInfo"));
			description.addProperty("retired");
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription d = new DelegatingResourceDescription();
		d.addProperty("propertyTag");
		d.addProperty("manufacturer");
		d.addProperty("model");
		d.addProperty("serialNumber");
		d.addProperty("location");
		d.addProperty("condition");
		d.addProperty("receivedDate");
		d.addProperty("receivedFrom");
		d.addProperty("receivedCost");
		d.addProperty("receivedValue");
		d.addProperty("conditionDate");
		d.addProperty("conditionConcept");
		d.addProperty("maintenanceVendor");
		d.addProperty("maintenancePhone");
		d.addProperty("maintenanceDescription");
		d.addProperty("testRuns");
//		TODO is not used in the object currently 
//		d.addProperty("name");
		return d;
	}
	
	@Override
	protected String getNamespacePrefix() {
		return "jsslab";
	}
	
	/**
	 * @see DelegatingCrudResource#newDelegate()
	 */
	@Override
	public LabInstrument newDelegate() {
		return new LabInstrument();
	}
	
	/**
	 * @see DelegatingCrudResource#save(java.lang.Object)
	 */
	@Override
	public LabInstrument save(LabInstrument labInstrument) {
		return Context.getService(LabManagementService.class)
				.saveLabInstrument(labInstrument);
	}
	
	/**
	 * Fetches a LabInstrument by uuid, if no match is found, it tries to look up one with a matching
	 * propertyTag or serialNumber
	 * 
	 * @see DelegatingCrudResource#getByUniqueId(java.lang.String)
	 */
	@Override
	public LabInstrument getByUniqueId(String uuid) {
		LabInstrument labInstrument = Context.getService(LabManagementService.class).getLabInstrumentByUuid(uuid);
		//We assume the caller was fetching by propertyTag or serialNumber
		if (labInstrument == null)
			labInstrument = Context.getService(LabManagementService.class).getLabInstrument(uuid);
		
		return labInstrument;
	}
	

	@Override
	public void delete(LabInstrument labInstrument, String reason,
			RequestContext context) throws ResponseException {
		if(labInstrument!=null)
		{
			//
			Context.getService(LabManagementService.class).retireLabInstrument(labInstrument,reason);
		}			
	}

	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#purge(java.lang.Object,
	 *      org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	public void purge(LabInstrument labInstrument, RequestContext context) throws ResponseException {
		if (labInstrument == null)
			return;
		Context.getService(LabManagementService.class).purgeLabInstrument(labInstrument);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doGetAll(org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected PageableResult doGetAll(RequestContext context) {
		return new NeedsPaging<LabInstrument>(Context.getService(LabManagementService.class).getAllLabInstruments(context.getIncludeAll()), context);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource#doSearch(java.lang.String,
	 *      org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@Override
	protected AlreadyPaged<LabInstrument> doSearch(String query, RequestContext context) {
		return new ServiceSearcher<LabInstrument>(LabManagementService.class, "getLabInstruments", "getCountOfLabInstruments").search(query,
		    context);
	}
	
	public String getDisplayString(LabInstrument labInstrument) {
		return labInstrument.getDisplayString();
	}
	
}

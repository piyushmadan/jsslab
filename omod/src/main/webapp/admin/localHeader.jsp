<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin"><spring:message code="admin.title.short"/></a>
	</li>
	
	<openmrs:hasPrivilege privilege="">
	<li <c:if test='<%= request.getRequestURI().contains("jsslab/admin/settings") %>'>class="active"</c:if>>
		<a href="settings.htm">
			<spring:message code="jsslab.settings.link"/>
		</a>
	</li>
	</openmrs:hasPrivilege>
	<openmrs:hasPrivilege privilege="">
	<li <c:if test='<%= request.getRequestURI().contains("jsslab/admin/catalog") %>'>class="active"</c:if>>
		<a href="catalog.htm">
			<spring:message code="jsslab.catalog.link"/>
		</a>
	</li>
	</openmrs:hasPrivilege>
	<openmrs:hasPrivilege privilege="">
	<li <c:if test='<%= request.getRequestURI().contains("jsslab/admin/templates") %>'>class="active"</c:if>>
		<a href="templates.htm">
			<spring:message code="jsslab.templates.link"/>
		</a>
	</li>
	</openmrs:hasPrivilege>
	<openmrs:hasPrivilege privilege="">
	<li <c:if test='<%= request.getRequestURI().contains("jsslab/admin/instruments") %>'>class="active"</c:if>>
		<a href="instruments.htm">
			<spring:message code="jsslab.instruments.link"/>
		</a>
	</li>
	</openmrs:hasPrivilege>
	<openmrs:hasPrivilege privilege="">
	<li <c:if test='<%= request.getRequestURI().contains("jsslab/admin/reports") %>'>class="active"</c:if>>
		<a href="reports.htm">
			<spring:message code="jsslab.reports.link"/>
		</a>
	</li>
	</openmrs:hasPrivilege>
	
</ul>
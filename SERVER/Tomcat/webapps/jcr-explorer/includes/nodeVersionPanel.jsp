<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<h:dataTable var="version" value="#{ContentBean.currentNode.versions}">
	<h:column>
		<f:facet name="header">
			<h:outputText value="Name" />
		</f:facet>
		<h:outputText value="#{version.name}" />
	</h:column>

	<h:column>
		<f:facet name="header">
			<h:outputText value="Created" />
		</f:facet>
		<h:outputText value="#{version.created.time}" converter="DateTimeConverter"/>
	</h:column>
</h:dataTable>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@include file="includes/head.inc.html"%>
<body>
<f:view>

	<%@include file="includes/header.jsp"%>

	<h:form>

		<h:panelGrid columns="1">

			<h:panelGrid columns="2">
				<h:outputText value="Observation console is active: " />
				<h:outputText value="#{ObservationBean.isRegistered}" />
				<h:outputText value="Event type to observe: " />
				<h:selectOneMenu value="#{ObservationBean.eventType}">
					<f:selectItems value="#{ObservationBean.eventTypeList}" />
				</h:selectOneMenu>
				<h:outputText value="Path: " />
				<h:inputText value="#{ObservationBean.path}" size="40" />
				<h:outputText value="Is deep? " />
				<h:selectBooleanCheckbox value="#{ObservationBean.isDeep}" />
				<h:outputText value="Ignore changes made by this session? " />
				<h:selectBooleanCheckbox value="#{ObservationBean.noLocal}" />
			</h:panelGrid>

			<h:panelGrid columns="4">
				<h:commandButton action="success"
					actionListener="#{ObservationBean.register}"
					value="Register with these settings" styleClass="submit" />
				<h:commandButton action="success"
					actionListener="#{ObservationBean.unregister}"
					value="Unregister" styleClass="submit" />
				<h:commandButton actionListener="#{ObservationBean.clear}"
					immediate="true" value="Clear" styleClass="submit" />
				<h:commandButton action="refresh" value="Refresh"
					styleClass="submit" />
			</h:panelGrid>

			<t:dataTable var="event" value="#{ObservationBean.eventList}">

				<h:column>
					<f:facet name="header">
						<h:outputText value="Path" />
					</f:facet>
					<h:commandLink actionListener="#{ContentBean.changeCurrentNode}"
						action="goToContentPanel">
						<f:param id="newPath" name="newPath" value="#{event.path}" />
						<h:outputText value="#{event.path}" />
					</h:commandLink>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Type" />
					</f:facet>
					<h:outputText value="#{event.type}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="UserID" />
					</f:facet>
					<h:outputText value="#{event.userID}" />
				</h:column>

			</t:dataTable>

		</h:panelGrid>
	</h:form>
</f:view>

</body>
</html>

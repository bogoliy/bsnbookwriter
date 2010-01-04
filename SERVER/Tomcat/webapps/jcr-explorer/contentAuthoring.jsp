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

	<h:panelGrid columns="2"
		columnClasses="pageColumnNavigation, pageColumnMain"
		style="width:100%; margin:0px;">

		<%@include file="/includes/nodeList.jsp"%>

		<h:form enctype="multipart/form-data" id="nodeForm">
			<t:panelTabbedPane serverSideTabSwitch="false"
				activeTabStyleClass="mainTabPanelActiveTab"
				inactiveTabStyleClass="mainTabPanelInactiveTab"
				disabledTabStyleClass="mainTabPanelDisabledTab"
				tabContentStyleClass="mainTabPanelContent"
				activeSubStyleClass="mainTabActiveSub">

				<t:panelTab id="tab1" label="Explore">
					<jsp:include page="/includes/mainNodePanel.jsp" />
				</t:panelTab>

				<t:panelTab id="tab2" label="Versions"
					rendered="#{ContentBean.currentNode.versionable==true}">
					<jsp:include page="/includes/nodeVersionPanel.jsp" />
				</t:panelTab>

				<t:panelTab id="tab3" label="Lock"
					rendered="#{ContentBean.currentNode.lock != null}">
					<jsp:include page="/includes/nodeLockPanel.jsp" />
				</t:panelTab>

			</t:panelTabbedPane>
		</h:form>

	</h:panelGrid>
</f:view>
</body>
</html>

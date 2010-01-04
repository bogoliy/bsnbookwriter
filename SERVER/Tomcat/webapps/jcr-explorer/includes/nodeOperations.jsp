<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:div style="margin-top:15px;">
	<h:panelGrid columns="3" styleClass="nodeOperationsBox"
		headerClass="formTitle">

		<h:outputText value="Add mixin" />
		<h:selectOneMenu value="#{ContentBean.newAddMixin}">
			<f:selectItems value="#{ContentBean.currentNode.addableMixinNodeTypeList}" />
		</h:selectOneMenu>
		<h:commandButton action="#{ContentBean.addMixinNodeType}"
			value="Add Mixin NodeType" styleClass="submit" />

		<h:outputText value="Remove mixin" />
		<h:selectOneMenu value="#{ContentBean.removeMixin}">
			<f:selectItems value="#{ContentBean.currentNode.mixinNodeTypeList}" />
		</h:selectOneMenu>
		<h:commandButton action="#{ContentBean.removeMixinNodeType}"
			value="Remove Mixin NodeType" styleClass="submit" />

		<h:outputText value="Add named property" />
		<h:selectOneMenu value="#{ContentBean.newProperty}">
			<f:selectItems value="#{ContentBean.currentNode.propertyList}" />
		</h:selectOneMenu>
		<h:commandButton action="#{ContentBean.addProperty}"
			value="Add Property" styleClass="submit" />

		<h:outputText value="Add wildcard property" />
		<h:panelGroup>
			<h:inputText value="#{ContentBean.newWildcardPropertyName}"></h:inputText>
			<h:selectOneMenu value="#{ContentBean.newWildcardPropertyType}">
				<f:selectItems value="#{ContentBean.currentNode.wildcardPropertyList}" />
			</h:selectOneMenu>
		</h:panelGroup>
		<h:commandButton action="#{ContentBean.addWildcardProperty}"
			value="Add wildcard property" styleClass="submit" />
		<h:outputText value="Add Node" />
		<h:panelGroup>
			<h:selectOneMenu value="#{ContentBean.newNodeType}">
				<f:selectItems value="#{ContentBean.currentNode.nodeTypeList}" />
			</h:selectOneMenu>
			<h:inputText value="#{ContentBean.newNodeName}"></h:inputText>
		</h:panelGroup>
		<h:commandButton actionListener="#{ContentBean.addNode}"
			action="addNode" value="Add Node" styleClass="submit" />
		<h:outputText value="Move node to:" />
		<h:inputText value="#{ContentBean.moveToPath}"></h:inputText>
		<h:commandButton actionListener="#{ContentBean.moveNode}"
			action="moveNode" value="Move node" styleClass="submit" />
		<h:outputText value="Copy node to:" />
		<h:inputText value="#{ContentBean.copyToPath}"></h:inputText>
		<h:commandButton actionListener="#{ContentBean.copyNode}"
			action="copyNode" value="Copy node" styleClass="submit" />
		<h:outputText value="Export this node" />
		<h:panelGrid columns="2">
			<h:panelGroup>
				<h:selectBooleanCheckbox value="#{EximBean.exportRecursive}" />
				<h:outputText value="Recursive" />
			</h:panelGroup>
			<h:panelGroup>
				<h:selectBooleanCheckbox value="#{EximBean.exportBinaries}" />
				<h:outputText value="Export Binaries" />
			</h:panelGroup>
			<h:selectOneMenu value="#{EximBean.exportView}">
				<f:selectItems value="#{EximBean.exportViewList}" />
			</h:selectOneMenu>
		</h:panelGrid>
		<h:commandButton action="#{EximBean.exportData}" value="Start export"
			styleClass="submit" />

		<h:outputText value="Locking" />

		<h:panelGrid columns="2"
			rendered="#{ContentBean.currentNode.node.locked==false}">
			<h:panelGroup>
				<h:selectBooleanCheckbox value="#{ContentBean.lockDeep}" />
				<h:outputText value="Lock is deep" />
			</h:panelGroup>
			<h:panelGroup>
				<h:selectBooleanCheckbox value="#{ContentBean.lockSessionScoped}" />
				<h:outputText value="Lock is session-scoped" />
			</h:panelGroup>
		</h:panelGrid>
		<h:outputText value=""
			rendered="#{ContentBean.currentNode.node.locked==true}" />

		<h:commandButton action="#{ContentBean.lock}" value="Lock"
			styleClass="submit"
			rendered="#{ContentBean.currentNode.node.locked==false}" />
		<h:commandButton action="#{ContentBean.unlock}" value="Unlock"
			styleClass="submit"
			rendered="#{ContentBean.currentNode.node.locked==true}" />


		<h:panelGroup rendered="#{ContentBean.currentNode.rootNode == false}">
			<h:commandButton actionListener="#{ContentBean.deleteNode}"
				action="deleteNode" value="Delete Node" styleClass="submit"
				onclick="if (!confirm('Do you really want to delete this node?')) return false; clear_nodeForm();" />			
		</h:panelGroup>
		<h:outputText value="" rendered="#{ContentBean.currentNode.rootNode == false}" />
		<h:outputText value="" rendered="#{ContentBean.currentNode.rootNode == false}" />
	</h:panelGrid>
</t:div>

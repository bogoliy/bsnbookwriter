package org.bookwriter.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import com.bookwriter.model.Part;
import com.bookwriter.persist.PartManager;

@WebService
public class PartService {
	private static final String NO_PART = "There is no sush part!";

	

	@WebMethod
	public OMElement getPart(OMElement element) throws XMLStreamException {
		element.build();
		element.detach();
		String rootName = element.getLocalName();
		OMElement partElement = element.getFirstElement();
		String partElementName = partElement.getLocalName();
		String partElementValue = partElement.getText();
		// PartManager partManager = new PartManager();
		// Part part = partManager.getPart(1);
		System.out.println(partElementName + ": " + partElementValue);

		SOAPFactory factory = OMAbstractFactory.getSOAP12Factory();
		OMNamespace namespace = factory.createOMNamespace(
				"http://ws.bookwriter.org", "resp");
		OMElement resultElem = factory.createOMElement("part", namespace);

		resultElem
				.setText(/* part == null ? NO_PART : part.getText() */NO_PART);

		return resultElem;
	}

	public void addPart(OMElement element) {
		element.build();
		element.detach();
		String rootName = element.getLocalName();
		OMElement partElement = element.getFirstElement();
		String partElementName = partElement.getLocalName();
		String partElementValue = partElement.getText();

		System.out.println("Part was added");
	}
}

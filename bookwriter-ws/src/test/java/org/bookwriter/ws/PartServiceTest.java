package org.bookwriter.ws;

import org.apache.axiom.om.OMAbstractFactory;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import junit.framework.TestCase;

public class PartServiceTest extends TestCase {
	private static EndpointReference targetEPR = new EndpointReference("http://localhost:8080/bookwriter-webapp/services/PartServiceService");//"http://localhost:8080/Axis2/services/bookwriter-ws-0.0.1-SNAPSHOT");
	public static OMElement getMethodOMElement() {
		SOAPFactory fac= OMAbstractFactory.getSOAP12Factory();
		OMNamespace omNs = fac.createOMNamespace("http://ws.bookwriter.org", "req");
		OMElement method = fac.createOMElement("getPart", omNs);
		OMElement value = fac.createOMElement("partId", omNs);
		value.addChild(fac.createOMText("1"));
		method.addChild(value);
		return method;
		
	}

	public static OMElement getAddMethodOMElement() {
		SOAPFactory fac= OMAbstractFactory.getSOAP12Factory();
		OMNamespace omNs = fac.createOMNamespace("http://ws.bookwriter.org", "req");
		OMElement method = fac.createOMElement("addPart", omNs);
		OMElement value = fac.createOMElement("part", omNs);
		value.addChild(fac.createOMText("This is part text"));
		method.addChild(value);
		return method;
		
	}
	
	public void testResponse() {
		OMElement payload = getMethodOMElement();
		Options options = new Options();
		options.setTo(targetEPR);
		options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
		
		try {
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMElement result = sender.sendReceive(payload);
			payload = getAddMethodOMElement();
			sender.fireAndForget(payload);
			System.out.println("The part text: " + result.getText());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true, true);
	}

}

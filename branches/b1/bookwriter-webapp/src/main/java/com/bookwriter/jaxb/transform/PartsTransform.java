package com.bookwriter.jaxb.transform;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.bookwriter.jaxb.parts.PartsType;
import com.bookwriter.model.Part;

public class PartsTransform {
	public static PartsType transform(List<Part> parts) throws DatatypeConfigurationException {
		PartsType partsXSD = new PartsType();
		for (Part part : parts) {
			PartsType.Part partXSD = new PartsType.Part();
			partXSD.setBookId(new BigInteger(part.getBookId() + ""));
			partXSD.setPartId(new BigInteger(part.getPartId() + ""));
			partXSD.setKeywords(part.getKeywords());
			partXSD.setText(part.getText());
			partXSD.setUserId(new BigInteger(part.getUserId() + ""));
			partXSD.setDatetime(dateTimeToXMLGregorianCalendar(part
					.getDatetime()));
			partsXSD.getPart().add(partXSD);
		}
		return partsXSD;

	}

	public static XMLGregorianCalendar dateTimeToXMLGregorianCalendar(
			Date dateTime) throws DatatypeConfigurationException {
		DatatypeFactory dataTypeFactory = DatatypeFactory.newInstance();
		return dataTypeFactory.newXMLGregorianCalendar(dateTime.toGMTString());
	}
}

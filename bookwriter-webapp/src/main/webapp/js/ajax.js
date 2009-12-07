/*
 * ���������� ����� XMLHttpRequest ������ ��� false, ���� ������� ��� �� ������������
 */
function newXMLHttpRequest() {

	var xmlreq = false;

	if (window.XMLHttpRequest) {

		// �������� XMLHttpRequest ������ ��� ��-Microsoft ���������
		xmlreq = new XMLHttpRequest();

	} else if (window.ActiveXObject) {

		// �������� XMLHttpRequest � ������� MS ActiveX
		try {
			// ��������� ������� XMLHttpRequest ��� ������� ������
			// Internet Explorer

			xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e1) {

			// �� ������� ������� ��������� ActiveXObject

			try {
				// ������� �������, ������� ��������� ����� ������ ������
				// Internet Explorer

				xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

			} catch (e2) {

				// �� � ��������� ������� XMLHttpRequest � ������� ActiveX
			}
		}
	}

	return xmlreq;
}

function sendRequest(jsonConfig) {
	var url = jsonConfig.url;
	var method = jsonConfig.method;
	var params = jsonConfig.params;
	var responseFunction = jsonConfig.responseFunction;
	var errorFunction = jsonConfig.errorFunction;

	// ���������� ���������� XMLHttpRequest
	var req = newXMLHttpRequest();
	// �������� ��� ��������� ��������� ��������� ������
	// �� ������� �������
	var handlerFunction = getReadyStateHandler(req, updateCart);
	req.onreadystatechange = responseFunction;
	// ��������� HTTP-���������� � ������� POST-������ � �������� �������
	// ����������.
	// ������ �������� ����������, ��� ������ �����������.
	req.open(method, url, true);
	// ����������, ��� � ���������� ������� ���� ������
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// �������� �������������� ������, ��������� � ���, ��� � ���� ��������
	// ������������ ������� � �������.
	var requestText = "";
	for ( var i = 0; i < params.length; i++) {
		requestText += params[i].name;
		requestText += "=";
		requestText += params[i].value;
		requestText += "&";
	}
	req.send(requestText);
}

function getReadyStateHandler(req, responseXmlHandler) {

	// ���������� �������������� �������, ������� ��������� 
	// ������ XMLHttpRequest return function () {

	// ���� ��������� ������ "��������"
	if (req.readyState == 4) {

		// ���������, ������ ��  �������� ����� ������� 
		if (req.status == 200) {

			// ��������  XML ��������� 
			responseXmlHandler(req.responseXML);

		} else {

			// �������� ������ HTTP 
			alert("HTTP error: " + req.status);
		}
	}
}

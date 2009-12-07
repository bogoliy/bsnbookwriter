// global reference to XML document object
var xDoc = document.createElement('variants');
/*
 * Возвращает новый XMLHttpRequest объект или false, если браузер его не поддерживает
 */
function newXMLHttpRequest() {

	var xmlreq = false;

	if (window.XMLHttpRequest) {

		// Создадим XMLHttpRequest объект для не-Microsoft браузеров
		xmlreq = new XMLHttpRequest();

	} else if (window.ActiveXObject) {

		// Создадим XMLHttpRequest с помощью MS ActiveX
		try {
			// Попробуем создать XMLHttpRequest для поздних версий
			// Internet Explorer

			xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e1) {

			// Не удалось создать требуемый ActiveXObject

			try {
				// Пробуем вариант, который поддержат более старые версии
				// Internet Explorer

				xmlreq = new ActiveXObject("Microsoft.XMLHTTP");

			} catch (e2) {

				// Не в состоянии создать XMLHttpRequest с помощью ActiveX
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

	// Возвращает содержимое XMLHttpRequest
	var req = newXMLHttpRequest();
	// Оператор для получения сообщения обратного вызова
	// из объекта запроса
	var handlerFunction = getReadyStateHandler(req, responseFunction);
	req.onreadystatechange = handlerFunction;
	// Открываем HTTP-соединение с помощью POST-метода к сервлету корзины
	// покупателя.
	// Третий параметр определяет, что запрос асинхронный.
	req.open(method, url, true);
	// Определяет, что в содержимом запроса есть данные
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// Посылаем закодированные данные, говорящие о том, что я хочу добавить
	// определенный продукт в корзину.
	var requestText = "";
	for ( var i = 0; i < params.length; i++) {
		requestText += params[i].name;
		requestText += "=";
		requestText += params[i].value;
		if (i != params.length -1 ) 
			requestText += "&";
	}
	//alert(responseFunction);
	req.send(requestText);
}

function getReadyStateHandler(req, responseXmlHandler) {

	// Возвращает неопределенную функцию, которая считывает 
	// данные XMLHttpRequest return function () {
	// Если требуется статус "закончен"
	// Return an anonymous function that listens to the 
    // XMLHttpRequest instance
	return function () {
	if (req.readyState == 4) {
		// Проверяем, пришел ли  успешный ответ сервера 
		if (req.status == 200) {
			// Передает  XML оператору 
			responseXmlHandler(req.responseXML);

		} else {

			// Возникла ошибка HTTP 
			alert("HTTP error: " + req.status);
		}
	}
	}
}

function setXDOC(resXML) {
	xDoc = resXML;
}

function error(errorMsg) {
	alert(errorMsg);
}
// JavaScript Document
// initialize first time -- invoked onload

function init() {
    String.prototype.startsWith = function(str)
    {return (this.match("^"+str)==str)};
    String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };
    // confirm browser supports needed features and load .xml file
    //if (verifySupport(xFile)) {
    //}
}



function Pane(title, content) {
    this.title = title;
    this.content = content;
    this.show = show;
    this.titleColor = "blue";
}

function Pane(title, content, titleColor) {
    this.title = title;
    this.content = content;
    this.show = show;
    this.titleColor = titleColor;
}

function show() {
    var titleText = new String (this.title);
    var searchInput  = document.createElement('input');
    searchInput.type = "text";
    searchInput.id = "searchInput";
    searchInput.value = this.content;
    searchInput.onclick = clearInput;
    //searchInput.onblur = fillInput;
    searchInput.onkeyup = showProposals;
    searchInput.parent = this;
    document.getElementById("searchContentPanel").appendChild(searchInput) ;
    
    var proposalsDiv  = document.createElement('div');
    proposalsDiv.id = "proposalsDiv";
    proposalsDiv.display = "none";
    proposalsDiv.className = "proposalsDiv";
    
    this.proposalsDiv = proposalsDiv;
    document.getElementById("roundcont").appendChild(proposalsDiv) ;
}

function showProposals() {
// Draw table from xDoc document tree data
    var tr, td, i, j, oneRecord;
    
    var proposalsDiv = document.getElementById("proposalsDiv");   
    if (proposalsDiv.childNodes.length > 0) {
        proposalsDiv.removeChild(proposalsDiv.firstChild);
    }
    var proposalsTable  = document.createElement('table');  
    proposalsTable.id = "proposalsTable";
    proposalsDiv.appendChild(proposalsTable);
    proposalsDiv.zIndex = 100;
    //Send request
	sendRequest({"method" : "POST",
		"url" : "http://localhost:8080/bookwriter-webapp/test",
		"params": [{"name" : "str", "value" : document.getElementById("searchInput").value.trim()}],
		"responseFunction" : setXDOC,
		"errorFunction" : error});
    
    // node tree
    var data = xDoc.getElementsByTagName("variants")[0];
    // for td class attributes
    if (data != null)
    for (i = 0; i < data.childNodes.length; i++) {
        // use only 1st level element nodes
        if (data.childNodes[i].nodeType == 1 && data.childNodes[i].firstChild.nodeValue.trim().startsWith(
        document.getElementById("searchInput").value.trim()) && 
        document.getElementById("searchInput").value.trim() != "") {
            tr = document.createElement('tr');
            td = document.createElement('td');
            tr.appendChild(td);
            proposalsTable.appendChild(tr);
            td.innerHTML = data.childNodes[i].firstChild.nodeValue;
            td.onclick = select;
        }
    }
    proposalsDiv.style.left = document.getElementById("searchInput").style.left + 10;
    proposalsDiv.style.top = document.getElementById("searchInput").style.top + 30;

    proposalsDiv.display = "block";
}

// get and identify the source of the event object
function getTarget(x){
    x = x || window.event;
    return x.target || x.srcElement;
} 

function select(event) {
	var t = getTarget(event); 
	document.getElementById("searchInput").value = t.innerHTML;
}

function clearInput() {
    if (document.getElementById("searchInput").value == searchInput.parent.content) {
        document.getElementById("searchInput").value = "";
    }
}

function fillInput() {
    if (document.getElementById("searchInput").value == "") {
        document.getElementById("searchInput").value = searchInput.parent.content;
    }
}

function MessageWindow(content) {
    var messageWindow = window.open ("", "Message", "toolbar=0, menubar=0, innerHeight=200, innerWidth=300");
    messageWindow.document.write(content);
}

// verify that browser supports XML features and load external .xml file
function verifySupport(xFile) {
    if (document.implementation && document.implementation.createDocument) {
        // this is the W3C DOM way, supported so far only in NN6
        xDoc = document.implementation.createDocument("", "theXdoc", null);
    } else if (typeof ActiveXObject != "undefined") {
        // make sure real object is supported (sorry, IE5/Mac)
        if (document.getElementById("msxml").async) {
            xDoc = new ActiveXObject("Msxml.DOMDocument");
        }
    }
    if (xDoc && typeof xDoc.load != "undefined") {
        // load external file (from same domain)
        xDoc.load(xFile);
        return true;
    } else {
        var reply = confirm("This example requires a browser with XML support, such as IE5+/Windows or Netscape 6+.\n \nGo back to previous page?");
        if (reply) {
            history.back();
        }
    }
    return false;
}

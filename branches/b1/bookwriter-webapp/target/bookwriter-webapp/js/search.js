// JavaScript Document
// initialize first time -- invoked onload
// global reference to XML document object
var xDoc;
//global reference to XML document object
var xDoc = document.createElement('variants');
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
	var handlerFunction = getReadyStateHandler(req, responseFunction);
	req.onreadystatechange = handlerFunction;
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
		if (i != params.length -1 ) 
			requestText += "&";
	}
	//alert(responseFunction);
	req.send(requestText);
}

function getReadyStateHandler(req, responseXmlHandler) {

	// ���������� �������������� �������, ������� ��������� 
	// ������ XMLHttpRequest return function () {
	// ���� ��������� ������ "��������"
	// Return an anonymous function that listens to the 
    // XMLHttpRequest instance
	return function () {
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
}

function setXDOC(resXML) {
	if (typeof(resXML.getElementsByTagName("variants")[0]) != undefined) {
		xDoc = resXML;
		showProposals();
		store[document.getElementById("searchInput").value.trim()] = resXML;
	}
}

function error(errorMsg) {
	alert(errorMsg);
}

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
    searchInput.onkeyup = retrieveData;
    searchInput.parent = this;
    var searchIcon = document.getElementById("searchIcon");
    document.getElementById("searchInputContainer").appendChild(searchInput) ;
    
    var proposalsDiv  = document.createElement('div');
    proposalsDiv.id = "proposalsDiv";
    proposalsDiv.display = "none";
    proposalsDiv.className = "proposalsDiv";
    
    this.proposalsDiv = proposalsDiv;
    document.getElementById("roundcont").appendChild(proposalsDiv) ;
}

var store = new Array();
function retrieveData() {
    //Send request
    xDoc = store[ document.getElementById("searchInput").value.trim()];
    if (typeof(xDoc) == "undefined") {
	sendRequest({"method" : "POST",
		"url" : "http://localhost:8080/bookwriter-webapp/test",
		"params": [{"name" : "str", "value" : document.getElementById("searchInput").value.trim()}],
		"responseFunction" : setXDOC,
		"errorFunction" : error});	
    } else{
    	showProposals();
    }
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
	    proposalsDiv.style.top = document.getElementById("searchInput").style.top + 10;

	    proposalsDiv.display = "block";
	}


function clearInput() {
    if (document.getElementById("searchInput").value == document.getElementById("searchInput").parent.content) {
        document.getElementById("searchInput").value = "";
    }
}

function fillInput() {
    if (document.getElementById("searchInput").value == "") {
        document.getElementById("searchInput").value = searchInput.parent.content;
    }
}

function select(event) {
	var t = getTarget(event); 
	document.getElementById("searchInput").value = t.innerHTML;
}

//get and identify the source of the event object
function getTarget(x){
    x = x || window.event;
    return x.target || x.srcElement;
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

function search() {
    window.location="http://localhost:8080/bookwriter-webapp/search.x?str="+encodeURIComponentNew(document.getElementById("searchInput").value.trim());
}

<!--
/* ***************************
** Most of this code was kindly 
** provided to me by
** Andrew Clover (and at doxdesk dot com)
** http://and.doxdesk.com/ 
** in response to my plea in my blog at 
** http://worldtimzone.com/blog/date/2002/09/24
** It was unclear whether he created it.
*/
function utf8(wide) {
  var c, s;
  var enc = "";
  var i = 0;
  while(i<wide.length) {
    c= wide.charCodeAt(i++);
    // handle UTF-16 surrogates
    if (c>=0xDC00 && c<0xE000) continue;
    if (c>=0xD800 && c<0xDC00) {
      if (i>=wide.length) continue;
      s= wide.charCodeAt(i++);
      if (s<0xDC00 || c>=0xDE00) continue;
      c= ((c-0xD800)<<10)+(s-0xDC00)+0x10000;
    }
    // output value
    if (c<0x80) enc += String.fromCharCode(c);
    else if (c<0x800) enc += String.fromCharCode(0xC0+(c>>6),0x80+(c&0x3F));
    else if (c<0x10000) enc += String.fromCharCode(0xE0+(c>>12),0x80+(c>>6&0x3F),0x80+(c&0x3F));
    else enc += String.fromCharCode(0xF0+(c>>18),0x80+(c>>12&0x3F),0x80+(c>>6&0x3F),0x80+(c&0x3F));
  }
  return enc;
}

var hexchars = "0123456789ABCDEF";

function toHex(n) {
  return hexchars.charAt(n>>4)+hexchars.charAt(n & 0xF);
}

var okURIchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";

function encodeURIComponentNew(s) {
  var s = utf8(s);
  var c;
  var enc = "";
  for (var i= 0; i<s.length; i++) {
    if (okURIchars.indexOf(s.charAt(i))==-1)
      enc += "%"+toHex(s.charCodeAt(i));
    else
      enc += s.charAt(i);
  }
  return enc;
}

function buildURL(fld)
{
	if (fld == "") return false;
	var encodedField = "";
	var s = fld;
	if (typeof encodeURIComponent == "function")
	{
		// Use JavaScript built-in function
		// IE 5.5+ and Netscape 6+ and Mozilla
		encodedField = encodeURIComponent(s);
	}
	else 
	{
		// Need to mimic the JavaScript version
		// Netscape 4 and IE 4 and IE 5.0
		encodedField = encodeURIComponentNew(s);
	}
	alert ("New encoding: " + encodeURIComponentNew(fld) +
		 "\n           escape(): " + escape(fld));
	return true;
}


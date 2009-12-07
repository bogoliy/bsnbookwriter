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
	var handlerFunction = getReadyStateHandler(req, updateCart);
	req.onreadystatechange = responseFunction;
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
		requestText += "&";
	}
	req.send(requestText);
}

function getReadyStateHandler(req, responseXmlHandler) {

	// Возвращает неопределенную функцию, которая считывает 
	// данные XMLHttpRequest return function () {

	// Если требуется статус "закончен"
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

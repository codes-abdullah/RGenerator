//rjs.js must be included here

function buildUrl() {
	//jdbc:derby://localhost:1526/mydb;create=true;
	let p = ["", "://", ":", "/", ";"];
	let url = "";
	for (let i = 0; i < ARRAY_JDBC_URL_PARTS.length; i++) {
		let el = document.getElementsByName(ARRAY_JDBC_URL_PARTS[i])[0];
		url += p[i]+el.value;
	}

	var el = document.getElementById(ID_DB_URL);
	el.href = url;
	el.innerText = url;

	console.log("done buildong url: " + url);

}

function testUrl() {

	document.getElementById(ID_JDBC_TEST_RESULT).innerText = "testing..";
	disableForTesting(true);
	let form = new FormData();
	for (let i = 0; i < ARRAY_JDBC_URL_FORM_PARTS.length; i++) {
		console.log(ARRAY_JDBC_URL_FORM_PARTS[i] + " -> " + document.getElementById(ARRAY_JDBC_URL_FORM_PARTS[i]).value);
		form.append(ARRAY_JDBC_URL_FORM_PARTS[i], document.getElementById(ARRAY_JDBC_URL_FORM_PARTS[i]).value);
	}
	form.append(KEY_TEST_URL, true);

	let usp = new URLSearchParams(form);
	let query = usp.toString();

	let url = window.location.pathname;
	let i = url.indexOf("/", 1);
	if (i >= 0) {
		url = url.substring(0, i);
	}

	url = url + SERVLET_INSTALL + "?" + query;

	const xhr = new XMLHttpRequest();
	xhr.open("GET", url, true);

	xhr.onload = function(e) {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {

				let result = "valid url: false";
				let rt = xhr.responseText;
				let i = rt.indexOf(KEY_TEST_URL);
				if (i >= 0) {
					result = "valid url: " + rt.substring(KEY_TEST_URL.length + 1);
				}
				document.getElementById(ID_JDBC_TEST_RESULT).innerText = result;
			} else {
				console.log(xhr.status);
				console.log(xhr.statusText);
				console.log(xhr.responseText);
				let parser = new DOMParser();
				let doc = parser.parseFromString(xhr.responseText, "text/html");
				let error = document.getElementById("error");
				error.innerHTML = doc.body.innerHTML;
				console.error("server msg is: " + xhr.responseText);
				document.getElementById(KEY_TEST_URL_RESULT).innerHTML = "<font color=\"red\">Failed</font>";
				//window.location.replace("error.jsp");


			}
			disableForTesting(false);
		}
	};
	xhr.onerror = function(e) {
		console.log("something went wrong: ");
		console.error(xhr.statusText);
		document.getElementById(KEY_TEST_URL_RESULT).innerHTML = "<font color=\"red\">Failed</font>";
	};
	xhr.send();

}

function disableForTesting(diable) {
	document.getElementById("test").disabled = diable;
	document.getElementById("install").disabled = diable;
	for (let i = 0; i < ARRAY_JDBC_URL_FORM_PARTS.length; i++) {
		document.getElementById(ARRAY_JDBC_URL_FORM_PARTS[i]).disabled = diable;
	}
}


window.onload = buildUrl



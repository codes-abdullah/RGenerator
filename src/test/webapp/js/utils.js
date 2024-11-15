//rjs.js must be included here


function init() {
	console.log(getScriptFileName() + " loaded");
}

function valueOf(elementId) {
	var elem = document.getElementById(elementId);
	return elem.innerText;
}


function editUserByRowId(id) {
	var row = document.getElementById(id);
	var tds = row.children;
	var form = new FormData();
	for (var i = 0; i < tds.length; i++) {
		var td = tds[i];
		
		if (td.childElementCount == 0) {
			form.append(td.getAttribute(ID_ID), td.innerText);
		}
	}
	var usp = new URLSearchParams(form);
	var query = usp.toString();
	var url = location.protocol + "//" + location.host + getContextPath() + JSP_EDIT_USERS + "?" + query;
	window.location.replace(url);
}

function getContextPath() {
	let url = window.location.pathname;
	var i = url.indexOf('/', 1);
	if (i >= 0) {
		return url.substring(0, i);
	}
	return url;
}

function getScriptFileName(){
	var scripts = document.getElementsByTagName('script'); 
    var filepath = scripts[ scripts.length-1 ].src;
	var ix = filepath.lastIndexOf("/");
	return filepath.substring(ix+1);
}

/*
How it work?
<div>
	<span>
	<i class="fas fa-eye"></i>
	<span>
</div>
*/
function togglePasswordVisibility() {
	const passwordField = document.getElementById(KEY_ID_PASSWORD);
	const togglePassword = document.querySelector("." + KEY_CLASS_ICON_CONTAINER + " span i");
	if (passwordField.type === "password") {
		passwordField.type = "text";
		togglePassword.classList.remove("fa-eye");
		togglePassword.classList.add("fa-eye-slash");
	} else {
		passwordField.type = "password";
		togglePassword.classList.remove("fa-eye-slash");
		togglePassword.classList.add("fa-eye");
	}
	passwordField.focus();
}

window.addEventListener("load", init, false);    

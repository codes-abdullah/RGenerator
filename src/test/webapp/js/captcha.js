//rjs.js must be included here

function init() {
	generateCaptcha();
}


function generateCaptcha() {
	let captchaOutput = document.getElementById(KEY_ID_CAPTCHA);

	var captchaStr = "";

	let alphaNums = [//
		'A', 'B', 'C', 'D', 'E', 'F', 'G',
		'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U',
		'V', 'W', 'X', 'Y', 'Z',
		'0', '1', '2', '3', '4',
		'5', '6', '7', '8', '9'];


	let emptyArr = [];

	for (let i = 1; i <= 4; i++) {
		emptyArr.push(alphaNums[Math.floor(Math.random() * alphaNums.length)]);
	}

	captchaStr = emptyArr.join('');
	captchaOutput.value = captchaStr;

	let captchaInput = document.getElementById(KEY_ID_CAPTCHA_INPUT);
	captchaInput.pattern = captchaStr;
}





window.addEventListener("load", init, false);   
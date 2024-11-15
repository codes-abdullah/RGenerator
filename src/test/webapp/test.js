function setCss() {
console.log("working");	
	var head = document.getElementsByTagName('head')[0];
	var link = document.createElement('link');
	link.id = "cssId";
	link.rel = 'stylesheet';
	link.type = 'text/css';
	link.href = 'widgets/x.css';
	link.media = 'all';
	head.appendChild(link);
	console.log("added");
}
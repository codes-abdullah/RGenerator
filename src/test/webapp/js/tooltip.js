//rjs.js must be included here

/*

How it work?

<div onmouseenter="onTooltipContainerMouseEnter(this)">
	<div class="tooltip">
		tooltip text
	</div>
</div>
*/

function getTooltipElement(container) {
	for (var i = 0; i < container.children.length; i++) {
		var elem = container.children[i];
		if (elem.className == 'tooltip')
			return elem;
	}
	container.onmouseenter = null;
	container.onmouseleave = null;
	container.removeEventListener('mousemove', updateTooptipCoordinates, false);
	throw TypeError("tooltip-container must have direct-child with id='tooltip'");
}

function onTooltipContainerMouseEnter(container) {
	var tooltip = getTooltipElement(container);
	tooltip.style.visibility = 'visible';
	container.addEventListener('mousemove', function(event) {
		updateTooptipCoordinates(event, tooltip);
	}, false);
	container.addEventListener('mouseleave', onTooltipContainerMouseLeave, false);
}


function onTooltipContainerMouseLeave(event) {
	var container = event.target;
	container.removeEventListener('mousemove', updateTooptipCoordinates, false);
	var tooltip = getTooltipElement(container);
	tooltip.style.visibility = 'hidden';
}

function updateTooptipCoordinates(event, tooltip) {
	var x = (event.layerX -tooltip.offsetWidth);
	var y = (event.layerY -tooltip.offsetHeight);
	tooltip.style.left = x + "px";
	tooltip.style.top = y + "px";

}

/*
function bodyEnter(event) {
	//console.log(event);
	var screenX = document.getElementById('screenX');
	var screenY = document.getElementById('screenY');
	var pageX = document.getElementById('pageX');
	var pageY = document.getElementById('pageY');
	var eventX = document.getElementById('eventX');
	var eventY = document.getElementById('eventY');

	var clientX = document.getElementById('clientX');
	var clientY = document.getElementById('clientY');

	var layerX = document.getElementById('layerX');
	var layerY = document.getElementById('layerY');

	var movementX = document.getElementById('movementX');
	var movementY = document.getElementById('movementY');
	
	
	
	
	var offsetX = document.getElementById('offsetX');
	var offsetY = document.getElementById('offsetY');
	
	
	
	var x = document.getElementById('x');
	var x = document.getElementById('x');
	
	
	event.addEventListener('mousemove', function(event) {
		console.log(event);
		screenX.innerText = `screenX ${event.screenX}`;
		screenY.innerText = `screenY ${event.screenY}`;

		pageX.innerText = `pageX ${event.pageX}`;
		pageY.innerText = `pageY ${event.pageY}`;

		eventX.innerText = `eventX ${event.eventX}`;
		eventY.innerText = `eventY ${event.eventY}`;


		clientX.innerText = `clientX ${event.clientX}`;
		clientY.innerText = `clientY ${event.clientY}`;

		layerX.innerText = `layerX ${event.layerX}`;
		layerY.innerText = `layerY ${event.layerY}`;

		movementX.innerText = `movementX ${event.movementX}`;
		movementY.innerText = `movementY ${event.movementY}`;


		offsetX.innerText = `offsetX ${event.offsetX}`;
		offsetY.innerText = `offsetY ${event.offsetY}`;


		x.innerText = `x ${event.x}`;
		y.innerText = `y ${event.y}`;

	}, false);
}*/



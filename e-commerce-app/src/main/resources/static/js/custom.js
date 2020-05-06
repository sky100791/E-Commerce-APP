function myFunction(){
	alert("Button Pressed");
}
function getProducts(){
	var request = new XMLHttpRequest();
	request.open("GET", "http://localhost:9001/products/recommendations", true);
	request.onreadystatechange = function(){
		if(request.readyState==4 && request.status==200){
			var response = JSON.parse(equest.responseText);
			var tableTop = '<table><tr><th>Name</th><th>Description</th><th>Price</th><th>Currency</th></tr>';
			var main = "";
			for(i=0; i<response.length; i++){
				main += "<tr><td>" + response[i].name + "</td><td>" + response[i].description + "</td><td>" + response[i].price + "</td><td>" + response[i].currency + "</tr>"; 
			}
			var tableBottom = '</table>';
			var table = tableTop + main + tableBottom;
			document.getElementById("productInfo").innerHTML = table;
		}
	}
	request.send();
}
window.onload = function(){
	getProducts();
}
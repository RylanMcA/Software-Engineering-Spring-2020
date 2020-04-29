document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;

	for (let i = 0; i < productListElements.length; i++) {
		productListElements[i].addEventListener("click", productClick);
	}
});

function findClickedListItemElement(clickedTarget) {
	if (clickedTarget.tagName.toLowerCase() === "li") {
		return clickedTarget;
	} else {
		let ancestorIsListItem = false;
		let ancestorElement = clickedTarget.parentElement;

		while (!ancestorIsListItem && (ancestorElement != null)) {
			ancestorIsListItem = (ancestorElement.tagName.toLowerCase() === "li");

			if (!ancestorIsListItem) {
				ancestorElement = ancestorElement.parentElement;
			}
		}

		return (ancestorIsListItem ? ancestorElement : null);
	}
}

function productClick(event) {
	let listItem = findClickedListItemElement(event.target);

	if(getTransactionId() === ""){
	window.location.assign(
		"/productDetail/"
		+ listItem.querySelector("input[name='productId'][type='hidden']").value);
	} else {
		saveData = {
			id: getProductId(),
			transactionId: getTransactionId()

		};
		createUrl = "/api/transaction/"+getTransactionId()+getProductId();
		ajaxPost(createUrl,saveData,(callbackResponse) => {
			if (isErrorResponse(callbackResponse)) {
				return;
			}
			window.location.assign(callbackResponse.data.redirectUrl);
		});
	}



	/*function ajaxPost(resourceRelativeUri, data, callback) {
	return ajax(resourceRelativeUri, "POST", data, callback);
}*/

}

function productSearch() {
	// temporary alert
	alert(document.getElementById("searchInput").value);
	// refresh the page, sending the search input to the java code
	// it will return the list of products with that search term
	// if no products returned, no results screen... html stuff
}


function getTransactionIdElement(){
	return document.getElementById("transactionId");
}

function getTransactionId(){
	return getTransactionIdElement().value;
}

function getProductIdElement(){
	return document.getElementById("productId");
}

function getProductId(){
	return getProductIdElement().value;
}
document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;

	for (let i = 0; i < productListElements.length; i++) {
		productListElements[i].addEventListener("click", productClick);
	}

	getSummaryElementId().addEventListener("click",viewSummary);
	document.getElementById("searchAction").addEventListener("click", productSearch);
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
	clickedItem = listItem.querySelector("input[name='productId'][type='hidden']").value;

	if(getTransactionId() === ""){
	window.location.assign(
		"/productDetail/"
		+ clickedItem);
	} else {

		createUrl = "/api/transaction/"+getTransactionId()+"/"+clickedItem;

		ajaxPost(createUrl,{},(callbackResponse) => {
			if (isErrorResponse(callbackResponse)) {
				return;
			}
			window.location.assign(callbackResponse.data.redirectUrl);
		});
	}
}

function viewSummary(event){
	if(transactionId != ""){
		window.location.assign("/cart/"+getTransactionId());
	}

}


function productSearch(event) {
	const searchEditElement = event.target.previousElementSibling;
	const productListElements = document.getElementById("productsListing").children;
	for (let i = 0; i < productListElements.length; i++) {
		const lookupcode = productListElements[i]
			.querySelector("span.productLookupCodeDisplay")
			.innerHTML;
		if (lookupcode.toLowerCase().indexOf(searchEditElement.value.toLowerCase()) >= 0) {
			if (productListElements[i].classList.contains("hidden")) {
				productListElements[i].classList.remove("hidden");
			}
		} else {
			if (!productListElements[i].classList.contains("hidden")) {
				productListElements[i].classList.add("hidden");
			}
		}
	}
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

function getSummaryElementId(){
	return document.getElementById("summaryPage")
}


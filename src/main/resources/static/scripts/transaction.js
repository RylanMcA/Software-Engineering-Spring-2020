document.addEventListener("DOMContentLoaded", () => {
	const productListElements = document.getElementById("productsListing").children;

	for (let i = 0; i < productListElements.length; i++) {
		productListElements[i].addEventListener("click", removeClick);
	}
    
    getCancelButtonId().addEventListener("click", cancelActionClick);
    getPaymentButton().addEventListener("click",paymentActionClick);
    
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

function removeClick(event){
	let listItem = findClickedListItemElement(event.target);
    clickedItem = listItem.querySelector("input[name='productId'][type='hidden']").value;
    
    const deleteActionElement = event.target;
    deleteActionUrl = "/api/transaction/"+getTransactionId()+clickedItem;

    ajaxDelete(deleteActionUrl, (callbackResponse) => {
		deleteActionElement.disabled = false;

		if (isSuccessResponse(callbackResponse)) {
			window.location.replace(callbackResponse.data.redirectUrl);
		}
	});

}

function paymentActionClick(event){
    window.location.assign("/payment");
}

//Cancel the entire transaction
function cancelActionClick(event) {
	const deleteActionElement = event.target;
	const deleteActionUrl = ("/api/transaction/" + getTransactionId());

	deleteActionElement.disabled = true;

	ajaxDelete(deleteActionUrl, (callbackResponse) => {
		deleteActionElement.disabled = false;

		if (isSuccessResponse(callbackResponse)) {
			window.location.replace("/mainMenu");
		}
	});
}


//getters
function getTransactionElementId() {
	return document.getElementById("transactionId");
}

function getTransactionId(){
    return getTransactionElementId().value;
}

function getCancelButtonId(){
    return document.getElementById("cancelButton")
}

function getPaymentButton(){
    return document.getElementById("payment")
}

function getRemoveButtonId(){
    return document.getElementById("remove")
}
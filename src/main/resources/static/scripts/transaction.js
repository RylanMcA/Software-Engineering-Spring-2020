document.addEventListener("DOMContentLoaded", () => {

	getCancelButtonId().addEventListener("click", deleteActionClick);

});

function deleteActionClick(event) {
	const deleteActionElement = event.target;
	const deleteActionUrl = ("/api/transaction/" + getTransactionId());

	deleteActionElement.disabled = true;

	ajaxDelete(deleteActionUrl, (callbackResponse) => {
		deleteActionElement.disabled = false;

		if (isSuccessResponse(callbackResponse)) {
			window.location.replace("/mainMenu");
		}
	});
};


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
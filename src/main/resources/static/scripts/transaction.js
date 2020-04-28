document.addEventListener("DOMContentLoaded", () => {

	getCancelButtonId().addEventListener("click", cancelTransaction);

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
function getTransactionId() {
	return document.getElementById("transactionId");
}

function getCancelButtonId(){
    return document.getElementById("cancelButton")

}
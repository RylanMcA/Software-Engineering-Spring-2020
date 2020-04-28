document.addEventListener("DOMContentLoaded", function(event) {
	const cardNumEditElement = getCardNumEditElement();
	cardNumEditElement.focus();
	cardNumEditElement.select();
});

function validateForm() {
	const cardNumEditElement = getCardNumEditElement();
	var regex = /\d{4}\s\d{4}\s\d{4}/;
	if (!regex.test(cardNumEditElement.value)) {

		displayError("Please provide a valid Credit Card Number.");

		cardNumEditElement.focus();
		cardNumEditElement.select();
		
		return false;
	}

	const expDateEditElement = getExpDateEditElement();
	var nextRegex = /\d{2}\/\d{2}/;
	if (!nextRegex.test(expDateEditElement.value)) {

		displayError("Please provide a valid Expiration Date.");

		expDateEditElement.focus();
		expDateEditElement.select();
		
		return false;
    }
    
	const secNumEditElement = getSecNumEditElement();
	var lastRegex = /\d{3}/;
    if (!lastRegex.test(secNumEditElement.value)) {

		displayError("Please provide a valid Security Number.");
		
		secNumEditElement.focus();
		secNumEditElement.select();

		return false;
    }

	return true;
}

//Getters and setters
function getCardNumEditElement() {
	return document.getElementById("cardNum");
}

function getExpDateEditElement() {
	return document.getElementById("expDate");
}

function getSecNumEditElement() {
    return document.getElementById("secNum");
}
//End getters and setters


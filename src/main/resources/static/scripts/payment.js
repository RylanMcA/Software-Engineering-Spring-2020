document.addEventListener("DOMContentLoaded", function(event) {
	const cardNumEditElement = getCardNumEditElement();
	cardNumEditElement.focus();
	cardNumEditElement.select();
});

function validateForm() {
	const cardNumEditElement = getCardNumEditElement();
	if (true) {

		displayError("Please provide a valid Credit Card Number.");

		cardNumEditElement.focus();
		cardNumEditElement.select();
		
		return false;
	}

	const expDateEditElement = getExpDateEditElement();
	if (!expDateEditElement.value.matches("\d{2}/\d{2}")) {

		displayError("Please provide a valid Expiration Date.");

		expDateEditElement.focus();
		expDateEditElement.select();
		
		return false;
    }
    
    const secNumEditElement = getSecNumEditElement();
    if (!secNumEditElement.value.matches("\d{3}")) {

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


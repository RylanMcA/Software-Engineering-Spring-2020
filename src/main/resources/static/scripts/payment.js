document.addEventListener("DOMContentLoaded", function(event) {
	const cardNumEditElement = getCardNumEditElement();
	cardNumEditElement.focus();
	cardNumEditElement.select();
});

function validateForm() {
	const cardNumEditElement = getCardNumEditElement();
	if (isNaN(Number(cardNumEditElement.value))
		|| (Number(cardNumEditElement.value) <= 0)) {

		displayError("Please provide a valid Credit Card Number.");

		cardNumEditElement.focus();
		cardNumEditElement.select();
		
		return false;
	}

	const expDateEditElement = getExpDateEditElement();
	if ((isNaN(Number(expDateEditElement.value))
		|| (expDateEditElement.value.trim() === "")) {

		displayError("Please provide a valid Expiration Date.");

		passwordEditElement.focus();
		passwordEditElement.select();
		
		return false;
    }
    
    const secNumEditElement = getSecNumEditElement();
    if (isNaN(Number(secNumEditElement.value)) || ) {

        displayError("Please provide a valid Security Number.")
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


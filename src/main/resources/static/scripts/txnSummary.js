document.addEventListener("DOMContentLoaded", () => {

    getSignReturnElement().addEventListener("click", returnClickHandler);
});

function returnClickHandler(){
    window.location.assign("/mainMenu")

}

function getTotalPrice(){
    //Grab all elements by id, add them all them all up
    //Display below the product's listed
    
}

//Getters/Setters 
function getSignReturnElement() {
	return document.getElementById("returnImage");
}
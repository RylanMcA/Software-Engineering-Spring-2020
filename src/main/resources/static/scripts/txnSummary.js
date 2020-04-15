document.addEventListener("DOMContentLoaded", () => {

    getSignReturnElement().addEventListener("click", returnClickHandler);
});

function returnClickHandler(){
    window.location.assign("/mainMenu")

}

//Getters/Setters 
function getSignReturnElement() {
	return document.getElementById("returnImage");
}
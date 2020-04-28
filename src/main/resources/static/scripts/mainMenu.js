document.addEventListener("DOMContentLoaded", () => {	
    getTransactionIdElement().addEventListener("click", txnActionClick);
    getProductListIdElement().addEventListener("click", prodActionClick);
    getEmployeeCreateIdElement().addEventListener("click",createActionClick);
    getEmployeeDetailIdElement().addEventListener("click",detailActionClick);
    getReportIdElement().addEventListener("click",reportActionClick);
});

function txnActionClick(event){
    ajaxPost("/api/transaction/", {}, (callbackResponse) => {
        if (isErrorResponse(callbackResponse)) {
            return;
        }
        window.location.assign(callbackResponse.data.redirectUrl);
    });
}

function prodActionClick(event){
    window.location.assign("productListing");
}
//commit
function createActionClick(event){
    window.location.assign("employeeDetail");
}

function detailActionClick(event){
    displayError("Functionality has not been implemented yet");
}

function reportActionClick(event){
    displayError("Functionality has not been implemented yet");
}


//Getters
function getTransactionIdElement(){
    return document.getElementById("startTxn");
}

function getProductListIdElement(){
    return document.getElementById("prodList");
}

function getEmployeeCreateIdElement(){
    return document.getElementById("empCreate");
}

function getEmployeeDetailIdElement(){
    return document.getElementById("empDetails");
}

function getReportIdElement(){
    return document.getElementById("cashReport");
}

dicoPlay.data =[];
var request = new XMLHttpRequest();
request.onerror = function (error){
	retour.innerHTML = 'la connection avec le back-end est en erreur';
	if (this.readyState ===4 && this.status ===0) retour.innerHTML = retour.innerHTML + ". il semble qu'il soit éteind";
}
request.onreadystatechange = function(){
	if (this.readyState ===4 && this.status === 200){
		dicoPlay.data = JSON.parse (this.responseText);
	}
};
request.open ('GET', 'journal.json', false);
request.send();
dpInit();
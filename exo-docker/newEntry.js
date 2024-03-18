const envelope = document.getElementsByTagName ('textarea')[0];
const title = document.getElementsByTagName ('input')[0];
const retour = document.getElementById ('retour');
const urlBackend = 'http://localhost:1407/new';

function erase(){
	title.value ="";
	envelope.value ="";
}
function send(){
	const data = {
		titre: title.value,
		texte: envelope.value
	};
	const dataJson = JSON.stringify (data);
	// ecrire le body propre dans un fichier grâce à un backend python
	var request = new XMLHttpRequest();
	request.onerror = function (error){
		retour.innerHTML = 'la connection avec le back-end est en erreur';
		if (this.readyState ===4 && this.status ===0) retour.innerHTML = retour.innerHTML + ". il semble qu'il soit éteind";
	}
	request.onreadystatechange = function(){
		if (this.readyState ===4 && this.status === 200) retour.innerHTML = 'les données ont bien été envoyées au back-end. sa réponse: '+ this.responseText;
	};
	request.open ('POST', urlBackend, true);
	request.send (dataJson);
}
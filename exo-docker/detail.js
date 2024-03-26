dicoPlay.titre = "nouvelle entrée";
dicoPlay.texte ="";
dicoPlay.date ="";
dicoPlay.retour ="";
const urlBackend = 'http://localhost:1407/';

function convertDate (dateStr){
	// date est au format utilisé dans toJson.py, %Y-%m-%d-%H-%M-%S
	const dayWeek =[ 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi', 'dimanche' ];
	const monthYear =[ 'janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre' ];
	const dateSplit = dateStr.split ('-');
	const month = parseInt (dateSplit[1]) -1;
	var dateDate = new Date (dateSplit[0], month, dateSplit[2], dateSplit[3], dateSplit[4], dateSplit[5]);
	var dateText = dayWeek [dateDate.getDay() -1] +" "+ dateSplit[2] +" "+ monthYear [month] +" "+ dateSplit[0] +', '+ dateSplit[3] +':'+ dateSplit[4] +':'+ dateSplit[5];
	return dateText;
}
var dateStr ="";
if (window.location.search){
	// on veux afficher une ancienne entrée
	dateStr = window.location.search.substring (6);
	var request = new XMLHttpRequest();
	request.onerror = function (error){
		dicoPlay.retour = 'la connection avec le back-end est en erreur';
		if (this.readyState ===4 && this.status ===0) dicoPlay.retour = dicoPlay.retour + ". il semble qu'il soit éteind";
	}
	request.onreadystatechange = function(){
		if (this.readyState ===4 && this.status === 200){
			const data = JSON.parse (this.responseText);
			for (var d=0; d< data.length; d++) if (data[d].date === dateStr){
				dicoPlay.titre = data[d].titre;
				dicoPlay.texte = data[d].texte;
				dicoPlay.date = convertDate (data[d].date);
	}}};
	request.open ('GET', 'journal.json', false);
	request.send();
}
dpInit();
function erase(){
	dicoPlay.titre ="";
	dicoPlay.texte ="";
	dicoPlay.retour ="";
	dpLoad();
}
function send(){
	console.log ('send a');
	var data = {
		titre: dicoPlay.titre,
		texte: dicoPlay.texte
	};
	if (dateStr) data.date = dateStr;
	const dataJson = JSON.stringify (data);
	// ecrire le body propre dans un fichier grâce à un backend python
	var request = new XMLHttpRequest();
	request.onerror = function (error){
		dicoPlay.retour = 'la connection avec le back-end est en erreur';
		if (this.readyState ===4 && this.status ===0) dicoPlay.retour = dicoPlay.retour + ". il semble qu'il soit éteind";
		dpLoad();
	}
	request.onreadystatechange = function(){
		if (this.readyState ===4 && this.status === 200) window.location.href = '/all';
	};
	request.open ('POST', urlBackend, true);
	request.send (dataJson);
}
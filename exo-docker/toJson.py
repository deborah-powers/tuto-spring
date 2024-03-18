#!/usr/bin/python3.8
# -*- coding: utf-8 -*-
import os
import codecs
import json
from datetime import datetime

letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZaàâbcdeéêèëfghiîïjkmlmnoôpqrstuûvwxyz0123456789-\xe7\xc7'
uppercaseLetters = ('aA', 'àA', 'bB', 'cC', '\xe7\xc7', 'dD', 'eE', 'éE', 'èE', 'êE', 'ëE', 'fF', 'gG', 'hH', 'iI', 'îI', 'ïI', 'jJ', 'kK', 'lL', 'mM', 'nN', 'oO', '\xf4\xe4', 'pP', 'qQ', 'rR', 'sS', 'tT', 'uU', 'vV', 'wW', 'xX', 'yY', 'zZ')
urlWords =( (': /', ':/'), ('localhost: ', 'localhost:'), ('www. ', 'www.'), ('. jpg', '.jpg'), ('. png', '.png'), ('. css', '.css'), ('. js', '.js'), (': 80', ':80'), ('. com', '.com'), ('. org', '.org'), ('. net', '.net'), ('. fr', '.fr'), ('. ico', '.ico') )
wordsBeginMaj = ('lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi', 'dimanche', 'janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre', 'deborah', 'powers', 'maman', 'mamie', 'papa', 'victo', 'tony', 'simplon', 'loïc', 'france', 'paris', 'rueil')
wordsBeginMin = ('Deborah.powers', 'Deborah.noisetier', 'Http', '\nPg_')
weirdChars =(
	('« ', '"'), (' »', '"'), ('«', '"'), ('»', '"'), ('–', '-'), ('‘', "'"), ('’', "'"), ('“', '"'), ('”', '"'), ('"', '"'), ('&hellip;', '...'), ('…', '...'),
	('\n ', '\n'), ('\r', ''), (' \n', '\n'), ("\\'", "'"), ('\\n', '\n'), ('\\r', ''), ('\\t', '\t'),
	('\\u00c2', 'Â'), ('\\u00ca', 'Ê'), ('\\u00cb', 'Ë'), ('\\u00ce', 'Î'), ('\\u00cf', 'Ï'), ('\\u00d4', 'Ô'), ('\\u00d6', 'Ö'), ('\\u00db', 'Û'), ('\\u00e0', 'à'), ('\\u00e2', 'â'), ('\\u00e7', 'ç'), ('\\u00e8', 'è'), ('\\u00e9', 'é'), ('\\u00ea', 'ê'), ('\\u00eb', 'ë'), ('\\u00ee', 'î'), ('\\u00ef', 'ï'), ('\\u00f4', 'ô'), ('\\u00f6', 'ö'), ('\\u00fb', 'û'),
	('\\', '/'),
	('\x85', '.'), ('\x92', "'"), ('\x96', '"'), ('\x97', "'"), ('\x9c', ' '), ('\xa0', ' '),
	('&agrave;', 'à'), ('&acirc;', 'â'), ('&ccedil;', 'ç'), ('&eacute;', 'é'), ('&egrave;', 'è'), ('&ecirc;', 'ê'), ('&icirc;', 'î'), ('&iuml;', 'ï'), ('&ocirc;', 'ô'), ('&ugrave;', 'ù'), ('&ucirc;', 'û'), ('&apos;', "'"),
	('&mdash;', ' '), ('&nbsp;', ''), ('&oelig;', 'oe'), ('&quot;', ''), ('&lt;', '<'), ('&gt;', '>'), ('&ldquo;', '"'), ('&rdquo;', '"'), ('&rsquo;', "'"), ('&#8220;', '"'), ('&#8221;', '"'),
	('&amp;', '&'), ('&#x27;', "'"), ('&#039', "'"), ('&#160;', ' '), ('&#39;', "'"), ('&#8217;', "'"), ('\n" ', '\n"')
)
def uppercase (text):
	points = '.!?:'
	text = '. '+ text
	for i, j in uppercaseLetters:
		for p in points: text = text.replace (p+" "+i, p+" "+j)
	punctuation = '({[?!;.,:]})"\' '
	for word in wordsBeginMaj:
		for p in punctuation:
			text = text.replace (" "+ word +p, " "+ word.capitalize() +p)
	for word in wordsBeginMin: text = text.replace (word, word.lower())
	text = text[2:]
	text = text.strip()
	return text

def findEndUrl (text, pos=0):
	charEndUrl = '\n\t \'",;!()[]{}'
	lenText = len (text) +1
	posEnd = lenText
	posTmp = lenText
	for char in charEndUrl:
		posTmp = text.find (char, pos)
		if posTmp >0 and posTmp < posEnd: posEnd = posTmp
	return posEnd

def cleanText (text):
	# nettoyage de base
	text = text.lower()
	for i, j in weirdChars: text = text.replace (i, j)
	text = text.strip()
	text = text.replace ('\n'," ")
	text = text.replace ('\t'," ")
	while '  ' in text: text = text.replace ('  ', ' ')
	# la ponctuation
	punctuation = '?!;.:,'
	for p in punctuation: text = text.replace (' '+p, p)
	while '....' in text: text = text.replace ('....', '...')
	for letter in letters:
		text = text.replace (letter +'?', letter +' ?')
		text = text.replace (letter +'!', letter +' !')
		text = text.replace (letter +';', letter +' ;')
		text = text.replace ('...' + letter, '... '+ letter)
	text = text.replace (':', ': ')
	while '  ' in text: text = text.replace ('  ', ' ')
	# restaurer les heures
	liste = text.split (': ')
	rliste = range (1, len (liste))
	for l in rliste:
		if len (liste[l]) >1 and liste[l][0] in '012345' and liste[l][1] in '0123456789':
			if len (liste[l]) >2 and liste[l][2] != '.':
				d= findEndUrl (liste[l])
				if d!=2: liste[l] =' '+ liste[l]
		else: liste[l] =' '+ liste[l]
	text = ':'.join (liste)
	# restaurer les url
	charEndUrl = ' \'",;!()[]{}'
	for wordStart, wordEnd in urlWords[:8]: text = text.replace (wordStart, wordEnd)
	for wordStart, wordEnd in urlWords[8:]:
		for e in charEndUrl: text = text.replace (wordStart +e, wordEnd +e)
	liste = text.split (' ?')
	rliste = range (1, len (liste))
	for l in rliste:
		d= findEndUrl (liste[l])
		if '=' not in liste[l][:d]: liste[l-1] = liste[l-1] +' '
	text = '?'.join (liste)
	text = uppercase (text)
	return text

def setDate():
	today = datetime.now()
	return today.strftime("%Y-%m-%d-%H-%M-%S")

class FileData():
	def __init__ (self, file =None):
		self.path = 'journal.json'
		self.data =[]

	def read (self):
		if not os.path.exists (self.path): return
		textBrut = open (self.path, 'rb')
		tmpByte = textBrut.read()
		encodingList = ('utf-8', 'ascii', 'ISO-8859-1')
		texte =""
		for encoding in encodingList:
			try: texte = codecs.decode (tmpByte, encoding=encoding)
			except UnicodeDecodeError: pass
			else: break
		textBrut.close()
		if texte:
			texte = texte.replace ('\n',"")
			texte = texte.replace ('\t',"")
			texte = texte.replace (',]', ']')
			self.data = json.loads (texte)

	def write (self, mode='w'):
		if not self.data:
			print ('rien a ecrire pour:', self.path)
			return
		texte = json.dumps (self.data)
		if mode == 'a' and os.path.isfile (self.path): texte ='\n'+ texte
		# ouvrir le fichier et ecrire le texte
		textBrut = open (self.path, mode +'b')
		textBrut.write (texte.encode ('utf-8'))
		textBrut.close()

	def append (self, data):
		data['date'] = setDate()
		data['titre'] = cleanText (data['titre'])
		data['texte'] = cleanText (data['texte'])
		self.data.append (data)


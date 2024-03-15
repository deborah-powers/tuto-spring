#!/usr/bin/python3
# -*- coding: utf-8 -*-
from http.server import HTTPServer, SimpleHTTPRequestHandler, test

htmlTemplate = """<!DOCTYPE html><html><head>
	<title>serveur python tournant sous docker</title>
	<meta name='viewport' content='width=device-width,initial-scale=1'/>
	<meta charset='utf-8'/>
<style type='text/css'>
	body {
		background-color: wheat;
		color: purple;
		font-size: 1em;
	}
	body >* {
		color: inherit;
		font-size: inherit;
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		font-family: inherit;
		font-style: normal;
		font-weight: normal;
		text-decoration: none;
		outline: none;
	}
	body >*:first-letter { text-transform: uppercase; }
	h1 {
		font-size: 1.5em;
		background-color: purple;
		color: wheat;
	}
</style></head><body>
	<h1>coucou !</h1>
	<p>je suis un serveur python lancé via docker</p>
</body></html>
"""

class BackEndCors (SimpleHTTPRequestHandler):
	def end_headers (self):
		self.send_header ('Access-Control-Allow-Origin', '*')
		self.send_header ('Access-Control-Allow-Methods', '*')
		self.send_header ('Access-Control-Allow-Headers', '*')
		SimpleHTTPRequestHandler.end_headers (self)

	def readBody (self):
		# rfile.read renvoie un texte en byte, il faut le transformer en string
		bodyLen = int (self.headers.get ('Content-Length'))
		bodyText =""
		if bodyLen >0: bodyText = self.rfile.read (bodyLen).decode('utf-8')
		return bodyText

	def do_GET (self):
		self.send_response (200)
		self.path = '/test-html.html'
		return SimpleHTTPRequestHandler.do_GET (self)

	def do_GET_pageInterne (self):
		self.send_response (200)
		self.end_headers()
		# wfile.write prend un texte en bytes comme argument, il faut parser les strings
		self.wfile.write (bytes (htmlTest, 'utf-8'))
		self.wfile.close()

test (BackEndCors, HTTPServer, port=1407)

"""
url correspondant à index.html
http://localhost:1407/
si je rajoute une fonction do_GET à ma classe, le html du fichier est écrasé.
il faut générer du nouveau html dynamiquement grâce à self.wfile()
"""
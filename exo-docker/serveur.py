#!/usr/bin/python3.8
# -*- coding: utf-8 -*-
from http.server import HTTPServer, SimpleHTTPRequestHandler, test
import json
from toJson import FileData

fileData = FileData()

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
		postBody = json.loads (bodyText)
		return postBody

	def writeBody (self, text):
		# wfile.write prend un texte en bytes comme argument, il faut parser les strings
		self.wfile.write (bytes (text, 'utf-8'))
	#	self.wfile.close()

	def do_GET (self):
		self.send_response (200)
		if self.path == '/new': self.path = 'nouveau.html'
		elif self.path == '/old': self.path = 'detail.html'
		elif self.path == '/': self.path = 'journal.html'
		elif self.path == '/all': self.path = 'journal.html'
		SimpleHTTPRequestHandler.do_GET (self)

	def do_POST (self):
		print (self.path)
		self.send_response (200)
		self.end_headers()
		postBody = self.readBody()
		fileData.read()
		if self.path == '/new': fileData.append (postBody)
		fileData.write()
		self.writeBody ('ok')

test (BackEndCors, HTTPServer, port=1407)

"""
url correspondant à index.html
http://localhost:1407/
"""
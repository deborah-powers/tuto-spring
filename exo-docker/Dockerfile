FROM python:3.8-alpine3.14
WORKDIR /exo-docker
COPY style.css ./
COPY journal.js ./
COPY detail.js ./
COPY journal.html ./
COPY detail.html ./
COPY toJson.py ./
COPY serveur.py ./
# repertoire a partager avec l'hote, ici logs
VOLUME /exo-docker/logs
# port de mon serveur
EXPOSE 1407
# toujours en dernier, l'action finale à réaliser
CMD [ "python3", "serveur.py" ]

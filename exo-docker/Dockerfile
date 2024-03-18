FROM python:3.8-alpine3.14
WORKDIR /exo-docker
COPY index.html ./
COPY action.js ./
COPY style.css ./
COPY toJson.py ./
COPY serveur.py ./
# repertoire a partager avec l'hote, ici logs
VOLUME /exo-docker/logs
# port de mon serveur
EXPOSE 1407
# toujours en dernier, l'action finale à réaliser
CMD [ "python3", "serveur.py" ]

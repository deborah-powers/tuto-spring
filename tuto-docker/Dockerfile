# image de base, contenant l'environnement
FROM debian:12
# executer une commande. \ afin de faire tenir le code sur plusieur lignes. chaque run est une layer
RUN apt update
RUN apt install python3 -yq
WORKDIR /tuto-docker
COPY test-simple.py ./
# repertoire a partager avec l'hote, ici logs
VOLUME /tuto-docker/logs
# toujours en dernier, l'action finale à réaliser
# CMD [ "python3", "test-simple.py" ]

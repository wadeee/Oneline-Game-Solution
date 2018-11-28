FROM gcr.io/google_appengine/openjdk8
VOLUME /tmp
ADD game-solve-1.0.0.war app.war
CMD [ "java","-Djava.security.egd=file:/dev/./urandom","-war","/app.war"]

FROM alpine
RUN apk add ffmpeg
RUN apk add openjdk11
WORKDIr /app
COPY *.class .
COPY MANIFEST.MF .
RUN jar cfm app.jar MANIFEST.mf *.class
RUN java -jar app.jar

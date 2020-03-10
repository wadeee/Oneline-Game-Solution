# This dockerfile uses the gradle image
# VERSION 1
# Author: wade

FROM gradle

MAINTAINER wade wadechen@outlook.com

RUN git clone https://github.com/wadeee/Oneline-Game-Solution.git\
  && cd Oneline-Game-Solution\
  && gradle bootJar

CMD java -jar ~/Oneline-Game-Solution/build/libs/Oneline-Game-Solution-1.0.jar --server.port=3001

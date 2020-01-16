# This dockerfile uses the gradle image
# VERSION 1
# Author: wade

FROM gradle

MAINTAINER wade wadechen@outlook.com

RUN git clone https://github.com/wadeee/Oneline-Game-Solution.git\
  && cd Oneline-Game-Solution\
  && gradle build

CMD cd Oneline-Game-Solution \
  && gradle bootRun

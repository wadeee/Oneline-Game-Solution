# This dockerfile uses the gradle image
# VERSION 1
# Author: wade

FROM gradle

MAINTAINER wade wadechen@outlook.com

RUN git clone https://github.com/wadeee/game-solution.git\
  && cd game-solution\
  && gradle build

CMD cd game-solution \
  && gradle bootRun

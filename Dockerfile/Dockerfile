# This dockerfile uses the gradle image
# VERSION 1
# Author: wade

FROM gradle

MAINTAINER wade wadechen@outlook.com

RUN export http_proxy="http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080" \
  && export https_proxy="http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080" \
  && git config --global http.proxy http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080\
  && git config --global https.proxy http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080\
  && git clone https://github.com/wadeee/game-solution.git\
  && cd game-solution\
  && git checkout pfu\
  && gradle build

CMD cd game-solution \
  && gradle bootRun

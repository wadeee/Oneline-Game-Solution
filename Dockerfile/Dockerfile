# This dockerfile uses the ubuntu image
# VERSION 1
# Author: wade

FROM ubuntu

MAINTAINER wade wadechen@outlook.com

RUN export http_proxy="http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080" \
  && export https_proxy="http://basechinissei:9457580000@oym.proxy.nic.fujitsu.com:8080" \
  && apt-get update \
  && echo 'Y' |apt-get install python \
  && echo "print(\"SUCCESS\")" > temp.py \
  && python temp.py


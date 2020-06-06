FROM tomcat:9.0

MAINTAINER Madhukar Gunda <madhukar.naiud.gunda@gmail.com>

VOLUME /tmp

ENV BOOTAPP_USR="root" BOOTAPP_GROUP="root" BOOTAPP_PATH="/usr/local/tomcat/webapps/app.war"

USER root
COPY maven/app.war $BOOTAPP_PATH
RUN chmod 555 $BOOTAPP_PATH && \
            touch $BOOTAPP_PATH

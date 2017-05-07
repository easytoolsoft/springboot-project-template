FROM azul/zulu-openjdk:8
MAINTAINER tomdeng <14068728@qq.com>, https://github.com/easytoolsoft/springboot-project-template.git

VOLUME /tmp

# Update CentOS
RUN \
  bash -c 'apt-get -qq update && apt-get -y upgrade && apt-get -y autoclean && apt-get -y autoremove' && \
  bash -c 'DEBIAN_FRONTEND=noninteractive apt-get install -y curl wget tar'

ENV USER_NAME springmvc-mybatis
ENV APP_HOME /opt/poc-api/$USER_NAME

RUN \
  useradd -ms /bin/bash $USER_NAME && \
  mkdir -p $APP_HOME

ADD springmvc-mybatis.jar ${APP_HOME}/springmvc-mybatis.jar
RUN \
  chown $USER_NAME $APP_HOME/springmvc-mybatis.jar && \
  bash -c 'touch ${APP_HOME}/springmvc-mybatis.jar'

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx128M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

USER $USER_NAME
WORKDIR $APP_HOME
ENTRYPOINT ["java", "-jar", "springmvc-mybatis.jar"]

# Run as:
# docker run -idt -p 8701:8701 -e appPort=8701 easytoolsoft/springmvc-mybatis:latest

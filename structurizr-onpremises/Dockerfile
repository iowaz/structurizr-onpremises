FROM tomcat:10.1.16-jre17-temurin-jammy
ENV PORT 8080

RUN set -eux; \
	apt-get update; \
	apt-get install -y --no-install-recommends graphviz; \
	apt-get install -y --no-install-recommends ca-certificates-java

RUN sed -i 's/port="8080"/port="${http.port}" maxPostSize="10485760"/' conf/server.xml \
  && echo 'export CATALINA_OPTS="-Xms512M -Xmx512M -Dhttp.port=$PORT"' > bin/setenv.sh

COPY ./Certs/ca.localiza.goskope.com.pem  /usr/local/share/ca-certificates/
COPY ./Certs/certadmin.pem  /usr/local/share/ca-certificates/

RUN update-ca-certificates

RUN keytool -importcert -alias ll1 -file /usr/local/share/ca-certificates/ca.localiza.goskope.com.pem -cacerts -storepass changeit -noprompt
RUN keytool -importcert -alias ll2 -file /usr/local/share/ca-certificates/certadmin.pem -cacerts -storepass changeit -noprompt
# RUN keytool -storepass changeit -noprompt -trustcacerts -importcert -alias llgoskope -file /usr/local/share/ca-certificates/ca.localiza.goskope.com.pem -keystore $JAVA_HOME/lib/jre/security/cacerts
# RUN keytool -storepass changeit -noprompt -trustcacerts -importcert -alias llgoskope2 -file /usr/local/share/ca-certificates/certadmin.pem -keystore $JAVA_HOME/lib/jre/security/cacerts


ADD build/libs/structurizr-onpremises.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE ${PORT}

CMD ["catalina.sh", "run"]
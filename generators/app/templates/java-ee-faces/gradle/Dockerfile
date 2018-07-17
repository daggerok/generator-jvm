FROM daggerok/jboss:eap-7.1
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \"" >> ${JBOSS_HOME}/bin/standalone.conf
EXPOSE 5005
HEALTHCHECK --timeout=2s --retries=66 \
        CMD wget -q --spider http://127.0.0.1:8080/app/ \
         || exit 1
ADD --chown=jboss-eap-7.1 ./build/libs/*.war ${JBOSS_HOME}/standalone/deployments/app.war

#FROM daggerok/glassfish:5.0-web
#ENV JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 "
#EXPOSE 5005
#HEALTHCHECK --timeout=2s --retries=66 \
#        CMD wget -q --spider http://127.0.0.1:8080/app/ \
#         || exit 1
#COPY --chown=glassfish5 ./build/libs/*.war ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/app.war

##FROM daggerok/apache-tomcat:9.0.2-alpine
##ARG JPDA_OPTS_ARG="${JAVA_OPTS} -agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
##ENV JPDA_OPTS="${JPDA_OPTS_ARG}"
##EXPOSE 5005
##HEALTHCHECK --timeout=2s --retries=66 \
##        CMD wget -q --spider http://127.0.0.1:8080/app/ \
##         || exit 1
##COPY --chown=tomcat ./build/libs/*.war ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/app.war

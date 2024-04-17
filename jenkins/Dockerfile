FROM jenkins/jenkins:lts
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false ${JAVA_OPTS:-}"
USER jenkins
RUN jenkins-plugin-cli --plugins "configuration-as-code:1775.v810dc950b_514"
FROM jenkins/jenkins:lts

# Switch to root user to install packages
USER root

# Install necessary tools
RUN apt-get update && \
    apt-get install -y wget tar

# Install JDK 21
RUN wget -O /tmp/openjdk-21_linux-x64_bin.tar.gz https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz && \
    mkdir -p /usr/lib/jvm && \
    tar -xvf /tmp/openjdk-21_linux-x64_bin.tar.gz -C /usr/lib/jvm && \
    ln -s /usr/lib/jvm/jdk-21 /usr/lib/jvm/default-java && \
    rm /tmp/openjdk-21_linux-x64_bin.tar.gz

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/default-java
ENV PATH $JAVA_HOME/bin:$PATH

# Switch back to the jenkins user
USER jenkins

# Install plugins (including Maven Integration)
RUN jenkins-plugin-cli --plugins "git maven-plugin"

# Create a default admin user (optional)
COPY --chown=jenkins:jenkins default-user.groovy /usr/share/jenkins/ref/init.groovy.d/

# Entry point
ENTRYPOINT ["/usr/bin/tini", "--", "/usr/local/bin/jenkins.sh"]

# Expose ports
EXPOSE 8080 50000

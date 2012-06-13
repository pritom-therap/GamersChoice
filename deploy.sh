mvn clean package
cp target/gamerschoice.war /usr/local/apache-tomcat-6.0.35/webapps/
cd /usr/local/apache-tomcat-6.0.35
rm -r webapps/gamerschoice
./bin/shutdown.sh
./bin/startup.sh

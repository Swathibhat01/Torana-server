 sudo mvn clean install
 sudo /opt/tomcat/bin/catalina.sh stop
sudo rm -r /opt/tomcat/webapps/torana
 sudo cp  torana-cloud/target/torana.war /opt/tomcat/webapps/
 sudo /opt/tomcat/bin/catalina.sh start

In order to see the Code Churn Measures for any Project,
Update the sonar-server-config.properties file in Project folder.
i.e update the following parameters with appropriate Sonar server urls and Project Key for a particular sonar analyzed project
sonar-server = http://localhost:9000
sonar-user = admin
sonar-pwd = admin
sonar-project-key = MyMailSD


JARS Required to run this program
commons-codec-1.4.jar,httpclient-4.0.1.jar,httpcore-4.0.1.jar
json-simple.jar,logging-1.0.4.jar,sonar-ws-client-3.2.jar
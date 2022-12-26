# java-example-chinook

Example Web Application on Java

Prerequisites:

1) JDK must be installed (see [Java 19 and Java 17 available now](https://www.oracle.com/java/technologies/downloads/#java17))

2) Look for Java Home folder location:

        java -XshowSettings:properties -version

    there is property `java.home`. Export it into environment:
    
        export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.0.4.1.jdk/Contents/Home

3) Download Apache Tomcat from [Tomcat 10 Software Downloads](https://tomcat.apache.org/download-10.cgi)

4) Install and run Apache Tomcat:

        sudo mv ~/Downloads/apache-tomcat-10.1.4 /usr/local/
        sudo chown -R username /usr/local/apache-tomcat-10.1.4
        chmod +x /usr/local/apache-tomcat-10.1.4/bin/*.sh
        sudo ln -s /usr/local/apache-tomcat-10.1.4 /Library/Tomcat
        /Library/Tomcat/bin/startup.sh


* Create new project with `Jakarta EE` generator

* Enter project name, eg `Chinook` (to work with database sample [Chinook Database](https://github.com/lerocha/chinook-database))

* Select `Template` as `Web Application`

* Select `Application server` as `Tomcat Server`
  
    Create new if not exists followinng steps `New...` -> `Tomcat Server`, then set `Tomcat Home` to `/Library/Tomcat` (which is `CATALINA_HOME` path)

* Select `Language` as `Java` and `Build System` as `Maven`

* Then press `Next` button and select `Version` as `Java EE 8`, and press `Create` button.

Done.

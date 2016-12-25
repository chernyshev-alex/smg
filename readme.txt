
Very lightweight google guice REST microservice with embedded http grizzly server
(no Spring RS)

Dependencies :
   
    google guice IOC container ver 3.0
    grizzly  http embedded server  ver 1.9.46
    jackson  json serializer  ver  2.4.0
    jersey JAVA-REST   ver 1.17.1

How  to build (maven central should be available)

    mvn compile    ;  download  all deps and compile
    mvn -Dport=8484 test

How  to run

    mvn -Dport=8484 exec:java   ;  run embedded server on port

    Http server will listen to 8484 port

API examples : 

    GET list of topics

        curl http://localhost:8484/smg/topics -i     

    GET topic details
     
        curl http://localhost:8484/smg/topics/3eb  

    GET articles of topic    

        curl http://localhost:8484/smg/topics/3eb/articles 

    GET article of topic    

        curl http://localhost:8484/smg/topics/3eb/articles/1  

    PUT and DELETE  are supported  also  Topic, Article





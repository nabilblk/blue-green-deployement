## Compile and generate docker images . 

```
mvn clean install 
```

> Results
 
```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] blue-green-deployement/parent ...................... SUCCESS [  0.285 s]
[INFO] blue-green-deployement/admin-service ............... SUCCESS [ 14.875 s]
[INFO] blue-green-deployement/dashboard-service ........... SUCCESS [  4.823 s]
[INFO] blue-green-deployement/discovery-service ........... SUCCESS [ 12.346 s]
[INFO] blue-green-deployement/edge-service ................ SUCCESS [  5.815 s]
[INFO] blue-green-deployement/perf-simulator .............. SUCCESS [  4.894 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

##  START ALL THE PLATEFORME 
```
docker-compose up -d 
```

```
➜ docker-compose ps 
      Name                     Command               State           Ports         
-----------------------------------------------------------------------------------
admin-service       java -Djava.security.egd=f ...   Up      0.0.0.0:9009->9009/tcp
dashboard-service   java -Djava.security.egd=f ...   Up                            
discovery-service   java -Djava.security.egd=f ...   Up      0.0.0.0:8761->8761/tcp
edge-service        java -Djava.security.egd=f ...   Up      0.0.0.0:9999->9999/tcp
weave-scope         /home/weave/entrypoint.sh  ...   Up      0.0.0.0:4040->4040/tcp
```

## SCALE DASHBOARD SERVICE 
```
docker-compose scale dashboard-service=2
```

## Run gatling simulator on the `echo` endpoint : 

```
cd mvn gatling:execute -Dgatling.simulationClass=ma.octo.simulations.BlueGreenSimulation
```

## Get Service discovery

``` 
curl -XGET -H "Accept: application/json" http://localhost:8761/eureka/apps/ | jq '.applications.application[2].instance[] | {app :.app, instanceId: .instanceId} '
curl PUT http://localhost:8761/eureka/apps/DASHBOARD-SERVICE/372c66090d74:dashboard-service:9001/status?value=OUT_OF_SERVICE
```

## START NEW VERSION 

```
docker run  --network=bluegreendeployement_default -it -e EUREKA_INSTANCE_INITIAL-STATUS=OUT_OF_SERVICE -e SPRING_PROFILES_ACTIVE=docker -e ECHO=new --link discovery-service  -d --name dashboard-service-blue octo/dashboard-service
```

## VERIFY IT

> Manual or automated Tasks   
 
## ACTIVATE IT AND DESACTIVATE OLD
  
```
curl -XPUT http://localhost:8761/eureka/apps/DASHBOARD-SERVICE/a68965be2fa8:dashboard-service:9001/status?value=UP
curl -XPUT http://localhost:8761/eureka/apps/DASHBOARD-SERVICE/e38190ec6297:dashboard-service:9001/status?value=OUT_OF_SERVICE
```

## Remove old 

```
docker rm -f bluegreendeployement_dashboard-service_2
```

## Start New version 
```
docker run  --network=bluegreendeployement_default -it -e SPRING_PROFILES_ACTIVE=docker -e ECHO=new --link discovery-service  -d --name dashboard-service-blue octo/dashboard-service 
```
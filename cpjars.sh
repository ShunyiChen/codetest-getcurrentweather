#!/bin/bash
echo "Start copying "$1

function pscp() {
   if [ $1 = "all" ];then
      PSCP J://codetest-getcurrentweather/*/target/*.jar root@192.168.3.131:/usr/shunyi/k8s-deployment/jars
   else
      PSCP J://codetest-getcurrentweather/$1/target/*.jar root@192.168.3.131:/usr/shunyi/k8s-deployment/jars
   fi
}
##############################################
#           sh cpjars.sh all                 #
##############################################

##################### MAIN ###################

if [ $1 == "all" ];then
   pscp "all"
elif [ $1 = "city-service" ];then
   pscp "city-service"
elif [ $1 = "weather-service" ];then
   pscp "weather-service"
elif [ $1 = "eureka-server1" ];then
   pscp "eureka-server1"
elif [ $1 = "eureka-server2" ];then
   pscp "eureka-server2"
elif [ $1 = "gateway1" ];then
   pscp "gateway1"
elif [ $1 = "gateway2" ];then
   pscp "gateway2"
elif [ $1 = "config-server" ];then
   pscp "config-server"
else
   echo method no supported!
fi

echo ""
echo "Copying completed"

 
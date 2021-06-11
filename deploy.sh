#!/bin/bash
######################################################
# Weather service deploy                             #
# Rev    : 1.0.0                                     #
# Author : Shunyi Chen                               #
# Date   : 2021/06/12                                #
######################################################

######################################################
# Docker stop                                        #
######################################################
option=$1
name=$2
port=$3

if [ "$option" == "install" ]
then
  docker build -t ${name}:1.0 .
  
  docker run -d -p ${port}:${port} --name weather-${name} ${name}:1.0

  echo ""
  echo "Installtion is Done."
  echo ""

elif [ "$option" == "uninstall" ]
then

  docker stop weather-${name}

  docker rm weather-${name}

  docker rmi ${name}:1.0

  rm -i ${name}-1.0.jar
  
  echo ""
  echo "Uninstallation is Done."
  echo ""

else
  echo $option" is unknown."

fi





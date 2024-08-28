#!/bin/bash

#starts all eps services registered on current service

for service in /etc/init.d/mmepool*
do
	$service start
done

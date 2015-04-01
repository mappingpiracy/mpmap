#!/bin/bash
#assumes the activator file is in the same directory

sudo killall -u mpmap

cd /var/www/mpmap
echo "***PULLING FROM MASTER***"
sudo git pull origin master
sudo chown -R mpmap:mpmap .
sudo chmod 400 ./conf/*
echo "***GENERATING NEW APPLICATION SECRET***"
sudo -u mpmap activator play-generate-secret
echo "***STARTING PLAY APPLICATION***"
sudo -u mpmap activator start

#!/bin/bash
#assumes the activator file is in the same directory

sudo killall -u mpmap

cd /var/www/mpmap 
echo "***GENERATING NEW APPLICATION SECRET***"
sudo -u mpmap ./activator play-generate-secret
echo "***STARTING PLAY APPLICATION***"
sudo -u mpmap ./activator start

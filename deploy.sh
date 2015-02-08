#!/bin/bash

sudo killall -u mpmap
echo "***GENERATING NEW APPLICATION SECRET***"
sudo -u mpmap ./activator play-generate-secret
echo "***STARTING PLAY APPLICATION***"
sudo -u mpmap ./activator start

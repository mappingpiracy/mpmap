#!/bin/bash

sudo killall -u mpmap
sudo ../activator play-generate-secret
sudo -u mpmap ./activator start 

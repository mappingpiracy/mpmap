#!/bin/bash

sudo killall -u mpmap
sudo -u mpmap ./activator run >> deploy.log

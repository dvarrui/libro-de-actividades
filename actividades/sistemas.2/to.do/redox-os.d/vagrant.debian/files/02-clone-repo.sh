#!/usr/bin/env bash

mkdir -p tryredox
cd tryredox
git clone https://gitlab.redox-os.org/redox-os/redox.git --origin upstream --recursive
cd redox
git submodule update --recursive --init


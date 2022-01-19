#!/usr/bin/env bash

zypper refresh

zypper in -y vim tree nmap
zypper in -y neofetch lsb-release

lsb_release -d


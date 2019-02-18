#!/bin/bash

echo "Booting Nginx..."
/usr/sbin/nginx &

echo "Waiting..."
while(true) do
  sleep 60
done

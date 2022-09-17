#!/usr/bin/env python
import os

print "[INFO] configuring Hadoop"

os.environ["JAVA_HOME"]="/usr/lib/jvm/java-11-openjdk-amd64"
os.environ["PATH"] = os.environ["PATH"] + ":" + "/usr/local/hadoop-3.3.1/bin"


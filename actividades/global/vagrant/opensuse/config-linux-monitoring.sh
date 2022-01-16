#!/bin/bash

app=TerminalTrainer
pattern="^export PROMPT_COMMAND=.*$"
bashrc=/etc/bash.bashrc

# Adds PROMPT_COMMAND variable to BASH global configuration (affecting all users) 
# in porder to log the last executed command in "syslog"
function enable-monitoring() {
	echo -e "Setting BASH monitorization:\n"
	if grep -q "$pattern" $bashrc
	then
		echo "- PROMPT_COMMAND variable already exsists in $bashrc"
	else
		echo "- Adding PROMPT_COMMAND variable to $bashrc"
		echo "export PROMPT_COMMAND='history -a && logger --tag $app \"bash:\$USER:\$PWD:\$OLDPWD:\$(tail -n 1 \$HISTFILE)\"'" >> $bashrc
		echo "echo -e \"This terminal is being monitored by $app\n\"" >> $bashrc
	fi
	echo -e "\nFinished!"
	exit 0
}

# Removes PROMPT_COMMAND variable from BASH global configuration (affecting all users) 
function disable-monitoring() {
	echo -e "Disabling BASH monitorization:\n"
	if grep -q "$pattern" $bashrc
	then
		echo "- Removing PROMPT_COMMAND variable from $bashrc"
		sed -i "/$pattern/d" $bashrc
                sed -i "/^echo -e .*$app.*$/d" $bashrc
	else
		echo "- PROMPT_COMMAND variable doesn't exist in $bashrc"
	fi
	echo -e "\nFinished!"
	exit 0
}

# Checks if PROMPT_COMMAND variable exists in "/etc/bash.bashrc" file
function test-monitoring() {
	echo -n "Is BASH monitoring enabled? "
	if grep -q "$pattern" $bashrc
	then	
		error=0
		echo "[ENABLED]"
	else
		error=1
		echo "[DISABLED]"
	fi
	exit $error
}

if [ "$1" == "--test" ]
then
	test-monitoring
elif [ "$1" == "--enable" ]
then
	enable-monitoring
elif [ "$1" == "--disable" ]
then
	disable-monitoring
else
	echo "$app's monitoring configuration tool"
	echo "Use: $0 [--test|--enable|--disable]"
	echo
fi

# Installing Python

## python 3.6

```console
$> sudo add-apt-repository ppa:jonathonf/python-3.6
$> sudo apt-get update
$> sudo apt-get install python3.6
```

Set default python version with alias:

```console
$> vi ~/.bashrc
alias python=python3.6

$> source ~/.bashrc
```

## pip

```console
$> apt-get install python3-pip
```

Set default pip version with alias:

```console
$> vi ~/.bashrc
alias pip=pip3

$> source ~/.bashrc
```

## virtualenvwrapper

```console
$> apt-get install virtualenvwrapper
```

Activate scripts from virtualenvwrapper:

```console
$> vi ~/.bashrc
export VIRTUALENV_PYTHON=/usr/bin/python3.6
export WORKON_HOME=~/.virtualenvs
source /usr/share/virtualenvwrapper/virtualenvwrapper.sh

$> source ~/.bashrc
```

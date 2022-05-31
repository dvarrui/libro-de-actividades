
# Aprendiendo RedoxOS

Enlaces de interés:
* [Getting started - The Redox Operating System](https://doc.redox-os.org/book/ch02-01-getting-started.html)
* [Running Redox on real hardware - The Redox Operating System](https://doc.redox-os.org/book/ch02-03-real-hardware.html)

# Preparing the build

> Enlaces de interés:
> * [Preparing the build](https://doc.redox-os.org/book/ch02-04-preparing-the-build.html)
> * [RedoxOs GitLab](https://gitlab.redox-os.org/redox-os/redox/)

Advanced Users

Advanced users may accomplish the same as the above bootstrap.sh script with the following steps.

Be forewarned, the documentation can't keep up with the bootstrap.sh script since there are so many distros from which to build Redox-Os from: MacOS, PopOS, Archlinux, Redhat/Centos/Fedora.

NOTE: The core redox-os developers use PopOs to build Redox-Os. We recommend to use PopOs for repeatable zero-painpoint Redox-os builds.
Clone the repository

Change to the folder where you want your copy of Redox to be stored and issue the following command:

```
$ mkdir -p ~/tryredox
$ cd ~/tryredox
$ git clone https://gitlab.redox-os.org/redox-os/redox.git --origin upstream --recursive
$ cd redox
$ git submodule update --recursive --init
```

Prerequisitos... el script

Install Rust Stable And Nightly

Install Rust, make the nightly version your default toolchain, the list the installed toolchains:

$ curl https://sh.rustup.rs -sSf | sh
$ rustup default nightly
$ rustup toolchain list
$ cargo install --force --version 0.3.20 xargo

NOTE: xargo allows redox-os to have a custom libstd

NOTE: ~/.cargo/bin has been added to your PATH for the running session.

Add the following line to your shell start-up file, like ".bashrc" or ".bash_profile" for future rust sessions:

export PATH=${PATH}:~/.cargo/bin

# Aprender Rust

* [Cargo](https://doc.rust-lang.org/cargo/): Rust package manager.
* [Rust by exampe](https://doc.rust-lang.org/stable/rust-by-example/)

#!/usr/bin/env bash

echo "Install Rust Stable And Nightly"

curl https://sh.rustup.rs -sSf | sh
rustup default nightly
rustup toolchain list
cargo install --force --version 0.3.20 xargo


echo "NOTE: ~/.cargo/bin has been added to your PATH for the running session."
echo "Add the following line to your shell start-up file, like .bashrc or .bash_profile for future rust sessions:"
echo "export PATH=${PATH}:~/.cargo/bin"


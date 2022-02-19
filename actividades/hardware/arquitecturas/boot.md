
```
Teoría/Explicación
```

# Boot

Con el comando `dmesg` podemos consultar un log que ha registrado los pasos realizados durante el arranque del sistema.

Consultar salida del comando dmesg:
1. [dmesg1](files/dmesg1.txt)
2. [dmesg2](files/dmesg2.txt)

Segundo 0
```
Linux version
Command line
BIOS-provided physical RAM map
NX (Execute Disable) protection: active
extended physical RAM map
efi
secureboot
DMI
tsc
x86/PAT
RAMDISK
ACPI
smpboot: Allowing 8 CPUs, 0 hotplug CPUs
PM: hibernation
Booting paravirtualized kernel on bare hardware
Kernel command line: BOOT_IMAGE=/boot/vmlinuz-5.3.18-150300.59.49-preempt root=UUID=5b8dcbd0-91a4-4b90-bf18-091b42220cef splash=silent resume=/dev/disk/by-id/nvme-Samsung_SSD_970_PRO_1TB_S5JXNC0N301764E-part2 quiet mitigations=auto
Spectre V1 : Mitigation
pci
```

Segundo 1
```
pnp
thermal_sys
NET: Registered protocol family 2
IP idents hash table entries: 262144 (order: 9, 2097152 bytes, linear)
tcp_listen_portaddr_hash hash table entries: 32768 (order: 7, 524288 bytes, linear)
Block layer SCSI generic (bsg) driver version 0.4 loaded (major 247)
efifb
```

Segundo 3
```
mousedev: PS/2 mouse device common for all mice
```

Segundo 5
```
systemd[1]: systemd 246.16+suse.191.g3850086c65 running in system mode. (+PAM +AUDIT +SELINUX -IMA +APPARMOR -SMACK +SYSVINIT +UTMP +LIBCRYPTSETUP +GCRYPT +GNUTLS +ACL +XZ +LZ4 +ZSTD +SECCOMP +BLKID +ELFUTILS +KMOD +IDN2 -IDN +PCRE2 default-hierarchy=hybrid)
systemd[1]: Detected architecture x86-64.
```

563?

=============
Arquitecturas

https://get.opensuse.org/tumbleweed#download

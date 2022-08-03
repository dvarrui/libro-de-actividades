#!/bin/bash

echo "[INFO] Instalar Icinga2..."

function step2_icinga() {
  echo "       ================= step 2 ======================="
  echo "[INFO] Instalar Icinga2"
  zypper install -y icinga2 monitoring-plugins
  systemctl enable icinga2
  systemctl start icinga2
  zypper install -y nano-icinga2
  echo "# Icinga 2" > /root/.nanorc
  echo 'include "/usr/share/nano/icinga2.nanorc"' >> /root/.nanorc
}

function step31_mariadb() {
  echo "       ================= step 3.1 ====================="
  echo "[INFO] Instalar MariaDB"
  zypper install -y mysql mysql-client icinga2-ido-mysql
  systemctl enable mysql
  systemctl start mysql
  mysql < icinga-crear-bd.sql
  mysql icinga < /usr/share/icinga2-ido-mysql/schema/mysql.sql
  icinga2 feature enable ido-mysql
  systemctl restart icinga2
}

function step32_apache() {
  echo "       ================= step 3.2 ====================="
  echo "[INFO] Instalar Apache"
  zypper install -y apache2
  a2enmod rewrite
  a2enmod php7
  systemctl enable apache2
  systemctl start apache2
  firewall-cmd --add-service=http
  firewall-cmd --permanent --add-service=http
}


function step33_apirest() {
  echo "       ================= step 3.3 ====================="
  echo "[INFO] Instalar API REST"
  icinga2 api setup
  systemctl restart icinga2
}


function step35_icingaweb() {
  echo "       ================= step 3.5  ====================="
  echo "[INFO] Instalar IcingaWeb"
  zypper install -y icingaweb2
  zypper install -y php7-imagick php7-curl
  icingacli module enable setup
  icingacli setup token create > icinga-token.txt
  chgrp icingaweb2 -R /etc/icingaweb2/
  chmod 770 /etc/icingaweb2/enabledModules
  systemctl restart apache2
}

# step2_icinga
# step31_mariadb
# step32_apache
# step33_apirest
# step35_icingaweb

echo "[NEXT] Abrimos un navegador cons el URL http://localhost/icingaweb2/"

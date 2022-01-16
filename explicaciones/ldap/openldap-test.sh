#!/bin/bash

HOST="$1"
if [ -z "$HOST" ]; then
	HOST="localhost"
fi
TMPFILE=$(mktemp)
ERRORS=0
TOTAL=0

ASIR1=(
	"ou=ASIR1,dc=iesdpm,dc=com"
	"ou: ASIR1"
	"objectClass: organizationalUnit"
	"objectClass: top"
)

AlumnosASIR1=(
	"cn=AlumnosASIR1,ou=ASIR1,dc=iesdpm,dc=com"
	"gidNumber: 2001"
	"cn: AlumnosASIR1"
	"memberUid: cbronson"
	"memberUid: cnorris"
	"memberUid: eastwood"
	"memberUid: mknight"
	"objectClass: posixGroup"
	"objectClass: top"
)

ProfesoresASIR1=(
	"cn=ProfesoresASIR1,ou=ASIR1,dc=iesdpm,dc=com"
	"gidNumber: 3001"
	"cn: ProfesoresASIR1"
	"memberUid: fvargas"
	"memberUid: aperez"
	"objectClass: posixGroup"
	"objectClass: top"
)

Charles_Bronson=(
	"cn=Charles Bronson,ou=ASIR1,dc=iesdpm,dc=com"
	"cn: Charles Bronson"
	"givenName: Charles"
	"sn: Bronson"
	"uid: cbronson"
	"uidNumber: 2000"
	"gidNumber: 2001"
	"loginShell: /bin/bash"
	"mail: bronson@gmail.com"
	"homeDirectory: /home/cbronson"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Chuck_Norris=(
	"cn=Chuck Norris,ou=ASIR1,dc=iesdpm,dc=com"
	"cn: Chuck Norris"
	"givenName: Chuck"
	"sn: Norris"
	"uid: cnorris"
	"uidNumber: 2001"
	"gidNumber: 2001"
	"loginShell: /bin/bash"
	"mail: norris@hotmail.com"
	"homeDirectory: /home/cnorris"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Clint_Eastwood=(
	"cn=Clint Eastwood,ou=ASIR1,dc=iesdpm,dc=com"
	"cn: Clint Eastwood"
	"givenName: Clint"
	"sn: Eastwood"
	"uid: eastwood"
	"uidNumber: 2002"
	"gidNumber: 2001"
	"loginShell: /bin/sh"
	"mail: clint@yahoo.es"
	"homeDirectory: /home/eastwood"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Michael_Knight=(
	"cn=Michael Knight,ou=ASIR1,dc=iesdpm,dc=com"
	"cn: Michael Knight"
	"givenName: Michael"
	"sn: Knight"
	"uid: mknight"
	"uidNumber: 2003"
	"gidNumber: 2001"
	"loginShell: /bin/bash"
	"mail: maikel@hotmail.es"
	"homeDirectory: /home/playita"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

ASIR2=(
	"ou=ASIR2,dc=iesdpm,dc=com"
	"ou: ASIR2"
	"objectClass: organizationalUnit"
	"objectClass: top"
)

AlumnosASIR2=(
	"cn=AlumnosASIR2,ou=ASIR2,dc=iesdpm,dc=com"
	"gidNumber: 2002"
	"cn: AlumnosASIR2"
	"memberUid: mbarracus"
	"memberUid: phoenix"
	"memberUid: asmith"
	"memberUid: murdock"
	"objectClass: posixGroup"
	"objectClass: top"
)

ProfesoresASIR2=(
	"cn=ProfesoresASIR2,ou=ASIR2,dc=iesdpm,dc=com"
	"gidNumber: 3002"
	"cn: ProfesoresASIR2"
	"memberUid: aperez"
	"memberUid: pgonzalez"
	"objectClass: posixGroup"
	"objectClass: top"
)

MA_Barracus=(
	"cn=MA Barracus,ou=ASIR2,dc=iesdpm,dc=com"
	"cn: MA Barracus"
	"givenName: MA"
	"sn: Barracus"
	"uid: mbarracus"
	"uidNumber: 2004"
	"gidNumber: 2002"
	"loginShell: /bin/sh"
	"mail: ma@barracus.com"
	"homeDirectory: /home/barracus"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Phoenix_Johnson=(
	"cn=Phoenix Johnson,ou=ASIR2,dc=iesdpm,dc=com"
	"cn: Phoenix Johnson"
	"givenName: Phoenix"
	"sn: Johnson"
	"uid: phoenix"
	"uidNumber: 2005"
	"gidNumber: 2002"
	"loginShell: /bin/bash"
	"homeDirectory: /home/phoenix"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Anibal_Smith=(
	"cn=Anibal Smith,ou=ASIR2,dc=iesdpm,dc=com"
	"cn: Anibal Smith"
	"givenName: Anibal"
	"sn: Smith"
	"uid: asmith"
	"uidNumber: 2006"
	"gidNumber: 2002"
	"loginShell: /bin/bash"
	"mail: asmith@todoloquepuedas.com"
	"homeDirectory: /home/asmith"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Murdock_Sanchez=(
	"cn=Murdock Sánchez,ou=ASIR2,dc=iesdpm,dc=com"
	"cn: Murdock Sánchez"
	"givenName: Murdock"
	"sn: Sánchez"
	"uid: murdock"
	"uidNumber: 2007"
	"gidNumber: 2002"
	"loginShell: /bin/ksh"
	"mail: murdock69@hotmail.com"
	"homeDirectory: /home/murdock"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Profesores=(
	"ou=Profesores,dc=iesdpm,dc=com"
	"ou: Profesores"
	"objectClass: organizationalUnit"
	"objectClass: top"
)

_Profesores=(
	"cn=Profesores,ou=Profesores,dc=iesdpm,dc=com"
	"gidNumber: 3000"
	"cn: Profesores"
	"memberUid: fvargas"
	"memberUid: aperez"
	"memberUid: pgonzalez"
	"objectClass: posixGroup"
	"objectClass: top"
)

Francisco_Vargas=(
	"cn=Francisco Vargas,ou=Profesores,dc=iesdpm,dc=com"
	"cn: Francisco Vargas"
	"givenName: Francisco"
	"sn: Vargas"
	"uid: fvargas"
	"uidNumber: 3000"
	"gidNumber: 3000"
	"loginShell: /bin/bash"
	"mail: fran@iesdpm.org"
	"homeDirectory: /home/fvargas"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Angel_Perez=(
	"cn=Ángel Pérez,ou=Profesores,dc=iesdpm,dc=com"
	"cn: Ángel Pérez"
	"givenName: Ángel"
	"sn: Pérez"
	"uid: aperez"
	"uidNumber: 3001"
	"gidNumber: 3000"
	"loginShell: /bin/bash"
	"mail: angel@iesdpm.org"
	"homeDirectory: /home/aperez"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

Paco_Gonzalez=(
	"cn=Paco González,ou=Profesores,dc=iesdpm,dc=com"
	"cn: Paco González"
	"givenName: Paco"
	"sn: González"
	"uid: pgonzalez"
	"uidNumber: 3002"
	"gidNumber: 3000"
	"loginShell: /bin/bash"
	"mail: paco@iesdpm.org"
	"homeDirectory: /home/pgonzalez"
	"objectClass: posixAccount"
	"objectClass: shadowAccount"
	"objectClass: inetOrgPerson"
	"objectClass: top"
)

main() {
	echo "################################"
	echo "   PRACTICA DE LDAP EN LINUX"
	echo "   SCRIPT VALIDADOR"
	echo "   servidor: $HOST"
	echo "   Autor: Javier Valencia Rodríguez"
	echo "################################"
	
	check "${ASIR1[@]}"
	check "${AlumnosASIR1[@]}"
	check "${ProfesoresASIR1[@]}"
	check "${Charles_Bronson[@]}"
	check "${Chuck_Norris[@]}"
	check "${Clint_Eastwood[@]}"
	check "${Michael_Knight[@]}"
	
	check "${ASIR2[@]}"
	check "${AlumnosASIR2[@]}"
	check "${ProfesoresASIR2[@]}"
	check "${MA_Barracus[@]}"
	check "${Phoenix_Johnson[@]}"
	check "${Anibal_Smith[@]}"
	check "${Murdock_Sanchez[@]}"
	
	check "${Profesores[@]}"
	check "${_Profesores[@]}"
	check "${Francisco_Vargas[@]}"
	check "${Angel_Perez[@]}"
	check "${Paco_Gonzalez[@]}"
	
	if (( $ERRORS > 0 )); then
		show strong "\nValidación fallida!"
	else
		show strong "\nValidación correcta!"
	fi
	
	echo "Entradas: $TOTAL"
	echo "Aciertos: $(( $TOTAL - $ERRORS ))"
	echo "Fallos:   $ERRORS"
}

check() {
	(( TOTAL += $# ))
	ldapsearch -x -b "$1" -s base -h "$HOST" >$TMPFILE 2>/dev/null
	
	case $? in
		255) show error "*** FALLO DE CONEXION ***"; exit ;;
		32) show not_found "\n# $1"; (( ERRORS += 1 )) ;;
		0) show found "\n# $1"
	esac
	
	decode_attributes $TMPFILE
	
	shift
	
	for line in "$@"; do
		if grep -e "^$line$" $TMPFILE &>/dev/null; then
			show good "  $line"
		else
			show bad "  $line"
			(( ERRORS++ ))
		fi
	done
}

show() {
	local good="\e[32m"
	local bad="\e[31m"
	local reset="\e[0m"
	local strong="\e[1m"
	
	if [[ $# < 2 ]]; then
		echo -e "$reset$1$reset"
	else
		case $1 in
			'good') echo -e "$reset$good$2$reset" ;;
			'success'|'found') echo -e "$reset$strong$good$2$reset" ;;
			'bad') echo -e "$reset$bad$2$reset" ;;
			'error'|'failed'|'not_found') echo -e "$reset$strong$bad$2$reset" ;;
			'strong') echo -e "$reset$strong$2$reset" ;;
		esac
	fi
}

decode_attributes() {
	while read line; do
		if [[ "$line" =~ ^[[:alpha:]]+::[[:space:]].+ ]]; then
			local attr=$(echo "$line" | tr -s ':' | cut -d' ' -f1)
			local data=$(echo "$line" | tr -s ':' | cut -d' ' -f2 | base64 -d)
			echo "$attr $data" >> "$1.decoded"
		else
			echo "$line" >> "$1.decoded"
		fi
	done < "$1"
	mv "$1.decoded" "$1"
}

if [[ ! -x /usr/bin/ldapsearch ]]; then
	echo "Necesita instalar el paquete 'ldap-utils'."
	exit
fi

trap "rm -f $TMPFILE" EXIT

main

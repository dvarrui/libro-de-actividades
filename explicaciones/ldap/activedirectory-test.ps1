$db = [ordered]@{
    "ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "organizationalUnit"
    }

    "cn=Charles Bronson,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Charles"
        "surname" = "Bronson"
        "mail" = "bronson@gmail.com"
        "userPrincipalName" = "cbronson@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Chuck Norris,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Chuck"
        "surname" = "Norris"
        "mail" = "norris@hotmail.com"
        "userPrincipalName" = "cnorris@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Clint Eastwood,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Clint"
        "surname" = "Eastwood"
        "mail" = "clint@yahoo.es"
        "userPrincipalName" = "eastwood@iesdpm.com"
        "enabled" = "false"
        "cannotChangePassword" = "true"
    }

    "cn=Michael Knight,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Michael"
        "surname" = "Knight"
        "mail" = "maikel@hotmail.es"
        "userPrincipalName" = "mknight@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=AlumnosASIR1,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "group"
        "members" = @(
            "cn=Michael Knight,ou=ASIR1,dc=iesdpm,dc=com"
            "cn=Chuck Norris,ou=ASIR1,dc=iesdpm,dc=com"
            "cn=Clint Eastwood,ou=ASIR1,dc=iesdpm,dc=com"
            "cn=Charles Bronson,ou=ASIR1,dc=iesdpm,dc=com"
        )
    }

    "cn=ProfesoresASIR1,ou=ASIR1,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "group"
        "members" = @(
            "cn=Francisco Vargas,ou=Profesores,dc=iesdpm,dc=com"
            "cn=Ángel Pérez,ou=Profesores,dc=iesdpm,dc=com"
        )
    }

    "ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "organizationalUnit"
    }

    "cn=MA Barracus,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "MA"
        "surname" = "Barracus"
        "mail" = "ma@barracus.com"
        "userPrincipalName" = "mbarracus@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Phoenix Johnson,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Phoenix"
        "surname" = "Johnson"
        "userPrincipalName" = "phoenix@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Anibal Smith,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Anibal"
        "surname" = "Smith"
        "mail" = "asmith@todoloquepuedas.com"
        "userPrincipalName" = "asmith@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Murdock Sánchez,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Murdock"
        "surname" = "Sánchez"
        "mail" = "murdock69@hotmail.com"
        "userPrincipalName" = "murdock@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "true"
    }

    "cn=AlumnosASIR2,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "group"
        "members" = @(
            "cn=MA Barracus,ou=ASIR2,dc=iesdpm,dc=com"
            "cn=Phoenix Johnson,ou=ASIR2,dc=iesdpm,dc=com"
            "cn=Anibal Smith,ou=ASIR2,dc=iesdpm,dc=com"
            "cn=Murdock Sánchez,ou=ASIR2,dc=iesdpm,dc=com"
        )
    }

    "cn=ProfesoresASIR2,ou=ASIR2,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "group"
        "members" = @(
            "cn=Ángel Pérez,ou=Profesores,dc=iesdpm,dc=com"
            "cn=Paco González,ou=Profesores,dc=iesdpm,dc=com"
        )
    }

    "ou=Profesores,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "organizationalUnit"
    }

    "cn=Francisco Vargas,ou=Profesores,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Francisco"
        "surname" = "Vargas"
        "mail" = "fran@iesdpm.org"
        "userPrincipalName" = "fvargas@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Ángel Pérez,ou=Profesores,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Ángel"
        "surname" = "Pérez"
        "mail" = "angel@iesdpm.org"
        "userPrincipalName" = "aperez@iesdpm.com"
        "enabled" = "true"
        "cannotChangePassword" = "false"
    }

    "cn=Paco González,ou=Profesores,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "user"
        "givenName" = "Paco"
        "surname" = "González"
        "mail" = "paco@iesdpm.org"
        "userPrincipalName" = "pgonzalez@iesdpm.com"
        "enabled" = "false"
        "cannotChangePassword" = "true"
    }

    "cn=Profesores,ou=Profesores,dc=iesdpm,dc=com" = [ordered]@{
        "objectClass" = "group"
        "members" = @(
            "cn=Francisco Vargas,ou=Profesores,dc=iesdpm,dc=com"
            "cn=Ángel Pérez,ou=Profesores,dc=iesdpm,dc=com"
            "cn=Paco González,ou=Profesores,dc=iesdpm,dc=com"
        )
    }
}

$global:errors = 0
$global:total = 0

function check_db() {
    foreach($dn in $db.keys) {

        $obj = $null
        $global:total += $db[$dn].Count + 1

        switch($db[$dn]["objectClass"]) {
            "user" {
                Try { $obj = Get-ADUser -Identity $dn -Properties * -ErrorAction SilentlyContinue }
                Catch {}
            }
            default {
                Try { $obj = Get-ADObject -Identity $dn -Properties * -ErrorAction SilentlyContinue }
                Catch {}
            }
        }
        
        if ($obj) {
            Write-Host -ForegroundColor Green "`n`# $dn"
            foreach($attr in $db[$dn].keys) {
                switch($attr) {
                    "members" {
                        $members = $db[$dn][$attr]
                        foreach($member in $members) {
                            if ($obj.contains("member") -and $obj.item("member") -eq $member) {
                                write-host -foregroundcolor green "  member: $member"
                            } else {
                                write-host -foregroundcolor red "  member: $member"
                                $global:errors += 1
                            }
                        }
                    }
                    default {
                        $value = $db[$dn][$attr]
                        if ($obj.contains($attr) -and ([string]$obj.item($attr) -eq $value)) {
                            write-host -foregroundcolor green "  $($attr): $value"
                        } else {
                            write-host -foregroundcolor red "  $($attr): $value"
                            $global:errors += 1
                        }
                    }
                }
            }
        } else {
            Write-Host -ForegroundColor Red "`n`# $dn"
            $global:errors += $db[$dn].Count + 1
        }
    }
}

Write-Host "###################################"
Write-Host "   PRACTICA DE ACTIVE DIRECTORY"
Write-Host "   SCRIPT VALIDADOR"
Write-Host " autor: Javier Valencia Rodriguez"
Write-Host "###################################"

check_db

if ($global:errors -gt 0) {
    Write-Host "`n`Validación fallida!" -BackgroundColor Red
} else {
    Write-Host "`n`Validación correcta!" -ForegroundColor Black -BackgroundColor Green
}

Write-Host "Entradas: $global:total"
Write-Host "Aciertos: $($global:total - $global:errors)"
Write-Host "Errores:  $global:errors"

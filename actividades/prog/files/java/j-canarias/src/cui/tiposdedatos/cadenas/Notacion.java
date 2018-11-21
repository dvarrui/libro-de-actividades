package cui.tiposdedatos.cadenas;

/**
 * Realiza transformaciones entre las distintas notaciones:
 * (a) Pascal
 * (b) Camel
 * (c) Húngara
 * (d) etc.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051122
 * 
 * Esta clase devuelve un String según las distintas nomenclaturas. Pascal,
 * Húngara, Camel, etc.
 */
public class Notacion {

    /**
     * Por ejemplo int numero -> intNumero
     * @param pTexto
     * @return Devuelve <b>pTexto</p> en notación Húngara
     */
    public static String getCamel(String pTexto) {
        String v = getSinGuiones(pTexto);
        return (v.substring(0, 1).toLowerCase() + v.substring(1));
    }

    /**
     * Por ejemplo int NumeroPersonas -> numeroPersonas
     * @param pTexto
     * @param pTipo
     * @return Devuelve el contenido de <b>pTexto</b> en notación Camel
     */
    public static String getHungara(String pTexto, String pTipo) {
        return (pTipo.toLowerCase() + getPascal(pTexto));
    }

    /**
     * Por ejemplo int numeroPersonas -> NumeroPersonas 
     * @param pTexto
     * @return Devuelve <b>pTexto</b> en notación Pascal
     */
    public static String getPascal(String pTexto) {
        String v = getSinGuiones(pTexto);
        return (v.substring(0, 1).toUpperCase() + v.substring(1));
    }

    /**
     * @param pTexto
     * @return Si pTexto es "log_avisos" se devuelve "logAvisos"
     */
    public static String getSinGuiones(String pTexto) {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < pTexto.length(); i++) {
            if (pTexto.charAt(i) != '_' && pTexto.charAt(i) != '-') {
                // Si NO se encuentra un guión en la posición <i>
                if (i > 0) {
                    if (pTexto.charAt(i - 1) == '_' || pTexto.charAt(i - 1) == '-') // Si anterior GUION -> MAYUS
                    {
                        resultado.append(pTexto.substring(i, i + 1).toUpperCase());
                    } else if (pTexto.substring(i - 1, i).toUpperCase().equals(
                            pTexto.substring(i - 1, i))) // Si anterior MAYUS -> minus
                    {
                        resultado.append(pTexto.substring(i, i + 1).toLowerCase());
                    } else {
                        resultado.append(pTexto.charAt(i));
                    }
                } else {
                    resultado.append(pTexto.charAt(i));
                }
            }
        }
        return resultado.toString();
    }

    /**
     * @return Conviete un tipo SQL en tipo Java.
     */
    public static String getTipoJava(String pTipo) {
        String tipo = pTipo.toLowerCase();

        if (tipo.startsWith("int")) {
            return "int";
        }
        if (tipo.startsWith("float")) {
            return "float";
        }
        if (tipo.startsWith("real")) {
            return "float";
        }
        if (tipo.startsWith("long")) {
            return "long";
        }
        if (tipo.startsWith("date")) {
            return "Date";
        }
        if (tipo.startsWith("string")) {
            return "String";
        }
        if (tipo.startsWith("time")) {
            return "Date";
        }
        if (tipo.startsWith("text")) {
            return "String";
        }
        if (tipo.startsWith("var")) {
            return "String";
        }
        if (tipo.startsWith("boolean")) {
            return "boolean";
        }

        return "[ERROR] Error en el tipo(" + pTipo + ") !!!";
    }

    /**
     * @return Conviete un tipo Java en tipo SQL
     */
    public static String getTipoSQL(String pTipo) {
        String tipo = pTipo.toLowerCase();

        if (tipo.startsWith("int")) {
            return "INTEGER";
        }
        if (tipo.startsWith("float")) {
            return "FLOAT";
        }
        if (tipo.startsWith("real")) {
            return "REAL";
        }
        if (tipo.startsWith("long")) {
            return "LONG";
        }
        if (tipo.startsWith("date")) {
            return "DATE";
        }
        if (tipo.startsWith("string")) {
            return "TEXT";
        }
        if (tipo.startsWith("time")) {
            return "DATETIME";
        }
        if (tipo.startsWith("text")) {
            return "TEXT";
        }
        if (tipo.startsWith("var")) {
            return "VAR";
        }
        if (tipo.startsWith("boolean")) {
            return "BOOLEAN";
        }

        return "[ERROR] Error en el tipo(" + pTipo + ") !!!";
    }

    /**
     * @return Conviete un tipo Java en tipo PostgreSQL
     */
    public static String getTipoPostgreSQL(String pTipo) {
        String tipo = pTipo.toLowerCase();

        if (tipo.startsWith("int")) {
            return "int";
        }
        if (tipo.startsWith("float")) {
            return "real";
        }
        if (tipo.startsWith("real")) {
            return "real";
        }
        if (tipo.startsWith("long")) {
            return "real";
        }
        if (tipo.startsWith("date")) {
            return "date";
        }
        if (tipo.startsWith("string")) {
            return "varchar(200)";
        }
        if (tipo.startsWith("time")) {
            return "datetime";
        }
        if (tipo.startsWith("text")) {
            return "TEXT";
        }
        if (tipo.startsWith("var")) {
            return "varchar(200)";
        }
        if (tipo.startsWith("boolean")) {
            return "boolean";
        }

        return "[ERROR] Error en el tipo(" + pTipo + ") !!!";
    }
}

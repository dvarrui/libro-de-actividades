package david.tenerife.lib;

import david.tenerife.lib.generador.*;

/**
 * Esta clase llama a los distitnos generadores de código
 * (a) Java & Swing
 * (b) Java Server Faces
 * (c) Java Server Pages
 * (d) PHP
 * (e) SQL
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051125
 */
public class Generador {
	BaseDatos bd;

	Parametros param;

	public Generador(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	public void generarCodigo() {
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.JAVA)
				|| param.getValor(Parametros.CODE_SALIDA)
						.equals(Parametros.ALL)) {
			GeneradorJava g = new GeneradorJava(bd, param);
			g.generarCodigo();
			GeneradorSwing g2 = new GeneradorSwing(bd, param);
			g2.generarCodigo();
		}
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.JSF)
				|| param.getValor(Parametros.CODE_SALIDA)
						.equals(Parametros.ALL)) {
			// Se genera código de salida JSF
			//GeneradorJSF g = new GeneradorJSF(bd, param);
			//g.generarCodigo();
		}
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.JSP)
				|| param.getValor(Parametros.CODE_SALIDA)
						.equals(Parametros.ALL)) {
			// Se genera código de salida JSP
			GeneradorJSP g = new GeneradorJSP(bd, param);
			g.generarCodigo();
		}
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.PHP)
					|| param.getValor(Parametros.CODE_SALIDA)
							.equals(Parametros.ALL)) {
				// Se genera código de salida PHP
				GeneradorPHP g = new GeneradorPHP(bd, param);
				g.generarCodigo();
		}
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.SQL)
				|| param.getValor(Parametros.CODE_SALIDA)
						.equals(Parametros.ALL)) {
			// Se genera código de salida SQL
			GeneradorSQL g = new GeneradorSQL(bd, param);
			g.generarCodigo();
	}
	}
}
package david.dir200607.CreadorGUI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CreadorGUI {

	public static void mostrarConsola(String fichero) {
		ClassLoader cargador = ClassLoader.getSystemClassLoader();
		Class clase;
		Field atributo[];
		Method metodo[];
		
		try {
			clase = cargador.loadClass(fichero);
			System.out.println(" * Clase      : "+clase.getSimpleName()+"  ["+clase.getPackage().getName()+"]");
			System.out.println();
			
			atributo = clase.getFields();
			for(int i=0;i<atributo.length;i++) {
				System.out.print(" * Atributo["+i+"]: ");
				
				if (Modifier.isPublic(atributo[i].getModifiers())) System.out.print(" public");
				if (Modifier.isStatic(atributo[i].getModifiers())) System.out.print(" static");
				if (Modifier.isFinal(atributo[i].getModifiers())) System.out.print(" final");
				
				System.out.print(" "+atributo[i].getType().getSimpleName());
				System.out.print(" "+atributo[i].getName());
				System.out.println();
			}
			System.out.println();
			
			metodo = clase.getMethods();
			for(int i=0;i<metodo.length;i++) {
				System.out.print(" * Método["+i+"]  : ");
				
				if (Modifier.isPublic(metodo[i].getModifiers())) System.out.print(" public");
				if (Modifier.isStatic(metodo[i].getModifiers())) System.out.print(" static");
				if (Modifier.isFinal(metodo[i].getModifiers())) System.out.print(" final");

				System.out.print(" "+metodo[i].getReturnType().getSimpleName());
				System.out.print(" "+metodo[i].getName());
				
				//Parámetros del método[i]
				Class parametro[]=metodo[i].getParameterTypes();
				System.out.print("(");
				for(int j=0;j<parametro.length;j++) {
					if (parametro.length>0 && j>0) System.out.print(" ,");
					System.out.print(parametro[j].getSimpleName());
					System.out.print(" param"+j);
				}
				System.out.println(")");
			}
			
		} catch(Exception e) {
			System.err.println("ERROR:"+e);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreadorGUI.mostrarConsola("david.dir200607.ejemplo0402.Calculadora");
	}

}

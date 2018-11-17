import utl.*;

import  java.io.*;
import  java.util.*;

public class prueba
{
	public prueba() {}
   
	/*----------------------------------*/
	public static void main(String args[])
	{
		convierteFicheroHtmlAtxt h = new convierteFicheroHtmlAtxt();
		h.setNombreFichero(args[0]);
		h.ejecutar();
	}
}


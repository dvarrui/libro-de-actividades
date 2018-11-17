package david.lalaguna.prueba;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * @author David Vargas Ruiz
 * @version 20060519
 * Fecha: 20050831
 * El carácter '_' se usa con un significado especial
 * por tanto no puede existir en la entrada
 */
public class ListaV01 
{
	final static String CARACTERESPECIAL = "_"; 
	private ArrayList sublistas;
	
	
	public ListaV01(String pEntrada)
	{
		sublistas = new ArrayList();
		this.descomponerLista(pEntrada);
	}
	
	
	private void descomponerLista(String pEntrada)
	{
		String entrada = pEntrada.trim();
		String patron = new String("\\([^\\(\\)|.]*\\)");
		int j=0;
		String s;
		
		//Patrones y emparejadores
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(entrada);

		while(m.find() && entrada.length()>2)
		{ 
			System.out.println("Entrada   = "+entrada);
			System.out.println("Selecci�n = " + entrada.substring(m.start(),+m.end()));
			
			sublistas.add(0,entrada.substring(m.start(),+m.end()).trim());
						
			if (m.start()>0 && m.end()<entrada.length())
			{entrada = entrada.substring(0,m.start()-1)+" "+CARACTERESPECIAL+" "+entrada.substring(m.end()+1);}
			else if (m.start()==0 && m.end()<entrada.length())
			{entrada = " "+CARACTERESPECIAL+" "+entrada.substring(m.end()+1);}
			else if (m.start()>0 && m.end()==entrada.length())
			{entrada = entrada.substring(0,m.start()-1)+" "+CARACTERESPECIAL+" ";}
			else
			{ entrada = new String(CARACTERESPECIAL);}
			 
			System.out.println("Resultado = "+entrada);
			m = p.matcher(entrada);
		}
		if (!entrada.equals(CARACTERESPECIAL)) System.err.println("ERROR");
		
		for(int i=0;i<sublistas.size();i++)
		{
			System.out.println(i+" "+(String)sublistas.get(i));
		}
		
		while (j<sublistas.size())
		{
			s=(String) sublistas.get(j);
			if (s.contains(CARACTERESPECIAL))
			{
				System.out.println(j+"contiene");
			}
			j++;
		}
	}
	
	public static void main(String[] args) 
	{
		new ListaV01("(david es (un (maquina, 567 y,(que mas da) (eh) ))) )");
	}
}

package david.lalaguna.junit;

import junit.framework.TestCase;
import david.lalaguna.estructuras.Lista;

public class testLista extends TestCase {

	Lista lista;
	
	protected void setUp() throws Exception {
		super.setUp();
	
		lista = new Lista();
	}
	
	public void testGetListaString00() {
		lista.setLista("(A B C D)");
		TestCase.assertEquals("(A B C D)",lista.getString());
	}
	
	public void testGetListaString01() {
		lista.setLista("(A(B(C(D))))");
		TestCase.assertEquals("(A(B(C(D))))",lista.getString());
	}

	public void testGetListaString02() {
		lista.setLista("((((A)B)C)D)");
		TestCase.assertEquals("((((A)B)C)D)",lista.getString());
	}

	public void testGetListaString03() {
		lista.setLista("((A B)C(D E))");
		TestCase.assertEquals("((A B)C(D E))",lista.getString());
	}
	
	public void testGetListaString04() {
		lista.setLista("((_a) (_b))");
		TestCase.assertEquals("((#_a)(#_b))",lista.getString());
	}
	
	public void testGetSublistaString01() {
		lista.setLista("(A(B(C D)))");
		TestCase.assertEquals("(B(C D))",lista.getSublistaString(1));
	}

	public void testGetSublistaString02() {
		lista.setLista("(A(B(C D)))");
		TestCase.assertEquals("(C D)",lista.getSublistaString(2));
	}

	public void testGetSublistaString03() {
		lista.setLista("(A(B(C(D))))");
		TestCase.assertEquals("(D)",lista.getSublistaString(3));
	}
	
	public void testGetProfundidad01() {
		lista.setLista("(A B C D)");
		TestCase.assertEquals(1,lista.getProfundidad());
	}
	
	public void testGetProfundidad02() {
		lista.setLista("(A(B C)D)");
		TestCase.assertEquals(2,lista.getProfundidad());
	}
	
	public void testGetProfundidad03() {
		lista.setLista("(((A) B)((C) D))");
		TestCase.assertEquals(3,lista.getProfundidad());
	}
	
	public void testGetNumeroAtomos01() {
		lista.setLista("(A B C D)");
		TestCase.assertEquals(4,lista.getNumeroAtomos());
	}
	
	public void testGetNumeroAtomos02() {
		lista.setLista("(A(B C)D)");
		TestCase.assertEquals(4,lista.getNumeroAtomos());
	}
	
	public void testGetNumeroAtomos03() {
		lista.setLista("(A(B(C)))");
		TestCase.assertEquals(3,lista.getNumeroAtomos());
	}

	public void testGetNumeroAtomosDeSublista01() {
		lista.setLista("(A B C D)");
		TestCase.assertEquals(4,lista.getNumeroAtomosDeSublista(0));
	}

	public void testGetNumeroAtomosDeSublista02() {
		lista.setLista("(A(B(C)))");
		TestCase.assertEquals(2,lista.getNumeroAtomosDeSublista(1));
	}

}

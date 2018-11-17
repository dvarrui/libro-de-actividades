/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 1999, International
 * Business Machines, Inc., http://www.apache.org.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package david.canarias.xml.dom;                    

import java.io.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Locator;
import org.xml.sax.helpers.*;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.utils.QName;

/**
 * A sample of Adding lines to the DOM Node. This sample program illustrates:
 * - How to override methods from  DocumentHandler ( XMLDocumentHandler) 
 * - How to turn off ignorable white spaces by overriding ignorableWhiteSpace
 * - How to use the SAX Locator to return row position ( line number of DOM element).
 * - How to attach user defined Objects to Nodes using method setUserData
 * This example relies on the following:
 * - Turning off the "fast" DOM so we can use set expansion to FULL 
 * @version
 */

public class DOMAddLines extends DOMParser  {

   /** Print writer. */
   private PrintWriter out;
   static private boolean NotIncludeIgnorableWhiteSpaces = false; 


   public DOMAddLines( String inputName ) {
      //fNodeExpansion = FULL; // faster than: this.setFeature("http://apache.org/xml/features/defer-node-expansion", false);

      try {                        
         this.setFeature( "http://apache.org/xml/features/dom/defer-node-expansion", false ); 
         this.parse( inputName );
         out = new PrintWriter(new OutputStreamWriter(System.out, "UTF8"));
      } catch ( IOException e ) {
         System.err.println( "except" + e );
      } catch ( org.xml.sax.SAXException e ) {
         System.err.println( "except" + e );
      }
   } // constructor

   /** Prints the specified node, recursively. */
   public void print(Node node) {
      // is there anything to do?
      if ( node == null ) {
         return;
      }

      String lineRowColumn = (String ) ((NodeImpl) node).getUserData();

      int type = node.getNodeType();
      switch ( type ) {
         // print document
         case Node.DOCUMENT_NODE: {
               out.println(  lineRowColumn + ":" + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
               print( ((Document)node).getDocumentElement());
               out.flush();
               break;
            }

            // print element with attributes
         case Node.ELEMENT_NODE: {
               out.print( lineRowColumn + ":" + '<');
               out.print(node.getNodeName());
               Attr attrs[] = sortAttributes(node.getAttributes());
               for ( int i = 0; i < attrs.length; i++ ) {
                  Attr attr = attrs[i];
                  out.print(' ');
                  out.print(attr.getNodeName());
                  out.print("=\"");
                  out.print( attr.getNodeValue());
                  out.print('"');
               }
               out.print('>');
               NodeList children = node.getChildNodes();
               if ( children != null ) {
                  int len = children.getLength();
                  for ( int i = 0; i < len; i++ ) {
                     print(children.item(i));
                  }
               }
               break;
            }

            // handle entity reference nodes
         case Node.ENTITY_REFERENCE_NODE: {
               out.print('&');
               out.print(node.getNodeName());
               out.print(';');
               break;
            }

            // print cdata sections
         case Node.CDATA_SECTION_NODE: {
               out.print("<![CDATA[");
               out.print(node.getNodeValue());
               out.print("]]>");
               break;
            }

            // print text
         case Node.TEXT_NODE: {
               out.print(  node.getNodeValue());
               break;
            }

            // print processing instruction
         case Node.PROCESSING_INSTRUCTION_NODE: {
               out.print("<?");
               out.print(node.getNodeName());
               String data = node.getNodeValue();
               if ( data != null && data.length() > 0 ) {
                  out.print(' ');
                  out.print(data);
               }
               out.print("?>");
               break;
            }
      }

      if ( type == Node.ELEMENT_NODE ) {
         out.print("</");
         out.print(node.getNodeName());
         out.print('>');
      }

      out.flush();

   } // print(Node)


   /** Returns a sorted list of attributes. */
   private Attr[] sortAttributes(NamedNodeMap attrs) {

      int len = (attrs != null) ? attrs.getLength() : 0;
      Attr array[] = new Attr[len];
      for ( int i = 0; i < len; i++ ) {
         array[i] = (Attr)attrs.item(i);
      }
      for ( int i = 0; i < len - 1; i++ ) {
         String name  = array[i].getNodeName();
         int    index = i;
         for ( int j = i + 1; j < len; j++ ) {
            String curName = array[j].getNodeName();
            if ( curName.compareTo(name) < 0 ) {
               name  = curName;
               index = j;
            }
         }
         if ( index != i ) {
            Attr temp    = array[i];
            array[i]     = array[index];
            array[index] = temp;
         }
      }

      return (array);

   } // sortAttributes(NamedNodeMap):Attr[]

   /* Methods that we override */

   /*   We override startElement callback  from DocumentHandler */

   public void startElement(QName elementQName, XMLAttrList attrList, int attrListIndex) throws Exception 
   {
      super.startElement(elementQName, attrList, attrListIndex);

      NodeImpl node = null;
      try {
      node = (NodeImpl) this.getProperty( "http://apache.org/xml/properties/dom/current-element-node" );
      //System.out.println( "The node = " + node );  TODO JEFF
      }
      catch( org.xml.sax.SAXException ex )
      {
          System.err.println( "except" + ex );;
      }
      //NodeImpl node = (NodeImpl)getCurrentNode();       // Get current node
      if( node != null )
          node.setUserData(  String.valueOf( getLocator().getLineNumber() ) ); // Save location String into node
   } //startElement 

   /* We override startDocument callback from DocumentHandler */

   public void startDocument(int versionIndex, int encodingIndex,
                                   int standAloneIndex)
   {
     //super.startDocument( versionIndex, encodingIndex,
     //                               standAloneIndex);
     super.startDocument();
     NodeImpl node = null ;
      try {
      node = (NodeImpl) this.getProperty( "http://apache.org/xml/properties/dom/current-element-node" );
      //System.out.println( "The node = " + node );
      }
     catch( org.xml.sax.SAXException ex )
      {
        System.err.println( "except" + ex );;
      }
     
//     NodeImpl node = (NodeImpl)getCurrentNode();       // Get current node
     if( node != null )
          node.setUserData(  String.valueOf( getLocator().getLineNumber() ) ); // Save location String into node
  } //startDocument 
   

   public void ignorableWhitespace(int dataIndex) throws Exception
    {
    if(! NotIncludeIgnorableWhiteSpaces )
       super.ignorableWhitespace( dataIndex);
    else
       ;// Ignore ignorable white spaces
    }// ignorableWhitespace
   


   //
   // Main
   //

   /** Main program entry point. */
   public static void main(String argv[]) {
      // is there anything to do?
      if ( argv.length == 0 ) {
         printUsage();
         System.exit(1);
      }
      // check parameters

      for ( int i = 0; i < argv.length; i++ ) {
         String arg = argv[i];

         // options
         if ( arg.startsWith("-") ) {
            if ( arg.equals("-h") ) {
               printUsage();
               System.exit(1);
            }
            if (arg.equals("-i")) {
                   NotIncludeIgnorableWhiteSpaces = true;
                   continue;
               }
            
         }
      // DOMAddLine parse and print

      DOMAddLines domAddExample = new DOMAddLines( arg );
      Document doc             = domAddExample.getDocument();
      domAddExample.print( doc );

     }
   } // main(String[])

   /** Prints the usage. */
   private static void printUsage() {
      System.err.println("usage: jre dom.DOMAddLines uri ...");
      System.err.println();
      System.err.println("  -h       This help screen.");
      System.err.println("  -i       don't print ignorable white spaces");

   } // printUsage()

}

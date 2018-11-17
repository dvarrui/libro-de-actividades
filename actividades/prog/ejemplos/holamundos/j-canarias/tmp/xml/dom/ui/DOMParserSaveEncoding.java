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
package david.canarias.xml.dom.ui;
import org.xml.sax.InputSource;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xerces.readers.MIME2Java;
import org.apache.xerces.parsers.DOMParser;

    /**
     *  The DOMParserSaveEncoding class extends DOMParser. It also provides
     *  the Java Encoding of the XML document by overriding the startDocument method 
     *  and providing a way to capture the MIME encoding from the XML document which
     *  in turn is converted to the Java Encoding by the internal MIME2Java class.
     *   
     */

  
public class DOMParserSaveEncoding extends DOMParser
  {
   String _mimeEncoding = "DEFAULT";//Default  MIME so we check the file.encoding
   private void setMimeEncoding( String encoding ) {
      _mimeEncoding = encoding;
   }
   private String getMimeEncoding() {
      return (_mimeEncoding);
   }
   public String getJavaEncoding() {
      String javaEncoding = null;
      String mimeEncoding = getMimeEncoding();

      if( mimeEncoding != null )
      {
      if( mimeEncoding.equals( "DEFAULT" ) )
         javaEncoding = System.getProperty( "file.encoding" );
      else if( ( javaEncoding = MIME2Java.convert( mimeEncoding ) ) == null )
         javaEncoding = "UTF8";      // We always return an encoding.
      }

      if( javaEncoding == null )  // Should never return null
         javaEncoding = "UTF8";

      return (javaEncoding);
   }
   public void startDocument( int versionIndex, int encodingIndex, int standAloneIndex ) {
      String encoding = null;
      if ( encodingIndex != -1 ) {
         encoding            = fStringPool.toString( encodingIndex );
         setMimeEncoding( encoding );
      }
 //     super.startDocument( versionIndex,  encodingIndex, standAloneIndex );//passes control to superclass
      super.startDocument();
   }

  }
    

package org.eclipse.jst.j2ee.web;

import org.eclipse.jst.j2ee.internal.web.operations.*;
import java.util.*;

public class ServletTemplate
{
  protected static String nl;
  public static synchronized ServletTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ServletTemplate result = new ServletTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL + NL + "import java.io.IOException;" + NL + "import javax.servlet.ServletException;" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "import javax.servlet.http.HttpServletResponse;" + NL + "" + NL + "/**" + NL + " * Servlet implementation class for Servlet: ";
  protected final String TEXT_4 = NL + " *" + NL + " */" + NL + " ";
  protected final String TEXT_5 = "public";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = "abstract ";
  protected final String TEXT_8 = "final ";
  protected final String TEXT_9 = "class ";
  protected final String TEXT_10 = " extends ";
  protected final String TEXT_11 = " implements ";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = " {" + NL + "    /* (non-Java-doc)" + NL + "\t * @see javax.servlet.http.HttpServlet#HttpServlet()" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_14 = "() {" + NL + "\t\tsuper();" + NL + "\t} ";
  protected final String TEXT_15 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see javax.servlet.Servlet#destroy()" + NL + "\t */" + NL + "\tpublic void destroy() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\tsuper.destroy();" + NL + "\t} ";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\tsuper.doDelete(request, response);" + NL + "\t} ";
  protected final String TEXT_18 = " \t";
  protected final String TEXT_19 = NL + "\t" + NL + "\t/* (non-Java-doc)" + NL + "\t * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)" + NL + "\t */" + NL + "\tprotected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t} ";
  protected final String TEXT_20 = " \t";
  protected final String TEXT_21 = NL + "\t" + NL + "\t/* (non-Java-doc)" + NL + "\t * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)" + NL + "\t */" + NL + "\tprotected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t} ";
  protected final String TEXT_22 = "  \t";
  protected final String TEXT_23 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\tsuper.doPut(request, response);" + NL + "\t} ";
  protected final String TEXT_24 = "  \t";
  protected final String TEXT_25 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see javax.servlet.Servlet#getServletInfo()" + NL + "\t */" + NL + "\tpublic String getServletInfo() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\treturn super.getServletInfo();" + NL + "\t} ";
  protected final String TEXT_26 = "  ";
  protected final String TEXT_27 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see javax.servlet.GenericServlet#init()" + NL + "\t */" + NL + "\tpublic void init() throws ServletException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\tsuper.init();" + NL + "\t} ";
  protected final String TEXT_28 = "  ";
  protected final String TEXT_29 = NL + "\t" + NL + "\t/* (non-Javadoc)" + NL + "\t * @see java.lang.Object#toString()" + NL + "\t */" + NL + "\tpublic String toString() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\treturn super.toString();" + NL + "\t} ";
  protected final String TEXT_30 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateServletTemplateModel model = (CreateServletTemplateModel) argument; 
if (model.getJavaPackageName()!=null && model.getJavaPackageName()!="") { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(model.getJavaPackageName());
    stringBuffer.append(TEXT_2);
    }
    stringBuffer.append(TEXT_3);
    stringBuffer.append(model.getServletClassName());
    stringBuffer.append(TEXT_4);
    if (model.isPublic()) {
    stringBuffer.append(TEXT_5);
    }
    stringBuffer.append(TEXT_6);
    if (model.isAbstract()) {
    stringBuffer.append(TEXT_7);
    }
    if (model.isFinal()) {
    stringBuffer.append(TEXT_8);
    }

    stringBuffer.append(TEXT_9);
    stringBuffer.append(model.getServletClassName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(model.getSuperclassName());
    
 List interfaces = model.getInterfaces(); 
 if (interfaces.size()>0) {
    stringBuffer.append(TEXT_11);
     }
 for (int i=0; i<interfaces.size(); i++) {
   String INTERFACE = (String) interfaces.get(i);
   if (i>0) { 
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(INTERFACE);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(model.getServletClassName());
    stringBuffer.append(TEXT_14);
     if (model.shouldGenDestroy()) { 
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
     if (model.shouldGenDoDelete()) { 
    stringBuffer.append(TEXT_17);
     } 
    stringBuffer.append(TEXT_18);
     if (model.shouldGenDoGet()) { 
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
     if (model.shouldGenDoPost()) { 
    stringBuffer.append(TEXT_21);
     } 
    stringBuffer.append(TEXT_22);
     if (model.shouldGenDoPut()) { 
    stringBuffer.append(TEXT_23);
     } 
    stringBuffer.append(TEXT_24);
     if (model.shouldGenGetServletInfo()) { 
    stringBuffer.append(TEXT_25);
     } 
    stringBuffer.append(TEXT_26);
     if (model.shouldGenInit()) { 
    stringBuffer.append(TEXT_27);
     } 
    stringBuffer.append(TEXT_28);
     if (model.shouldGenToString()) { 
    stringBuffer.append(TEXT_29);
     } 
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}

package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Base class for pages showing catalog entries.
 *  Servlets that extend this class must specify
 *  the catalog entries that they are selling and the page
 *  title <I>before</I> the servlet is ever accessed. This
 *  is done by putting calls to setItems and setTitle
 *  in init.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public abstract class CatalogPage extends HttpServlet {
  private Item[] items;
  private String[] itemIDs;
  private String title;

  /** Given an array of item IDs, look them up in the
   *  Catalog and put their corresponding Item entry
   *  into the items array. The Item contains a short
   *  description, a long description, and a price,
   *  using the item ID as the unique key.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setItems(String[] itemIDs) {
    this.itemIDs = itemIDs;
    items = new Item[itemIDs.length];
    for(int i=0; i<items.length; i++) {
      items[i] = Catalog.getItem(itemIDs[i]);
    }
  }

  /** Sets the page title, which is displayed in
   *  an H1 heading in resultant page.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setTitle(String title) {
    this.title = title;
  }

  /** First display title, then, for each catalog item,
   *  put its short description in a level-two (H2) heading
   *  with the price in parentheses and long description
   *  below. Below each entry, put an order button
   *  that submits info to the OrderPage servlet for
   *  the associated catalog entry.
   *  <P>
   *  To see the HTML that results from this method, do
   *  "View Source" on KidsBooksPage or TechBooksPage, two
   *  concrete classes that extend this abstract class.
   */
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    if (items == null) {
      response.sendError(response.SC_NOT_FOUND,
                         "Missing Items.");
      return;
    }
    PrintWriter out = response.getWriter();
    out.println(ServletUtilities.headWithTitle(title) +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    Item item;
    for(int i=0; i<items.length; i++) {
      out.println("<HR>");
      item = items[i];
      // Show error message if subclass lists item ID
      // that's not in the catalog.
      if (item == null) {
        out.println("<FONT COLOR=\"RED\">" +
                    "Unknown item ID " + itemIDs[i] +
                    "</FONT>");
      } else {
        out.println();
        String formURL =
          "/servlet/coreservlets.OrderPage";
        // Pass URLs that reference own site through encodeURL.
        formURL = response.encodeURL(formURL);
        out.println
          ("<FORM ACTION=\"" + formURL + "\">\n" +
           "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + item.getItemID() + "\">\n" +
           "<H2>" + item.getShortDescription() +
           " ($" + item.getCost() + ")</H2>\n" +
           item.getLongDescription() + "\n" +
           "<P>\n<CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\" " +
           "VALUE=\"Add to Shopping Cart\">\n" +
           "</CENTER>\n<P>\n</FORM>");
}
    }
    out.println("<HR>\n</BODY></HTML>");
  }

  /** POST and GET requests handled identically. */
  
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}

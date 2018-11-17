package coreservlets;

/** A catalog listing the items available in inventory.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class Catalog {
  // This would come from a database in real life
  private static Item[] items =
    { new Item("hall001",
               "<I>Core Servlets and JavaServer Pages</I> " +
                 " by Marty Hall",
               "The definitive reference on servlets " +
                 "and JSP from Prentice Hall and \n" +
                 "Sun Microsystems Press. Nominated for " +
                 "the Nobel Prize in Literature.",
               39.95),
      new Item("hall002",
               "<I>Core Web Programming, Java2 Edition</I> " +
                 "by Marty Hall, Larry Brown, and " +
                 "Paul McNamee",
               "One stop shopping for the Web programmer. " +
                 "Topics include \n" +
                 "<UL><LI>Thorough coverage of Java 2; " +
                 "including Threads, Networking, Swing, \n" +
                 "Java2D, and Collections\n" +
                 "<LI>A fast introduction to HTML 4.01, " +
                 "including frames, style sheets, layers,\n" +
                 "and Netscape and Internet Explorer " +
                 "extensions.\n" +
                 "<LI>A fast introduction to HTTP 1.1, " +
                 "servlets, and JavaServer Pages.\n" +
                 "<LI>A quick overview of JavaScript 1.2\n" +
                 "</UL>",
               49.95),
      new Item("lewis001",
               "<I>The Chronicles of Narnia</I> by C.S. Lewis",
               "The classic children's adventure pitting " +
                 "Aslan the Great Lion and his followers\n" +
                 "against the White Witch and the forces " +
                 "of evil. Dragons, magicians, quests, \n" +
                 "and talking animals wound around a deep " +
                 "spiritual allegory. Series includes\n" +
                 "<I>The Magician's Nephew</I>,\n" +
                 "<I>The Lion, the Witch and the " +
                   "Wardrobe</I>,\n" +
                 "<I>The Horse and His Boy</I>,\n" +
                 "<I>Prince Caspian</I>,\n" +
                 "<I>The Voyage of the Dawn " +
                   "Treader</I>,\n" +
                 "<I>The Silver Chair</I>, and \n" +
                 "<I>The Last Battle</I>.",
               19.95),
      new Item("alexander001",
               "<I>The Prydain Series</I> by Lloyd Alexander",
               "Humble pig-keeper Taran joins mighty " +
                 "Lord Gwydion in his battle against\n" +
                 "Arawn the Lord of Annuvin. Joined by " +
                 "his loyal friends the beautiful princess\n" +
                 "Eilonwy, wannabe bard Fflewddur Fflam," +
                 "and furry half-man Gurgi, Taran discovers " +
                 "courage, nobility, and other values along\n" +
                 "the way. Series includes\n" +
                 "<I>The Book of Three</I>,\n" +
                 "<I>The Black Cauldron</I>,\n" +
                 "<I>The Castle of Llyr</I>,\n" +
                 "<I>Taran Wanderer</I>, and\n" +
                 "<I>The High King</I>.",
               19.95),
      new Item("rowling001",
               "<I>The Harry Potter Trilogy</I> by " +
                 "J.K. Rowling",
               "The first three of the popular stories " +
                 "about wizard-in-training Harry Potter\n" +
                 "topped both the adult and children's " +
                 "best-seller lists. Series includes\n" +
                 "<I>Harry Potter and the " +
                   "Sorcerer's Stone</I>,\n" +
                 "<I>Harry Potter and the " +
                   "Chamber of Secrets</I>, and\n" +
                 "<I>Harry Potter and the " +
                   "Prisoner of Azkaban</I>.",
               25.95)
    };

  public static Item getItem(String itemID) {
    Item item;
    if (itemID == null) {
      return(null);
    }
    for(int i=0; i<items.length; i++) {
      item = items[i];
      if (itemID.equals(item.getItemID())) {
        return(item);
      }
    }
    return(null);
  }
}
               

package Contrib;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

public class AddPopup
    extends Frame
    implements ActionListener, MouseListener {

  PopupMenu pm = new PopupMenu();

  public static void main (String argv[]) {
    new AddPopup();
  }

  public AddPopup() {
    MenuItem item = new MenuItem("file-1");
    item.addActionListener(this);
    Menu m = new Menu("file");
    m.add(item);
    item = new MenuItem("file-2");
    m.add(item);
    MenuBar mb = new MenuBar();
    mb.add(m);

    setMenuBar(mb);
    setSize(100, 100);
    setLayout(new BorderLayout());

    Label l = new Label("label");
    addPopup(l, "label");
    add(l, BorderLayout.NORTH);

    Panel p = new Panel();
    addPopup(p, "Panel");
    add(p, BorderLayout.CENTER);

    Button b = new Button("button");
    addPopup(b, "button");
    add(b, BorderLayout.SOUTH);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    System.out.println("actionPerformed, event=" + e + ", mod=" + getMods(e));
    System.out.println(" command=" + e.getActionCommand());
    System.out.println(" param=" + e.paramString());
    System.out.println(" source=" + e.getSource());
  }

  String getMods(ActionEvent e) { return getMods(e.getModifiers()); }

  String getMods(MouseEvent e) { return getMods(e.getModifiers()); }

  String getMods(int mods) {
    String modstr = "";
    if ((mods & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK)
      modstr += (" SHIFT");
    if ((mods & ActionEvent.ALT_MASK) == ActionEvent.ALT_MASK)
      modstr += (" ALT");
    if ((mods & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK)
      modstr += (" CTRL");
    if ((mods & ActionEvent.META_MASK) == ActionEvent.META_MASK)
      modstr += (" META");
    return modstr;
  }

  public void mouseClicked (MouseEvent e) {
    mouseAction("mouseClicked", e);
  }

  public void mouseEntered (MouseEvent e) {
  }

  public void mouseExited (MouseEvent e) {
  }

  public void mousePressed (MouseEvent e) {
    mouseAction("mousePressed", e);
  }

  public void mouseReleased (MouseEvent e) {
    mouseAction("mouseReleased", e);
  }

  void mouseAction (String which, MouseEvent e) {
    Component c = e.getComponent();
    System.out.println(which + "e=" + e + ", mods=" + getMods(e) +
                       ", component=" + c);
    if (e.isPopupTrigger()) {
      System.out.println("isPopup");
      PopupMenu pm = getHash(c);
      pm.show(c, c.getSize().width/2, c.getSize().height/2);
    }
  }


  void addPopup(Component c, String name) {
    PopupMenu pm = new PopupMenu();
    MenuItem mi = new MenuItem(name + "-1");
    mi.addActionListener(this);
    pm.add(mi);

    mi = new MenuItem(name + "-2");
    pm.add(mi);

    setHash(c, pm);
    c.add(pm);
    c.addMouseListener(this);
  }


  Hashtable popupTable = new Hashtable();

  void setHash(Component c, PopupMenu p) {
    popupTable.put(c, p);
  }

  PopupMenu getHash(Component c) {
    return (PopupMenu)(popupTable.get(c));
  }

}



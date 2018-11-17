package Contrib.oreilly.swing;

//  JogShuttle.java
// A custom jog shuttle component.  (Some VCRs have such a thing for doing
// variable speed fast-forward and fast-reverse.)  An example of using the
// JogShuttle can be found in Sketch.java.

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JogShuttle extends JComponent implements ChangeListener {

    private BoundedRangeModel model;

    //  The dialInsets property tells how far the dial is inset
    //  from the sunken border.
    private Insets dialInsets = new Insets(3, 3, 3, 3);

    //  The valuePerRevolution property tells how many units the dial
    //  takes to make a complete revolution.
    private int valuePerRevolution;

    //  Constructors
    public JogShuttle() {
        init(new DefaultBoundedRangeModel());
    }

    public JogShuttle(BoundedRangeModel m) {
        init(m);
    }

    public JogShuttle(int min, int max, int value) {
        init(new DefaultBoundedRangeModel(value, 1, min, max));
    }

    protected void init(BoundedRangeModel m) {
        setModel(m);
        valuePerRevolution = m.getMaximum() - m.getMinimum();
        setMinimumSize(new Dimension(80, 80));
        setPreferredSize(new Dimension(80, 80));
        updateUI();
    }

    public void setUI(JogShuttleUI ui) {super.setUI(ui);}

    public void updateUI() {
       setUI((JogShuttleUI)UIManager.getUI(this));
       invalidate();
    }

    public String getUIClassID() {
        return JogShuttleUI.UI_CLASS_ID;
    }

    public void setModel(BoundedRangeModel m) {
        BoundedRangeModel old = model;
        if (old != null)
            old.removeChangeListener(this);

        if (m == null)
            model = new DefaultBoundedRangeModel();
        else
            model = m;
        model.addChangeListener(this);

        firePropertyChange("model", old, model);
    }

    public BoundedRangeModel getModel() {
        return model;
    }

    //  Methods
    public void resetToMinimum() {model.setValue(model.getMinimum());}

    public void resetToMaximum() {model.setValue(model.getMaximum());}

    public void stateChanged(ChangeEvent e) {repaint();}

    //  Accessors and mutators
    public int getMinimum() {return model.getMinimum();}

    public void setMinimum(int m) {
        int old = getMinimum();
        if (m != old) {
            model.setMinimum(m);
            firePropertyChange("minimum", old, m);
        }
    }

    public int getMaximum() {return model.getMaximum();}

    public void setMaximum(int m) {
        int old = getMaximum();
        if (m != old) {
            model.setMaximum(m);
            firePropertyChange("maximum", old, m);
        }
    }

    public int getValue() {return model.getValue();}

    public void setValue(int v) {
        int old = getValue();
        if (v != old) {
            model.setValue(v);
            firePropertyChange("value", old, v);
        }
    }


    //  Display-specific properties
    public int getValuePerRevolution() {return valuePerRevolution;}

    public void setValuePerRevolution(int v) {
        int old = getValuePerRevolution();
        if (v != old) {
            valuePerRevolution = v;
            firePropertyChange("valuePerRevolution", old, v);
        }
        repaint();
    }

    public void setDialInsets(Insets i) {dialInsets = i;}

    public void setDialInsets(int top, int left, int bottom, int right) {
        dialInsets = new Insets(top, left, bottom, right);
    }

    public Insets getDialInsets() {return dialInsets;}
}

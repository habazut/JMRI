package jmri.jmrit.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class PositionablePropertiesUtilTest {

    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Editor ef = new EditorScaffold();
        PositionableIcon iti = new PositionableIcon(ef);
        PositionablePropertiesUtil t = new PositionablePropertiesUtil(iti);
        Assert.assertNotNull("exists",t);
    }

    @Test
    public void testNoChangesApplyLabel() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Editor ef = new EditorScaffold();
        PositionableLabel label = new PositionableLabel("one", ef);
        label.setBounds(80, 80, 40, 40);
        ef.putItem(label);
        // store properties before
        Color bc = label.getBackground();
        boolean opaque = label.isOpaque();
        Color fc = label.getForeground();
        Font f = label.getFont();
        PositionablePropertiesUtil t = new PositionablePropertiesUtil(label);
        t.display();
        t.fontApply(); // fontApply is package protected in PositionablePropertiesUtil.
        // we haven't made any changes, so the properties should be the same as before.
        Assert.assertEquals("No Change Background Color",bc,label.getBackground());
        Assert.assertEquals("No Change opaque",opaque,label.isOpaque());
        Assert.assertEquals("No Change Foreground Color",fc,label.getForeground());
        Assert.assertEquals("No Change Font",f,label.getFont());
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        JUnitUtil.setUp();
    }

    @After
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(PositionablePropertiesUtilTest.class);

}

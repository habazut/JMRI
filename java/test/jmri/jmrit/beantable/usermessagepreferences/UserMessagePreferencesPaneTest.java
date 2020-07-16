package jmri.jmrit.beantable.usermessagepreferences;

import jmri.swing.PreferencesPanelTestBase;
import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test simple functioning of UserMessagePreferencesPane
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class UserMessagePreferencesPaneTest extends PreferencesPanelTestBase {

    @Override
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
        JUnitUtil.initDefaultUserMessagePreferences();
        prefsPanel = new UserMessagePreferencesPane();
    }

    @Override
    @AfterEach
    public void tearDown() {
        prefsPanel = null;
        JUnitUtil.deregisterBlockManagerShutdownTask();
        JUnitUtil.tearDown();
    }
    @Override
    public void isPersistant() {
        assertThat(prefsPanel.isPersistant()).isFalse();
    }

    @Override
    public void getPreferencesTooltip() {
        // should this actually return null?
        assertThat(prefsPanel.getPreferencesTooltip()).isNull();
    }

    @Override
    public void getLabelKey(){
        //should this actually return null?
        assertThat(prefsPanel.getLabelKey()).isNotNull();
    }

    @Override
    public void getPreferencesTitle() {
        // should this actually return null?
        assertThat(prefsPanel.getPreferencesTitle()).isNull();
    }

}


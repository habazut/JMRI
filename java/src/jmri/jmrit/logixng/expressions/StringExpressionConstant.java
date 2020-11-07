package jmri.jmrit.logixng.expressions;

import java.util.Locale;

import jmri.jmrit.logixng.Category;
import jmri.jmrit.logixng.FemaleSocket;

/**
 * Constant value.
 * This can be useful for example by the ActionThrottle.
 * 
 * @author Daniel Bergqvist Copyright 2019
 */
public class StringExpressionConstant extends AbstractStringExpression {

    private String _value;
    
    public StringExpressionConstant(String sys, String user)
            throws BadUserNameException, BadSystemNameException {
        
        super(sys, user);
    }
    
    /** {@inheritDoc} */
    @Override
    public Category getCategory() {
        return Category.ITEM;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isExternal() {
        return false;
    }
    
    public void setValue(String value) {
        assertListenersAreNotRegistered(log, "setValue");
        _value = value;
    }
    
    public String getValue() {
        return _value;
    }
    
    /** {@inheritDoc} */
    @Override
    public String evaluate() {
        return _value;
    }
    
    /** {@inheritDoc} */
    @Override
    public void reset() {
        // Do nothing
    }
    
    @Override
    public FemaleSocket getChild(int index)
            throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public String getShortDescription(Locale locale) {
        if (_value == null) {
            return Bundle.getMessage(locale, "StringExpressionConstantNull");
        } else {
            return Bundle.getMessage(locale, "StringExpressionConstant1", _value);
        }
    }

    @Override
    public String getLongDescription(Locale locale) {
        return getShortDescription(locale);
    }

    /** {@inheritDoc} */
    @Override
    public void setup() {
        // Do nothing
    }
    
    /** {@inheritDoc} */
    @Override
    public void registerListenersForThisClass() {
        // This class does not have any listeners registered, but we still don't
        // want a caller to change the value then listeners are registered.
        // So we set this property to warn the caller when the caller is using
        // the class in the wrong way.
        _listenersAreRegistered = true;
    }
    
    /** {@inheritDoc} */
    @Override
    public void unregisterListenersForThisClass() {
        _listenersAreRegistered = false;
    }
    
    /** {@inheritDoc} */
    @Override
    public void disposeMe() {
    }
    
    
    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StringExpressionConstant.class);
    
}

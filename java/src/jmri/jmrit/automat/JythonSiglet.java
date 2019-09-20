package jmri.jmrit.automat;

import jmri.InstanceManager;

import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This sample Automaton invokes a Jython interpreter to handle a script that
 * defines a Siglet implementation.
 * <p>
 * The python file should define two functions:
 * <ul>
 * <li>defineIO()
 * <li>setOutput()
 * </ul>
 *
 * @author Bob Jacobsen Copyright (C) 2003
 */
public class JythonSiglet extends Siglet {

    PythonInterpreter interp;

    public JythonSiglet(String file) {
        filename = file;
    }

    String filename;

    /**
     * Initialize this object.
     * <ul>
     * <li>Create the Python interpreter.
     * <li>Load the generally-available objects
     * <li>Read the file
     * <li>Run the python defineIO routine
     * </ul>
     * Initialization of the Python in the actual script file is deferred until
     * the {@link #defineIO} method.
     */
    @Override
    public void defineIO() {

        PySystemState.initialize();

        interp = new PythonInterpreter();

        // load some general objects
        interp.set("inputs", inputs);
        interp.set("outputs", outputs);
        interp.set("turnouts", InstanceManager.turnoutManagerInstance());
        interp.set("sensors", InstanceManager.sensorManagerInstance());
        interp.set("signals", InstanceManager.getDefault(jmri.SignalHeadManager.class));
        interp.set("dcc", InstanceManager.getNullableDefault(jmri.CommandStation.class));

        interp.set("CLOSED", Integer.valueOf(jmri.Turnout.CLOSED));
        interp.set("THROWN", Integer.valueOf(jmri.Turnout.THROWN));
        interp.set("ACTIVE", Integer.valueOf(jmri.Sensor.ACTIVE));
        interp.set("INACTIVE", Integer.valueOf(jmri.Sensor.INACTIVE));
        interp.set("GREEN", Integer.valueOf(jmri.SignalHead.GREEN));
        interp.set("YELLOW", Integer.valueOf(jmri.SignalHead.YELLOW));
        interp.set("RED", Integer.valueOf(jmri.SignalHead.RED));

        // have jython read the file
        interp.execfile(filename);

        // execute the init routine in the jython class
        interp.exec("defineIO()");

        log.info("inputs[0]: {}", inputs[0]);
    }

    /**
     * Invoke the Jython setOutput function
     */
    @Override
    public void setOutput() {
        if (interp == null) {
            log.error("No interpreter, so cannot handle automat");
            return;
        }
        interp.exec("setOutput()");
    }

    // initialize logging
    private static final Logger log = LoggerFactory.getLogger(JythonSiglet.class);

}

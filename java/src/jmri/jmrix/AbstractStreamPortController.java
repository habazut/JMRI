// AbstractStreamPortController.java

package jmri.jmrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Provide an abstract implementation of a *StreamPortController for 
 * stream based ports.
 * <P>
 * Implementing classes need to implement status and configure from 
 * the portAdapter interface, along with any system specific requirements.
 * <P>
 * @author			Paul Bender Copyright (C) 2014
 * @version			$Revision$
 */
public abstract class AbstractStreamPortController extends AbstractPortController {

    protected String _name = null;
    protected DataInputStream input = null;
    protected DataOutputStream output = null;
    protected SystemConnectionMemo adaptermemo = null;

    public AbstractStreamPortController(DataInputStream in,DataOutputStream out, String pname){
       _name = pname;
       input = in;
       output = out;
    }

    // returns the InputStream from the port
    public DataInputStream getInputStream(){ return input; }

    // returns the outputStream to the port
    public DataOutputStream getOutputStream(){ return output; }

    public String getCurrentPortName() { return _name; }

    public void recover() {
       // no recovery possible here.
    }

    @Override
    public SystemConnectionMemo getSystemConnectionMemo() {
        log.debug("adapter memo {}null", (this.adaptermemo != null) ? "not " : "");
        return this.adaptermemo;
    }

    // connection shouldn't require any action.
    public void connect() {}

    public void dispose(){
       adaptermemo.dispose();
       adaptermemo=null;
       input = null;
       output = null;
    }

   

    static private Logger log = LoggerFactory.getLogger(AbstractStreamPortController.class.getName());

}

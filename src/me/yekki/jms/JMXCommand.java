package me.yekki.jms;


import me.yekki.jmx.utils.JMXWrapperRemote;

import javax.naming.Context;
import java.util.Hashtable;

public abstract class JMXCommand extends Thread implements Constants {

    protected AppConfig config;

    protected JMXWrapperRemote jmxWrapper;

    protected String username;

    protected String password;

    protected String url;

    protected boolean isEdit;

    protected boolean isDomainRuntime;

    public JMXCommand(AppConfig config) {

        this.config = config;
        Hashtable<String, String> env = new Hashtable<>(config.getEnvironment());
        url = env.get(Context.PROVIDER_URL);
        username = env.get(Context.SECURITY_PRINCIPAL);
        password = env.get(Context.SECURITY_CREDENTIALS);
        jmxWrapper = new JMXWrapperRemote();
        isEdit = false;
        isDomainRuntime = true;
    }

    public void init(boolean isEdit, boolean isDomainRuntime) {

        this.isEdit = isEdit;
        this.isDomainRuntime = isDomainRuntime;
    }

    public void connect(boolean isEdit, boolean isDomainRuntime) throws JMSClientException {

        try {
            jmxWrapper.connectToAdminServer(isEdit, isDomainRuntime, username, password, url);
        }
        catch (Exception e) {
            throw new JMSClientException("Failed to connect admin server:" + e.getMessage());
        }
    }

    public void disconnect() throws JMSClientException {

        try {
            jmxWrapper.disconnectFromAdminServer(true);
        }
        catch (Exception e) {
            throw new JMSClientException("Failed to disconnect from admin server:" + e.getMessage());
        }
    }

    abstract public void execute() throws JMSClientException;

    @Override
    public void run() {

        try {
            connect(isEdit, isDomainRuntime);
            execute();
            disconnect();
        }
        catch(JMSClientException je) {
            je.printStackTrace();
        }
    }
}

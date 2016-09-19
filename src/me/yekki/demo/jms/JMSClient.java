package me.yekki.demo.jms;

import me.yekki.demo.jms.cmd.*;
import org.apache.commons.cli.CommandLine;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.Context;
import java.io.Serializable;

public interface JMSClient extends AutoCloseable, Constants {

    public void close();
    public Context getInitialContext();
    public JMSContext getJMSContext();
    public JMSConsumer getConsumer();
    public JMSProducer getProducer();
    public ConnectionFactory getConnectionFactory();
    public AppConfig getAppConfig();
    public void send(Serializable msg);

    public static void execute(Constants.Role role, CommandLine cmd) {

        AppConfig config = AppConfig.newConfig(role, cmd);
        Thread thread = null;

        switch (role) {
            case Sender:
                int total = 1;
                if (cmd.hasOption("n")) total = Integer.parseInt(cmd.getOptionValue("n"));
                thread = new SendCommand(config, total);
                break;
            case Cleaner:
                thread =  new CleanJMXCommand(config);
                break;
            case Uninstaller:
                thread =  new UninstallWLSTCommand(config);
                break;
            case StoreAdmin:
                thread =  new StoreAdminCommand();
                break;
            case Installer:
                thread = new InstallWLSTCommand(config);
                break;
            default:
                thread = new HelpCommand(cmd);
        }

        thread.start();

        try {
            thread.join();
        }
        catch( InterruptedException ie) {
        }
    }
}

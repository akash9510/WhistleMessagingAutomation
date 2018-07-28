package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class Log4J {
	
	public static org.apache.log4j.Logger	logf	= org.apache.log4j.Logger.getLogger("framework");
	public static org.apache.log4j.Logger	logp	= org.apache.log4j.Logger.getLogger("product");

	// public static Logger log = LoggerFactory.getLogger(Log4J.class);
	public static void loadLogger()
	{
		//static {
		try
		{

			Properties logProperties = new Properties();
			
			logProperties.load(new FileInputStream("src/main/resources/Properties/log4j.properties"));
			
			logProperties.setProperty("log4j.appender.MAIN_LOG.File", "src/main/resources/Logs/Framework.html");
			logProperties.setProperty("log4j.appender.MAIN_LOG.layout", "library.MyHTMLLayout");
			PrintWriter writer = new PrintWriter("src/main/resources/Logs/Framework.html");
			writer.print("");
			writer.close();

			PropertyConfigurator.configure(logProperties);
			PatternLayout pLayout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m - (in %M) %n");
			FileAppender faFrame = null;
			FileAppender faProd = null;
			try
			{
				String frameworkLogFilename = "FrameworkLog.txt";
				String productLogFilename = "appLog.txt";
				//String productLogFilepath = mainServlet.path + "/Framework/Log/";
				String productLogFilepath = null;
				PrintWriter file = new PrintWriter("src/main/resources/Logs/" + productLogFilename);
				file.print("");
				file.close();
				productLogFilepath = "src/main/resources/Logs/";
				faFrame = new FileAppender(pLayout, productLogFilepath + frameworkLogFilename, true);
				faProd = new FileAppender(pLayout, productLogFilepath + productLogFilename, true);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			logf.addAppender(faFrame);
			logp.addAppender(faProd);
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
		}

	}

}

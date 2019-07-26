import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.HostConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.VersionLoggerListener;

import javax.servlet.ServletException;
import java.io.File;
import repository.repository_implementations.UserRepositoryImpl;

public class App {

  public static void main(String[] args) {
    File catalinaHome = new File("/tomcat.8090");

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(9050);
    tomcat.setBaseDir(catalinaHome.getAbsolutePath());
    tomcat.getServer().addLifecycleListener(new VersionLoggerListener());

    // File war = new File("D:/Projects/TestsProject/target/TestsProject.war");
    File war = new File("D:/Projects/TestsProject/target/TestsProject.war");

    try {
      tomcat.addWebapp("", war.getAbsolutePath());
    } catch (ServletException ex) {
      ex.printStackTrace();
    }

    tomcat.getHost().addLifecycleListener(new HostConfig());

    try {
      tomcat.start();
    } catch (LifecycleException ex) {
      ex.printStackTrace();
    }

    tomcat.getServer().await();



  }

}

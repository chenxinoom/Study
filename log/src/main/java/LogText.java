import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LogText {

    private static Logger logger = LoggerFactory.getLogger(LogText.class);
//public static Log logger  = LogFactory.getLog(LogText.class);
    public static void main(String[] args) {
        //级别为debug的日志
        logger.debug("Hello! debug!");
        //级别为info的日志
        logger.info("Hello! info!");
        //级别为warn的日志
        logger.warn("Hello! warn!");
        //级别为error的日志
        logger.error("Hello! error!");

        String path = System.getProperty("user.dir");
        System.out.println(path);

        File file = new File("test");
        if (!file.exists()) {
            file.mkdir();
        }

        Math.abs(foo());
        String osName = System.getProperty("os.name");
        System.out.println(osName);
    }
    public static int foo(){
        return 1;
    }
}

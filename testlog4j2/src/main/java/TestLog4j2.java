import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaopei
 * @create 2018-12-23 0:10
 */
public class TestLog4j2 {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
        RestTemplate rest = new RestTemplate();
        String url = "http://10.145.196.85:8080";
        rest.getForEntity(url, String.class);
        System.out.println(rest.getForEntity(url, String.class));
    }
}

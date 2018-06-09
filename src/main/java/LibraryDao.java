import org.h2.tools.Server;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class LibraryDao {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LibraryDao.class);
    private Server tcpServer;
    private String url;
    private Server webServer;


    public static void main(String[] args) throws InterruptedException {
        LibraryDao libraryDao = new LibraryDao();
        libraryDao.initialize();
        while (true) {
            Thread.sleep(500);
        }
    }

    public void initialize() {
        String paramsString = "-baseDir /tmp/h2-test -tcpPort 8081 -tcpAllowOthers";
        String[] dbParams = paramsString.split(" ");
        try {
            tcpServer = Server.createTcpServer(dbParams).start();
            webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8080").start();
            System.out.println(tcpServer.getStatus());
            System.out.println(webServer.getStatus());
            url = String.format("jdbc:h2:%s/test", tcpServer.getURL());
            InputStream sqlFile = LibraryDao.class.getResourceAsStream("skrypt.sql");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sqlFile));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String[] queries = stringBuilder.toString().split(";");
            for (String query : queries) {
                try (Connection con = DriverManager.getConnection(url, "sa", "")) {
                    Statement st = con.createStatement();
                    st.executeUpdate(query);
                }
            }


        } catch (Exception e) {
            log.error("Failed to initialize data base ", e);
        }

    }

    public void close() {
        if (tcpServer != null) {
            tcpServer.stop();
        }
        if (webServer != null) {
            webServer.stop();
        }

    }

}

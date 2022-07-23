//package ci.palmafrique.vonabri.utils;
//
//import org.apache.commons.net.ftp.FTPClient;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class FTPUploadFile {
//    public static void main(String[] args) {
//        FTPClient client = new FTPClient();
//        String filename = "/Users/dembelesiaka/card.jpeg";
//
//        // Read the file from resources folder.
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        try (InputStream is = classLoader.getResourceAsStream(filename)) {
//            client.connect("ftp://api-vonabri.cip-palmafrique.com");
//            boolean login = client.login("api-vonabri.cip-palm_mmtxbldsc2q", "Rb1k3b70");
//            if (login) {
//                System.out.println("Login success...");
//                // Store file to server
//                client.storeFile(filename, is);
//                client.logout();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                client.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

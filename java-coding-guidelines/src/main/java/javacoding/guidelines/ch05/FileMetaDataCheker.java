package javacoding.guidelines.ch05;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Apache Tika로 파일 메타 데이터 체크
 */
public class FileMetaDataCheker {
    public static boolean checkMetaData(File f, String expectedContentType) {
        try (InputStream is = new FileInputStream(f)) {
            ContentHandler contentHandler = new BodyContentHandler();

            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());

            Parser parser = new AutoDetectParser();

            try {
                parser.parse(is, contentHandler, metadata, new ParseContext());
            } catch (TikaException | SAXException e) {
                e.printStackTrace();
                return false;
            }

            String fileContentType = metadata.get(Metadata.CONTENT_TYPE);
            System.out.println("Content Type of " + f.getName() + " is " + fileContentType);
            if (fileContentType.equalsIgnoreCase(expectedContentType)) {
                return true;
            }

            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkMetaData(new File("build.gradle"), "text/plain"));
        System.out.println(checkMetaData(new File("gradlew"), "application/x-sh"));
        System.out.println(checkMetaData(new File("config/checkstyle/checkstyle.xml"), "application/xml"));
        System.out.println(checkMetaData(new File("java.jpg"), "image/png")); // 확장자와 달리 실제로는 png 파일임
    }
}

package javacoding.guidelines.ch08;

import nux.xom.pool.XQueryFactory;
import nux.xom.xquery.XQuery;
import nux.xom.xquery.XQueryException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 이 예제에서 사용한 XQuery 라이브러리 는 JBoss 에서 나오는 nux 와 xom 인 것으로 보임.
// https://xom.nu/
public class XPathInjectionCompliant {
    private static boolean doLogin(String username, char[] password) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, XQueryException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("java-coding-guidelines/src/main/java/javacoding/guidelines/ch08/user.xml");
        String pwd = hashPassword(password);

        XQuery xquery = new XQueryFactory().createXQuery(new File("java-coding-guidelines/src/main/java/javacoding/guidelines/ch08/login.xq"));
        Map<String, String> queryVars = new HashMap<>();
        queryVars.put("userName", username);
        queryVars.put("password", pwd);

//        NodeList nodes = xquery.execute(doc, null, queryVars).toNodes();


        return false;
    }

    /**
     * 문자열로 입력된 비밀번호를 일방향 해싱을 해야하지만 이번 예제에서는 비밀번호가 무의미하므로 pass
     */
    private static String hashPassword(char[] password) {
        return "hashed";
    }
}

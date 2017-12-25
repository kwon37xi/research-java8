package javacoding.guidelines.ch08;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class XPathInjectionNoncompliant {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        // and 가 먼저 평가 되고 그 뒤에 or 가 평가 되는데, or는 무조건 'Utah'라는 사용자가 존재하므로 항상 true가 됨. 따라서 통과
        System.out.println("Login with Utah : " + doLogin("Utah' or '1'='1", new char[]{'a', 'b', 'c'}));
        System.out.println("Login with Bohdi : " + doLogin("Bohdi' or '1'='1", new char[]{'a', 'b', 'c'}));

        // '1'='1' 은 실행 결과에 아무런 영향을 못 끼침.
        System.out.println("Login with Utah : " + doLogin("Utah' or '1'='2", new char[]{'a', 'b', 'c'}));

        // 존재하지 않는 사용자로 하면 '1'='1' 이라도 false임.
        System.out.println("Login with None : " + doLogin("None' or '1'='1", new char[]{'a', 'b', 'c'}));

    }

    private static boolean doLogin(String username, char[] password) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("java-coding-guidelines/src/main/java/javacoding/guidelines/ch08/user.xml");
        String pwd = hashPassword(password);

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile("//users/user[username/text()='" + username + "' and password/text()='" + pwd + "' ]");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i).getChildNodes().item(1).getChildNodes().item(0);
            System.out.println("Authenticated: " + node.getNodeValue());
        }

        return (nodes.getLength() >= 1);
    }

    /**
     * 문자열로 입력된 비밀번호를 일방향 해싱을 해야하지만 이번 예제에서는 비밀번호가 무의미하므로 pass
     */
    private static String hashPassword(char[] password) {
        return "hashed";
    }
}

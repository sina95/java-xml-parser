/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPathConstants;

/**
 *
 * @author HP
 */
public class XmlParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);

        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
        XPathExpression xPE = path.compile("//book[price>10 and translate(publish_date,'-','')>20050000]");
        File xmlDoc = new File("resources/xmlDocument.xml");
        InputSource source = new InputSource(new FileInputStream(xmlDoc));
        Object result = xPE.evaluate(source, XPathConstants.NODESET);
        NodeList nList = (NodeList) result;

        for (int i = 0; i < nList.getLength(); i++) {
            System.out.println(nList.item(i).getNodeName() + " ");
            System.out.println(nList.item(i).getAttributes().item(0));
            System.out.println(" Author: " + nList.item(i).getFirstChild().getNextSibling().getTextContent());
            System.out.println(" Title: " + nList.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println(" Genre: " + nList.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println(" Price: " + nList.item(i).getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());
            System.out.println(" Publish date: " + nList.item(i).getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());
            System.out.println(" Description: " + nList.item(i).getLastChild().getPreviousSibling().getTextContent());
            System.out.println("\n");
        }
    }

}

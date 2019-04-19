package com.ideal.xmlparse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.jdom.Content;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author zhaopei
 * @create 2019-04-19 10:11
 */
public class XmlParse {
    @Test
    public void parseXml() throws Exception {
        File file = new File("C:\\Users\\zhaopei\\Desktop\\log4j2.xml");
        StringBuffer stringBuffer = new StringBuffer();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        String content = stringBuffer.toString();
        Document doc = DocumentHelper.parseText(content);
        Node rootNode = doc.selectSingleNode("//Root");
        System.out.println(rootNode);

    }
}

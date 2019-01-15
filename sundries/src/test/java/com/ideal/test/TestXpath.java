package com.ideal.test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.junit.Test;

public class TestXpath {
	@Test
	public void testXpath() {
		String a="sss";
		Class<String> clazz=(Class<String>) a.getClass();
		System.out.println(clazz);
		System.out.println(a.getClass());
		System.out.println(String.class);
		
		System.out.println(System.getProperty("user.dir"));

		try {
			File f = new File("C:\\Users\\zhaopei\\Desktop\\2.xml");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			System.out.println(sb.toString());
			Document parseText = DocumentHelper.parseText(sb.toString());
			
		List<Node>	fa=parseText
			.selectNodes("//SOO[@type='QRY_MKT_RES_INST_RES_TYPE']/MKT_RES_INST");
			
			System.out.println("-----------------------------"+fa);
			// Map xmlMap = new HashMap();
			// xmlMap.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
			// xmlMap.put("ns1", "http://points.com");
			// xmlMap.put("defalt", "http://svccont");
			//
			// XPath xPath1 =
			// parseText.createXPath("//soap:Envelope//soap:Body//ns1:queryPointsBasicInfoResponse//ns1:out//defalt:objQryInfoRsp//defalt:objInfoCont//defalt:InfoCont//defalt:objPointsInfo");
			// xPath1.setNamespaceURIs(xmlMap);
			// Element selectSingleNode =
			// (Element)xPath1.selectSingleNode(parseText);
			// Node
			// node=parseText.selectSingleNode("//soap:Envelope//soap:Body//queryPointsBasicInfoResponse//out//objQryInfoRsp//objInfoCont//InfoCont//objPointsInfo");
			Map xmlMap = new HashMap();
			xmlMap.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
			xmlMap.put("ns1", "http://points.com");
			xmlMap.put("defalt", "http://svccont");

			XPath xPath = parseText.createXPath(
					"//soap:Envelope//soap:Body//ns1:queryPointsHistoryInfoResponse//ns1:out//defalt:objQryInfoRsp//defalt:objInfoCont//defalt:InfoCont//defalt:objPointCosumeHistory//defalt:PointCosumeHistory");
			xPath.setNamespaceURIs(xmlMap);
			List<Element> selectNodes = xPath.selectNodes(parseText);
			System.out.println(selectNodes);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDocumentException() {
		String xml = "ssssss2222s";
		try {
			Document docu1 = DocumentHelper.parseText(xml);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
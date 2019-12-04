package com.codeanalysis.simulator;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class XMLReaderSimulator {
    /**
     * JAXP attribute used to configure the schema language for validation.
     */
    private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    /**
     * JAXP attribute value indicating the XSD schema language.
     */
    private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";


    /**
     * Suffix for DTD files
     */
    public static final String DTD_SUFFIX = ".dtd";

    /**
     * Suffix for schema definition files
     */
    public static final String XSD_SUFFIX = ".xsd";


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }
        });
        builder.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                System.out.println("publicId:" + publicId + " systemId:" + systemId);
                if (systemId != null) {
                    if (systemId.endsWith(DTD_SUFFIX)) {

                    } else if (systemId.endsWith(XSD_SUFFIX)) {
                        Properties mappings =
                                PropertiesLoaderUtils.loadAllProperties("META-INF/spring.schemas");
                        Map<String, String> schemaMappings = new ConcurrentHashMap<String, String>(mappings.size());
                        CollectionUtils.mergePropertiesIntoMap(mappings, schemaMappings);

                        Resource resource = new ClassPathResource(schemaMappings.get(systemId));
                        InputSource source = new InputSource(resource.getInputStream());
                        source.setPublicId(publicId);
                        source.setSystemId(systemId);
                        return source;
                    }
                }
                return null;
            }
        });
        Document doc = builder.parse(new ClassPathResource("spring/beanFactoryTest.xml").getInputStream());
        Element root = doc.getDocumentElement();
        if (isDefaultNamespace(root.getNamespaceURI())) {
            System.out.println("parsing default namespace:"+root.getTagName());
            NodeList nl = root.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node instanceof Element) {
                    Element ele = (Element) node;
                    if (isDefaultNamespace(ele.getNamespaceURI())) {
                        System.out.println("parsing default namespace:"+ele.getTagName());
                    }
                    else {

                    }
                }
            }
        }
        else {

        }
    }


    public static boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || "http://www.springframework.org/schema/beans".equals(namespaceUri));
    }
}

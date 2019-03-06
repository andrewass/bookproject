package com.bookproject.misc;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodreadsAPI {

    private GoodreadsAPI() {
    }

    public static Map<String, String> getFieldsForBook(String bookTitle, String apiKey) {
        try {
            Map<String, String> fieldValues = new HashMap<>();
            List<NameValuePair> keyValues = new ArrayList<>();
            keyValues.add(new BasicNameValuePair("q", bookTitle));
            keyValues.add(new BasicNameValuePair("key", apiKey));
            keyValues.add(new BasicNameValuePair("search[field]", "title"));
            URL url = generateUrlWithParameters("https://www.goodreads.com/search/index.xml", keyValues);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                String xmlResponse = getXMLfromResponse(httpURLConnection);
                fieldValues.put("goodreads_id", fetchValueFromXML(
                        "/GoodreadsResponse/search/results/work/id", xmlResponse));
                fieldValues.put("image_url", fetchValueFromXML(
                        "/GoodreadsResponse/search/results/work/best_book/image_url", xmlResponse));
            }
            return fieldValues;
        } catch (IOException | ParserConfigurationException | URISyntaxException | SAXException |
                XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getXMLfromResponse(HttpURLConnection httpURLConnection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        builder.append(reader.readLine());
        String lineRead;
        while ((lineRead = reader.readLine()) != null) {
            builder.append(lineRead);
        }
        reader.close();
        return builder.toString();
    }

    private static String fetchValueFromXML(String expression, String responseXML) throws
            ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(responseXML)));
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = xPath.compile(expression);
        return xPathExpression.evaluate(document, XPathConstants.STRING).toString();
    }

    private static URL generateUrlWithParameters(String url, List<NameValuePair> paramters)
            throws URISyntaxException, MalformedURLException {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.addParameters(paramters);
        return uriBuilder.build().toURL();
    }
}

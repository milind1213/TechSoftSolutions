package com.techSoft.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Assert;
import java.io.*;
import java.util.List;

public class HtmlParser {

    /** Parses an HTML file. */
    public Document parseHtml(String filePath, String encodingStandard, String baseUrl) {
        try {
            return Jsoup.parse(new File(filePath), encodingStandard, baseUrl);
        } catch (IOException e) {
            Assert.fail("Failed to parse HTML: " + filePath);
            return null;
        }
    }

    /** Parses HTML string. */
    public Document parseHtml(String html) {
        return Jsoup.parse(html);
    }

    /** Parses HTML with base URL. */
    public Document parseHtml(String html, String baseUrl) {
        return Jsoup.parse(html, baseUrl);
    }

    /** Parses body fragment. */
    public Document parseHtmlBodyFragment(String html) {
        return Jsoup.parseBodyFragment(html);
    }

    /** Fetches and parses HTML from a URL. */
    public Document parseHtmlFromURL(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Extracts attribute from element. */
    public String extractAttribute(Element element, String key) {
        return element.attr(key);
    }

    /** Extracts text from element. */
    public String extractText(Element element) {
        return element.text();
    }

    /** Extracts inner HTML. */
    public String extractInnerHtml(Element element) {
        return element.html();
    }

    /** Extracts outer HTML. */
    public String extractOuterHtml(Element element) {
        return element.outerHtml();
    }

    /** Appends HTML/text to an element. */
    public void appendHtml(Element element, String selector, String htmlOrText) {
        element.select(selector).append(htmlOrText);
    }

    /** Creates an element in a document. */
    public void createElement(Document document, String tagName) {
        document.createElement(tagName);
    }

    /** Prepends HTML/text to an element. */
    public void prependHtml(Element element, String selector, String htmlOrText) {
        element.select(selector).prepend(htmlOrText);
    }

    /** Removes an element by CSS selector. */
    public void removeElement(Document document, String selector) {
        document.select(selector).remove();
    }

    /** Gets an element by ID. */
    public Element getElementById(Document document, String id) {
        return document.getElementById(id);
    }

    /** Gets an element by class name. */
    public Element getElementByClass(Document document, String className, int index) {
        return document.getElementsByClass(className).get(index);
    }

    /** Gets elements by tag name. */
    public List<Element> getElementsByTag(Document document, String tagName) {
        return document.getElementsByTag(tagName);
    }

    /** Removes an attribute from elements by CSS selector. */
    public void removeAttribute(Document document, String selector, String attribute) {
        document.select(selector).removeAttr(attribute);
    }

    /** Gets an element by CSS selector. */
    public Element getElementByCss(Document document, String selector, int index) {
        return document.select(selector).get(index);
    }

    /** Replaces text inside an element. */
    public Element replaceText(Document document, String selector, int index, String newText) {
        return document.select(selector).get(index).text(newText);
    }

    /** Extracts text from a Reader. */
    public static String extractText(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return Jsoup.parse(sb.toString()).text();
    }
}

/*
 * The MIT License
 * 
 * Copyright 2011 JogAmp Community, Sven Gothel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package hudson.plugins.tolerantxmlparsing;

import hudson.Extension;
import hudson.tasks.junit.SuiteResult.SuiteResultParserConfigurationContext;
import hudson.util.io.ParserConfigurator;
import hudson.tasks.junit.XMLEntityResolver;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// import java.util.logging.Logger;
// import java.util.logging.Level;

/**
 * See <tt>src/main/resources/hudson/plugins/XMLTolerantParsing/*.jelly</tt>
 * for the actual HTML fragment for the configuration screen.
 */
@Extension
public class TolerantXMLEntityResolver extends XMLEntityResolver implements EntityResolver {
    boolean enabled = true; // TODO: check how to configure his with jelly, extentions can't have a constructor ..

    @Override
    public void configure(SAXReader reader, Object context) {
        if (enabled && context instanceof SuiteResultParserConfigurationContext) {
            // LOGGER.info("TolerantXMLEntityResolver: enabled");
            try {
                TolerantXMLErrorHandler.attach(reader, true);
            } catch (SAXException ex) {
                ex.printStackTrace();
            }
        }
        super.configure(reader, context);
    }

    // private static final Logger LOGGER = Logger.getLogger(XMLEntityResolver.class.getName());
}


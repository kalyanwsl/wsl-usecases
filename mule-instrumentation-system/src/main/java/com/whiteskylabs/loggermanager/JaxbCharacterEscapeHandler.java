package com.whiteskylabs.loggermanager;

import java.io.IOException;
import java.io.Writer;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

/**
 *	This class avoids the conversion of < to &lt; and > to &gt; etc
 */
public class JaxbCharacterEscapeHandler implements CharacterEscapeHandler {

	@Override
	public void escape(char[] buf, int start, int len, boolean isAttValue,
            Writer out) throws IOException {

	    for (int i = start; i < start + len; i++) {
	            char ch = buf[i];
	            out.write(ch);
	    }
	}

}

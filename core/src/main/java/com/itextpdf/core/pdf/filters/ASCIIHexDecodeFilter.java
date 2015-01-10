package com.itextpdf.core.pdf.filters;

import com.itextpdf.basics.PdfException;
import com.itextpdf.basics.io.ByteBuffer;
import com.itextpdf.core.pdf.PdfDictionary;
import com.itextpdf.core.pdf.PdfName;
import com.itextpdf.core.pdf.PdfObject;
import com.itextpdf.core.pdf.PdfTokeniser;

import java.io.ByteArrayOutputStream;

/**
 * Handles ASCIIHexDecode filter
 */
public class ASCIIHexDecodeFilter implements FilterHandler {

    @Override
    public byte[] decode(byte[] b, PdfName filterName, PdfObject decodeParams, PdfDictionary streamDictionary) throws PdfException {
        b = ASCIIHexDecode(b);
        return b;
    }

    public static byte[] ASCIIHexDecode(final byte in[]) throws PdfException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean first = true;
        int n1 = 0;
        for (int k = 0; k < in.length; ++k) {
            int ch = in[k] & 0xff;
            if (ch == '>')
                break;
            if (PdfTokeniser.isWhitespace(ch))
                continue;
            int n = ByteBuffer.getHex(ch);
            if (n == -1)
                throw new PdfException(PdfException.IllegalCharacterInAsciihexdecode);
            if (first)
                n1 = n;
            else
                out.write((byte)((n1 << 4) + n));
            first = !first;
        }
        if (!first)
            out.write((byte)(n1 << 4));
        return out.toByteArray();
    }
}
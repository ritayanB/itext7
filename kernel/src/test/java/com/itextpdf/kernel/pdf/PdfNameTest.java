package com.itextpdf.kernel.pdf;

import com.itextpdf.test.ITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class PdfNameTest extends ITextTest {

    @Test
    public void specialCharactersTest(){
        String str1 = " %()<>";
        String str2 = "[]{}/#";
        PdfName name1 = new PdfName(str1);
        Assert.assertEquals(str1, createStringByEscaped(name1.getInternalContent()));
        PdfName name2 = new PdfName(str2);
        Assert.assertEquals(str2, createStringByEscaped(name2.getInternalContent()));
    }

}

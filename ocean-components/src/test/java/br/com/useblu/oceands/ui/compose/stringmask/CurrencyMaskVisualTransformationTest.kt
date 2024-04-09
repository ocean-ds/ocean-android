package br.com.useblu.oceands.ui.compose.stringmask

import androidx.compose.ui.text.AnnotatedString
import org.junit.Assert
import org.junit.Test

class CurrencyMaskVisualTransformationTest {

    private val currencyMask = CurrencyMaskVisualTransformation()

    @Test
    fun testFilter() {
        Assert.assertEquals("1.234.567,89", currencyMask.filter(AnnotatedString("1234567.89")).text.text)
        Assert.assertEquals("1.234,56", currencyMask.filter(AnnotatedString("1234.56")).text.text)
        Assert.assertEquals("0,56", currencyMask.filter(AnnotatedString("0.56")).text.text)
        Assert.assertEquals("", currencyMask.filter(AnnotatedString("")).text.text)
    }

    @Test
    fun testOriginalToTransformed_123456() {
        val offsetMapping = currencyMask.offsetTranslator("1234.56", "1.234,56")

        Assert.assertEquals(0, offsetMapping.originalToTransformed(0))
        Assert.assertEquals(1, offsetMapping.originalToTransformed(1))
        Assert.assertEquals(3, offsetMapping.originalToTransformed(2))
        Assert.assertEquals(4, offsetMapping.originalToTransformed(3))
        Assert.assertEquals(5, offsetMapping.originalToTransformed(4))
        Assert.assertEquals(6, offsetMapping.originalToTransformed(5))
        Assert.assertEquals(7, offsetMapping.originalToTransformed(6))
        Assert.assertEquals(8, offsetMapping.originalToTransformed(7))
    }

    @Test
    fun testOriginalToTransformed_12123123456() {
        val offsetMapping = currencyMask.offsetTranslator("121231234.56", "121.231.234,56")

        Assert.assertEquals(0, offsetMapping.originalToTransformed(0))
        Assert.assertEquals(1, offsetMapping.originalToTransformed(1))
        Assert.assertEquals(2, offsetMapping.originalToTransformed(2))
        Assert.assertEquals(3, offsetMapping.originalToTransformed(3))

        Assert.assertEquals(5, offsetMapping.originalToTransformed(4))
        Assert.assertEquals(6, offsetMapping.originalToTransformed(5))
        Assert.assertEquals(7, offsetMapping.originalToTransformed(6))

        Assert.assertEquals(9, offsetMapping.originalToTransformed(7))
        Assert.assertEquals(10, offsetMapping.originalToTransformed(8))
        Assert.assertEquals(11, offsetMapping.originalToTransformed(9))

        Assert.assertEquals(12, offsetMapping.originalToTransformed(10))
        Assert.assertEquals(13, offsetMapping.originalToTransformed(11))
        Assert.assertEquals(14, offsetMapping.originalToTransformed(12))
    }

    @Test
    fun testTransformedToOriginal_123456() {
        val offsetMapping = currencyMask.offsetTranslator("1234.56", "1.234,56")

        Assert.assertEquals(0, offsetMapping.transformedToOriginal(0))
        Assert.assertEquals(1, offsetMapping.transformedToOriginal(1))

        Assert.assertEquals(1, offsetMapping.transformedToOriginal(2))
        Assert.assertEquals(2, offsetMapping.transformedToOriginal(3))
        Assert.assertEquals(3, offsetMapping.transformedToOriginal(4))
        Assert.assertEquals(4, offsetMapping.transformedToOriginal(5))
        Assert.assertEquals(5, offsetMapping.transformedToOriginal(6))
        Assert.assertEquals(6, offsetMapping.transformedToOriginal(7))
        Assert.assertEquals(7, offsetMapping.transformedToOriginal(8))
    }

    @Test
    fun testTransformedToOriginal_12123123456() {
        val offsetMapping = currencyMask.offsetTranslator("121231234.56", "121.231.234,56")

        Assert.assertEquals(0, offsetMapping.transformedToOriginal(0))
        Assert.assertEquals(1, offsetMapping.transformedToOriginal(1))
        Assert.assertEquals(2, offsetMapping.transformedToOriginal(2))
        Assert.assertEquals(3, offsetMapping.transformedToOriginal(3))
        Assert.assertEquals(3, offsetMapping.transformedToOriginal(4))

        Assert.assertEquals(4, offsetMapping.transformedToOriginal(5))
        Assert.assertEquals(5, offsetMapping.transformedToOriginal(6))
        Assert.assertEquals(6, offsetMapping.transformedToOriginal(7))
        Assert.assertEquals(6, offsetMapping.transformedToOriginal(8))

        Assert.assertEquals(7, offsetMapping.transformedToOriginal(9))
        Assert.assertEquals(8, offsetMapping.transformedToOriginal(10))
        Assert.assertEquals(9, offsetMapping.transformedToOriginal(11))

        Assert.assertEquals(10, offsetMapping.transformedToOriginal(12))
        Assert.assertEquals(11, offsetMapping.transformedToOriginal(13))
        Assert.assertEquals(12, offsetMapping.transformedToOriginal(14))
    }
}
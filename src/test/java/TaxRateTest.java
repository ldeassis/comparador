import org.junit.jupiter.api.Test;

import PFxPJ.util.TaxRate;

import static org.junit.jupiter.api.Assertions.*;

public class TaxRateTest {
    @Test
    public void testOverlap() {
        TaxRate taxRate1 = new TaxRate(0.0, 1000.0, 0.1);
        TaxRate taxRate2 = new TaxRate(500.0, 1500.0, 0.2);
        TaxRate taxRate3 = new TaxRate(1000.0, 3000.0, 0.3);
        TaxRate taxRate4 = new TaxRate(4000.0, 5000.0, 0.4);

        assertTrue(taxRate1.overlap(taxRate2));
        assertTrue(taxRate2.overlap(taxRate1));
        assertTrue(taxRate1.overlap(taxRate3));
        assertTrue(taxRate3.overlap(taxRate1));
        assertTrue(taxRate2.overlap(taxRate3));
        assertTrue(taxRate3.overlap(taxRate2));
        assertFalse(taxRate1.overlap(taxRate4));
        assertFalse(taxRate4.overlap(taxRate1));
        assertFalse(taxRate2.overlap(taxRate4));
        assertFalse(taxRate4.overlap(taxRate2));
        assertFalse(taxRate3.overlap(taxRate4));
        assertFalse(taxRate4.overlap(taxRate3));
    }

    @Test
    public void testEquals() {
        TaxRate taxRate1 = new TaxRate(0.0, 1000.0, 0.1);
        TaxRate taxRate2 = new TaxRate(0.0, 1000.0, 0.1);
        TaxRate taxRate3 = new TaxRate(500.0, 1500.0, 0.2);

        assertTrue(taxRate1.equals(taxRate2));
        assertTrue(taxRate2.equals(taxRate1));
        assertFalse(taxRate1.equals(taxRate3));
        assertFalse(taxRate3.equals(taxRate1));
    }

    @Test
    public void testGetMin() {
        TaxRate taxRate1 = new TaxRate(0.0, 1000.0, 0.1);
        assertEquals(0.0, taxRate1.getMin());

        TaxRate taxRate2 = new TaxRate(500.0, 1500.0, 0.2);
        assertEquals(500.0, taxRate2.getMin());

        TaxRate taxRate3 = new TaxRate(2000.0, 3000.0, 0.3);
        assertEquals(2000.0, taxRate3.getMin());

        TaxRate taxRate4 = new TaxRate(4000.0, 5000.0, 0.4);
        assertEquals(4000.0, taxRate4.getMin());
    }

    @Test
    public void testGetMax() {
        TaxRate taxRate1 = new TaxRate(0.0, 1000.0, 0.1);
        assertEquals(1000.0, taxRate1.getMax());

        TaxRate taxRate2 = new TaxRate(500.0, 1500.0, 0.2);
        assertEquals(1500.0, taxRate2.getMax());

        TaxRate taxRate3 = new TaxRate(2000.0, 3000.0, 0.3);
        assertEquals(3000.0, taxRate3.getMax());

        TaxRate taxRate4 = new TaxRate(4000.0, 5000.0, 0.4);
        assertEquals(5000.0, taxRate4.getMax());
    }

    @Test
    public void testGetRate() {
        TaxRate taxRate1 = new TaxRate(0.0, 1000.0, 0.1);
        assertEquals(0.1, taxRate1.getRate());

        TaxRate taxRate2 = new TaxRate(500.0, 1500.0, 0.2);
        assertEquals(0.2, taxRate2.getRate());

        TaxRate taxRate3 = new TaxRate(2000.0, 3000.0, 0.3);
        assertEquals(0.3, taxRate3.getRate());

        TaxRate taxRate4 = new TaxRate(4000.0, 5000.0, 0.4);
        assertEquals(0.4, taxRate4.getRate());
    }
}
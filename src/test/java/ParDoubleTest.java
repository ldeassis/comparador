import org.junit.jupiter.api.Test;

import PFxPJ.util.ParDouble;

import static org.junit.jupiter.api.Assertions.*;

public class ParDoubleTest {
    @Test
    public void testEquals() {
        ParDouble par1 = new ParDouble(1.0, 2.0);
        ParDouble par2 = new ParDouble(1.0, 2.0);
        ParDouble par3 = new ParDouble(2.0, 3.0);

        assertTrue(par1.equals(par2));
        assertTrue(par2.equals(par1));
        assertFalse(par1.equals(par3));
        assertFalse(par3.equals(par1));
    }

    @Test
    public void testGetters() {
        ParDouble par = new ParDouble(1.0, 2.0);

        assertEquals(1.0, par.getValue());
        assertEquals(2.0, par.getExpected());
    }

    @Test
    public void testToString() {
        ParDouble par = new ParDouble(1.0, 2.0);

        assertEquals("ParDouble [value=1.0, expected=2.0]", par.toString());
    }
}
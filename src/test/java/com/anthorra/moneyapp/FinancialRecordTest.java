
package com.anthorra.moneyapp;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Anthorra
 */
public class FinancialRecordTest
{
    public FinancialRecord fr;
    public FinancialRecord fr2;
    
    public FinancialRecordTest()
    {
        fr = new FinancialRecord(1500, true, 1, 2, "Kenyeret vettem", "2023-03-18");
        fr2 = new FinancialRecord(100000, false, 0, 0, "Fizetés", "2023-03-10");
    }
    
    @BeforeEach
    public void setUp()
    {
        //fr = new FinancialRecord(1500, true, 1, 2, 'Kenyeret vettem', '2023-03-18');
    }

    @Test
    public void testGetters()
    {
        assertEquals(1500, fr.getAmount(),"testGetAmount-fr");
        assertEquals(100000, fr2.getAmount(),"testGetAmount-fr2");
        
        assertTrue(fr.isIsExpense(),"testIsIsExpense-fr");
        assertFalse(fr2.isIsExpense(),"testIsIsExpense-fr2");
        
        assertEquals(1, fr.getType(),"testGetType-fr");
        assertEquals(0, fr2.getType(),"testGetType-fr2");
        
        assertEquals(2, fr.getSubtype(),"testGetSubtype-fr");
        assertEquals(0, fr2.getSubtype(),"testGetSubtype-fr2");
        
        assertEquals("Kenyeret vettem", fr.getComment(),"testGetComment-fr");
        assertEquals("Fizetés", fr2.getComment(),"testGetComment-fr2");
        
        assertEquals("2023-03-18", fr.getRealizedDate(),"testGetRealizedDate-fr");
        assertEquals("2023-03-10", fr2.getRealizedDate(),"testGetRealizedDate-fr2");
    }

   
    
    @Test
    public void testSetters()
    {
        fr.setAmount(2000);
        fr2.setAmount(120000);
        assertEquals(2000, fr.getAmount(),"setAmount-fr");
        assertEquals(120000, fr2.getAmount(),"setAmount-fr2");
        
        fr.setIsExpense(false);
        fr2.setIsExpense(true);
        assertFalse(fr.isIsExpense(),"setIsExpense-fr");
        assertTrue(fr2.isIsExpense(),"setIsExpense-fr2");
        
        fr.setType(4);
        fr2.setType(5);
        assertEquals(4, fr.getType(),"setType-fr");
        assertEquals(5, fr2.getType(),"setType-fr2");
        
        fr.setSubtype(7);
        fr2.setSubtype(9);
        assertEquals(7, fr.getSubtype(),"setSubtype-fr");
        assertEquals(9, fr2.getSubtype(),"setSubtype-fr2");
        
        fr.setComment("Kávé");
        fr2.setComment("Új fezetés");
        assertEquals("Kávé", fr.getComment(),"setComment-fr");
        assertEquals("Új fezetés", fr2.getComment(),"setComment-fr2");
        
        fr.setRealizedDate("2023-03-20");
        fr2.setRealizedDate("2023-03-09");
        assertEquals("2023-03-20", fr.getRealizedDate(),"setRealizedDate-fr");
        assertEquals("2023-03-09", fr2.getRealizedDate(),"setRealizedDate-fr2");
        
        
    }
    
    @Test
    public void testExtremeCase()
    {
        FinancialRecord fr3 = new FinancialRecord(-5500, true, 1, 2, "Kenyeret vettem", "2023-03-18");
        assertEquals(0, fr3.getAmount(),"testExtremeCase-negativeAmount-fr3");
        
        /*KÉRDÉS: ha van Constructor és Setter is, akkor érdemes-e a constructorban is a settert használni? vagy lehet?*/
        /* ha használom akkor overridable method? */
        /* SETTER teszteket külön a szélsőségesekre */
        
        //fr3.setAmount(1521.6565);
        fr3 = new FinancialRecord(1521.6562, true, 1, 2, "Kenyeret vettem", "2023-03-18");
        assertEquals(1521.656, fr3.getAmount(),"testExtremeCase-roundAmount-fr3");
    }
    
}

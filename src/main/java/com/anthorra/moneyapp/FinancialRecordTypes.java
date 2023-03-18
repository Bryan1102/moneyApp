
package com.anthorra.moneyapp;

/**
 *
 * @author Anthorra
 */
public class FinancialRecordTypes
{
    private int mainId;
    private String mainDesc;
    private int subId;
    //private int subParentId;
    private String subDesc;

    public FinancialRecordTypes(int mainId, String mainDesc, int subId, /*int subParentId,*/ String subDesc)
    {
        this.mainId = mainId;
        this.mainDesc = mainDesc;
        this.subId = subId;
        //this.subParentId = subParentId;
        this.subDesc = subDesc;
    }

    public String getMainDesc()
    {
        return mainDesc;
    }

    public String getSubDesc()
    {
        return subDesc;
    }
    
    

    
    
    
    
}

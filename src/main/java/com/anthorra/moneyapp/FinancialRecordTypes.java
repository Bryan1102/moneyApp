
package com.anthorra.moneyapp;

/**
 *
 * @author Anthorra
 */
public class FinancialRecordTypes
{
    public int id;
    public String desc;
    public String created;
    public boolean isDeleted;

    public FinancialRecordTypes(int id, String desc, String created, boolean isDeleted)
    {
        this.id = id;
        this.desc = desc;
        this.created = created;
        this.isDeleted = isDeleted;
    }


    
    
    
}

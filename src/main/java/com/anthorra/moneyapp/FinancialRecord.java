
package com.anthorra.moneyapp;

import java.util.Date;

/**
 *
 * @author Anthorra
 */
public class FinancialRecord
{
    private double amount;
    private boolean isExpense;
    private int type, subtype;
    private String realizedDate;
    private String comment;

    public FinancialRecord(double amount, boolean isExpense, int type, int subtype, String comment, String realizedDate)
    {
        this.amount = amount;
        this.isExpense = isExpense;
        this.type = type;
        this.subtype = subtype;
        this.comment = comment;
        this.realizedDate = realizedDate;
        
    }

    /* GETTER SECTION */
    public double getAmount()    {        return amount;    }
    public boolean isIsExpense()    {        return isExpense;    }
    public int getType()    {        return type;    }
    public int getSubtype()    {        return subtype;    }
    public String getComment()    {        return comment;    }
    public String getRealizedDate()    {        return realizedDate;    }

    /* SETTER SECTION */
    public void setAmount(double amount)    {        this.amount = amount;    }
    public void setIsExpense(boolean isExpense)    {        this.isExpense = isExpense;    }
    public void setType(int type)    {        this.type = type;    }
    public void setSubtype(int subtype)    {        this.subtype = subtype;    }
    public void setRealizedDate(String realizedDate)    {        this.realizedDate = realizedDate;    }
    public void setComment(String comment)    {        this.comment = comment;    }
    
    
    
    
    
}

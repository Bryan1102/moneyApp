
package com.anthorra.moneyapp;

/**
 *
 * @author Anthorra
 */
public class FinancialRecordTypes
{
    MainType mainType;
    SubType subType;

    public FinancialRecordTypes(MainType mainType, SubType subType)
    {
        this.mainType = mainType;
        this.subType = subType;
    }

    
    
    private class MainType
    {
        public int id;
        public String desc;
        public boolean isDeleted;
        
        public MainType()
        {
            this.id = id;
            this.desc = desc;
            this.isDeleted = isDeleted;
        }

        /* GETTER */
        public String getMainTypeDesc()        {            return desc;        }
        
        
    }
        
    public class SubType
    {
        public int id;
        public int parentId;
        public String desc;
        public boolean isDeleted;
        
        public SubType()
        {
            this.id = id;
            this.parentId = parentId;
            this.desc = desc;
            this.isDeleted = isDeleted;
        }    

        /* GETTER */
        public String getSubTypeDesc()        {            return desc;        }
        
        
    }

    
    
    
    
}

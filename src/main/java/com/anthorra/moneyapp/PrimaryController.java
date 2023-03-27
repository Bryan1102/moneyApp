package com.anthorra.moneyapp;

import static com.anthorra.moneyapp.DBHandler.queryTypeList;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable
{
    /* GENERAL */
        /* Containers */
    @FXML FlowPane paneTitle, paneBottom;
    @FXML HBox paneNav, paneMain;
    @FXML VBox pageInput;
    
        /* Tables */
    @FXML TreeTableView<FinancialRecordTypes> treeTableTypes;
    @FXML TreeTableColumn<FinancialRecordTypes, String> treeTableTypeColumn, treeTableSubTypeColumn, treeTableTypeId, treeTableTypeSubTypeId;
    
    /* FINANCIAL RECORD PAGE */
        /* Controls */
    @FXML Button btnSubmit;
    @FXML RadioButton rbtnIncome, rbtnExpense;
    @FXML TextField inpAmount;
    @FXML ComboBox<String> cbSubType, cbType;
    @FXML DatePicker inpDatePicker;
    @FXML TextArea inpComment;
    @FXML Label txtStatus;
    
        /* Others */
    StatusMessage sm;
    ArrayList<FinancialRecordTypes> frtList = new ArrayList<>();
    
    /* INITIALIZE */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        sm = new StatusMessage(txtStatus);
        frtList = queryTypeList();
        
        initInputPage();
        initInputTypePage();
        
    }
    
    /*PAGE 1*/
    public void initInputPage()
    {
        cbType.setPromptText("- Válassz -");
        cbSubType.setPromptText("- Válassz -");
        
        refreshInputPageTypes();
        
        ToggleGroup rbtnToggleGroup = new ToggleGroup();
        rbtnExpense.setToggleGroup(rbtnToggleGroup);
        rbtnIncome.setToggleGroup(rbtnToggleGroup);
        rbtnExpense.setSelected(true);
                
        inpDatePicker.setValue(LocalDate.now());
    }
    public void refreshInputPageTypes()
    {
        cbType.getItems().clear();
        
        for(int i = 0; i < frtList.size(); i++)
        {
            if(i==0 || frtList.get(i).getMainId()!=frtList.get(i-1).getMainId())
            {
                cbType.getItems().add(frtList.get(i).getMainDesc());
                //cbType.getItems().add(frtList.get(i).getMainId(), frtList.get(i).getMainDesc());
            }
        }
    }
    @FXML
    public void refreshInputPageSubType()
    {
        cbSubType.getItems().clear();
        
        for(int i = 0; i < frtList.size(); i++)
        {
            if(frtList.get(i).getMainDesc().equals(cbType.getValue()))
            {
                cbSubType.getItems().add(frtList.get(i).getSubDesc());
                //cbSubType.getItems().add(frtList.get(i).getSubId(), frtList.get(i).getSubDesc());
            }
        }
    }
    public void clearInputPage()
    {
        rbtnExpense.setSelected(true);
        inpAmount.clear();
        cbType.getSelectionModel().clearSelection();
        cbSubType.getSelectionModel().clearSelection();
        //inpDatePicker.setValue(LocalDate.now());
        inpComment.clear();
    }
    
    /*PAGE 2*/
    public void initInputTypePage()
    {
        treeTableTypeId.setCellValueFactory(new TreeItemPropertyValueFactory<>("mainId"));
        treeTableTypeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("mainDesc"));
        treeTableTypeSubTypeId.setCellValueFactory(new TreeItemPropertyValueFactory<>("subId"));
        treeTableSubTypeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("subDesc"));
        
        TreeItem top = new TreeItem(new FinancialRecordTypes(0,"Kategóriák",0,""));
        
        TreeItem ti, ti2;
        for(int i = 0; i < frtList.size(); i++)
        {
            if(i==0 || frtList.get(i).getMainId()!=frtList.get(i-1).getMainId())
            {
                ti2 = new TreeItem(new FinancialRecordTypes(frtList.get(i).getMainId(),frtList.get(i).getMainDesc(),0,""));
                top.getChildren().add(ti2);
                ti2.setExpanded(true);
                
                for(int j = i; j < frtList.size() && frtList.get(i).getMainId()==frtList.get(j).getMainId(); j++)
                {
                    ti = new TreeItem(frtList.get(j));
                    ti2.getChildren().add(ti);
                }
            }
        }
        top.setExpanded(true);
        treeTableTypes.setRoot(top);
    }
    
    public void refreshInputTypePage()
    {
        frtList = queryTypeList();
        
        TreeItem top = treeTableTypes.getRoot();
        top.getChildren().clear();
        
        TreeItem ti, ti2;
        for(int i = 0; i < frtList.size(); i++)
        {
            if(i==0 || frtList.get(i).getMainId()!=frtList.get(i-1).getMainId())
            {
                ti2 = new TreeItem(new FinancialRecordTypes(frtList.get(i).getMainId(),frtList.get(i).getMainDesc(),0,""));
                top.getChildren().add(ti2);
                //ti2.setExpanded(true);
                
                for(int j = i; j < frtList.size() && frtList.get(i).getMainId()==frtList.get(j).getMainId(); j++)
                {
                    ti = new TreeItem(frtList.get(j));
                    ti2.getChildren().add(ti);
                }
            }
        }
        treeTableTypes.setRoot(top);
    }
    
    
    /* BACKGROUND PROCESSES */
    /**
     * 
     * @param type integer -> 1 MainType Desc, 2 if SubType
     * @param desc Description text from the combobox on Page1
     * @return int is the DB ID for that category to store
     */
    public int retrieveTypeIdByDesc(int type, String desc)
    {
        int ret_val = -1;
        
        switch(type)
        {
            case 1:
                for(int i = 0; i < frtList.size(); i++)
                {
                    if(desc.equals(frtList.get(i).getMainDesc()))
                    {
                        ret_val = frtList.get(i).getMainId();
                    }
                }
                break;
            case 2:
                for(int i = 0; i < frtList.size(); i++)
                {
                    //if(frtList.get(i).getSubDesc().equals(desc))
                    if(desc.equals(frtList.get(i).getSubDesc()))
                    {
                        ret_val = frtList.get(i).getSubId();
                    }
                }
                break;
        }
        return ret_val;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* UI PROCESSES */
        /* Submit */
    @FXML public void submitFinancialRecord()
    {
        /*VALIDÁCIÓK!!!*/
        
        Date realizedDate = Date.from(inpDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Date.from(inpDatePicker.getValue());
        String datum = inpDatePicker.getValue().toString();
        int typeId = retrieveTypeIdByDesc(1, cbType.getValue());
        int subTypeId = retrieveTypeIdByDesc(2, cbSubType.getValue());
        
        
        double amount = Integer.parseInt(inpAmount.getText());
        boolean isExpense = rbtnExpense.isSelected();
        DBHandler.insertIntoDB(new FinancialRecord(amount, isExpense, typeId, subTypeId,inpComment.getText(),datum), sm);
        clearInputPage();
    }

         /* Add Type */   
    @FXML TextField inpType;
    @FXML Button btnInpType;
    @FXML public void submitTypeRecord()
    {
        /*VALIDATION!*/
        
        DBHandler.insertTypeIntoDB(inpType.getText(), sm);
        refreshInputTypePage();
        refreshInputPageTypes();
        refreshInputPageSubType();
        inpType.clear();
        /*frissiteni minden oldalt ami most vagy majd érintett*/
    }
    
    
   
    
    
}

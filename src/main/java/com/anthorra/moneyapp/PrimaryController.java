package com.anthorra.moneyapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
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
    @FXML TreeTableView treeTableTypes;
    @FXML TreeTableColumn treeTableTypeColumn, treeTableSubTypeColumn;
    
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
    ObservableList<FinancialRecordTypes> typeList;
    
    /* INITIALIZE */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        sm = new StatusMessage(txtStatus);
        
        initInputPage();
        initInputTypePage();
    }
    public void initInputPage()
    {
        cbType.setPromptText("- Válassz -");
        cbSubType.setPromptText("- Válassz -");
        
        
        String[] types = new String[]{"valami", "még valami"};
        cbType.getItems().addAll(types);
        cbSubType.getItems().addAll(types);
        
        
        ToggleGroup rbtnToggleGroup = new ToggleGroup();
        rbtnExpense.setToggleGroup(rbtnToggleGroup);
        rbtnIncome.setToggleGroup(rbtnToggleGroup);
        rbtnExpense.setSelected(true);
                
        inpDatePicker.setValue(LocalDate.now());
    }
    
    public void initInputTypePage()
    {
        typeList = FXCollections.observableArrayList();
        
        FinancialRecordTypes frt = new FinancialRecordTypes(1, "maint1", 2, "subt1");
        typeList.add(frt);
        
        
        
    }
    
    
    
    
    /* PROCESSES */
        /* Submit */
    @FXML public void submitFinancialRecord()
    {
        /*VALIDÁCIÓK!!!*/
        
        Date realizedDate = Date.from(inpDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); //Date.from(inpDatePicker.getValue());
        String datum = inpDatePicker.getValue().toString();
        
        double amount = Integer.parseInt(inpAmount.getText());
        boolean isExpense = rbtnExpense.isSelected();
        FinancialRecord fr = new FinancialRecord(amount, isExpense, cbType.getSelectionModel().getSelectedIndex(), cbSubType.getSelectionModel().getSelectedIndex(),inpComment.getText(),datum);
        DBHandler.insertIntoDB(fr, sm);
        
    }

         /* Add Type */   
    @FXML TextField inpType;
    @FXML Button btnInpType;
    @FXML public void submitTypeRecord()
    {
        DBHandler.insertTypeIntoDB(inpType.getText(), sm);
    }
}

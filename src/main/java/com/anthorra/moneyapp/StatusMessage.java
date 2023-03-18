
package com.anthorra.moneyapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Anthorra
 */
public class StatusMessage
{
   Label txtStatus;

    public StatusMessage(Label txtStatus)
    {
        this.txtStatus = txtStatus;
    }
   
   
   
   public void updateStatus(String text)
   {
       txtStatus.setText("");
       txtStatus.setText(text);
   }
    
}

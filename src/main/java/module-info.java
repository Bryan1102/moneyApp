module com.anthorra.moneyapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.anthorra.moneyapp to javafx.fxml;
    exports com.anthorra.moneyapp;
}

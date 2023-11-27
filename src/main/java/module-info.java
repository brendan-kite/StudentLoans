module com.example.studentloans {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.example.studentloans to javafx.fxml;
    exports com.example.studentloans;
}
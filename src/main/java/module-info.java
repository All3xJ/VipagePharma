module com.vipagepharma {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.vipagepharma.farmacia;
    opens com.vipagepharma.farmacia to javafx.fxml;
}

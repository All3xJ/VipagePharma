module com.vipagepharma {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.vipagepharma to javafx.fxml;
    exports com.vipagepharma;
    exports com.vipagepharma.farmacia;
    opens com.vipagepharma.farmacia to javafx.fxml;
}

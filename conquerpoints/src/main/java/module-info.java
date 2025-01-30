module conq.points {
    requires javafx.controls;
    requires javafx.fxml;

    opens conq.points to javafx.fxml;
    exports conq.points;
}

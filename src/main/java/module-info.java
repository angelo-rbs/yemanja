module br.ufrn.imd.yeamanja {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.ufrn.imd.yeamanja to javafx.fxml;
    exports br.ufrn.imd.yeamanja;
}
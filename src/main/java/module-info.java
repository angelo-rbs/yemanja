module br.ufrn.imd.yeamanja {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;


    opens br.ufrn.imd.yeamanja to javafx.fxml;
    exports br.ufrn.imd.yeamanja;
    exports br.ufrn.imd.yeamanja.controller;
    opens br.ufrn.imd.yeamanja.controller to javafx.fxml;
}
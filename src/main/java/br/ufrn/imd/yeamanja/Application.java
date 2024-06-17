package br.ufrn.imd.yeamanja;

import br.ufrn.imd.yeamanja.model.enumerations.Tela;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private static Stage stage;
    private static Scene cenaPrincipal;
    private static Scene cenaJogo;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        //
        Parent fmxlPrincipal = FXMLLoader.load(getClass().getResource("view/tela-principal.fxml"));
        cenaPrincipal = new Scene(fmxlPrincipal, 780, 460);

        Parent fxmlJogo = FXMLLoader.load(getClass().getResource("view/jogo.fxml"));
        cenaJogo = new Scene(fxmlJogo, 1000, 825);

        stage.setTitle("Yemanja!");
        stage.setScene(cenaPrincipal);
        stage.show();
    }

    public static void changeScene(Tela tela) {

        switch (tela) {
            case PRINCIPAL -> stage.setScene(cenaPrincipal);
            case JOGO -> stage.setScene(cenaJogo);
        }

    }

    private static void main(String[] args) {
        launch();
    }
}
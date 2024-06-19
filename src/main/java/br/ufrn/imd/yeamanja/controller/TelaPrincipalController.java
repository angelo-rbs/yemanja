package br.ufrn.imd.yeamanja.controller;

import br.ufrn.imd.yeamanja.Application;
import br.ufrn.imd.yeamanja.model.enumerations.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TelaPrincipalController {

    /**
     * exibe a tela do jogo
     * @param event
     * @throws IOException
     */
    @FXML
    public void exibirJogo(ActionEvent event) throws IOException {
        Application.changeScene(Tela.JOGO);
    }
}
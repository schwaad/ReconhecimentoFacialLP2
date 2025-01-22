package com.example.lp2reconhecimentofacial;

import com.reconhecimento_facial_lp2.javafx.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfaceController {
    @FXML
    private Stage stage;
    private Scene scene;
    public Button exitButton;
    public Button adminLoginButton;
    public Button userLoginButton;
    public Label errorLabel;
    public TextField passwordField;

    @FXML
    protected void onExitButtonClick() {
        stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void switchToUserArea(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-do-usuario.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void switchToAdminLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-de-login-admin.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void switchToAdminArea(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-do-admin.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/janela-inicial.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void autenticateAdmin(ActionEvent event) throws IOException {
        String adminSenha = "123";
        String senha = passwordField.getText();
        if(senha.equals(adminSenha)){
            switchToAdminArea(event);
        }
        else{
            errorLabel.setText("Senha incorreta!");
        }
    }
}
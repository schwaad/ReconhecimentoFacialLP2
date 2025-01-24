package com.reconhecimento_facial_lp2.javafx;

import com.reconhecimento_facial_lp2.springboot.service.UserService;
import com.reconhecimento_facial_lp2.springboot.service.AdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class InterfaceController {

    @FXML
    private Stage stage;
    private Scene scene;

    // Botões e componentes FXML
    @FXML
    public Button exitButton;
    @FXML
    public Button adminLoginButton;
    @FXML
    public Button userLoginButton;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField passwordField;

    // Campos de texto para o cadastro de biometria
    @FXML
    public TextField userNameField;
    @FXML
    public TextField userFaceHashField;

    // Injeção de dependência do UserService e AdminService
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    // Método para sair da aplicação
    @FXML
    protected void onExitButtonClick() {
        stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    // Navegação para a área do usuário
    @FXML
    protected void switchToUserArea(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-do-usuario.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Navegação para a página de login do admin
    @FXML
    protected void switchToAdminLoginPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-de-login-admin.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Navegação para a área do admin
    @FXML
    protected void switchToAdminArea(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/area-do-admin.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Navegação para voltar à tela inicial
    @FXML
    protected void goBackToMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/janela-inicial.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Autenticação de admin (verificação da senha)
    @FXML
    protected void autenticateAdmin(ActionEvent event) throws IOException {
        String senha = passwordField.getText();
        if (adminService.isPasswordValid(senha)) {
            switchToAdminArea(event);
        } else {
            errorLabel.setText("Senha incorreta!");
        }
    }

    // Método para abrir a tela de cadastro de biometria
    @FXML
    protected void openCadastrarBiometria(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/cadastrar-biometria.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Método para abrir a tela de exclusão de biometria
    @FXML
    protected void openExcluirBiometria(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/excluir-biometria.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // Método para registrar um usuário (biometria) com o UserService
    @FXML
    protected void registerUser(ActionEvent event) {
        // Pega os valores do formulário
        String name = userNameField.getText();
        String faceHash = userFaceHashField.getText();

        // Chama o método de registro do UserService
        String result = userService.registerUser(name, faceHash);

        // Exibe o resultado (mensagem de sucesso ou erro)
        errorLabel.setText(result);
    }

}

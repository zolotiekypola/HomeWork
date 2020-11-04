package sample.Contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Connection.ControllerUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerSignIn {

    Connection conn;

    public ControllerSignIn() {
        conn = ControllerUtil.conDB();
    }


    @FXML
    private Label labelSignIn;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignUp;

    @FXML
    private void initialize() {
        buttonSignUp.setOnAction(event -> {

            buttonSignUp.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/FXML/sampleSignUp.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Окно регистрации");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });
    }

    @FXML
    public void buttonSignIn(ActionEvent event) throws SQLException {

        String login = txtLogin.getText();
        String password = txtPassword.getText();

        String sql = "SELECT login, password from users WHERE login = ? and password = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,login);
        statement.setString(2,password);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            if (login.equals(result.getString("login")) &&
                password.equals(result.getString("password"))) {
                labelSignIn.setTextFill(Color.GREEN);
                labelSignIn.setText("Вы успешно авторизировались");

                buttonSignIn.setOnAction(event1 -> {
                    buttonSignIn.getScene().getWindow().hide();

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/sample/FXML/sampleWorkWin.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Рабочее окно");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });
            }
        }

    }

}

package sample.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Connection.ControllerUtil;

import java.io.IOException;
import java.sql.*;

public class ControllerSignUp {

    Connection conn;

    public ControllerSignUp(){
        conn = ControllerUtil.conDB();
    }

    @FXML
    private Label labelSignUp;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignUp;

    @FXML
    private void buttonSignUp() throws SQLException {
        String buttonSignUpSQL = "INSERT INTO users (firstName,lastName,email,login,password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement signUpStatement = conn.prepareStatement(buttonSignUpSQL, Statement.RETURN_GENERATED_KEYS);
        signUpStatement.setString(1,txtFirstName.getText());
        signUpStatement.setString(2,txtLastName.getText());
        signUpStatement.setString(3,txtEmail.getText());
        signUpStatement.setString(4,txtLogin.getText());
        signUpStatement.setString(5,txtPassword.getText());
        int result = signUpStatement.executeUpdate();
        if (result == 1) {
            ResultSet resultSet = signUpStatement.getGeneratedKeys();
            while (resultSet.next()){
                labelSignUp.setTextFill(Color.GREEN);
                labelSignUp.setText("Пользователь " + txtLogin.getText() + " " + "успешно зарегистрирован");
            }
        }
    }

    @FXML
    private void initialize(){
        buttonSignIn.setOnAction(event ->{

            buttonSignIn.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/FXML/sampleSignIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }


}

package sample.Contollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Connection.ControllerUtil;
import sample.Tables.TableCourses;
import sample.Tables.TableLessons;
import sample.Tables.TableTeachers;
import sample.Tables.TableUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerWorkWin {

    Connection conn;

    public ControllerWorkWin(){
        conn = ControllerUtil.conDB();
    }

    ObservableList<TableCourses> listCourses = FXCollections.observableArrayList();
    ObservableList<TableLessons> listLessons = FXCollections.observableArrayList();
    ObservableList<TableTeachers> listTeachers = FXCollections.observableArrayList();
    ObservableList<TableUsers> listUsers = FXCollections.observableArrayList();

    @FXML
    private TableView<TableCourses> tableCourses;

    @FXML
    private TableColumn<TableCourses, String> coursesId;
    @FXML
    private TableColumn<TableCourses, String> coursesTitle;
    @FXML
    private TableColumn<TableCourses, String> coursesLength;
    @FXML
    private TableColumn<TableCourses, String> coursesDescription;

    @FXML
    private TableView<TableLessons> tableLessons;

    @FXML
    private TableColumn<TableLessons, String> lessonsId;
    @FXML
    private TableColumn<TableLessons, String> lessonsTeacher;
    @FXML
    private TableColumn<TableLessons, String> lessonsCourse;
    @FXML
    private TableColumn<TableLessons, String> lessonsRoom;
    @FXML
    private TableColumn<TableLessons, String> lessonsLessonDate;

    @FXML
    private TableView<TableTeachers> tableTeachers;

    @FXML
    private TableColumn<TableTeachers, String> teachersId;
    @FXML
    private TableColumn<TableTeachers, String> teachersName;
    @FXML
    private TableColumn<TableTeachers, String> teachersAddress;
    @FXML
    private TableColumn<TableTeachers, String> teachersPhone;

    @FXML
    private TableView<TableUsers> tableUsers;

    @FXML
    private TableColumn<TableTeachers, String> usersId;
    @FXML
    private TableColumn<TableTeachers, String> usersFirstName;
    @FXML
    private TableColumn<TableTeachers, String> usersLastName;
    @FXML
    private TableColumn<TableTeachers, String> usersEmail;
    @FXML
    private TableColumn<TableTeachers, String> usersLogin;
    @FXML
    private TableColumn<TableTeachers, String> usersPassword;


    @FXML
    public void initialize() throws SQLException {

        try {
            String coursesSQL = "SELECT * FROM courses";
            PreparedStatement coursesStatement = conn.prepareStatement(coursesSQL);
            ResultSet resultCourses = coursesStatement.executeQuery();
            while (resultCourses.next()){
                listCourses.add(new TableCourses(
                        resultCourses.getString(1),
                        resultCourses.getString(2),
                        resultCourses.getString(3),
                        resultCourses.getString(4)
                ));
            }

            coursesId.setCellValueFactory(new PropertyValueFactory<>("id"));
            coursesTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            coursesLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            coursesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            tableCourses.setItems(listCourses);

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }


        try {
            String lessonsSQL = "SELECT * FROM lessons";
            PreparedStatement lessonsStatement = conn.prepareStatement(lessonsSQL);
            ResultSet resultLessons = lessonsStatement.executeQuery();
            while (resultLessons.next()){
                listLessons.add(new TableLessons(
                        resultLessons.getString(1),
                        resultLessons.getString(2),
                        resultLessons.getString(3),
                        resultLessons.getString(4),
                        resultLessons.getString(5)
                ));
            }

            lessonsId.setCellValueFactory(new PropertyValueFactory<>("id"));
            lessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            lessonsCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
            lessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
            lessonsLessonDate.setCellValueFactory(new PropertyValueFactory<>("lessonDate"));

            tableLessons.setItems(listLessons);

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }


        try {
            String teachersSQL = "SELECT * FROM teachers";
            PreparedStatement teachersStatement = conn.prepareStatement(teachersSQL);
            ResultSet resultTeachers = teachersStatement.executeQuery();
            while (resultTeachers.next()){
                listTeachers.add(new TableTeachers(
                        resultTeachers.getString(1),
                        resultTeachers.getString(2),
                        resultTeachers.getString(3),
                        resultTeachers.getString(4)
                ));
            }

            teachersId.setCellValueFactory(new PropertyValueFactory<>("id"));
            teachersName.setCellValueFactory(new PropertyValueFactory<>("name"));
            teachersAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            teachersPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            tableTeachers.setItems(listTeachers);

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }


        try {
            String usersSQL = "SELECT * FROM users";
            PreparedStatement usersStatement = conn.prepareStatement(usersSQL);
            ResultSet resultUsers = usersStatement.executeQuery();
            while (resultUsers.next()){
                listUsers.add(new TableUsers(
                        resultUsers.getString(1),
                        resultUsers.getString(2),
                        resultUsers.getString(3),
                        resultUsers.getString(4),
                        resultUsers.getString(5),
                        resultUsers.getString(6)
                ));
            }

            usersId.setCellValueFactory(new PropertyValueFactory<>("id"));
            usersFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            usersLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            usersEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            usersLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            usersPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

            tableUsers.setItems(listUsers);

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}

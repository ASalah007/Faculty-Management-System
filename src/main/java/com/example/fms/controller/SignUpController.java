package com.example.fms.controller;

import com.example.fms.App;
import com.example.fms.dao.StudentDao;
import com.example.fms.dao.StudentDaoJdbc;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SignUpController {
    
    @FXML
    TextField name;

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    TextField address;

    @FXML
    TextField day;

    @FXML
    TextField month;

    @FXML
    TextField year;

    @FXML
    protected void onSignUpButtonClick(){
       StudentDao dao = new StudentDaoJdbc();
       String birthdate= year.getText() + "-" + month.getText() + "-" + day.getText();
       boolean b = dao.insertNewStudent(email.getText(), password.getText(), name.getText(), address.getText(), birthdate);
       if(!b){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("Wrong data please try again");
           alert.show();
       }
       else{
           App.startLogInView();
       }
    }

    @FXML
    protected void onCloseButtonClick(){
        App.startLogInView();
    }

}

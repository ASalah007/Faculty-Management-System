package com.example.fms.view;

import com.example.fms.dao.InstructorDao;
import com.example.fms.dao.InstructorDaoJdbc;
import com.example.fms.dao.StudentDao;
import com.example.fms.dao.StudentDaoJdbc;
import com.example.fms.dao.UserDao;
import com.example.fms.dao.UserDaoJdbc;
import com.example.fms.model.Instructor;
import com.example.fms.model.Student;
import com.example.fms.model.User;
import com.example.fms.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    protected void onSignInButtonClick() {
        String userEmail = email.getText();
        String userPassword = password.getText();
        UserDao dao = new UserDaoJdbc();
        User user= dao.findUserByemail(userEmail);

        if(user == null || !user.getPassword().equals(userPassword)){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Wrong email or password");
            alert.show();
            return;
        }

        StudentDao studentDao = new StudentDaoJdbc(); 
        Student student = studentDao.findStudentById(user.getId());
        if(student != null){
            App.mainUser = student;
            App.startStudentView();
            return;
        }
        InstructorDao InstructorDao = new InstructorDaoJdbc();
        Instructor instructor = InstructorDao.findInstructorById(user.getId());
        if(instructor != null){
            App.mainUser = instructor;
            App.startInstructorView();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("your email is not assigned to a student nor an instructor");
        alert.show();
    }

    @FXML
    protected void onSignUpButtonClick(){
        App.startSignUpView();
    }

}

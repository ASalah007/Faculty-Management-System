package com.example.fms.controller;

import com.example.fms.App;
import com.example.fms.dao.StudentDaoJdbc;

import java.util.List;

import com.example.fms.dao.CourseOfferingDao;
import com.example.fms.dao.CourseOfferingDaoJdbc;
import com.example.fms.dao.StudentDao;
import com.example.fms.model.CourseOffering;
import com.example.fms.model.Student;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InstructorController {
    @FXML
    TextField studentName;

    @FXML
    TextField studentGPA;

    @FXML
    VBox studentCourses;


    @FXML
    TextField studentID;

    @FXML
    protected void onSearchButtonClick(){
        studentCourses.getChildren().clear();
        StudentDao dao = new StudentDaoJdbc();
        Student st = dao.findStudentByFacultyId(studentID.getText());
        if(st == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("please check the student id");
            alert.show();
            return;
        }
        studentName.setText(st.getName());
        studentGPA.setText(""+st.getGPA());
        
        CourseOfferingDao coDao = new CourseOfferingDaoJdbc();
        List<CourseOffering> co = coDao.findPendingStudentOfferings(st);

        for(var c : co){
            HBox h =new HBox();
            h.getChildren().add(new Label(c.getName()));
            h.getChildren().add(new Label(c.getCourseCode()));
            h.getChildren().add(new Label(c.getSemester().toString()));
            h.getChildren().add(new Label(c.getYear()+""));

            Button btn = new Button("Approve");
            Button btn2 = new Button("Reject");
            btn.setOnAction(e->{
                Button bt = (Button)e.getSource();
                HBox hb = (HBox)bt.getParent();
                String code = ((Label)hb.getChildren().get(1)).getText();
                String semester = ((Label)hb.getChildren().get(2)).getText();
                String year = ((Label)hb.getChildren().get(3)).getText();

                CourseOffering offering = coDao.findCourseOffering(code, year, semester);
                dao.approveEnrollment(st, offering);

                studentCourses.getChildren().remove(hb);
            });
            btn2.setOnAction(e->{
                Button bt = (Button)e.getSource();
                HBox hb = (HBox)bt.getParent();
                String code = ((Label)hb.getChildren().get(1)).getText();
                String semester = ((Label)hb.getChildren().get(2)).getText();
                String year = ((Label)hb.getChildren().get(3)).getText();

                CourseOffering offering = coDao.findCourseOffering(code, year, semester);
                dao.unenrollStudent(st, offering);

                studentCourses.getChildren().remove(hb);
            });
            h.getChildren().addAll(btn, btn2);
            h.setSpacing(20);
            studentCourses.getChildren().add(h);
        }
    }

    @FXML
    protected void onLogoutButtonClick(){
        App.startLogInView();
    }

    @FXML
    protected void onCourseDescriptionsButtonClick(){
        App.startCourseDescriptionView();
    }
}



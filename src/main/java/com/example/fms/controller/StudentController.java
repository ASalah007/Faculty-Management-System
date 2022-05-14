package com.example.fms.controller;

import com.example.fms.App;
import com.example.fms.dao.*;
import com.example.fms.model.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentController {

    private static Student currentStudent = (Student)App.mainUser;

    @FXML
    VBox registeredCourses;

    @FXML
    VBox allCourses;

    @FXML 
    public void initialize(){
        currentStudent = (Student)App.mainUser;
        CourseOfferingDao dao = new CourseOfferingDaoJdbc();
        ArrayList<CourseOffering> studentApprovedCourses = dao.findApprovedStudentOfferings(currentStudent);
        ArrayList<CourseOffering> studentPendingCourses = dao.findPendingStudentOfferings(currentStudent);
        //ArrayList<CourseOffering> allCourseOfferings = dao.findAllCourseOffering();
        ArrayList<CourseOffering> allCourseOfferings = dao.findAvailableStudentOfferings(currentStudent);

        for(CourseOffering co : studentApprovedCourses){
            Label courseName = new Label(co.getName());
            Label courseCode = new Label(co.getCourseCode());
            Label year = new Label(String.valueOf(co.getYear()));
            Label semester = new Label(co.getSemester().toString());
            Label stat = new Label("Registered");
            Label grade = new Label(dao.findStudentGrade(currentStudent, co));

            HBox h = new HBox();
            h.setSpacing(10);
            h.getChildren().addAll(courseName, courseCode, year, semester, stat, grade);
            h.setAlignment(Pos.CENTER);
            registeredCourses.getChildren().add(h);
        }

       for(CourseOffering co : studentPendingCourses){
           Label courseName = new Label(co.getName());
           Label courseCode = new Label(co.getCourseCode());
           Label year = new Label(String.valueOf(co.getYear()));
           Label semester = new Label(co.getSemester().toString());

           Label stat = new Label("Pending");
           Button bt = new Button("Cancel");

           bt.setOnAction(e->{
               Button cancelButton = (Button) e.getSource();
               var hb = cancelButton.getParent().getChildrenUnmodifiable();

               String offeringCourseCode = ((Label)hb.get(1)).getText();
               String offeringYear = ((Label)hb.get(2)).getText();
               String offeringSemester =((Label)hb.get(3)).getText();
               
               CourseOfferingDao coDao = new CourseOfferingDaoJdbc();
               StudentDao stDao = new StudentDaoJdbc();

               CourseOffering c = coDao.findCourseOffering(offeringCourseCode, offeringYear, offeringSemester);
               stDao.unenrollStudent(currentStudent, c);

               registeredCourses.getChildren().clear();
               allCourses.getChildren().clear();
               initialize();
           });

           HBox h = new HBox();
           h.setSpacing(10);
           h.getChildren().addAll(courseName, courseCode,year, semester, stat, bt);
           h.setAlignment(Pos.CENTER);
           registeredCourses.getChildren().add(h);
       }

       for(CourseOffering co : allCourseOfferings){
           Label courseName = new Label(co.getName());
           Label courseCode = new Label(co.getCourseCode());
           Label year = new Label(String.valueOf(co.getYear()));
           Label semester = new Label(co.getSemester().toString());

           Button bt = new Button("Register");
           Button bt2 = new Button("View Description");

           bt.setOnAction(e->{
               Button registerButton = (Button) e.getSource();
               var hb = registerButton.getParent().getChildrenUnmodifiable();

               String offeringCourseCode = ((Label)hb.get(1)).getText();
               String offeringYear = ((Label)hb.get(2)).getText();
               String offeringSemester =((Label)hb.get(3)).getText();
               
               CourseOfferingDao coDao = new CourseOfferingDaoJdbc();
               StudentDao stDao = new StudentDaoJdbc();

               CourseOffering c = coDao.findCourseOffering(offeringCourseCode, offeringYear, offeringSemester);
               stDao.enrollStudent(currentStudent, c);

               registeredCourses.getChildren().clear();
               allCourses.getChildren().clear();
               initialize();

           });
           bt2.setOnAction(e->{
               //create a new window to show the description
               Stage stage = new Stage();
               stage.setTitle(courseCode.getText()+" "+courseName.getText());
               stage.setMinWidth(300);
               stage.setMinHeight(300);
               stage.setMaxWidth(300);
               stage.setMaxHeight(300);
               stage.setResizable(false);
               //create a new vbox to show the description
               VBox vb = new VBox();
               //create a new label to show the description
               Label l = new Label();
               //set the description
               l.setText(co.getCourseDescription());
               l.setWrapText(true);
               //adding label to vbox
               vb.getChildren().add(l);
               //adding vbox to scene
               Scene scene = new Scene(vb, 300, 300);
               //setting scene to stage
               stage.setScene(scene);
               stage.show();
           });
           HBox h = new HBox();
           h.setSpacing(10);
           h.getChildren().addAll(courseName, courseCode,year, semester, bt,bt2);
           h.setAlignment(Pos.CENTER);
           allCourses.getChildren().add(h);
       }

    }

    @FXML
    protected void onLogoutButtonClick()
    {
        App.startLogInView();
    }
}

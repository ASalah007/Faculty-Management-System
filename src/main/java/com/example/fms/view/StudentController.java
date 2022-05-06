package com.example.fms.view;

import com.example.fms.App;
import com.example.fms.dao.*;
import com.example.fms.model.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.*;

public class StudentController {

    private static Student currentStudent = (Student)App.mainUser;

    @FXML
    VBox registeredCourses;

    @FXML
    VBox allCourses;

    @FXML 
    public void initialize(){
    
        CourseOfferingDao dao = new CourseOfferingDaoJdbc();
        ArrayList<CourseOffering> studentApprovedCourses = dao.findApprovedStudentOfferings(currentStudent);
        ArrayList<CourseOffering> studentPendingCourses = dao.findPendingStudentOfferings(currentStudent);
        ArrayList<CourseOffering> allCourseOfferings = dao.findAllCourseOffering();

        for(CourseOffering co : studentApprovedCourses){
            Label courseName = new Label(co.getName());
            Label courseCode = new Label(co.getCourse_code());
            Label year = new Label(String.valueOf(co.getYear()));
            Label semester = new Label(co.getSemester().toString());
            Label stat = new Label("Registered");

            HBox h = new HBox();
            h.setSpacing(10);
            h.getChildren().addAll(courseName, courseCode, year, semester, stat);
            h.setAlignment(Pos.CENTER);
            registeredCourses.getChildren().add(h);
        }

       for(CourseOffering co : studentPendingCourses){
           Label courseName = new Label(co.getName());
           Label courseCode = new Label(co.getCourse_code());
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

               registeredCourses.getChildren().remove(cancelButton.getParent());
           });

           HBox h = new HBox();
           h.setSpacing(10);
           h.getChildren().addAll(courseName, courseCode,year, semester, stat, bt);
           h.setAlignment(Pos.CENTER);
           registeredCourses.getChildren().add(h);
       }

       for(CourseOffering co : allCourseOfferings){
           Label courseName = new Label(co.getName());
           Label courseCode = new Label(co.getCourse_code());
           Label year = new Label(String.valueOf(co.getYear()));
           Label semester = new Label(co.getSemester().toString());

           Button bt = new Button("Register");

           bt.setOnAction(e->{
               Button registerButton = (Button) e.getSource();
               var hb = registerButton.getParent().getChildrenUnmodifiable();

               String offeringCourseCode = ((Label)hb.get(1)).getText();
               String offeringYear = ((Label)hb.get(2)).getText();
               String offeringSemester =((Label)hb.get(3)).getText();
               
               CourseOfferingDao coDao = new CourseOfferingDaoJdbc();
               StudentDao stDao = new StudentDaoJdbc();

               CourseOffering c = coDao.findCourseOffering(offeringCourseCode, offeringYear, offeringSemester);
               System.out.println(offeringCourseCode + " " + offeringYear + " " + offeringSemester);
               stDao.enrollStudent(currentStudent, c);

               registeredCourses.getChildren().clear();
               allCourses.getChildren().clear();
               initialize();

           });
           HBox h = new HBox();
           h.setSpacing(10);
           h.getChildren().addAll(courseName, courseCode,year, semester, bt);
           h.setAlignment(Pos.CENTER);
           allCourses.getChildren().add(h);
       }

    }
}

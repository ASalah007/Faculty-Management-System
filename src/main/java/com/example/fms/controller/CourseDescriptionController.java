package com.example.fms.controller;

import com.example.fms.App;
import com.example.fms.dao.*;
import com.example.fms.model.Course;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CourseDescriptionController {

    @FXML
    VBox allCourses;

    @FXML
    public void initialize(){

        CourseDao dao = new CourseDaoJdbc();
        ArrayList<Course> courses = dao.getAllCourses();

        for(Course c : courses) {
            Label courseCode = new Label(c.getCourseCode());
            Label courseName = new Label(c.getName());
            Label creditHours = new Label("credit hours: "+c.getCredit_hours());

            Button showDescription = new Button("Show Description");

            HBox h = new HBox();
            h.setSpacing(10);
            h.getChildren().addAll(courseCode,courseName,creditHours, showDescription);
            h.setAlignment(Pos.CENTER);
            allCourses.getChildren().add(h);

            showDescription.setOnAction(e -> {
                Stage stage = new Stage();
                stage.setTitle(courseCode.getText()+" "+courseName.getText());
                stage.setResizable(false);
                //create a new vbox to show the description
                VBox vb = new VBox();
                //create a new editable text area to show the description
                TextArea ta = new TextArea();
                ta.setText(c.getCourseDescription());
                ta.setPrefSize(800,500);
                ta.setWrapText(true);
                //add save button to keep changes and close button to close the window
                Button save = new Button("Save");
                save.setOnAction(e1 -> {
                    c.setCourseDescription(ta.getText());
                    dao.updateDescription(c, ta.getText());
                    stage.close();
                });
                Button close = new Button("Close");
                close.setOnAction(e1 -> stage.close());
                //add the buttons to hbox
                HBox hb = new HBox();
                hb.setSpacing(10);
                hb.getChildren().addAll(save, close);
                hb.setAlignment(Pos.CENTER);
                //add the hbox to vbox
                vb.getChildren().addAll(ta, hb);
                vb.setAlignment(Pos.CENTER);
                //adding vbox to scene
                Scene scene = new Scene(vb, 800, 600);
                //setting scene to stage
                stage.setScene(scene);
                stage.show();
            });
        }
    }

    @FXML
    protected void onBackButtonClick()
    {
        App.startInstructorView();
    }

}

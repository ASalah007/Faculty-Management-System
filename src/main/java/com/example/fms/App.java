package com.example.fms;

import com.example.fms.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage mainStage;
    public static User mainUser;

    private static void startView(String name){
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(name));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load(), 800, 600);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        mainStage.setScene(scene);
    }
    public static void startSignUpView(){
        startView("view/signup-view.fxml");
    }

    public static void startInstructorView(){
        startView("view/instructor-view.fxml");
    }

    public static void startStudentView(){
        startView("view/student-view.fxml");
    }

    public static void startLogInView(){
        startView("view/login-view.fxml");
    }

    public static void startCourseDescriptionView(){
        startView("view/course-description-view.fxml");
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        startLogInView();
        stage.setTitle("FMS");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

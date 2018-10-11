
package main;
import models.ModelAgenda;
import views.ViewAgenda;
import controllers.ControllerAgenda;


public class Main {

 
    public static void main(String[] args) {
        ModelAgenda modelAgenda = new ModelAgenda();
        ViewAgenda viewAgenda = new ViewAgenda();
        ControllerAgenda controllerAgenda = new ControllerAgenda(modelAgenda, viewAgenda);
    }
    
}

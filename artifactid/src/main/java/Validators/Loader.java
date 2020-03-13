package Validators;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Loader
{
    static Stage pr=new Stage();
    static Parent root;

    public static class LoadLogin
    {
        public LoadLogin() throws IOException
        {
            root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            pr.setResizable(false);
            pr.setTitle("Login");
            pr.setScene(new Scene(root));
            pr.show();
        }
    }

    public static class LoadEmployee
    {
        public LoadEmployee() throws IOException
        {
            root = FXMLLoader.load(getClass().getResource("/fxml/Employee.fxml"));
            pr.setTitle("Main window for employee");
            pr.setScene(new Scene(root));
            pr.show();
        }
    }

    public static class LoadAdmin
    {
        public LoadAdmin() throws IOException
        {
            root = FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
            pr.setTitle("Main window for admin");
            pr.setScene(new Scene(root));
            pr.show();
        }
    }

    public static class LoadCreate
    {
        public LoadCreate() throws IOException
        {
            Stage cr=new Stage();
            Parent r = FXMLLoader.load(getClass().getResource("/fxml/Create.fxml"));
            cr.setTitle("Window for creating employee");
            cr.setScene(new Scene(r));
            cr.show();
        }
    }
}

package Controllers;
import Tables.Admin;
import Tables.Employee;
import Tables.StartDate;
import Validators.Database;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import Validators.Loader.LoadLogin;
import Validators.Loader.LoadAdmin;
import Validators.Loader.LoadEmployee;

public class LoginController extends Application
{
    @FXML private TextField username, password;
    @FXML private Label label;
    StartDate s=new StartDate();

    @Override public void start(Stage primstage) throws Exception
    { LoadLogin ll=new LoadLogin(); }

    public void action() throws IOException
    {
        Database.DB db= new Database.DB();
        List l = db.session.createQuery("from Employee").list();
        Admin a = db.session.get(Admin.class, 1);

        for(int i=0; i<l.size(); i++)
        {
            if((((Employee)l.get(i)).getUsername()).equals(username.getText())
            && (((Employee)l.get(i)).getPassword()).equals(password.getText()))
            {
                db.session.beginTransaction();
                s.setDate(Timestamp.valueOf(LocalDateTime.now()));
                s.setE_id(((Employee)l.get(i)).getId());
                db.session.save(s);
                db.session.getTransaction().commit();
                db.session.close();
                db.factory.close();

                LoadEmployee le=new LoadEmployee();
            }

            else if(a.getUsername().equals(username.getText()) && a.getPassword().equals(password.getText()))
            { LoadAdmin ll=new LoadAdmin(); }

            else { label.setText("Invalid input!"); }
        }
    }
}
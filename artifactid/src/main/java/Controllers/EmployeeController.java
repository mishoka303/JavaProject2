package Controllers;
import Tables.EndDate;
import Tables.StartDate;
import Validators.Database;
import Validators.Loader.LoadLogin;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class EmployeeController
{
    public void action() throws IOException
    {
        EndDate e=new EndDate();
        Database.DB db=new Database.DB();
        List l = db.session.createQuery("from StartDate").list();

        db.session.beginTransaction();
        e.setDate(new Timestamp(new Date().getTime()));
        e.setE_id(((StartDate)l.get(l.size()-1)).getE_id());
        e.setS_id(((StartDate)l.get(l.size()-1)).getId());
        db.session.save(e);
        db.session.getTransaction().commit();
        db.session.close();

        LoadLogin ll=new LoadLogin();
    }
}
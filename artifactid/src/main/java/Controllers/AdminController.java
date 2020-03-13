package Controllers;
import Inqueries.AdminShow;
import Tables.Employee;
import Tables.EndDate;
import Tables.StartDate;
import Validators.Database;
import Validators.Validators;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.query.Query;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import Validators.Loader.LoadLogin;
import Validators.Loader.LoadCreate;

public class AdminController
{
    @FXML DatePicker date, date2;
    @FXML Button button;
    @FXML TextField search;
    @FXML TableView<AdminShow> tableview;
    @FXML private TableColumn<AdminShow, String> first;
    @FXML private TableColumn<AdminShow, String> middle;
    @FXML private TableColumn<AdminShow, String> last;
    @FXML private TableColumn<AdminShow, String> time;

    private SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

    public void action() throws ParseException
    {
        Validators.DV dv= new Validators.DV(date, date2);

        if(!Validators.error)
        {
            tableview.getItems().clear();
            Database.DB db= new Database.DB();
            List<Employee>l=db.session.createQuery("FROM Employee").list();

            Query q = db.session.createQuery("FROM StartDate where date between :d and :d2");
            q.setParameter("d", java.sql.Date.valueOf(date.getValue()));
            q.setParameter("d2", java.sql.Date.valueOf(date2.getValue()));
            List<StartDate> l11 = q.list();

            Query q1 = db.session.createQuery("FROM EndDate where date between :d and :d2");
            q1.setParameter("d", java.sql.Date.valueOf(date.getValue()));
            q1.setParameter("d2", java.sql.Date.valueOf(date2.getValue()));
            List<EndDate> l22 = q1.list();
            ObservableList<AdminShow>d=FXCollections.observableArrayList();

            first.setCellValueFactory(new PropertyValueFactory<>("first"));
            middle.setCellValueFactory(new PropertyValueFactory<>("middle"));
            last.setCellValueFactory(new PropertyValueFactory<>("last"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));

            for (Employee o:l)
            {
                AdminShow a=new AdminShow();
                long td=0;

                for(StartDate startDate:l11)
                {
                    for(EndDate endDate:l22)
                    {
                        if(startDate.getId()==endDate.getS_id()
                        && startDate.getE_id()==(o.getId())
                        && endDate.getE_id()==(o.getId()))
                        {
                            Date d1=f.parse(f.format(endDate.getDate().getTime()));
                            Date d2=f.parse(f.format(startDate.getDate().getTime()));

                            td+=d1.getTime()-d2.getTime();
                            a.setTime(""+td/(60*60*1000)%24+":"+td/(60*1000)%60+":"+td/1000%60);
                        }
                    }
                }

                if(td!=0)
                {
                    a.setFirst(o.getFirst());
                    a.setMiddle(o.getMiddle());
                    a.setLast(o.getLast());
                    d.add(a);
                }
            }

            tableview.getItems().addAll(d);
            button.disableProperty().bind(Bindings.isNotEmpty(search.textProperty()));

            FilteredList<AdminShow>fd=new FilteredList<>(d, e -> true);
            search.setOnKeyReleased(e -> { search.textProperty().addListener((observableValue, oldValue, newValue) ->
            fd.setPredicate((Predicate<? super AdminShow>) as ->
            {
                if(newValue == null || newValue.isEmpty()) { return true; }
                String lower = newValue.toLowerCase();
                if(as.getFirst().toLowerCase().contains(lower)) { return true; }
                else if(as.getMiddle().toLowerCase().contains(lower)) { return true; }
                else return as.getLast().toLowerCase().contains(lower);
            }));

                SortedList<AdminShow>sd=new SortedList<>(fd);
                sd.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sd);
            });

            db.session.close();
            db.factory.close();
        }
    }

    public void action2() throws IOException { LoadLogin ll=new LoadLogin(); }
    public void action3() throws IOException { LoadCreate lc=new LoadCreate(); }
}
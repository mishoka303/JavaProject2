package Tables;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name="startdate")
public class StartDate
{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique=true) @Id private int id;
    @Column(name = "e_id") private int e_id;
    @Column(name = "date") private Timestamp date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getE_id() { return e_id; }
    public void setE_id(int e_id) { this.e_id = e_id; }
    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }
}
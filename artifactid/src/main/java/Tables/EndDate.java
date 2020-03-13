package Tables;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name="enddate")
public class EndDate
{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique=true) @Id private int id;
    @Column(name = "e_id") private int e_id;
    @Column(name = "s_id") private int s_id;
    @Column(name = "date") private Timestamp date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getE_id() { return e_id; }
    public void setE_id(int s_id) { this.e_id = s_id; }
    public int getS_id() { return s_id; }
    public void setS_id(int s_id) { this.s_id = s_id; }
    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }
}
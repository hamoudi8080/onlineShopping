package via.sep3.group2.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table (name="orderss")


public class OrdersDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private java.sql.Date date;
    private String status;
    @ManyToOne (fetch = FetchType.LAZY)
  //  @JoinColumn(name = "userid", referencedColumnName="id")

    @JoinColumn(
            name="username",
            // nullable=false,
            foreignKey = @ForeignKey(
                    name="userid",
                    foreignKeyDefinition = "FOREIGN KEY (username) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE "
            )
    )
    private UserDTO user;
  //  @ManyToOne (mappedBy="orders",  fetch = FetchType.LAZY)
  //  private UserDTO userDTO;

 /* @OneToMany(mappedBy = "ordersDTO")
  Set<OrdersDTO> ratings;*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return user;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.user = userDTO;
    }


}

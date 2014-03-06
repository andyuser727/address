package exercise.domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class PersistentObject  implements Serializable {

    protected Long id;

    public PersistentObject(){
    }

    @Id
    @GeneratedValue
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

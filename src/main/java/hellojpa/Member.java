package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @Table(name = "USER") -> 데이터베이스에 Member가 아니라 User 테이블이 있다면
public class Member {

    @Id
    private Long id;
    // @Column(name = "username")
    private String name;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

package hello.hellospring.domain;

import javax.persistence.*;

// JPA 가 관리하는 Entity
@Entity
public class Member {

    // @ID = PK
    // @GeneratedValue(strategy = GenerationType.IDENTITY) = DB 가 알아서 생성해주는 SEQUENCE
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // @Column = JPA 에게 해당 변수는 DB 테이블의 name 값이라고 명시하는 역할
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }


}

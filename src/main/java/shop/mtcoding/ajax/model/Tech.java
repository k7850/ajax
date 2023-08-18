package shop.mtcoding.ajax.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Table(name="tech_tb")
@Entity
public class Tech {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name; // Spring, Java, Javascript, React, HTML

    // @JsonIgnore // 실무에선 @JsonIgnore 안쓰고 dto로 깊은복사해서
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}

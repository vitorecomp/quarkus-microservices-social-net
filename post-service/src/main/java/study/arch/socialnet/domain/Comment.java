package study.arch.socialnet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data @Getter @Setter @ToString @EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private Long userId;

    @ManyToOne
    private Post post;
}

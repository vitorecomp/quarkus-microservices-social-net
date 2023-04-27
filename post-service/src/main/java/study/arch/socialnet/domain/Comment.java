package study.arch.socialnet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


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

    private String content;
    private Long userId;

    @ManyToOne
    private Post post;
}

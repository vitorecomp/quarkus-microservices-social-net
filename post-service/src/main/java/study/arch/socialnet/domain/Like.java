package study.arch.socialnet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "post_likes")
@Data @Getter @Setter @ToString @EqualsAndHashCode
public class Like {

    @Id
    @GeneratedValue
    private Long id;


    private Long userId;
    private Long postId;
    
}
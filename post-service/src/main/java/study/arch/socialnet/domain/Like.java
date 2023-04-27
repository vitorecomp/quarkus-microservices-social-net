package study.arch.socialnet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

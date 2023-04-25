package study.arch.socialnet.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Post {
        
    @Id
    @GeneratedValue
    private Long id;
    
    private Long userId;
    private String text;

    private Long likes = 0L;

    @UpdateTimestamp
	private LocalDateTime updatedAt;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
    
}

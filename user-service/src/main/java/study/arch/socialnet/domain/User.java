package study.arch.socialnet.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue
 
    private Long id;
    
    private String name;

    @UpdateTimestamp
	private LocalDateTime updatedAt;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

    public User(Long id){
        this.id = id;
    }
}
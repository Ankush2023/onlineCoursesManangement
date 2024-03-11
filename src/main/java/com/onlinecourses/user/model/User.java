package com.onlinecourses.user.model;

import com.onlinecourses.user.utils.gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "oc_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="user_id")
    private Long userId;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="mobile_no")
    private String mobileNo;

    @Column(name="gender")
    private gender gender;

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="is_active")
    private String isActive;
}

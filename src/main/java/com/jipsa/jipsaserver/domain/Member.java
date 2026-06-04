package com.jipsa.jipsaserver.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "age")
    private Integer age;

    @Column(name = "monthly_step")
    private Integer monthlyStep = 1;

    @Column(name = "jeonse_step")
    private Integer jeonseStep = 1;

    @Column(name = "sale_step")
    private Integer saleStep = 1;

    @Column(name = "asset_profile", columnDefinition = "TEXT")
    private String assetProfile;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updateAge(Integer age) {
        this.age = age;
    }

    public void updateGuideStep(String type, int step) {
        switch (type.toUpperCase()) {
            case "MONTHLY" -> this.monthlyStep = step;
            case "JEONSE"  -> this.jeonseStep = step;
            case "SALE"    -> this.saleStep = step;
        }
    }

    public void updateAssetProfile(String assetProfileJson) {
        this.assetProfile = assetProfileJson;
    }
}

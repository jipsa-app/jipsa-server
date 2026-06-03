package com.jipsa.jipsaserver.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "checklist_records",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "item_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChecklistRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "item_id", nullable = false)
    private String itemId;
}

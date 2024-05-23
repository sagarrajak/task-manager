package com.taskmanager.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "updated_item")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedItemsEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "updated_field_name", nullable = false)
    String updateValueName;

    @Column(name = "old_value", nullable = false, length = 1000)
    String oldValue;

    @Column(name = "new_value", nullable = false, length = 1000)
    String newValue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "history_id")
    HistoryEntity history;
}

package site.potatolog.potatolog.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntityWithIsDeleted extends BaseEntity {

    @Column(nullable = false)
    private boolean isDeleted;
}

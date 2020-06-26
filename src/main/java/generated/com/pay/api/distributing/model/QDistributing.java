package com.pay.api.distributing.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDistributing is a Querydsl query type for Distributing
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDistributing extends EntityPathBase<Distributing> {

    private static final long serialVersionUID = -957524692L;

    public static final QDistributing distributing = new QDistributing("distributing");

    public final com.pay.api.base.QBaseEntity _super = new com.pay.api.base.QBaseEntity(this);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> giverId = createNumber("giverId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> roomUserId = createNumber("roomUserId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> takenAt = createDateTime("takenAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> takerId = createNumber("takerId", Long.class);

    public final StringPath token = createString("token");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDistributing(String variable) {
        super(Distributing.class, forVariable(variable));
    }

    public QDistributing(Path<? extends Distributing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDistributing(PathMetadata metadata) {
        super(Distributing.class, metadata);
    }

}


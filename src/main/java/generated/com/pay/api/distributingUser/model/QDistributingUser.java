package com.pay.api.distributingUser.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDistributingUser is a Querydsl query type for DistributingUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDistributingUser extends EntityPathBase<DistributingUser> {

    private static final long serialVersionUID = -487603892L;

    public static final QDistributingUser distributingUser = new QDistributingUser("distributingUser");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> distributingId = createNumber("distributingId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> takenAt = createDateTime("takenAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDistributingUser(String variable) {
        super(DistributingUser.class, forVariable(variable));
    }

    public QDistributingUser(Path<? extends DistributingUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDistributingUser(PathMetadata metadata) {
        super(DistributingUser.class, metadata);
    }

}


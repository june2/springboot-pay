package com.pay.api.roomUser.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoomUser is a Querydsl query type for RoomUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoomUser extends EntityPathBase<RoomUser> {

    private static final long serialVersionUID = 135455660L;

    public static final QRoomUser roomUser = new QRoomUser("roomUser");

    public final com.pay.api.base.QBaseEntity _super = new com.pay.api.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRoomUser(String variable) {
        super(RoomUser.class, forVariable(variable));
    }

    public QRoomUser(Path<? extends RoomUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoomUser(PathMetadata metadata) {
        super(RoomUser.class, metadata);
    }

}


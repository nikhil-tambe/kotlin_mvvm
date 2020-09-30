package com.nikhil.suven.app.db.messages

import com.nikhil.suven.app.domain_model.Message
import com.nikhil.suven.utils.EntityMapper
import javax.inject.Inject

class CacheMessageMapper
@Inject constructor() : EntityMapper<MessageEntity, Message> {
    override fun mapFromEntity(entity: MessageEntity): Message {
        return Message(
            message = entity.message,
            isSender = entity.isSender,
            timestamp = entity.timestamp
        )
    }

    override fun mapToEntity(model: Message): MessageEntity {
        return MessageEntity(
            message = model.message,
            isSender = model.isSender,
            timestamp = model.timestamp
        )
    }


}
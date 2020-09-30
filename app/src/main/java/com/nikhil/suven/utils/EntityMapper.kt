package com.nikhil.suven.utils

/**
 * @param Entity can be either from Local cache source or from Network source
 * @param DomainModel is the UI representation of the Entity.
 * @see com.nikhil.suven.app.domain_model for all DomainModels
 * */
interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(model: DomainModel): Entity

}
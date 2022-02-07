package com.julio.coppel.utils

/**
 * Mapper base para convertir las respuestas al dominio.
 *
 * @param Entity Clase de la entidad que se desea convertir.
 * @param Domain Clase de dominio destino.
 */
interface Mapper<Entity, Domain> {

    /**
     * Declara el comportamiento para convertir una entidad al dominio.
     *
     * @param entity Entidad.
     * @return Entidad de dominio.
     */
    fun toDomain(entity: Entity): Domain
}
package com.atef.clubhouse.data.base.mapper

interface ListRemoteModelMapper<M, E> {

    fun mapFromModel(model: M): E

    fun mapFromModelList(dataModels: List<M>): List<E> {
        return dataModels.mapTo(mutableListOf(), ::mapFromModel)
    }
}
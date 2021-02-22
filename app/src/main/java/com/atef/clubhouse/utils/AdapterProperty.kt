package com.atef.clubhouse.utils

import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AdapterProperty<T>(initialValue: T) : ReadWriteProperty<RecyclerView.Adapter<*>, T> {

    private var backfield: T = initialValue

    override fun getValue(thisRef: RecyclerView.Adapter<*>, property: KProperty<*>): T {
        return backfield
    }

    override fun setValue(thisRef: RecyclerView.Adapter<*>, property: KProperty<*>, value: T) {
        backfield = value
        thisRef.notifyDataSetChanged()
    }
}

typealias adapterProperty<T> = AdapterProperty<T>

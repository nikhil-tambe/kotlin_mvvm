package com.nikhil.suven.app.db.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity (

    @PrimaryKey
    @ColumnInfo(name = "date_created")
    var dateCreated: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "for_whom")
    var forWhom: Int,

    @ColumnInfo(name = "is_variable")
    var isVariable: Boolean,

    @ColumnInfo(name = "date_of_purchase")
    var dateOfPurchase: Long,

    @ColumnInfo(name = "number_of_unit")
    var numberOfUnit: Int,

    @ColumnInfo(name = "purchase_price")
    var purchasePrice: Float

)
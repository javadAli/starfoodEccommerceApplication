package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity("groups_table")
data class GroupsTable(
    var created_date: String,
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var mainGroupPriority: String,
    var percentTakhf: String,
    var secondBranchId: String,
    var selfGroupId: String,
    var subGroupPriority: String,
    var thirdBranchId: String,
    var title: String,
var subProductGroupId: String
)
package com.example.starfood.datamodel.offlinedata

data class MainGroupBranche(
    val created_date: String,
    val id: String,
    val listGroups: List<Groups>,
    val mainGroupPriority: String,
    val percentTakhf: String,
    val secondBranchId: String,
    val selfGroupId: String,
    val subGroupPriority: Any,
    val thirdBranchId: String,
    val title: String
)
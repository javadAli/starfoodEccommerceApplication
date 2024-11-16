package com.example.starfood.datamodel.offlinedata

data class Groups(
    val created_date: String,
    val id: String,
    val listKala: List<Kala>,
    val mainGroupPriority: Any,
    val percentTakhf: String,
    val secondBranchId: Any,
    val selfGroupId: String,
    val subGroupPriority: String,
    val thirdBranchId: Any,
    val title: String
)
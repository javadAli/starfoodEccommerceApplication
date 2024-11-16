package com.example.starfood.datamodel.applydiscount

data class CodeState(
    val AssignDate: String,
    val CodeAssingSn: String,
    val CodeId: String,
    val CustomerId: String,
    val UsedDate: String,
    val UsedMoney: String,
    val isSeen: String,
    val isUsed: String
)
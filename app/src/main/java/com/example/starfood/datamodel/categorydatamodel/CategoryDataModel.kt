package com.example.starfood.datamodel.categorydatamodel

import androidx.room.Entity

@Entity(tableName = "product_tbl")
class CategoryDataModel : ArrayList<CategoryDataModelItem>()
package com.example.starfood.dao

import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec
@RenameTable(fromTableName = "order", toTableName = "local_order")
class AutoMigrationApp:AutoMigrationSpec
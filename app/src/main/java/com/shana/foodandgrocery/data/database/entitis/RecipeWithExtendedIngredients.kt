package com.shana.foodandgrocery.data.database.entitis

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithExtendedIngredients(
    @Embedded val recipesEntity: RecipesEntity,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "id",
        associateBy = Junction(RecipeExtendedIngredientCrossRefEntity::class)
    )
    val extendedIngredient: List<ExtendedIngredientEntity>
)
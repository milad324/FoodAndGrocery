package com.shana.foodandgrocery.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shana.foodandgrocery.R


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppView() {
    TopAppBar(title = {
        Text(
            text = "Food And Grocery",
            style = MaterialTheme.typography.headlineSmall
        )
    },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ShoppingBasket,
                    contentDescription = stringResource(R.string.login),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.login),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        })
}
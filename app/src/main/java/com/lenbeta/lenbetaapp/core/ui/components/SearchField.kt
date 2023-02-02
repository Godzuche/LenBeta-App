package com.lenbeta.lenbetaapp.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lenbeta.lenbetaapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    hint: Int,
    onSearch: (String) -> Unit,
    search: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var queryText by remember { mutableStateOf("") }
    val isTextFieldEmpty = queryText.isEmpty()
    OutlinedTextField(
        value = queryText,
        onValueChange = { inputText ->
            queryText = inputText
            // this is for searching while the inputText changes i.e while the user is typing
            onSearch.invoke(inputText)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClickLabel = stringResource(R.string.search),
                        role = Role.Button
                    ) {
                        // search when the search icon is clicked
                        search.invoke(queryText)
                    }
            )
        },
        trailingIcon = {
            // visible if there is input text
            AnimatedVisibility(
                visible = !isTextFieldEmpty,
                enter = expandIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    expandFrom = Alignment.Center
                ),
                exit = shrinkOut(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    shrinkTowards = Alignment.Center
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            queryText = ""
                        }
                )
            }
        },
        placeholder = {
            Text(text = stringResource(hint))
        },
        label = {
            Text(text = "Search")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { search.invoke(queryText) }
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .then(modifier)
    )
}
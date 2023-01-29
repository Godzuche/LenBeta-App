package com.lenbeta.lenbetaapp.feature.user

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme

@Composable
fun UsersRoute(
    modifier: Modifier = Modifier,
    navigateToTeacher: () -> Unit,
    navigateToStudent: () -> Unit
) {
    UsersScreen(
        navigateToTeacher = navigateToTeacher,
        navigateToStudent = navigateToStudent,
        modifier = modifier
    )
}

@Composable
fun UsersScreen(
    navigateToTeacher: () -> Unit,
    navigateToStudent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .wrapContentSize(Alignment.Center)
            .then(modifier)
    ) {
        Text(text = stringResource(R.string.i_am_a))
        Spacer(modifier = Modifier.height(16.dp))
        UserSelectionButton(
            text = R.string.bt_teacher,
            onUserClick = navigateToTeacher
        )
        UserSelectionButton(
            text = R.string.bt_student,
            onUserClick = navigateToStudent
        )
    }

}

@Composable
fun UserSelectionButton(
    @StringRes text: Int, modifier: Modifier = Modifier, onUserClick: () -> Unit
) {
    FilledTonalButton(
        onClick = onUserClick, modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(text))
    }

}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun UserSelectionPreview() {
    LenBetaAppTheme {
        UsersScreen(
            navigateToStudent = {},
            navigateToTeacher = {}
        )
    }
}

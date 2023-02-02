package com.lenbeta.lenbetaapp.feature.user

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lenbeta.lenbetaapp.R

@Composable
fun UsersRoute(
    modifier: Modifier = Modifier,
    navigateToTeacherAuth: () -> Unit,
    navigateToStudentAuth: () -> Unit
) {
    UsersScreen(
        navigateToTeacher = navigateToTeacherAuth,
        navigateToStudent = navigateToStudentAuth,
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
//        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .wrapContentSize(Alignment.Center)
            .then(modifier)
    ) {
        Text(
            text = "Welcome to LenBeta!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Start)
        )
//        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "To get started, who are you?",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = stringResource(R.string.i_am_a),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        UserSelectionButton(
            text = R.string.bt_teacher,
            onUserClick = navigateToTeacher
        )
        Spacer(modifier = Modifier.height(4.dp))
        UserSelectionButton(
            text = R.string.bt_student,
            onUserClick = navigateToStudent
        )
    }

}

/*@Composable
fun UsersScreen(
    navigateToTeacher: () -> Unit,
    navigateToStudent: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
//            .wrapContentSize(Alignment.Center)
            .then(modifier)
    ) {
        val (welcomeText, identityText, teacherBtn, studentBtn) = createRefs()
        Text(
            text = "Welcome to LenBeta!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.constrainAs(welcomeText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(R.string.i_am_a),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(identityText) {
                top.linkTo(welcomeText.bottom, margin = 48.dp)
                start.linkTo(welcomeText.start)
                end.linkTo(parent.end)
            }
        )
//        Spacer(modifier = Modifier.height(16.dp))
        UserSelectionButton(
            text = R.string.bt_teacher,
            onUserClick = navigateToTeacher,
            modifier = Modifier.constrainAs(teacherBtn) {
                top.linkTo(identityText.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        UserSelectionButton(
            text = R.string.bt_student,
            onUserClick = navigateToStudent,
            modifier = Modifier.constrainAs(studentBtn) {
                top.linkTo(teacherBtn.bottom, margin = 8.dp)
                start.linkTo(teacherBtn.start)
                end.linkTo(teacherBtn.end)
            }
        )
    }

}*/

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
    UsersScreen(
        navigateToStudent = {},
        navigateToTeacher = {}
    )
}

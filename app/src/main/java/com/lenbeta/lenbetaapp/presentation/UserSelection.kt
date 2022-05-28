package com.lenbeta.lenbetaapp.presentation

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen

@Composable
fun UserSelection(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LenBetaAppTheme() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .wrapContentSize(Alignment.Center)
                .then(modifier)
        ) {
            Text(text = "I am a...")
            Spacer(modifier = Modifier.height(16.dp))
            UserSelectionButton(
                text = R.string.bt_teacher,
                onButtonClick = {    navController.navigate(LenBetaScreen.TeacherSignUp.route)        }
            )
            UserSelectionButton(text = R.string.bt_student,
                onButtonClick = { navController.navigate(LenBetaScreen.StudentSignUp.route) }
            )
        }
    }
}

@Composable
fun UserSelectionButton(
    @StringRes text: Int, modifier: Modifier = Modifier, onButtonClick: () -> Unit
) {
    FilledTonalButton(
        onClick = onButtonClick, modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(text))
    }

}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun UserSelectionPreview() {
    LenBetaAppTheme() {
        UserSelection(navController = rememberNavController())
    }
}

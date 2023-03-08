package `is`.hi.hbv601g.projectplanner.ui

import android.graphics.Paint.Align
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val firstname = remember {
            mutableStateOf(TextFieldValue())
        }
        val lastname = remember {
            mutableStateOf(TextFieldValue())
        }
        val email = remember {
            mutableStateOf(TextFieldValue())
        }
        val password = remember {
            mutableStateOf(TextFieldValue())
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "First Name")},
            value = firstname.value,
            onValueChange = {firstname.value = it}
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Last Name")},
            value = lastname.value,
            onValueChange = {lastname.value = it}
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email")},
            value = email.value,
            onValueChange = {email.value = it}
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = {Text(text = "Password")},
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {password.value = it}
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}
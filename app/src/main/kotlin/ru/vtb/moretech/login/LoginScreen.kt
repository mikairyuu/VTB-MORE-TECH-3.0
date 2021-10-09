package ru.vtb.moretech.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vtb.moretech.R
import ru.vtb.moretech.ui.theme.VTBTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val nameState by viewModel.name.collectAsState()
    val emailState by viewModel.email.collectAsState()
    val passwordState by viewModel.password.collectAsState()
    var passwordVisibility by remember { mutableStateOf(true) }

//    val (focusRequester) = FocusRequester.createRefs()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
            ) {
                Text(
                    text = "Регистрация",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .size(150.dp)
                )
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { viewModel.name.value = it },
                    label = { Text("Имя") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xFF3A83F1),
                        focusedBorderColor = Color(0xFF3A83F1),
                        focusedLabelColor = Color(0xFF3A83F1),
                    )
                )
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { viewModel.email.value = it },
                    label = { Text("Электронная почта") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xFF3A83F1),
                        focusedBorderColor = Color(0xFF3A83F1),
                        focusedLabelColor = Color(0xFF3A83F1),
                    ),
                    modifier = Modifier.focusRequester(focusRequester)
                )
                OutlinedTextField(
                    value = passwordState,
                    onValueChange = { viewModel.password.value = it },
                    label = { Text("Пароль") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            if (passwordVisibility)
                                Icon(
                                    painterResource(id = R.drawable.ic_visibility),
                                    contentDescription = null
                                )
                            else
                                Icon(
                                    painterResource(id = R.drawable.ic_visibility_off),
                                    contentDescription = null
                                )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xFF3A83F1),
                        focusedBorderColor = Color(0xFF3A83F1),
                        focusedLabelColor = Color(0xFF3A83F1),
                    ),
                    modifier = Modifier.focusRequester(focusRequester),
                    visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None
                )
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .padding(start = 40.dp, end = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF3A83F1)
                )
            ) {
                Text(
                    text = "Зарегистрироваться",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}


@Composable
@Preview
fun LoginPreview() {

    VTBTheme {
        LoginScreen()
    }

}
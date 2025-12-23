package com.example.ca2

import android.R.attr.textColor
import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ca2.ui.theme.CA2Theme
import org.w3c.dom.Text
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CA2Theme {
                Signup()
            }
        }
    }
    @Preview
    @Composable
    fun Signup(){

        var username by remember{mutableStateOf("")}
        var password by remember{mutableStateOf("")}
        var member by remember{mutableStateOf("")}
        var isDarkMode by remember{mutableStateOf(false)}

        val isFormValid = username.length>=4 && password.length>=6 && member.isNotEmpty()

        val backgroundColor = if (isDarkMode) {
            Color.Black
        } else {
            Color.White
        }
        val context = LocalContext.current

        Column(modifier = Modifier.fillMaxSize().background(backgroundColor).padding(top=20.dp)

            ) {
            Text("Signup",fontSize = 20.sp,color =contentColor,modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Username",fontSize = 10.sp,color = contentColor,modifier = Modifier.padding(start = 10.dp))
            OutlinedTextField(
                modifier = Modifier.padding(start = 10.dp),
                value = username,
                onValueChange = {username = it},
                label = {  }
            )
            Text("Password",fontSize = 10.sp,color = contentColor, modifier = Modifier.padding(start = 10.dp))
            OutlinedTextField(
                modifier = Modifier.padding(start = 10.dp),
                value = password,
                onValueChange = {password = it},
                label = { },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Select User Type:",color = contentColor,fontSize = 10.sp,modifier = Modifier.padding(start = 10.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                RadioButton(selected = member == "Student", onClick = { member = "Student" })
                Text("Student",color = contentColor,fontSize = 10.sp)
                Spacer(modifier = Modifier.padding(start = 10.dp))
                RadioButton(selected = member == "Admin", onClick = { member = "Admin" })
                Text("Admin",color = contentColor,fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                Text("Dark Mode",color = contentColor,fontSize = 10.sp,modifier = Modifier.padding(start = 10.dp))
                Spacer(modifier = Modifier.padding(start = 250.dp))
                Switch(checked = isDarkMode, onCheckedChange = { isDarkMode = it })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {if(isFormValid){Log.d("SignupData","User:$username,member:$member")
            Toast.makeText(context,"Signed Up Successfully",Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            }
            },
                enabled = isFormValid,modifier=Modifier.fillMaxWidth()){
                Text("Signup")
            }
        }
    }
}

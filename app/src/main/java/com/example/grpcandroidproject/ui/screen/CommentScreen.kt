package com.example.grpcandroidproject.ui.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.grpcandroidproject.R
import com.example.grpcandroidproject.model.Comment
import com.example.grpcandroidproject.model.User
import com.example.grpcandroidproject.navigation.Screen

@Composable
fun CommentScreen(navController: NavHostController) {
    val user= User(
        name = "User Temp",
        profileImage = R.drawable.user_icon
    )

    val commentsList= listOf<Comment>(
        Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        ),Comment(
            user = user,
            text = "this is first comment",
            timestamp = "2m"
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Header(navController)
        CommentsList(commentsList)
    }
}

@Composable
fun Header(navController: NavHostController) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.clickable{},
                onClick = { navController.navigate(Screen.HomeScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(text = "Comments", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(24.dp))
        }
        Divider(thickness = 2.dp, color = Color.Gray)
    }
}


@Composable
fun CommentsList(comments: List<Comment>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(comments) { comment ->
            CommentItem(comment)
            Divider()
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_icon),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = comment.user.name, fontWeight = FontWeight.Bold,color= Color.DarkGray)
                Text(text = comment.timestamp, style = MaterialTheme.typography.titleSmall, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.text)
        }
    }
}

@Composable
@Preview(showBackground=true)
fun CommentScreenPreview(){
    CommentScreen(navController= rememberNavController())
}
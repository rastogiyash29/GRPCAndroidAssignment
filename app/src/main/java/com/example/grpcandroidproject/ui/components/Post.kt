package com.example.grpcandroidproject.ui.components

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.grpcandroidproject.R
import com.example.grpcandroidproject.navigation.Screen
import com.example.quotify.utils.TimePastCalculator
import com.kotlang.social.FeedResponse
import com.kotlang.social.UserPostProto
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp


@Composable
fun Post(navController: NavController, post: UserPostProto) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val imageMaxWidth = screenWidth
    val imageMaxHeight = screenHeight * 3 / 5

    val postImages = ArrayList<String>()
    post.mediaUrlsList.forEach {
        postImages.add(it.url)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage2(
                url = post.authorInfo.photoUrl,
                errorImg = R.drawable.user_icon,
                contentDescription = "This is profile image of user",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = if(post.authorInfo.name.isNotEmpty()){post.authorInfo.name}else{"User Name not Avl.."},
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                    color = Color.DarkGray
                )
                Text(
                    text = "${TimePastCalculator.toDuration(post.createdOn)}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = Color.Gray
                )
            }
            Text(text = "Question",
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFD1DBFA))
                    .padding(4.dp),
                color = Color(0xFF123BB6)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.width(40.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.options_icon),
                    contentDescription = "More Options"
                )
            }
        }
        Text(
            text = post.post,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 17.sp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
        )
        if (post.mediaUrlsCount > 1) {  //Display Images iff media urls are present
            LazyRow(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {
                items(postImages) { imageUrl ->
                    NetworkImage2(url = imageUrl,
                        errorImg = R.drawable.error_image,
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageMaxHeight)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }else if(post.mediaUrlsCount==1){
            Box(modifier = Modifier.fillMaxWidth(),Alignment.Center){
                NetworkImage2(url = post.mediaUrlsList.get(0).url,
                    contentDescription = "Post Image",
                    errorImg = R.drawable.error_image,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {},
                    onClick = { /* Handle click */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_favorite_24),
                        contentDescription = "Likes",
                        tint = Color.Blue,
                    )
                }
                Text(text = "${123} likes")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {},
                    onClick = { navController.navigate(route = Screen.CommentScreen.route) }
                ) {
                    Icon(
                        painterResource(id = R.drawable.baseline_comment_24),
                        contentDescription = "Comments",
                    )
                }
                Text(text = "${12} comments")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {},
                    onClick = { /* Handle click */ }
                ) {
                    Icon(
                        painterResource(id = R.drawable.baseline_share_24),
                        contentDescription = "Share",
                    )
                }
                Text(text = "Share")
            }
        }
        Divider(color = Color.Gray)
    }
}

@Composable
fun NetworkImage2(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    errorImg:Int
){
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        error = painterResource(errorImg),
        contentScale = ContentScale.Crop
    )
}




package com.example.grpcandroidproject.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grpcandroidproject.repository.GrpcRepository
import com.kotlang.social.FeedResponse
import com.kotlang.social.GetFeedRequest
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlang.social.UserPostProto
import com.example.grpcandroidproject.ui.components.Post
import com.example.grpcandroidproject.ui.components.SettingsList


class homeScreenViewModel : ViewModel() {
    lateinit var repository: GrpcRepository
    private val _responseData=MutableLiveData<FeedResponse>()
    val responseData:LiveData<FeedResponse> = _responseData

    init {
        repository= GrpcRepository.getInstance()
        val newResponse=repository.getFeed(GetFeedRequest.newBuilder().build())
        Log.d("tag","response is: ${newResponse}")
        _responseData.value=newResponse
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel:homeScreenViewModel= viewModel()
    val feedResponse=viewModel.responseData.observeAsState(initial = null)

    Box(modifier = Modifier.fillMaxSize()){
            var selectedOption by remember { mutableStateOf("charcha") }

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val options = listOf("charcha", "baazar", "settings")
                        for (option in options) {
                            Column(
                                modifier = Modifier
                                    .clickable { selectedOption = option }
                                    .padding(horizontal = 15.dp)
                                    .height(IntrinsicSize.Min)
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = option,
                                    modifier = Modifier
                                        .padding(vertical = 18.dp),
                                    color = if (selectedOption == option) Color.Blue else Color.DarkGray,
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(if (selectedOption == option) 2.dp else 0.dp)
                                        .background(Color.Blue)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Gray)
                    )
                }
                Box(modifier = Modifier.fillMaxSize()){
                    if(feedResponse.value==null){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }else{
                        when (selectedOption) {
                            "charcha" ->  CharchaContent(feedResponse.value!!.postsList,navController)
                            "baazar" -> BaazarContent()
                            "settings" -> SettingsContent()
                        }
                    }
                }
        }
    }
}

@Composable
fun CharchaContent(postList: MutableList<UserPostProto>, navController: NavController) {
    Box(modifier=Modifier.fillMaxSize()){
        LazyColumn(
        ) {
            items(postList) { post ->
                Post(post=post, navController = navController)
            }
        }
    }
}

@Composable
fun BaazarContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Content for baazar")
    }
}

@Composable
fun SettingsContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsList()
    }
}

package com.example.chatpoc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatpoc.ui.theme.ChatPOCTheme
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryChannelRequest
import io.getstream.chat.android.client.api.models.QueryChannelsRequest
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.token.TokenProvider
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.channels.SearchMode
import io.getstream.chat.android.compose.ui.channels.list.ChannelList
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory
import io.getstream.chat.android.models.Channel
import io.getstream.chat.android.models.Filters
import io.getstream.chat.android.models.InitializationState
import io.getstream.chat.android.models.User
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import kotlinx.coroutines.flow.MutableStateFlow

val userID = "tom"
val userName = "Tom"
val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MjY4Mjg2NjIsImV4cCI6MTc1ODM2NDY2MiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsInVzZXJfaWQiOiJ0b20iLCJuYW1lIjoiVG9tIn0.yEzw1tWgzV7VAUFXxiGaOzJeFedBTqaAI1eswHnLcR0"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var users: MutableStateFlow<List<User>> = MutableStateFlow(listOf())

        //enableEdgeToEdge()

        val offlinePluginFactory = StreamOfflinePluginFactory(appContext = applicationContext)
        val statePluginFactory =
            StreamStatePluginFactory(config = StatePluginConfig(), appContext = this)

        // 2 - Set up the client for API calls and with the plugin for offline storage
        val client = ChatClient.Builder("n2j2u56jp76c", applicationContext)
            .withPlugins(offlinePluginFactory, statePluginFactory)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()



        // connect and create channel

        /*
        val user = User(
            id = "user3",
            name = "user3",
            image = "https://bit.ly/2TIt8NR"
        )
        client.connectUser(
            user = user,
            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MjY4MTQ4NDgsImV4cCI6MTc1ODM1MDg0OCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsInVzZXJfaWQiOiJ1c2VyMyIsIm5hbWUiOiJ1c2VyMyJ9.JP5VHazPZ0RGF1izVfBwgBOWmn5eWclohfYc51VIliw"
        ).enqueue { result ->
//                    client.createChannel(
//                        channelType = "messaging",
//                        channelId = "channel123",
//                        memberIds = listOf("user2","user3"),
//                        extraData = emptyMap()
//                    ).enqueue { result ->
//                        if (result.isSuccess) {
//                            Log.d("TAG", "success: ")
//                        } else {
//                            Log.d("TAG", "failure: ${result.errorOrNull()}")
//
//                        }
//                    }


            val request = QueryUsersRequest(
                filter = Filters.eq("banned", false),
                offset = 0,
                limit = 300,
            )
            client.queryUsers(request).enqueue { result ->
                if (result.isSuccess) {
                    result.getOrNull()?.let {
                        users.value = it
                    }
                    Log.d("TAG", "onCreate: ${result.getOrNull()}")

                } else {
                    Log.d("TAG", "onCreate: ${result.errorOrNull()}")
                }
            }

        }


         */




//        val user = User(
//            id = "user8",
//            name = "Rocket",
//            image = "https://bit.ly/2TIt8NR"
//        )
//        client.connectUser(
//            user = user,
//            token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MjY4Mjg2NjIsImV4cCI6MTc1ODM2NDY2MiwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsInVzZXJfaWQiOiJ1c2VyOCIsIm5hbWUiOiJSb2NrZXQifQ.7ZRuP3OVvKc86evBJN0c3LwIAsTSo-8WtK7tnQXJH90"
//        ).enqueue { result ->
//
//            client.createChannel(
//                channelType = "messaging",
//                channelId = "channel123",
//                memberIds = listOf("user8", "user3"),
//                extraData = emptyMap()
//            ).enqueue { result ->
//                if (result.isSuccess) {
//                    Log.d("TAG", "success: ")
//                } else {
//                    Log.d("TAG", "failure: ${result.errorOrNull()}")
//
//                }
//            }
//
//            val request = QueryUsersRequest(
//                filter = Filters.eq("banned", false),
//                offset = 0,
//                limit = 10,
//            )
//            client.queryUsers(request).enqueue { result ->
//                if (result.isSuccess) {
//                    result.getOrNull()?.let {
//                        users.value = it
//                    }
//                    Log.d("TAG", "onCreate: ${result.getOrNull()}")
//
//                } else {
//                    Log.d("TAG", "onCreate: ${result.errorOrNull()}")
//                }
//            }
//
//        }


        val user = User(
            id = userID,
            name = userName,
            image = "https://bit.ly/2TIt8NR"
        )
        client.connectUser(
            user = user,
            token = authToken
        ).enqueue { result ->

            client.createChannel(
                channelType = "messaging",
                channelId = "channel123",
                memberIds = listOf(userID, "user3"),
                extraData = emptyMap()
            ).enqueue { result ->
                if (result.isSuccess) {
                    Log.d("TAG", "success: ")
                } else {
                    Log.d("TAG", "failure: ${result.errorOrNull()}")

                }
            }

            Log.d("TAG", "failure: ${result.errorOrNull()}")
            val request = QueryUsersRequest(
                filter = Filters.eq("banned", false),
                offset = 0,
                limit = 10,
            )
            client.queryUsers(request).enqueue { result ->
                if (result.isSuccess) {
                    result.getOrNull()?.let {
                        users.value = it
                    }
                    Log.d("TAG", "onCreate: ${result.getOrNull()}")

                } else {
                    Log.d("TAG", "onCreate: ${result.errorOrNull()}")
                }
            }

        }


        setContent {
            val userList by users.collectAsState()
            val clientInitialisationState by client.clientState.initializationState.collectAsState()
            var channelID: String? = null

            var showUserListScreen by remember { mutableStateOf(false) }

            ChatPOCTheme {
                ChatTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (showUserListScreen) {
                            NavigateToUserList(userList = userList,
                                client = client,
                                onBackPress = {
                                    showUserListScreen = false
                                })
                        } else {
                            when (clientInitialisationState) {
                                InitializationState.COMPLETE -> {
                                    ChannelsScreen(
                                        onHeaderActionClick = {
                                            showUserListScreen = true
                                        },
                                        title = stringResource(id = R.string.app_name),
                                        onChannelClick = { channel ->
                                            startActivity(
                                                ChannelActivity.getIntent(
                                                    this@MainActivity,
                                                    channel.cid
                                                )
                                            )
                                        },
                                        searchMode = SearchMode.Channels,
                                        onBackPressed = { finish() }
                                    )
                                }

                                InitializationState.INITIALIZING -> {
                                    Text(text = "Initialising...")
                                }

                                InitializationState.NOT_INITIALIZED -> {
                                    Text(text = "Not initialized...")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NavigateToUserList(
    modifier: Modifier = Modifier.fillMaxSize(), userList: List<User>, onBackPress: () -> Unit,
    client: ChatClient
) {
    BackHandler(enabled = true) {
        onBackPress()
    }
    LazyColumn {
        itemsIndexed(userList) { index, item ->
            Text(
                item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        Color.Blue.copy(alpha = 0.3f)
                    )
                    .clickable {
                        client
                            .createChannel(
                                channelType = "messaging",
                                channelId = item.id.plus(userID),
                                memberIds = listOf(userID, item.id),
                                extraData = emptyMap()
                            )
                            .enqueue { result ->
                                if (result.isSuccess) {
                                    Log.d("TAG", "success: ")
                                    onBackPress()
                                } else {
                                    Log.d("TAG", "failure: ${result.errorOrNull()}")
                                }
                            }
                    }
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun MessageUi(channelId: String, onBackPress: () -> Unit) {
    val context = LocalContext.current
    MessagesScreen(
        viewModelFactory = MessagesViewModelFactory(
            context = context,
            channelId = channelId,
            messageLimit = 30
        ),
        onBackPressed = { onBackPress() }
    )
}


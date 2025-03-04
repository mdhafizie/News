package com.news.android.presentation.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.news.android.R
import com.news.android.data.model.Article
import com.news.android.presentation.viewmodel.BookmarkViewModel
import com.news.android.presentation.viewmodel.NewsViewModel

@Composable
fun NewsScreen(navController: NavController,
               viewModel: NewsViewModel = hiltViewModel(),
               bookmarkViewModel: BookmarkViewModel = hiltViewModel()) {
    val articles by viewModel.newsState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        itemsIndexed(articles) { index, article ->
            NewsItem(article.title, article.urlToImage, article.url, navController,bookmarkViewModel)

            // Trigger pagination when the user reaches the end
            if (index == articles.lastIndex) {
                LaunchedEffect(Unit) {
                    viewModel.loadMoreNews()
                }
            }
        }

        // Show progress indicator at the bottom when loading
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun NewsItem(
    title: String,
    imageUrl: String?,
    url: String,
    navController: NavController,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val isBookmarked by viewModel.bookmarks.collectAsState()

    val isSaved = remember { mutableStateOf(isBookmarked.any { it.url == url }) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
            .clickable { navController.navigate("webview/${Uri.encode(url)}") },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .error(R.drawable.image_placeholder)
                    .placeholder(R.drawable.image_placeholder)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = title, fontSize = 16.sp, maxLines = 4, color = Color.Black)
            }

            IconButton(
                onClick = {
                    viewModel.toggleBookmark(Article(url, title, imageUrl ?: ""))
                    isSaved.value = !isSaved.value
                },
                modifier = Modifier.align(Alignment.Top)
            ) {
                Icon(
                    painter = painterResource(id = if (isSaved.value) R.drawable.ic_bookmark_remove else R.drawable.ic_bookmark_add),
                    contentDescription = "Bookmark",
                    tint = if (isSaved.value) Color.Red else Color.Blue
                )
            }
        }
    }
}

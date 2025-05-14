package sugratech.com.composedemo25.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sugratech.com.composedemo25.models.Category
import sugratech.com.composedemo25.models.Item
import sugratech.com.composedemo25.models.hasChildren
import sugratech.com.composedemo25.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(viewModel: MainViewModel, modifier: PaddingValues) {
    // Observe state from ViewModel
    val currentList by viewModel.currentList.collectAsState()
    val path by viewModel.pathFlow.collectAsState()

    // For displaying snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(path.joinToString(" > ")) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (!viewModel.onBackPressed()) {
                            viewModel.resetToHome()
                        }
                    }) {
                        Icon(
                            imageVector = if (path.isEmpty()) Icons.Default.Home else Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(currentList) { item ->
                    val backgroundColor = when (item) {
                        is Category -> Color(0xFF4CAF50)
                        is Item -> if (item.price != null) Color(0xFF8BC34A) else Color(0xFFFFEB3B)
                        else -> Color.Gray
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = backgroundColor),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                when (item) {
                                    is Category -> {
                                        if (item.hasChildren()) {
                                            viewModel.onItemClicked(item)
                                        } else {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("No subcategories or items available")
                                            }
                                        }
                                    }

                                    is Item -> {
                                        if (item.hasChildren()) {
                                            viewModel.onItemClicked(item)
                                        } else {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("No subcategories or items available")
                                            }
                                        }
                                    }
                                }
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = when (item) {
                                is Category -> item.name
                                is Item -> item.name
                                else -> "Unknown"
                            })
                            if (item is Item && item.price != null) {
                                Text(text = "$${item.price}")
                            }
                        }
                    }
                }
            }
        }
    )
}

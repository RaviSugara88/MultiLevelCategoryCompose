package sugratech.com.composedemo25.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sugratech.com.composedemo25.models.CategoriesResponse
import sugratech.com.composedemo25.models.Category
import sugratech.com.composedemo25.models.Item
import java.util.Stack

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _currentList = MutableStateFlow<List<Any>>(emptyList())
    val currentList: StateFlow<List<Any>> = _currentList

    private val _pathFlow = MutableStateFlow<List<String>>(emptyList())
    val pathFlow: StateFlow<List<String>> = _pathFlow

    private val navigationStack = Stack<List<Any>>()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val json = getApplication<Application>().assets.open("categories.json").bufferedReader().use { it.readText() }
            val categoriesResponse = Gson().fromJson(json, CategoriesResponse::class.java)
            _currentList.value = categoriesResponse.categories
        }
    }

    fun onItemClicked(item: Any) {
        navigationStack.push(_currentList.value)

        when (item) {
            is Category -> {
                _pathFlow.value += item.name
                item.subcategories?.let {
                    _currentList.value = it
                    return
                }
                item.items?.let {
                    _currentList.value = it
                    return
                }
            }
            is Item -> {
                _pathFlow.value += item.name
                item.items?.let {
                    _currentList.value = it
                    return
                }
            }
        }
    }

    fun onBackPressed(): Boolean {
        return if (navigationStack.isNotEmpty()) {
            _currentList.value = navigationStack.pop()
            _pathFlow.value = _pathFlow.value.dropLast(1)
            true
        } else false
    }

    fun resetToHome() {
        navigationStack.clear()
        _pathFlow.value = emptyList()
        loadCategories()
    }

    fun isAtHome(): Boolean = _pathFlow.value.isEmpty()
}


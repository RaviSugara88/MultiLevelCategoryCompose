package sugratech.com.composedemo25.models

data class CategoriesResponse(
    val categories: List<Category>
)

data class Category(
    val name: String,
    val subcategories: List<Category>? = null,
    val items: List<Item>? = null
)


data class Item(
    val name: String,
    val price: Double? = null,
    val items: List<Item>? = null
)

fun Category.hasChildren(): Boolean {
    return (subcategories?.isNotEmpty() == true) || (items?.isNotEmpty() == true)
}

fun Item.hasChildren(): Boolean {
    return (items?.isNotEmpty() == true)
}

package cn.codeguy.foldersample.text

data class DummyItem(
        val id: String,
        val url: String,
        val title: String) {
    override fun toString(): String = url
}

data class FolderBean(
        val folders: List<FolderBean>,
        val icon: String,
        val title: String,
        val type: String
)


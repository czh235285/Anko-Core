package czh.fast.lib.bean

data class ItemBean(

        var leftIcon: Int? = null,
        var leftText: String,
        var rightText: String? = "",
        var height: Int? = null,
        var marginBottom: Int? = null,
        var hasTopLine: Boolean = false
)
package czh.fast.sample.mvp.model

data class Content(
        val Activity: List<Any>,
        val Advert: List<AdvertX>,
        val HaveMoreActivity: Boolean,
        val IsNewUser: Boolean,
        val Picture: Picture,
        val Product: Any,
        val Project: List<Project>,
        val Special: List<Special>,
        val User: Any
)
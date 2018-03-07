package com.eden.orchid.posts.pages

import com.eden.orchid.api.options.annotations.Archetype
import com.eden.orchid.api.options.annotations.Archetypes
import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.options.annotations.Option
import com.eden.orchid.api.options.annotations.StringDefault
import com.eden.orchid.api.options.archetypes.ConfigArchetype
import com.eden.orchid.api.resources.resource.OrchidResource
import com.eden.orchid.api.theme.pages.OrchidPage
import com.eden.orchid.posts.PostsGenerator
import com.eden.orchid.posts.model.Author
import com.eden.orchid.posts.model.PostsModel
import com.eden.orchid.utilities.OrchidUtils
import org.json.JSONObject

@Archetypes(
    Archetype(value = ConfigArchetype::class, key = "${PostsGenerator.generatorKey}.allPages"),
    Archetype(value = ConfigArchetype::class, key = "${PostsGenerator.generatorKey}.authorPages")
)
class AuthorPage(resource: OrchidResource, val author: Author, val postsModel: PostsModel) : OrchidPage(resource, "postAuthor") {

    @Option @StringDefault("authors/:authorName")
    @Description("The permalink structure to use only for this author bio page.")
    lateinit var permalink: String

    init {
        this.extractOptions(this.context, data)
        postInitialize(title)
    }

    override fun initialize(title: String?) {

    }

    fun initializeAuthorFromPageData() {
        author.extractOptions(context, this.allData.element as JSONObject)
    }

    override fun getTemplates(): List<String> {
        val templates = super.getTemplates()
        templates.add(0, "$key-${OrchidUtils.toSlug(author.name)}")
        return templates
    }

}


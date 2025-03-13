package com.ung.feedback.model

import java.time.Instant

data class Post(   val id: String,
                        val author: String,
                        val title: String,
                        val publishDate: Instant,
                        val content: String,)

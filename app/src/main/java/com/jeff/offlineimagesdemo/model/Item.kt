package com.jeff.offlineimagesdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Entity
@Root(strict = false, name = "image")
data class Item (
    @PrimaryKey @field:Element(name = "id") @param:Element(name = "id") val imageId : String,
    @field:Element(name ="url") @param:Element(name ="url")val url : String,
    @field:Element(name ="source_url") @param:Element(name ="source_url") val source_url : String

){
     var title:String = "image"+((0..100).random())
     var description:String

    init {
         description="This is the description for $title , It's a really cool image, bask in it's gloriousness"
    } }
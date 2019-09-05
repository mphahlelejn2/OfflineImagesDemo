package com.jeff.offlineimagesdemo.model
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "images")
 data class Images(@field:ElementList(inline = true,name = "image")
                   @param:ElementList(name = "image") var imageList: List<Item>)
package com.aleksandar.wunder.ui

import com.aleksandar.wunder.vo.Placemark

interface ListItemClickListener {

    fun selectItem(adapterPosition: Int, placemark: Placemark)
}
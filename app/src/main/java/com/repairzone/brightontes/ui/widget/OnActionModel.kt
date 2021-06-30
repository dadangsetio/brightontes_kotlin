package com.repairzone.brightontes.ui.widget

abstract class OnActionModel : OnClickEvent{
    override var actionOpen: Int
        get() = 0
        set(value) {}
    override var onClick: (Int) -> Unit = {}
}
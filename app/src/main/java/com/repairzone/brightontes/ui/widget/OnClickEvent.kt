package com.repairzone.brightontes.ui.widget

interface OnClickEvent {
    var actionOpen: Int
    var onClick: (action: Int) -> Unit
}
package com.demoss.idp.util

fun ifThereIsNoNull(vararg props: Any?, then: () -> Unit) {
    props.forEach { if (it == null) return }
    then()
}
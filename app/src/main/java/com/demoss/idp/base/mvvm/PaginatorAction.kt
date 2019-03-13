package com.demoss.idp.base.mvvm

sealed class PaginatorAction

object PAGINATOR_RESTART: PaginatorAction()
object PAGINATOR_REFRESH: PaginatorAction()
object PAGINATOR_LOAD_NEXT_PAGE: PaginatorAction()
package com.demoss.cat.base.mvvm

sealed class BasePaginatorAction

object ACTION_RESTART: BasePaginatorAction()
object ACTION_: BasePaginatorAction()
object ACTION_LOAD_NEXT_PAGE: BasePaginatorAction()
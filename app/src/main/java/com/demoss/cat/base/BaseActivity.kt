package com.demoss.cat.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.demoss.cat.base.mvvm.BaseView
import com.demoss.cat.base.mvvm.BaseViewModel
import io.reactivex.subjects.PublishSubject

abstract class BaseActivity<Action, State, VM : BaseViewModel<Action, State>>
    : AppCompatActivity(), BaseView<Action, State, VM> {

    override val actions: PublishSubject<Action> = PublishSubject.create()
    abstract val layoutResourceId: Int

    protected fun <T> LiveData<T>.observeWithThis(observerFunction: (T) -> Unit) {
        this.observe(this@BaseActivity, Observer(observerFunction))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        subscribeToViewModel(this)
        viewModel.states.observeWithThis { dispatchState(it) }
    }

    override fun onDestroy() {
        viewModel.release()
        super.onDestroy()
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        (getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
    }
}
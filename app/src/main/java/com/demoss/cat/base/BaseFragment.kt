package com.demoss.cat.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.demoss.cat.base.mvvm.BaseView
import com.demoss.cat.base.mvvm.BaseViewModel
import io.reactivex.subjects.PublishSubject

abstract class BaseFragment<Action, State, VM : BaseViewModel<Action, State>>
    : Fragment(), BaseView<Action, State, VM> {

    override val actions: PublishSubject<Action> = PublishSubject.create()
    abstract val layoutResourceId: Int

    protected fun <T> LiveData<T>.observeWithThis(observerFunction: (T) -> Unit) {
        this.observe(this@BaseFragment, Observer(observerFunction))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToViewModel(this)
        viewModel.states.observeWithThis { dispatchState(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onDestroy() {
        viewModel.release()
        super.onDestroy()
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        (activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
    }
}
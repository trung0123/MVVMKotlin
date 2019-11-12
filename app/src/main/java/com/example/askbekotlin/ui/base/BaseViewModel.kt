package com.example.askbekotlin.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), LifecycleObserver {

    val mException: MutableLiveData<Throwable> = MutableLiveData()

    val mLoading: MutableLiveData<Boolean> = MutableLiveData()

    val mProgress:MutableLiveData<Boolean> = MutableLiveData()

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(block, {}, {}, true)
        }
    }

    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    private suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
        finallyBlock: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    mLoading.value = false
                    mException.value = e
                    mProgress.value = false
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }
}
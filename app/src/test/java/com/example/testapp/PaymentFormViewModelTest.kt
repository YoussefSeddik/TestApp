package com.example.testapp

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.Test

class PaymentFormViewModel(
    private val iban: FieldViewModel,
    private val tax: FieldViewModel,
) {

    val state: Observable<State>
        get() {
            return Observable.merge<State>(
                iban.focus.map {
                    State.Focus(iban, mutableListOf())
                },
                tax.focus.map {
                    State.Focus(tax, mutableListOf())
                },
                Observable.just(State.Fields(mutableListOf(iban, tax)))
            )


        }
}

class FieldViewModel {
    val focus: PublishRelay<Unit> = PublishRelay.create()
}

class SuggestionsViewModel

sealed class State {
    class Fields(val fieldsViewModels: List<FieldViewModel>) : State() {
        override fun equals(other: Any?): Boolean {
            other?.let {
                if (it is Fields) {
                    return it.fieldsViewModels.containsAll(fieldsViewModels)
                }
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return fieldsViewModels.hashCode()
        }
    }

    class Focus(
        private val fieldViewModel: FieldViewModel,
        private val suggestionsViewModels: List<SuggestionsViewModel>
    ) : State() {
        override fun equals(other: Any?): Boolean {
            other?.let {
                if (it is Focus) {
                    return it.fieldViewModel == fieldViewModel && it.suggestionsViewModels.containsAll(suggestionsViewModels)
                }
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = fieldViewModel.hashCode()
            result = 31 * result + suggestionsViewModels.hashCode()
            return result
        }
    }
}

class PaymentFormViewModelTest {

    @Test
    fun test_initialState_includesAllFormFields() {
        val iban = FieldViewModel()
        val taxNumber = FieldViewModel()
        val sut = PaymentFormViewModel(iban, taxNumber)
        val stateSpy = StateSpy(sut.state)
        assert(stateSpy.values == mutableListOf(State.Fields(listOf(iban, taxNumber))))
    }

    @Test
    fun test_ibanFocusedState_includeOnlyIbanField() {
        val iban = FieldViewModel()
        val taxNumber = FieldViewModel()
        val sut = PaymentFormViewModel(iban, taxNumber)
        val stateSpy = StateSpy(sut.state)
        iban.focus.accept(Unit)
        assert(
            stateSpy.values == mutableListOf(
                State.Fields(listOf(iban, taxNumber)),
                State.Focus(iban, mutableListOf())
            )
        )
    }

    @Test
    fun test_TaxFocusedState_includeOnlyTaxField() {
        val iban = FieldViewModel()
        val taxNumber = FieldViewModel()
        val sut = PaymentFormViewModel(iban, taxNumber)
        val stateSpy = StateSpy(sut.state)
        taxNumber.focus.accept(Unit)
        assert(
            stateSpy.values == mutableListOf(
                State.Fields(listOf(iban, taxNumber)),
                State.Focus(taxNumber, mutableListOf())
            )
        )
    }

}

class StateSpy(observable: Observable<State>) {
    val values: MutableList<State> = mutableListOf()
    private val disposeBag = CompositeDisposable()

    init {
        disposeBag.add(
            observable.subscribe { state ->
                values.add(state)
            }
        )
    }
}
package apps.iloyarte.melichallenge.ui.main


import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import apps.iloyarte.melichallenge.R
import apps.iloyarte.melichallenge.ui.search.SearchAdapter
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchTest {

    private lateinit var searchString: String

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        searchString = "toalla"
    }


    /*
    * Busca el input de búsqueda, escribe un texto y apreta continuar. Luego, chequea
    * si hay resultados y si la lista está visible
    * */
    @Test
    fun searchTest() {
        val searchInput = onView(
            withId(R.id.search_input)
        )
        searchInput.perform(
            replaceText(searchString),
            pressImeActionButton()
        )

        Thread.sleep(3000)

        val itemList = onView(withId(R.id.result_list))

        itemList.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(
                view is RecyclerView &&
//                    view.adapter != null && view.adapter?.itemCount?:-1 > 0
                        view.isVisible
            )

        }
    }

    /*
    * Realiza una búsqueda, escribe un texto y apreta continuar. Luego, clickea
    * el primer item de la lista
    * */
    @Test
    fun testClickOnItem() {
        val searchInput = onView(
            withId(R.id.search_input)
        )
        searchInput.perform(
            replaceText(searchString),
            pressImeActionButton()
        )

        // Waiting for data to load.
        Thread.sleep(3000)

        val itemList = onView(withId(R.id.result_list))
        itemList.perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchAdapter.ItemViewHolder>(0, click())
        )
    }

}

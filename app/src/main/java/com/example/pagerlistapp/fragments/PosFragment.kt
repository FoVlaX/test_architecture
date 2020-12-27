package com.example.pagerlistapp.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pagerlistapp.R
import com.example.pagerlistapp.adapters.LoadAdapter
import com.example.pagerlistapp.adapters.ImageAdapter
import com.example.pagerlistapp.amodels.Value
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.repository.State
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import javax.inject.Inject

class PosFragment : Fragment(){

    lateinit var recyclerView: RecyclerView
    companion object {
        val COLUMN_COUNT = 2
    }

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    private val viewModel: MainActivityViewModel by activityViewModels {
        viewModelsFactory
    }

    private lateinit var refreshSwipeLayout: SwipeRefreshLayout


    @Inject
    lateinit var imageAdapter: ImageAdapter

    @Inject
    lateinit var loadAdapter: LoadAdapter

    /**
     * Создаем новую конфигурацию для @ConcatAdapter и задаем параметру isolateViewTypes значение false,
     * для того чтобы getItemViewType(global_position) возвращала значение функции адаптера getItemViewType
     * к которому относится элемент на позиции global_position
     */
    private val builder = ConcatAdapter.Config.Builder()
    private val config = builder.setIsolateViewTypes(false).build()

    /**
     * Создаем ConcatAdapter и передадим в конструктор созданную конфигурацию и два адаптера
     * Основные элементы будет регулировать адаптер возвращаемой функцией getAdapter(),
     * а сразу после них будет элемент с прогресс баром
     * который будет регулировать LoadAdapter
     * это надо для того чтобы определить тип элемента в зависимости от того к какому адаптеру он относится
     * в этом случае тип не будет жеско привязан к позиции, а будет
     * зависеть от адаптера к которому отнситься элемент на запрашиваемой глобальной позиции,
     * В каждом адаптере перегружена функция getItemViewType(position) которая и вернет нужный тип элемента
     */
    var concatAdapter:ConcatAdapter? = null

    lateinit var textInput: EditText
    lateinit var imageView: ImageView
    lateinit var status: LiveData<State>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    val observer =
        Observer<PagedList<Value>> { images ->
            imageAdapter.submitList(images)
            refreshSwipeLayout.isRefreshing = false
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view)

        App.instance?.applicationComponent?.viewModelComponent()
            ?.with(this)
            ?.with(savedInstanceState)
            ?.build()
            ?.inject(this)
        concatAdapter = ConcatAdapter(config, imageAdapter, loadAdapter)

        recyclerView = view.findViewById(R.id.recycler_view)

        refreshSwipeLayout = view.findViewById(R.id.swipe_refresh)

        textInput = activity?.findViewById(R.id.text_input)!!

        imageView = activity?.findViewById(R.id.search_button)!!

        recyclerView.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(view.context, COLUMN_COUNT)

        /**
         * Для того чтобы в зависимости от типа элемента, он занимал разное количество колонок
         * передадим новый объект GridLayoutManager.SpanSizeLookup в метод setSpanSizeLookup и реализуюем
         * метод getSpanSize
         * */

        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                var countSpan = 1
                /**
                 * Если элемент это прогресс бар он будет занимать все колонки в GridLayout
                 * т.е. расплогаться так как надо
                 */
                if (concatAdapter?.getItemViewType(position) == R.layout.load_bar) {
                    countSpan = COLUMN_COUNT
                }
                return countSpan
            }
        }

        // specify an adapter (see also next example)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = concatAdapter

        viewModel.imageData.observe(requireActivity(), observer)

        refreshSwipeLayout.setOnRefreshListener {
            viewModel.refreshImages()
        }

        textInput.setText("Cat")

        status = viewModel.status

        status.observe(viewLifecycleOwner){

            if (it is State.Loaded){
                loadAdapter.hideLoadBar(viewModel.imageData.value?.size?:0 == 0 )
            }else{
                loadAdapter.visibleLoadBar()
            }

        }

        imageView.setOnClickListener {
            recyclerView.scrollToPosition(0)
            viewModel.imageData.removeObserver(observer)
            viewModel.setQuery(textInput.text.toString())
            viewModel.imageData.observe(requireActivity(), observer)
        }

    }

}
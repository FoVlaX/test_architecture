package com.example.pagerlistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pagerlistapp.R
import com.example.pagerlistapp.adapters.*
import com.example.pagerlistapp.amodels.UIRockAndMortyModel
import com.example.pagerlistapp.application.App
import com.example.pagerlistapp.decorations.RecyclerViewMargin
import com.example.pagerlistapp.repository.State
import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import javax.inject.Inject

class RickAndMortyFragment : BaseFragment() {


    companion object {
        val COLUMN_COUNT = 1
    }

    private val viewModel: MainActivityViewModel by activityViewModels {
        viewModelsFactory
    }

    lateinit var refreshSwipeLayout: SwipeRefreshLayout

    private val rickAndMortyAdapter: RickAndMortyAdapter by createAdapters{
        adaptersFactory
    }

    private val loadAdapter: LoadAdapter by createAdapters{
        adaptersFactory
    }
    lateinit var textInput: EditText
    lateinit var imageView: ImageView
    lateinit var status: LiveData<State>
    lateinit var recyclerView: RecyclerView

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
    var concatAdapter: ConcatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    val observer =
        Observer<PagedList<UIRockAndMortyModel>> { images ->
            rickAndMortyAdapter.submitList(images)
            refreshSwipeLayout.isRefreshing = false
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view)
        concatAdapter = ConcatAdapter(config, rickAndMortyAdapter, loadAdapter)
        recyclerView = view.findViewById(R.id.recycler_view)
        refreshSwipeLayout = view.findViewById(R.id.swipe_refresh)
        textInput = activity?.findViewById(R.id.text_input)!!
        imageView = activity?.findViewById(R.id.search_button)!!
        textInput.visibility = View.GONE
        imageView.visibility = View.GONE
        activity?.findViewById<TextView>(R.id.title_app_bar)?.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(RecyclerViewMargin(10,COLUMN_COUNT))
        val gridLayoutManager = GridLayoutManager(view.context, COLUMN_COUNT)

        /**
         * Для того чтобы в зависимости от типа элемента, он занимал разное количество колонок
         * передадим новый объект GridLayoutManager.SpanSizeLookup в метод setSpanSizeLookup и реализуюем
         * метод getSpanSize
         * */

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
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

        viewModel.rockAndMortyData.observe(requireActivity(), observer)

        rickAndMortyAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int,itemCount: Int) {
                if(positionStart == 0) {
                    (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(positionStart,0)
                }
            }
        })

        refreshSwipeLayout.setOnRefreshListener {
            viewModel.refreshCharacters()
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

    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.rockAndMortyData.hasActiveObservers()){
           viewModel.rockAndMortyData.observe(viewLifecycleOwner, observer)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.rockAndMortyData.removeObserver(observer)
    }

}
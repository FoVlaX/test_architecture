package com.example.pagerlistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pagerlistapp.R
import com.example.pagerlistapp.adapters.LoadAdapter
import com.example.pagerlistapp.adapters.WorksAdapter
import com.example.pagerlistapp.application.App
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
    private val worksAdapter: WorksAdapter = WorksAdapter()
    private val loadAdapter = LoadAdapter()

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
    val concatAdapter = ConcatAdapter(config, worksAdapter, loadAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        App.instance?.applicationComponent?.viewModelComponent()
            ?.with(this)
            ?.with(savedInstanceState)
            ?.build()
            ?.inject(this)

        recyclerView = view.findViewById(R.id.recycler_view)
        refreshSwipeLayout = view.findViewById(R.id.swipe_refresh)
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
                if (concatAdapter.getItemViewType(position) == R.layout.load_bar) {
                    countSpan = COLUMN_COUNT
                }
                return countSpan
            }
        }
        // specify an adapter (see also next example)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = concatAdapter

        viewModel.worksData.observe(requireActivity()) { works ->
            worksAdapter.submitList(works)
            refreshSwipeLayout.isRefreshing = false
        }

        refreshSwipeLayout.setOnRefreshListener {
            viewModel.refreshWorks()
        }

        viewModel.worksState.observe(viewLifecycleOwner){
            activity?.title = it.description
        }

    }

}
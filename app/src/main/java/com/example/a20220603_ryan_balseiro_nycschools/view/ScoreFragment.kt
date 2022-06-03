package com.example.a20220603_ryan_balseiro_nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.a20220603_ryan_balseiro_nycschools.R
import com.example.a20220603_ryan_balseiro_nycschools.databinding.ShowScoreFragmentBinding
import com.example.a20220603_ryan_balseiro_nycschools.viewmodel.SchoolViewModel

class ScoreFragment: Fragment() {

    companion object {
        const val SCORE_KEY = "SCORE_KEY"
        fun newInstance(dbn: String): ScoreFragment {
            val fragment = ScoreFragment()
            val bundle = Bundle()
            bundle.putString(SCORE_KEY, dbn)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: ShowScoreFragmentBinding? = null
    private val binding : ShowScoreFragmentBinding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binds the views
        _binding = ShowScoreFragmentBinding.inflate(layoutInflater)
        //gets this school's SAT scores from the viewModel
        viewModel.getThisScore(arguments?.getString(SCORE_KEY)!!)
        configureObserver()

        //alternative back button to bring up the list of schools
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.
            popBackStack("SchoolFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return binding.root
    }

    //this function handles displaying the SAT scores
    private fun configureObserver() {
        viewModel.scoreData.observe(viewLifecycleOwner) { score ->
            if (!score.isNullOrEmpty()) {
                binding.apply {
                    scoreSchoolName.text = score[0].school_name
                    scoreMath.text = score[0].sat_math_avg_score
                    scoreReading.text = score[0].sat_critical_reading_avg_score
                    scoreWriting.text = score[0].sat_writing_avg_score

                    mathContainer.visibility = View.VISIBLE
                    readingContainer.visibility = View.VISIBLE
                    writingContainer.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    scoreSchoolName.text = resources.getString(R.string.score_error)
                    mathContainer.visibility = View.INVISIBLE
                    readingContainer.visibility = View.INVISIBLE
                    writingContainer.visibility = View.INVISIBLE
                }

            }
        }
    }

    //sets the binding to null when so it can be garbage collected
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
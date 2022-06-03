package com.example.a20220603_ryan_balseiro_nycschools.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220603_ryan_balseiro_nycschools.databinding.SchoolListItemBinding
import com.example.a20220603_ryan_balseiro_nycschools.model.School
//this class dictates how the recycler view will be populated
class SchoolAdapter(
    private val schools: List<School>,
    private val showDetails: (String) -> Unit
): RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    inner class SchoolViewHolder(private val binding: SchoolListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(school: School) {
                binding.apply {
                    //sets data to the appropriate views
                    listSchoolName.text = school.school_name
                    listSchoolWebsite.text = school.website
                    //sets a click listener to the show details button that calls the function
                    //that displays the ScoreFragment and passes it the item's dbn.
                    listButtonScore.setOnClickListener {
                        showDetails(school.dbn)
                    }
                    //alternative click option. click listener set to the item card as well.
                    //does the same thing as the details button.
                    cvItem.setOnClickListener {
                        showDetails(school.dbn)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            SchoolListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    override fun getItemCount(): Int {
        return schools.size
    }
}
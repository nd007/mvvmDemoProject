package com.example.mvvmdemoproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmdemoproject.Util.NetworkResult
import com.example.mvvmdemoproject.ViewModel.AuthViewModel
import com.example.mvvmdemoproject.databinding.FragmentRegisterBinding
import com.example.mvvmdemoproject.models.UserRequest
import com.example.mvvmdemoproject.network.DemoApi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var btn: Button
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!



//    private val authViewModel by viewModels<AuthViewModel>()
//    val authViewModel: AuthViewModel by viewModels()

    private val authViewModel by activityViewModels<AuthViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
//        val v = inflater.inflate(R.layout.fragment_register, container, false)
//        btn = v.findViewById(R.id.demoBAckButton)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }

        return binding.root


    }

    private fun signup() {
        val request= UserRequest(
            "neerajdimri",
            "test123",
            "nd"
        )
        Log.e("## REGISTER",request.toString())
        authViewModel.signup(request)

        Log.e("## authViewModel",authViewModel.toString())

        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("## userResponseLiveData",authViewModel.userResponseLiveData.hasActiveObservers().toString())
            binding.progressBar.visibility=View.GONE

            when(it){
                is NetworkResult.Sucess->{
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),"ERROR",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }
            }
        })

    }


    private fun getUserDetails(){

        authViewModel.getUserDetails()
        authViewModel.getUserDetailsLiveData.observe(viewLifecycleOwner, Observer {

            binding.progressBar.visibility=View.GONE

            when(it){
                is NetworkResult.Sucess->{
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is NetworkResult.Error->{
                    Toast.makeText(requireContext(),"ERROR",Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    binding.progressBar.visibility=View.VISIBLE
                }
            }


        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
//            signup()
            getUserDetails()
        }
    }

}
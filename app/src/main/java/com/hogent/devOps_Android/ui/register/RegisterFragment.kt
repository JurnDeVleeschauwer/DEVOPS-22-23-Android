package com.hogent.devOps_Android.ui.register

import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    /*private lateinit var viewModel : RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = DatabaseImp.getInstance(application).customerDao
        val repository = RegisterRepository(dao)
        val factory = RegisterFactoryModel(repository, application)
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this
        binding.registerViewModel = viewModel
        binding.root.closeKeyboardOnTouch()

        Log.d("registercreated", "register fragment created")
        viewModel.navigateHome.observe(viewLifecycleOwner, Observer{
            if(it){
                NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                viewModel.navigated()
            }
        })

        val loginFrag = LoginFragment()
        val bundle = Bundle()
        bundle.putString("message", "Log in met je account.")
        loginFrag.arguments = bundle

        viewModel.navToLogin.observe(viewLifecycleOwner, Observer {
            if(it){
                NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                viewModel.navigated()
            }
        })



        return binding.root
    }*/
}

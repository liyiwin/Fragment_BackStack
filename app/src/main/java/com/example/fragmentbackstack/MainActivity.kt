package com.example.fragmentbackstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,FragmentManager.OnBackStackChangedListener{

    lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setData ()

        setActions()

    }

    fun setData (){

        manager = supportFragmentManager

    }

    fun setActions(){

        button_add_A.setOnClickListener {addA()}

        button_remove_A.setOnClickListener { removeA() }

        button_detach_A.setOnClickListener { detachA() }

        button_attach_A.setOnClickListener { attachA() }

        button_add_B.setOnClickListener{addB()}

        button_remove_B.setOnClickListener { removeB() }

        button_pop_add_B.setOnClickListener { popaddB() }

        button_replace_with_A.setOnClickListener { replacewithA() }

        button_replace_with_B.setOnClickListener { replacewithB() }

        button_back.setOnClickListener { back() }

        manager.addOnBackStackChangedListener(this)

    }

    private fun addA(){

        val a = FragmentA()

        val transaction = manager.beginTransaction()

        transaction.add(R.id.group,a,"A")

        transaction.addToBackStack("addA")

        transaction.commit()


    }

    private fun removeA(){

        val a = manager.findFragmentByTag("A")

        val transaction = manager.beginTransaction()

        if(a != null){

            transaction.remove(a)

            transaction.addToBackStack("removeA")

            transaction.commit()


        }

        else{

            Toast.makeText(this,"The fragment A was not added before ",Toast.LENGTH_LONG).show()


        }


    }

    private fun detachA(){

        val a = manager.findFragmentByTag("A")

        val transaction = manager.beginTransaction()

        if(a != null){

            transaction.detach(a)

            transaction.addToBackStack("detachA")

            transaction.commit()


        }


    }

    private fun attachA(){

        val a = manager.findFragmentByTag("A")

        val transaction = manager.beginTransaction()

        if(a != null){

            transaction.attach(a)

            transaction.addToBackStack("attachA")

            transaction.commit()

        }

    }


    private fun replacewithA(){

        val a = FragmentA()

        val transaction = manager.beginTransaction()

        transaction.replace(R.id.group,a,"A")

        transaction.addToBackStack("replacewithA")

        transaction.commit()


    }


    private fun addB(){

        val b = FragmentB()

        val transaction = manager.beginTransaction()

        transaction.add(R.id.group,b,"B")

        transaction.addToBackStack("addB")

        transaction.commit()

    }

    private fun removeB(){

        val b = manager.findFragmentByTag("B")

        val transaction = manager.beginTransaction()

        if(b!= null){

            transaction.remove(b)

            transaction.addToBackStack("removeB")

            transaction.commit()

        }

        else{
            Toast.makeText(this,"The fragment B was not added before ",Toast.LENGTH_LONG).show()

        }



    }

    private fun replacewithB(){

        val b = FragmentB()

        val transaction = manager.beginTransaction()

        transaction.replace(R.id.group,b,"B")

        transaction.addToBackStack("replacewithB")

        transaction.commit()

    }

    private fun popaddB(){

        manager.popBackStack("addB",0)


    }


    private fun back(){

        manager.popBackStack()

    }

    override fun onBackStackChanged() {
        messege.text = "${messege.text}"+"\n"

        messege.text = "${messege.text}"+ "The current status of Back Stack"

        val count =  manager.backStackEntryCount

        for (i in 0 downTo count-1){

            val entry = manager.getBackStackEntryAt(i)
            messege.text = "${messege.text}" + "" + entry.name + "\n"
        }

        messege.text = "${messege.text}"+"\n"

    }


}
